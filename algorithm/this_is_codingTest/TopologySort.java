package this_is_codingTest;
import java.util.*;

public class TopologySort {

    // 노드의 개수(V)와 간선의 개수(E)
    // 노드의 개수는 최대 100,000개라고 가정
    public static int v, e;
    public static int[] indegree; // 부모 테이블 초기화하기
    // 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트 초기화
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

        // 방향 그래프의 모든 간선 정보를 입력 받기
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

