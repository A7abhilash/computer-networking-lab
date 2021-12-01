import java.util.Scanner;
import java.io.*;

public class CRC{
        public static void main(String[] args){
                Scanner sc = new Scanner(System.in);
                /*
                        System.out.println("Enter your message:");
                        String msg = sc.nextLine();
                        sc.close();

                        byte[] msg_bytes=msg.getBytes();
                        int crc=0xFFFF;
                        int polynomial=0x1021;

                        for(byte b:msg_bytes){
                                for(int i=0;i<8;i++){
                                        boolean bit = ((b>>(7-i))&1)==1;
                                        boolean flip = ((crc>>15)&1)==1;

                                        crc <<= 1;
                                        if(bit ^ flip){
                                                crc ^= polynomial;
                                        }
                                }
                        }

                        crc &= 0xFFFF;

                        System.out.println("CRC of the msg in hexa decimal: " + Integer.toHexString(crc));
                */

                System.out.print("Enter the message bits: ");
                String message = sc.nextLine();

                String generator = "10001000000100001";
                System.out.println("Using CRC-CCITT generator : " + generator);

                int data[] = new int[message.length() + generator.length() - 1];
                int divisor[] = new int[generator.length()];


                for (int i = 0; i < message.length(); i++){
                        data[i] = Integer.parseInt(message.charAt(i) + "");
                }

                for (int i = 0; i < generator.length(); i++){
                        divisor[i] = Integer.parseInt(generator.charAt(i) + "");
                }

                for (int i = 0; i < message.length(); i++) {
                        if(data[i] == 1) {
                                for (int j = 0; j < divisor.length; j++){
                                        data[i + j] ^= divisor[j];
                                }
                        }
                }

                System.out.print("The checksum code is: ");
                for(int i = 0; i < message.length(); i++)
                        data[i] = Integer.parseInt(message.charAt(i) + "");

                for(int i=0; i<data.length; i++){
                        System.out.print(data[i]);
                }
                System.out.println();

                System.out.print("Enter the checksum code: ");
                message = sc.nextLine();

                System.out.println("Using CRC-CCITT generator : " + generator);

                data = new int[message.length() + generator.length()-1];
                divisor = new int[generator.length()];

                for(int i=0; i<message.length(); i++){
                        data[i] = Integer.parseInt(message.charAt(i) + "");
                }

                for(int i=0; i<generator.length(); i++){
                        divisor[i] = Integer.parseInt(generator.charAt(i) + "");
                }

                for(int i=0; i<message.length(); i++){
                        if(data[i] == 1){
                                for(int j=0; j<divisor.length; j++){
                                        data[i+j] ^= divisor[j];
                                }
                        }
                }

                boolean valid = true;
                for(int i=0; i<data.length; i++){
                        if(data[i] == 1){
                                valid=false;
                                break;
                        }
                }

                if(valid){
                        System.out.println("Datastream is valid!");
                }else{
                        System.out.println("Datastream is invalid! CRC error occurred");
                }
        }
}