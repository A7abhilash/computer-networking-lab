import java.util.Scanner;

public class LeakyBucket{
        public static void main(String[] args){
                Scanner sc = new Scanner(System.in);

                int bucket_size, input_rate, output_rate, time_steps, current_size=0;

                System.out.print("Enter bucket size: ");
                bucket_size = sc.nextInt();

                System.out.print("Enter input rate: ");
                input_rate = sc.nextInt();

                System.out.print("Enter output rate: ");
                output_rate = sc.nextInt();

                System.out.print("Enter time steps to simulate: ");
                time_steps = sc.nextInt();

                //simulation starts
                for(int i=0; i< time_steps; i++){
                        int space_left = bucket_size - current_size;
                        int loss = 0;

                        if(input_rate <= space_left){
                                current_size += input_rate;
                        }else{
                                loss = input_rate - space_left;
                                current_size = bucket_size;
                        }

                        current_size -= output_rate;

                                System.out.println("Time = " + i + "s | Bucket/Buffer status:" + current_size + "/" + bucket_size + "Packet loss = " + loss);
                }

                sc.close();
        }
}