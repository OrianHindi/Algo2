import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Euler {

    private ArrayList<Integer>[] graph,input_graph;
    private int deg[];
    private boolean isPath,isCycle;
    private int v_start;

    public Euler(ArrayList<Integer>[] graph){ input_graph=graph;}

    private void init(){
        this.graph=copy(input_graph);
        deg = new int[graph.length];
        isPath=isCycle=false;
        v_start=0;
        int numOfOddVertexes=0;
        for (int i = 0; i <graph.length ; i++) {
            deg[i]= graph[i].size();
            if(deg[i]%2==1){
                numOfOddVertexes++;
                v_start=i;
            }
        }
        if(numOfOddVertexes==0) isPath=isCycle=true;
        else if(numOfOddVertexes==2) isPath=true;
    }
    public ArrayList<Integer> eulerPath(){
        init();
        if(!isPath) return null;
        return eulerAlgo();
    }
    public ArrayList<Integer> eulerCycle(){
        init();
        if(!isCycle) return null;
        return eulerAlgo();
    }
    private ArrayList<Integer> eulerAlgo(){
        int v = v_start;
        Stack<Integer> st = new Stack<Integer>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        st.add(v);

        while(!st.isEmpty()){
            v=st.peek();
            if(deg[v]==0){
                path.add(v);
                st.pop();
            }
            else{
                int u = graph[v].get(0);
                st.push(u);
                deg[v]--;
                deg[u]--;
                graph[v].remove((Integer) u);
                graph[u].remove((Integer)v);
            }
        }
        return path;
    }

    private ArrayList<Integer>[] copy(ArrayList<Integer>[] g) {
        ArrayList<Integer>[] copy = new ArrayList[g.length];
        for (int i = 0; i < g.length; i++) {
            copy[i] = new ArrayList<Integer>();
            for (int j = 0; j < g[i].size(); j++) {
                copy[i].add(g[i].get(j));
            }
        }
        return copy;
    }

    public static void main(String[] args) {
        ArrayList<Integer> g[] = new ArrayList[6];
        ArrayList<Integer> g0 = new ArrayList<>();
        g0.add(1);
        g0.add(3);
        g0.add(4);
        g0.add(5);
        g[0]=g0;
        ArrayList<Integer> g1 = new ArrayList<>();
        g1.add(0);
        g1.add(3);
        g1.add(4);
        g1.add(2);
        g[1]=g1;
        ArrayList<Integer> g2 = new ArrayList<>();
        g2.add(1);
        g2.add(3);
        g[2]=g2;
        ArrayList<Integer> g3 = new ArrayList<>();
        g3.add(2);
        g3.add(1);
        g3.add(4);
        g3.add(0);
        g[3]=g3;
        ArrayList<Integer> g4 = new ArrayList<>();
        g4.add(0);
        g4.add(3);
        g4.add(1);
        g4.add(5);
        g[4]=g4;
        ArrayList<Integer> g5 = new ArrayList<>();
        g5.add(0);
        g5.add(4);
        g[5]=g5;
        Euler e = new Euler(g);
        ArrayList<Integer> ans = e.eulerCycle();
        for (int i = 0; i <ans.size() ; i++) {
            System.out.print(ans.get(i) + " ,");
        }

    }
}
