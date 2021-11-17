import java.util.Scanner;

class Node{
        public long parent;
        public long distance;

        Node(long distance, long parent){
                this.parent=parent;
                this.distance=distance;
        }
}

public class DVR{
        public static void main(String[] args){
                Scanner sc = new Scanner(System.in);

                System.out.print("Enter the no. of nodes: ");
                long n = sc.nextLong();

                long[][] distance = new long[(int)n][(int)n];
                Node[][] routing_tables = new Node[(int)n][(int)n];

                System.out.println("Enter the distance matrix (-1 for infinity): ");
                for(int i=0; i<n; i++){
                        for(int j=0; j<n; j++){
                                routing_tables[i][j] = new Node(Integer.MAX_VALUE, -1);
                                distance[i][j] = sc.nextLong();
                                if(distance[i][j] == -1){
                                        distance[i][j] = Integer.MAX_VALUE;
                                }
                        }
                }

                for(int i=0; i<n; i++){
                        routing_tables[i][i].distance = 0;
                        routing_tables[i][i].parent = i;
                }


                for(int k=0; k<n; k++){
                        for(int u=0; u<n; u++){
                                for(int v=0; v<n; v++){
                                        for(int i=0; i<n; i++){
                                                if( distance[u][v] != Integer.MAX_VALUE &&
                                                        routing_tables[i][v].distance != Integer.MAX_VALUE &&
                                                        routing_tables[i][u].distance >
                                                        routing_tables[i][v].distance + distance[v][u]
                                                ){
                                                        routing_tables[i][u].distance = routing_tables[i][v].distance + distance[v][u];
                                                        routing_tables[i][u].parent = v;
                                                }
                                        }
                                }
                        }
                }

                for(int i=0; i<n; i++){
                        System.out.println("Routing table for router "+(i+1)+":");
                        System.out.println("Dest\tDist\tParent");
                        System.out.println("--------------------------------");
                        for(int j=0;j<n;j++){
                                System.out.println((j+1) + "\t" + routing_tables[i][j].distance + "\t" + (routing_tables[i][j].parent + 1));
                        }
                        System.out.println("\n");
                }
        }
}