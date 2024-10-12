package assignment3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class TFTPServer {
	public static final int TFTPPORT = 4970;
	public static final int BUFSIZE = 516;
	public static final String READDIR = "C:\\Users\\wicea\\OneDrive\\Dokument\\ComputerNetwork\\computernetwork-assignment3\\readWrite\\";
	public static final String WRITEDIR = "C:\\Users\\wicea\\OneDrive\\Dokument\\ComputerNetwork\\computernetwork-assignment3\\readWrite\\";
	// public static final String READDIR = "/home/username/read/"; // custom
	// address at your PC
	// public static final String WRITEDIR = "/home/username/write/"; // custom
	// address at your PC
	// OP codes
	public static final short OP_RRQ = 1; // Opcode Read Request
	public static final short OP_WRQ = 2; // Opcode Write Request
	public static final short OP_DAT = 3; // Opcode Data Packet
	public static final short OP_ACK = 4; // Opcode Acknowledgment
	public static final short OP_ERR = 5; // Opcode Error

	public static void main(String[] args) {
		if (args.length > 0) {
			System.err.printf("usage: java %s\n", TFTPServer.class.getCanonicalName());
			System.exit(1);
		}
		// Starting the server
		try {
			TFTPServer server = new TFTPServer();
			server.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	private void start() throws SocketException {
		byte[] buf = new byte[BUFSIZE];

		// Create socket
		DatagramSocket socket = new DatagramSocket(null);

		// Create local bind point
		SocketAddress localBindPoint = new InetSocketAddress(TFTPPORT);
		socket.bind(localBindPoint);

		System.out.printf("Listening at port %d for new requests\n", TFTPPORT);

		// Loop to handle client requests
		while (true) {

			final InetSocketAddress clientAddress = receiveFrom(socket, buf);

			// If clientAddress is null, an error occurred in receiveFrom()
			if (clientAddress == null)
				continue;

			final StringBuffer requestedFile = new StringBuffer();
			final int reqtype = ParseRQ(buf, requestedFile);

			new Thread() {
				public void run() {
					try {
						DatagramSocket sendSocket = new DatagramSocket(0);

						// Connect to client
						sendSocket.connect(clientAddress);

						System.out.printf("%s request for %s from %s using port %d\n",
								(reqtype == OP_RRQ) ? "Read" : "Write", requestedFile.toString(),
								clientAddress.getHostName(), clientAddress.getPort());

						// Read request
						if (reqtype == OP_RRQ) {
							requestedFile.insert(0, READDIR);
							HandleRQ(sendSocket, requestedFile.toString(), OP_RRQ);
						}
						// Write request
						else {
							requestedFile.insert(0, WRITEDIR);
							HandleRQ(sendSocket, requestedFile.toString(), OP_WRQ);
						}
						sendSocket.close();
					} catch (SocketException | FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}

	/**
	 * Reads the first block of data, i.e., the request for an action (read or
	 * write).
	 * 
	 * @param socket (socket to read from)
	 * @param buf    (where to store the read data)
	 * @return socketAddress (the socket address of the client)
	 */
	private InetSocketAddress receiveFrom(DatagramSocket socket, byte[] buf) {
		// Create datagram packet
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);

		// Receive packet
		try {
			socket.receive(datagramPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Get client address and port from the packet
		InetSocketAddress socketAddress = new InetSocketAddress(datagramPacket.getAddress(), datagramPacket.getPort());

		return socketAddress;
	}

	/**
	 * Parses the request in buf to retrieve the type of request and requestedFile
	 * 
	 * @param buf           (received request)
	 * @param requestedFile (name of file to read/write)
	 * @return opcode (request type: RRQ or WRQ)
	 */
	private short ParseRQ(byte[] buf, StringBuffer requestedFile) {
		// RRQ and WRQ packets (opcodes 1 and 2 respecitvely) have the format (short
		// Opcode, String Filename, byte 0, String Mode, byte 0)
		ByteBuffer wrap = ByteBuffer.wrap(buf);
		short opcode = wrap.getShort();
		int currBufValue = 2; // start at 1 to skip the opcodes.

		// System.out.println(buf[currBufValue]);
		while (currBufValue < buf.length && buf[currBufValue] != 0) {
			char letter = (char) buf[currBufValue];
			// System.out.println(String.valueOf(letter));
			requestedFile.append(letter);
			currBufValue++;
		}
		// System.out.println(opcode);
		return opcode;
	}

	/**
	 * Handles RRQ and WRQ requests
	 * 
	 * @param sendSocket    (socket used to send/receive packets)
	 * @param requestedFile (name of file to read/write)
	 * @param opcode        (RRQ or WRQ)
	 * @throws FileNotFoundException
	 */
	private void HandleRQ(DatagramSocket sendSocket, String requestedFile, int opcode) throws FileNotFoundException {
		if (opcode == OP_RRQ) {
			File file = new File(requestedFile);
			FileInputStream fileInputStream = new FileInputStream(file);

			short blockNumber = 1;
			byte[] data = new byte[BUFSIZE - 4];

			// Read the file, send the data in blocks of 512 bytes
			int n = -1;
			do {
				try {
					n = fileInputStream.read(data);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (n == -1) {
					break;
				}
				send_DATA_receive_ACK(sendSocket, blockNumber, data, n);
				blockNumber++;
			} while (n == BUFSIZE - 4);

			// Close the file
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (opcode == OP_WRQ) {
			File file = new File(requestedFile);
			FileOutputStream fileOutputStream = new FileOutputStream(file);

			byte[] data = new byte[BUFSIZE - 4];
			short blockNumber = 0;
			DatagramPacket ackPacket = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);

			do {
				receive_DATA_send_ACK(sendSocket, blockNumber, fileOutputStream);
				if (ackPacket.getLength() == 4) {
					break;
				}
			} while (ackPacket.getLength() == BUFSIZE);

			// Close the file
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			return;
		}
	}

	/**
	 * To be implemented
	 */
	private boolean send_DATA_receive_ACK(DatagramSocket sendSocket, short block, byte[] data, int len) {
		// System.out.println("BLOCK NUMBER = " + block);
		// Send a data packet.
		ByteBuffer byteBuffer = ByteBuffer.allocate(BUFSIZE);
		byteBuffer.putShort(OP_DAT);
		byteBuffer.putShort(block);
		for (int i = 0; i < len; i++) {
			byteBuffer.put(data[i]);
		}
		DatagramPacket sendPacket = new DatagramPacket(byteBuffer.array(), byteBuffer.position());

		try {
			sendSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Receive ack packet.
		byte[] buf = new byte[4];
		DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
		try {
			sendSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteBuffer testBuffer = ByteBuffer.wrap(receivePacket.getData());
		short opcode = testBuffer.getShort();
		// System.out.println((int) opcode);

		return true;
	}

	private boolean receive_DATA_send_ACK(DatagramSocket sendSocket, short block, FileOutputStream fileOutputStream) {
		// Creates an Ack packet and sending it.
		DatagramPacket ackPacket = genAckPacket(block);

		try {
			sendSocket.send(ackPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Receive a data packet.
		byte[] buf = new byte[BUFSIZE];
		DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);

		try {
			sendSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ByteBuffer byteBuffer = ByteBuffer.wrap(receivePacket.getData());
		byte[] data = receivePacket.getData();
		try {
			fileOutputStream.write(data , 4, receivePacket.getLength()-4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byteBuffer.getShort();
		short blockNumber = byteBuffer.getShort();

		// Creates an Ack packet and sending it.
		ackPacket = genAckPacket(blockNumber);

		try {
			sendSocket.send(ackPacket);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		// DatagramPacket datagramPacket = new DatagramPacket(null, opcode)

		// FileOutputStream outpuy = new FileOutputStream(file);
		// outpuy.write(null, opcode, opcode);

		return true;
	}

	public DatagramPacket genAckPacket(short block) {
		ByteBuffer ackBuffer = ByteBuffer.allocate(4);
		ackBuffer.putShort(OP_ACK);
		ackBuffer.putShort(block);

		DatagramPacket ackPacket = new DatagramPacket(ackBuffer.array(), 4);
		return ackPacket;
	}
}
