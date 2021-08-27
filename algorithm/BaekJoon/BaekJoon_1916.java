package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_1916 {
    static int INF = Integer.MAX_VALUE;
    static List<node1>[] list;
    static boolean check[];
    static int[] distance;
    
    @SuppressWarnings("unchecked")

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int start;
        int last;

        check = new boolean[n+1];
        distance = new int[n+1];
        list = new ArrayList[n+1];

        Arrays.fill(distance, INF);
    
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()){
                int index = Integer.parseInt(st.nextToken());
                int finish = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                list[index].add(new node1(finish, weight));
            }
        }
        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        last = Integer.parseInt(st.nextToken());

        dijkstra(start, last);

        System.out.println(distance[last]);

    }

    private static void dijkstra(int s, int l){
        PriorityQueue<node1> q = new PriorityQueue<>();

        q.add(new node1(s, 0));
        distance[s] = 0;

        while(!q.isEmpty()){
            node1 node = q.poll();
            int now = node.index;
            

            if(check[now] == true) continue;
            check[now] = true;

            for(node1 n : list[now]){
                if(distance[n.index] > distance[now] + n.weight){
                    distance[n.index] = distance[now] + n.weight;
                    q.add(new node1(n.index, distance[n.index]));
                }
            }
        }
    }
    
}

class node1 implements Comparable<node1>{
    int index;
    int weight;

    node1(int i, int w){
        this.index = i;
        this.weight = w;
    }

    @Override
    public int compareTo(node1 o) {
        return weight - o.weight;
    }
}