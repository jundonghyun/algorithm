package BaekJoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BaekJoon_1753 {
    static int INF = Integer.MAX_VALUE;
    static List<node>[] list;
    static boolean[] check;
    static int[] distance;
    static int start;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int v = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        check = new boolean[v+1]; //방문
        distance = new int[v+1]; //최단거리 저장
        list = new ArrayList[v + 1];

        for(int i = 0; i < distance.length; i++){
            distance[i] = INF;
        }

        for(int i = 1; i <= v; i++){
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());


        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()){
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());
                list[x].add(new node(y, val));
            }
        }

        dijkstra(start);

        for(int i = 1; i <= v; i++){
            if(distance[i] == INF){
                System.out.println("INF");
            }
            else{
                System.out.println(distance[i]);
            }
        }
        
    }

    private static void dijkstra(int start){
        PriorityQueue<node> queue = new PriorityQueue<>();
        queue.add(new node(start, 0));
        distance[start] = 0;

        while(!queue.isEmpty()){
            node curNode = queue.poll();
            int now = curNode.index;

            if(check[now] == true) continue;
            check[now] = true;

            for(node n : list[now]){
                if(distance[n.index] > distance[now] + n.weight){
                    distance[n.index] = distance[now] + n.weight;
                    queue.add(new node(n.index, distance[n.index]));
                }
            }
        }
    }
    
}

class node implements Comparable<node>{
    int index;
    int weight;

    node(int i, int w){
        this.index = i;
        this.weight = w;
    }

    @Override
    public int compareTo(node o) {
        return weight - o.weight;
    }
}
