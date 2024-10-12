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
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

public class TFTPServer {
	public static final int TFTPPORT = 4970;
	public static final int BUFSIZE = 516;
	public static final String READDIR = "C:\\Users\\wicea\\OneDrive\\Dokument\\ComputerNetwork\\computernetwork-assignment3\\assignment3\\readWrite\\";
	public static final String WRITEDIR = "C:\\Users\\wicea\\OneDrive\\Dokument\\ComputerNetwork\\computernetwork-assignment3\\assignment3\\readWrite\\";
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
			System.out.println(reqtype);

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
						else if (reqtype == OP_WRQ) {
							requestedFile.insert(0, WRITEDIR);
							HandleRQ(sendSocket, requestedFile.toString(), OP_WRQ);
						} else {
							DatagramPacket datagramPacket2 = genErrorPacket((short) 4, "Illegal TFTP operation.");
							try {
								sendSocket.send(datagramPacket2);
							} catch (IOException e) {
								e.printStackTrace();
							}
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
		File file = new File(requestedFile);
		if (file.exists()) {
			if (opcode == OP_RRQ) {
				FileInputStream fileInputStream = new FileInputStream(file);

				short blockNumber = 1;
				send_DATA_receive_ACK(sendSocket, blockNumber, fileInputStream);
				blockNumber++;
			} else if (opcode == OP_WRQ) {

				FileOutputStream fileOutputStream = new FileOutputStream(file);

				short blockNumber = 0;
				receive_DATA_send_ACK(sendSocket, blockNumber, fileOutputStream);
			}
		} else {
			DatagramPacket datagramPacket = genErrorPacket((short) 1, "File not found.");
			try {
				sendSocket.send(datagramPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * To be implemented
	 */
	private boolean send_DATA_receive_ACK(DatagramSocket sendSocket, short block, FileInputStream fileInputStream) {
		while (true) {
			byte[] buf = new byte[512];
			int n = -1;
			try {
				n = fileInputStream.read(buf);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (n == -1) {
				return false;
			}

			ByteBuffer byteBuffer = ByteBuffer.allocate(n + 4);
			byteBuffer.putShort(OP_DAT);
			byteBuffer.putShort(block);
			byteBuffer.put(buf, 0, n);

			short ackOp = -1;
			short ackBlockNumber = -1;

			do {
				DatagramPacket sendPacket = new DatagramPacket(byteBuffer.array(), byteBuffer.limit());

				try {
					sendSocket.send(sendPacket);
					System.out.println("Sending data packet...");
				} catch (IOException e) {
					e.printStackTrace();
				}

				byte[] ackBuf = new byte[4];
				DatagramPacket ackPacket = new DatagramPacket(ackBuf, ackBuf.length);
				try {
					sendSocket.setSoTimeout(5000);
					sendSocket.receive(ackPacket);
					System.out.println("Received ack packet...");
				} catch (IOException e) {
					System.out.println("Error, times out getting packet...");
					continue;
				}
				ByteBuffer ackByteBuffer = ByteBuffer.wrap(ackPacket.getData(), 0, ackPacket.getLength());
				ackOp = ackByteBuffer.getShort();
				ackBlockNumber = ackByteBuffer.getShort();

			} while (ackOp != OP_ACK || ackBlockNumber != block);

			System.out.println("Acknowledgment received for block " + block);
			if (n < 512) {
				break;
			}

			block++;
		}
		return true;
	}

	private boolean receive_DATA_send_ACK(DatagramSocket sendSocket, short block, FileOutputStream fileOutputStream) {
		// Creates an Ack packet and sending it.
		DatagramPacket ackPacket = genAckPacket(block);

		try {
			sendSocket.send(ackPacket);
			System.out.println("Sending ack packet...");
		} catch (IOException e) {
			System.err.println("Error sending ACK packet...");
			return false;
		}

		while (true) {
			// Receive a data packet.
			byte[] buf = new byte[BUFSIZE];
			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);

			try {
				sendSocket.setSoTimeout(5000);
				sendSocket.receive(receivePacket);
				System.out.println("Received data packet...");
			} catch (SocketTimeoutException e) {
				System.err.println("Timed out waiting for data packet.");
				continue;
			} catch (IOException e) {
				System.err.println("Error receiving data packet: " + e.getMessage());
				return false;
			}

			ByteBuffer byteBuffer = ByteBuffer.wrap(receivePacket.getData());
			byte[] data = receivePacket.getData();
			short dataOp = byteBuffer.getShort();
			short blockNumber = byteBuffer.getShort();

			if (dataOp == OP_DAT) {
				try {
					fileOutputStream.write(data, 4, receivePacket.getLength() - 4);
				} catch (IOException e) {
					System.err.println("Error writing data to file: " + e.getMessage());
					return false;
				}

				// Creates an Ack packet and sending it.
				ackPacket = genAckPacket(blockNumber);

				try {
					sendSocket.send(ackPacket);
					System.out.println("Sending data packet...");
				} catch (IOException e) {
					System.err.println("Error sending ach packet...");
					return false;
				}

				// Check if this was the last data packet.
				if (receivePacket.getLength() < BUFSIZE) {
					break;
				}
			} else {
				System.err.println("Received unexpected opcode: " + dataOp);
				return false;
			}
		}
		return true;
	}

	public DatagramPacket genAckPacket(short block) {
		ByteBuffer ackBuffer = ByteBuffer.allocate(4);
		ackBuffer.putShort(OP_ACK);
		ackBuffer.putShort(block);

		DatagramPacket ackPacket = new DatagramPacket(ackBuffer.array(), 4);
		return ackPacket;
	}

	public DatagramPacket genErrorPacket(short block, String errorMsg) {
		ByteBuffer errBuffer = ByteBuffer.allocate(BUFSIZE);
		errBuffer.putShort(OP_ERR);
		errBuffer.putShort(block);
		errBuffer.put(errorMsg.getBytes());
		errBuffer.putShort((short) 0);

		DatagramPacket errPacket = new DatagramPacket(errBuffer.array(), errBuffer.array().length);
		return errPacket;
	}
}
