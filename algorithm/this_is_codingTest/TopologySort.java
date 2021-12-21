package this_is_codingTest;
import java.util.*;

public class TopologySort {

    // �끂�뱶�쓽 媛쒖닔(V)��� 媛꾩꽑�쓽 媛쒖닔(E)
    // �끂�뱶�쓽 媛쒖닔�뒗 理쒕�� 100,000媛쒕씪怨� 媛��젙
    public static int v, e;
    public static int[] indegree; // 遺�紐� �뀒�씠釉� 珥덇린�솕�븯湲�
    // 媛� �끂�뱶�뿉 �뿰寃곕맂 媛꾩꽑 �젙蹂대�� �떞湲� �쐞�븳 �뿰寃� 由ъ뒪�듃 珥덇린�솕
    public static List<edge>[] graph;
    public static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        graph = new ArrayList[v+1];
        indegree = new int[v+1];

        for(int i = 0; i <= v; i++){
            graph[i] = new ArrayList<>();
        }

        // 諛⑺뼢 洹몃옒�봽�쓽 紐⑤뱺 媛꾩꽑 �젙蹂대�� �엯�젰 諛쏄린
        for (int i = 1; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a].add(new edge(a, b));
            indegree[b] += 1;
        }
        topology_sort();

        for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i));
        }        
    }

    static void topology_sort(){
        
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i < v + 1; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();
            result.add(now);
            
            for(edge n : graph[now]){
                indegree[n.end] -= 1;

                if(indegree[n.end] == 0){
                    q.add(n.end);
                }
            }
        }
    }
}

class edge{
    int start;
    int end;

    edge(int s, int e){
        this.start = s;
        this.end = e;
    }
}

