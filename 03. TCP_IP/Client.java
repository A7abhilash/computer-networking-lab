import java.net.*;
import java.io.*;

public class Client{
        private Socket serverSocket = null;
        private DataInputStream in = null;
        private DataOutputStream out = null;

        public Client(String host, int port, String infilePath, String outfilePath){
                try{
                        serverSocket = new Socket(host, port);

                        in = new DataInputStream(serverSocket.getInputStream());
                        out = new DataOutputStream(serverSocket.getOutputStream());

                        File sendFile = new File(infilePath);
                        long fileLength = sendFile.length();

                        if(fileLength > Integer.MAX_VALUE){
                                throw new Exception("File length is too long!");
                        }

                        System.out.println("Sending file to source...");

                        FileInputStream fin = new FileInputStream(sendFile);

                        int count = 0;
                        byte[] buffer = new byte[4096];

                        while((count = fin.read(buffer)) > 0){
                                out.write(buffer, 0, count);
                                String send = new String(buffer);
                                System.out.println(send);
                        }

                        System.out.println("Listening for echo from server");

                        File recvFile = new File(outfilePath);
                        FileOutputStream fout = new FileOutputStream(recvFile);

                        long remainingFileLength = fileLength;

                        while((count = in.read(buffer)) > 0){
                                fout.write(buffer, 0, count);
                                String recv = new String(buffer);
                                System.out.print(recv);
                                remainingFileLength -= count;

                                if(remainingFileLength == 0) break;
                        }

                        System.out.println("Saved file in: " + outfilePath);
                        System.out.println("Closing socket & freezing resources...");
                        fin.close();
                        fout.close();
                        in.close();
                        out.close();
                        serverSocket.close();
                }catch(UnknownHostException e){
                        System.err.println("Error: " + e);
                }catch(IOException e){
                        System.err.println("Error: " + e);
                }catch(Exception e){
                        System.err.println("Error: " + e);
                }
        }

        public static void main(String[] args){
                try{
                        if(args.length == 0){
                                System.out.println("java Client <host> <port> <infile path> <outfile path>");
                                return;
                        }

                        if(args.length != 4){
                                throw new Exception("Invalid argument");
                        }

                        String host = args[0];
                        int port = Integer.parseInt(args[1]);
                        String infilePath = args[2];
                        String outfilePath = args[3];

                        Client c = new Client(host, port, infilePath, outfilePath);
                }catch(Exception e){
                        System.err.println("Error:" + e);
                }
        }
}