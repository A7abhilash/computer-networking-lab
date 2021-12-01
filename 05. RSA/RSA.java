import java.util.Scanner;
import java.math.BigInteger;

public class RSA{
        public static void main(String[] args){
                Scanner sc = new Scanner(System.in);

                System.out.println("Enter the first prime: ");
                int p = sc.nextInt();

                System.out.println("Enter the second prime: ");
                int q = sc.nextInt();

                int n = p*q;
                System.out.println("The value of n: " + n);

                int z = (p-1)*(q-1);
                System.out.println("The value of z: " + z);

                int e, d=0;
                for(e=2; e<z; e++){
                        if(gcd(e, z) == 1){
                                break;
                        }
                }

                System.out.println("The public key is (e, n) = (" + e + ", " + n + ")");

                for(int i=0; i<=9; i++){
                        int x = 1 + (i*z);
                        if(x%e == 0){
                                d=x/e;
                                break;
                        }
                }

                System.out.println("The private key is (d, n) = (" + d + ", " + n + ")");

                System.out.println("Enter the no. to be encrypted: ");
                long msg = sc.nextLong();

                BigInteger bi_msg = BigInteger.valueOf(msg);
                BigInteger cipher = bi_msg.pow(e).mod(BigInteger.valueOf(n));

                System.out.println("The cipher text = " + cipher);

                BigInteger decrypt = cipher.pow(d).mod(BigInteger.valueOf(n));

                System.out.println("The decrypted plain text = " + decrypt);

                sc.close();
        }

        public static int gcd(int n, int m){
                if(n==0) return m;

                return gcd(m%n, n);
        }
}