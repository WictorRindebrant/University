import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 0;
        String firstArg = "";
        String secondArg = "";
        File file = null;

        try {
            firstArg = args[0];
            secondArg = args[1];
            file = new File(secondArg);
            if (file.isAbsolute()) {
                System.out.println("This is an Absolute path, the program wants an relative path.");
                System.exit(0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Two arguments required (port, path)");
            System.exit(1);
        }

        // if first argument (port) cant be converted to an Integer, then program exits.
        try {
            port = Integer.parseInt(firstArg);
        } catch (NumberFormatException e) {
            System.out.println("Port argument has to be Integer");
            System.exit(1);
        }

        // Check if the folder exists in the current working directory by checking the
        // subfolders that should exist.
        if (file.isDirectory()) {
            file = new File(secondArg);
        } else {
            System.out.println("The folder '" + secondArg + "' does not exist in the current working directory.");
            System.exit(1);
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server source file exists!");
            System.out.println("Detailed path: " + file.getAbsolutePath());

            ArrayList<ArrayList<String>> nameTypeDir = pathToListFile(new ArrayList<>(), file, file);
            Collections.reverse(nameTypeDir);
            ArrayList<ArrayList<String>> nameDir = pathToListDir(new ArrayList<>(), file, file);

            while (true) {
                try (Socket client = serverSocket.accept()) {
                    System.out.println("\n-----Assigned a new client to a seperate thread------- ");

                    InputStreamReader isr = new InputStreamReader(client.getInputStream());
                    BufferedReader br = new BufferedReader(isr);

                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();
                    if (line != null) {
                        while (!line.isBlank()) {
                            sb.append(line + "\n");
                            line = br.readLine();
                        }
                    }

                    String firstLine = sb.toString().split("\n")[0];
                    String resource = firstLine.split(" ")[1];
                    if (resource.endsWith("/")) {
                        resource = resource.substring(0, resource.length() - 1);
                    }

                    System.out.println(sb.toString().split("\n")[1] + ", Method: " + firstLine.split(" ")[0]
                            + ", Path: " + firstLine.split(" ")[1] + ", Version: " + firstLine.split(" ")[2]);

                    OutputStream clientOutput = client.getOutputStream();

                    // First time is for only creating headers one time.
                    Boolean firstTime = true;
                    firstTime = fileHandler(nameTypeDir, resource, clientOutput, firstTime, secondArg);
                    if (firstTime) {
                        firstTime = dirHandler(nameDir, resource, clientOutput, firstTime, file);
                    }

                    // Handles response code 302.
                    System.out.println(resource);
                    if (resource.equals("/aa")) {
                        String redirectLocation = "127.0.0.1:" + port + "/a";
                        String response = "HTTP/1.1 302 Found\n";
                        response += "connection: close\n"; // add connection header
                        response += "date: " + new Date() + "\n"; // add current date header
                        response += "\n";
                        System.out.println(response);
                        response += "Location: " + redirectLocation + "\n\n";
                        clientOutput.write(response.getBytes());
                        clientOutput.flush();

                    }
                    // Handles response code 404.
                    else if (firstTime && !isXInListOfLists(nameTypeDir, resource)
                            && !isXInListOfLists(nameTypeDir, resource.toLowerCase())) {
                        FileInputStream txt = new FileInputStream("404.html");
                        String response = "HTTP/1.1 404 Not Found\n";
                        response += "connection: close\n"; // add connection header
                        response += "date: " + new Date() + "\n"; // add current date header
                        response += "\n";
                        System.out.println(response);
                        clientOutput.write((response.getBytes()));
                        clientOutput.write((txt.readAllBytes()));
                        clientOutput.flush();
                        System.out.println("Server request file does not exists!");
                        firstTime = false;
                        txt.close();
                    }
                    client.close();
                }
            }
        }
    }

    /**
     * Finds all files in all directories from the choosen path.
     *
     * @param listFile  is where all the files will be stored.
     * @param pathDir   the path to all the files and directories.
     * @param cleanPath a path that will never change from the starting path.
     * @return an arraylist with an arraylist of type string with the information
     *         about the file (Filename, Filetype and Filepath).
     */
    private static ArrayList<ArrayList<String>> pathToListFile(ArrayList<ArrayList<String>> listFile, File pathDir,
            File cleanPath) {
        File files[] = pathDir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                ArrayList<String> tempListFile = new ArrayList<>();
                tempListFile.add(
                        file.getAbsolutePath().substring(cleanPath.getAbsolutePath().length()).replaceAll("\\\\", "/"));
                tempListFile.add(file.getName().split("[.]")[1]);
                tempListFile.add(file.getAbsolutePath());
                listFile.add(tempListFile);
            } else {
                pathToListFile(listFile, file, cleanPath);
            }
        }
        return listFile;
    }

    // Finds all directories from the choosen path.
    /**
     * Finds all directories from the choosen path.
     *
     * @param listDir   is where all the directories will be stored.
     * @param pathDir   the path to all the directories.
     * @param cleanPath a path that will never change from the starting path.
     * @return an arraylist with an arraylist of type string with the information
     *         about the directory (Directoryname and Directorypath).
     */
    private static ArrayList<ArrayList<String>> pathToListDir(ArrayList<ArrayList<String>> listDir, File pathDir,
            File cleanPath) {
        File files[] = pathDir.listFiles();
        for (File file : files) {
            if (file.isFile()) {

            } else {
                ArrayList<String> tempListDir = new ArrayList<>();
                tempListDir.add(
                        file.getAbsolutePath().substring(cleanPath.getAbsolutePath().length()).replaceAll("\\\\", "/"));
                tempListDir.add(file.getAbsolutePath());
                listDir.add(tempListDir);
                pathToListDir(listDir, file, cleanPath);
            }
        }
        return listDir;
    }

    /**
     * Finds all Files from the choosen directory.
     *
     * @param listFile  is where all the files will be stored.
     * @param pathDir   the path to all the files and directories.
     * @param cleanPath a path that will never change from the starting path.
     * @return an arraylist with an arraylist of type string with the information
     *         about the file (Filename, Filetype and Filepath).
     */
    private static ArrayList<ArrayList<String>> filesInDir(ArrayList<ArrayList<String>> listFile, File pathDir,
            File cleanPath) {
        File files[] = pathDir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                ArrayList<String> tempListFile = new ArrayList<>();
                tempListFile.add(
                        file.getAbsolutePath().substring(cleanPath.getAbsolutePath().length()).replaceAll("\\\\", "/"));
                tempListFile.add(file.getName().split("[.]")[1]);
                tempListFile.add(file.getAbsolutePath());
                listFile.add(tempListFile);
            }
        }
        return listFile;
    }

    /**
     * Fuction to check if "String x" does excists in the ArrayList of ArraLists of
     * the type String called list.
     *
     * @param list the ArrayList of ArraLists of the type String.
     * @param x    Sting variable to check if it excists inside the variable "list".
     * @return returns true if it does excist and false if it does not excist.
     */
    private static boolean isXInListOfLists(ArrayList<ArrayList<String>> list, String x) {
        for (ArrayList<String> innerList : list) {
            if (innerList.get(0).equals(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Going trough the "nameTypeDir" Arraylist and checks it against the
     * "resource", if it's the same then it will make an suiteble webpage for that
     * file.
     *
     * @param nameTypeDir  an arraylist with an arraylist of type string with the
     *                     information about the file (Filename, Filetype and
     *                     Filepath).
     * @param resource     the last part of the url that the user enters to search
     *                     up different things on the server.
     * @param clientOutput used to write things on the website.
     * @return true if the file was not found as a file and false if it was found as
     *         a file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static boolean fileHandler(ArrayList<ArrayList<String>> nameTypeDir, String resource,
            OutputStream clientOutput, Boolean firstTime, String dir) throws FileNotFoundException, IOException {
        for (ArrayList<String> i : nameTypeDir) {
            if (resource.equals(i.get(0))) {
                if (i.get(1).equals("html") || i.get(1).equals("htm")) {
                    try (FileInputStream txt = new FileInputStream(i.get(2))) {
                        if (firstTime) {
                            responseStringWrite(clientOutput, txt, "200 OK", "text/html", (long) txt.available(),
                                    "close");
                            firstTime = false;
                        }
                        break;
                    }
                } else if (i.get(1).toLowerCase().equals("png") || i.get(1).toLowerCase().equals("jpeg")
                        || i.get(1).toLowerCase().equals("jpg")) {
                    try (FileInputStream image = new FileInputStream(i.get(2))) {
                        if (firstTime) {
                            File picture = new File(i.get(2));
                            long length = picture.length();
                            responseStringWrite(clientOutput, image, "200 OK", "image/png", length, "close");
                            firstTime = false;
                        }
                    }
                }
                // front page
            } else if (resource.equals("")) {
                try (FileInputStream txt = new FileInputStream(dir + "/index.html")) {
                    if (firstTime) {
                        responseStringWrite(clientOutput, txt, "200 OK", "text/html", (long) txt.available(),
                                "close");
                        firstTime = false;
                    }
                    break;
                }
            }

        }
        return firstTime;
    }

    /**
     * Going trough the "nameTypeDir" Arraylist and checks it against the string
     * "index", if it's the same then it will make an suiteble webpage for that
     * file.
     *
     * @param nameTypeDir  an arraylist with an arraylist of type string with the
     *                     information about the file (Filename, Filetype and
     *                     Filepath).
     * @param clientOutput used to write things on the website.
     * @return true if the one of the files was not found as an "index" file and
     *         false if it was found as an "index" file
     *         a file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static boolean fileHandlerFromDir(ArrayList<ArrayList<String>> nameTypeDir, OutputStream clientOutput,
            Boolean firstTime)
            throws FileNotFoundException, IOException {
        for (ArrayList<String> i : nameTypeDir) {
            System.out.println(i.get(0));
            if (i.get(1).equals("html") && i.get(0).contains("index.html")) {
                try (FileInputStream txt = new FileInputStream(i.get(2))) {
                    if (firstTime) {
                        responseStringWrite(clientOutput, txt, "200 OK", "text/html", (long) txt.available(),
                                "close");
                        System.out.println("Server request file exists!");
                        firstTime = false;
                    }
                }
            }
        }
        return firstTime;
    }

    /**
     * Going trough the "nameDir" Arraylist and checks it against the
     * "resource", if it's the same then it send all those files from that directory
     * in an ArrayList<String> to the fileHandlerFromDir fuction
     *
     * @param nameDir      an arrayList where all the directories are stored with
     *                     there name and paths.
     * @param resource     the last part of the url that the user enters to search
     *                     up different things on the server.
     * @param clientOutput used to write things on the website.
     * @param cleanPath    a path that will never change from the starting path.
     * @return true if an directory was'nt found and false if an directory was
     *         found.
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static boolean dirHandler(ArrayList<ArrayList<String>> nameDir, String resource, OutputStream clientOutput,
            Boolean firstTime, File cleanPath)
            throws FileNotFoundException, IOException {
        ArrayList<ArrayList<String>> filenameTypeDir = new ArrayList<>();
        for (ArrayList<String> dir : nameDir) {
            if (resource.equals(dir.get(0))) {
                if (firstTime) {
                    System.out.println("Server request file exists!");
                    System.out.println("Request item is a directory!");
                    ArrayList<ArrayList<String>> filesInTheDir = new ArrayList<>(
                            filesInDir(filenameTypeDir, new File(dir.get(1)), cleanPath));
                    fileHandlerFromDir(filesInTheDir, clientOutput, firstTime);
                    firstTime = false;
                    break;
                }
            }
        }
        return firstTime;
    }

    /**
     * A function that fills in all the response parameters to avoid duplicated code.
     * Then writes it to the web server and when it's done flushing it.
     *
     * @param clientOutput used to write things on the website.
     * @param txtORimage fileInputStream for either text or image.
     * @param StatusCode string with the status code.
     * @param contentType the content type of the file.
     * @param contentLength the content length of the file.
     * @param connection if the connection will close or not.
     * @throws IOException exception handeling.
     */
    private static void responseStringWrite(OutputStream clientOutput, FileInputStream txtORimage, String StatusCode,
            String contentType, Long contentLength, String connection) throws IOException {
        String response = "HTTP/1.1 " + StatusCode + "\n";
        response += "content-Type: " + contentType + "\n";
        response += "content-length: " + contentLength + "\n"; // add content-length header
        response += "connection: " + connection + "\n"; // add connection header
        response += "date: " + new Date() + "\n"; // add current date header
        response += "\n";
        System.out.println(response);
        clientOutput.write((response.getBytes()));
        clientOutput.write((txtORimage.readAllBytes()));
        clientOutput.flush();
    }

}
