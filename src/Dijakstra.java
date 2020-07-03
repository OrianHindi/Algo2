import java.util.PriorityQueue;

public class Dijakstra {
    static final int inf = Integer.MAX_VALUE;

    /**
     * dijkstra algorithem, find the minimum path between 2 vertices when weight on edges is given.
     * @param source the start vertice we want to start.
     * @param dest dest vertice
     * @param weight the matrix that represent weight on graph.
     * @return the minimum value of path.
     */
    public static int DijkAlgo (int source,int dest, int weight[][]){
        int n = weight.length;
        int dist[] = new int[n];
        int prev[] = new int[n];
        boolean visited[] = new boolean[n];
        for (int i = 0; i <n ; i++) {
            dist[i]=inf;
            prev[i]=-1;
            visited[i]=false;
        }
        dist[source] =0;
        int v=-1;
        while( v!= dest){
            v=ExtractMin(dist,visited);
            for (int i = 0; i < n ; i++) {
                if(weight[v][i]!=inf && !visited[i] && dist[i]>dist[v]+weight[v][i]){
                    dist[i] = dist[v] + weight[v][i];
                    prev[i]=v;
                }
            }
            visited[v]=true;
        }
        return dist[dest];
    }

    public static int ExtractMin(int[] dist, boolean[] visited){
        int minIndex = inf;
        int minVal = inf;
        int V = visited.length;
        for (int i = 0; i <V; i++) {
            if(!visited[i]&& dist[i]<minVal){
                minVal= dist[i];
                minIndex=i;
            }

        }

      return minIndex;
    }

    public static int twoDirdijkstra(){

        return 0;
    }

//
//    public static int[] fullDijkstra(int source, int mat[][]){
//        PriorityQueue<Integer> pQ;
//        int n = mat.length;
//        int dist[] = new int[n];
//        boolean visited[] = new boolean[n];
//        int prev[] = new int[n];
//        for (int i = 0; i <n ; i++) {
//            prev[i]=-1;
//            visited[i]=false;
//            dist[i]= inf;
//        }
//        dist[source]=0;
//
//
//
//    }

    public static void main(String[] args) {
       int[][] mat = {{0 ,1 ,2 ,inf ,inf ,inf ,inf ,inf },
                {1 ,0 ,4 ,inf ,5 ,inf ,inf ,inf },
                {2 ,4 ,0 ,7 ,inf ,3 ,inf ,inf },
                {inf ,inf ,7 ,0 ,3 ,8 ,inf ,inf },
                {inf ,5 ,inf ,3 ,0 ,inf ,1 ,3 },
                {inf ,inf ,3 ,8 ,inf ,0 ,2 ,inf },
                {inf ,inf ,inf ,inf ,1 ,2 ,0 ,5 },
                {inf ,inf ,inf ,inf ,4 ,inf ,5 ,0 }};

        System.out.println(DijkAlgo(5,7,mat));
    }


}
