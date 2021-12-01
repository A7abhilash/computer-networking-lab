import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Client{
        public static void main(String[] args){
                if(args.length != 1){
                        System.out.println("Command is: java Client <port>");
                        return;
                }

                try{
                        Scanner sc = new Scanner(System.in);
                        DatagramSocket ds = new DatagramSocket();
                        InetAddress ip = InetAddress.getLocalHost();

                        while(true){
                                System.out.print("Enter the msg to send to the client: ");
                                String msg = sc.nextLine();
                                byte[] buffer = msg.getBytes();
                                DatagramPacket packet =new DatagramPacket(buffer, buffer.length, ip, Integer.parseInt(args[0]));
                                ds.send(packet);

                                if(msg.equals("exit")){
                                        break;
                                }
                        }

                        sc.close();
                } catch(IOException e){
                        System.err.println(e);
                }
        }
}