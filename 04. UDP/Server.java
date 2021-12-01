import java.net.*;
import java.io.*;

public class Server{
        public static void main(String[] args){
                if(args.length != 1){
                        System.out.println("Command is: java Server <port>");
                        return;
                }

                try{
                        DatagramSocket datagramSocket = new DatagramSocket(Integer.parseInt(args[0]));
                        DatagramPacket datagramPacket = null;
                        byte[] buffer = new byte[65535];

                        System.out.println("Waiting for client msg...");

                        while(true){
                                datagramPacket = new DatagramPacket(buffer, buffer.length);
                                datagramSocket.receive(datagramPacket);
                                String recv_msg = new String(buffer);
                                System.out.println("Client - " + recv_msg);
                                buffer = new byte[65535];

                                if(recv_msg.equals("exit\n")){
                                        break;
                                }
                        }
                } catch(IOException e){
                        System.err.println(e);
                }
        }
}