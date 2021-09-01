package this_is_codingTest;
import java.util.*;

public class Kruskal {

    // 노드의 개수(V)와 간선의 개수(E)
    // 노드의 개수는 최대 100,000개라고 가정
    public static int v, e;
    public static int result = 0;
    public static int[] parent = new int[100001]; // 부모 테이블 초기화하기
    // 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트 초기화
    public static ArrayList<edgelist> graph;

    public static int findParent(int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if (parent[x] != x){
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    // 두 원소가 속한 집합을 합치기
    public static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        graph = new ArrayList<>();


        // 방향 그래프의 모든 간선 정보를 입력 받기
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            graph.add(new edgelist(a, b, cost));
        }

        Collections.sort(graph);

        for(edgelist n : graph){
            int cost = n.weight;
            int a = n.start;
            int b = n.end;

            if(findParent(parent[a]) != findParent(parent[b])){
                unionParent(a, b);
                result += cost;
            }
        }

        System.out.println(result);
    }
}

class edgelist implements Comparable<edgelist>{
    int start;
    int end;
    int weight;

    edgelist(int s, int e, int w){
        this.start = s;
        this.end = e;
        this.weight = w;
    }

    @Override
    public int compareTo(edgelist o) {
        return weight - o.weight;
    }
}