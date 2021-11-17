import java.util.Scanner;

public class CRC{
        public static void main(String[] args){
                Scanner sc = new Scanner(System.in);

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

                System.out.println("CRC of the msg in hexadecimal: " + Integer.toHexString(crc));
        }
}