import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Fire {

    public static int fire(ArrayList<Integer>[] tree){
        int n = tree.length;
        int nvert=n;
        Queue<Integer> leaves= new LinkedList<>();
        int deg[] = new int[n];
        int levels[] = new int[n];
        for (int i = 0; i <n ; i++) {
            deg[i]=tree[i].size();
            if(deg[i]==1) leaves.add(i);
        }
        int maxlevel=0;
        int leaf,v;
        while(nvert<2){
            leaf = leaves.poll();
            deg[leaf]=0;
            v=tree[leaf].get(0);
            nvert--;
            deg[v]--;
            if(deg[v]==1){
                leaves.add(v);
                levels[v]=levels[leaf]+1;
                maxlevel=Math.max(maxlevel,levels[v]);
            }
        }
        ArrayList<Integer> center= new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            if(levels[i]==maxlevel) center.add(i);
        }
        int radius,diameter;
        if(center.size()==2){
            radius=maxlevel+1;
            diameter=2*radius-1;
        }
        else{
            radius=maxlevel;
            diameter=2*radius;
        }
        System.out.println("Radius:" + radius + "\t Diameter:" + diameter + "\t centers:" + center.toString());
        return diameter;
    }
}
