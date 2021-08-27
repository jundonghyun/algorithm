package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_1238 {
    static List<Node1>[] list;
    static int[] go;
    static int[] back;
    static int[] distance;
    static boolean[] check;

    @SuppressWarnings("unchecked")

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int point = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        go = new int[n+1];
        back = new int[n+1];
        distance = new int[n+1];
        check = new boolean[n+1];

        for(int i = 1; i < n+1; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()){
                int idx = Integer.parseInt(st.nextToken());
                int pos = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                list[idx].add(new Node1(pos, weight));
            }
        }

        start_djisktra(n, point);
        finsih_djisktra(point);

        int result = go[1] + back[1];

        for(int i = 2; i < n+1; i ++){
            result = Math.max(result, go[i]+back[i]);
        }

        System.out.println(result);
    }

    static void start_djisktra(int finish, int point){
        for(int i = 1; i <= finish; i++){ //1부터 끝까지 정해진 위치(point)까지의 최단거리를
            //각각 계산해서 go배열에 넣어줌
            //ex) 1->2, 2->2, 3->2, 4->2
            Arrays.fill(distance, Integer.MAX_VALUE); //for루프가 한번돌면 distacn배열 재활용 위해 초기화
            PriorityQueue<Node1> q = new PriorityQueue<>();

            q.add(new Node1(i, 0));
            distance[i] = 0;

            while(!q.isEmpty()){
                Node1 n = q.poll();
                int now = n.index;
                if(check[n.index] == true) continue;
                check[n.index] = true;

                for(Node1 node : list[now]){
                    if(distance[node.index] > distance[now] + node.weight){
                        distance[node.index] = distance[now] + node.weight;
                        q.add(new Node1(node.index, distance[node.index]));
                    }
                }
            }
            Arrays.fill(check, false); //방문배열 초기화
            go[i] = distance[point]; //각 출발지로부터 목적지까지의 최단거리를 go배열에 넣음
        }
    }

    static void finsih_djisktra(int finish){ //목적지부터 각 출발지로부터의 최단거리(다익스트라 알고리즘 그대로 사용하면됨)
        Arrays.fill(back, Integer.MAX_VALUE);
        PriorityQueue<Node1> q = new PriorityQueue<>();

        q.add(new Node1(finish, 0));
        back[finish] = 0;

        while(!q.isEmpty()){
            Node1 n = q.poll();
            int now = n.index;
            if(check[n.index] == true) continue;
            check[n.index] = true;

            for(Node1 node : list[now]){
                if(back[node.index] > back[now] + node.weight){
                    back[node.index] = back[now] + node.weight;
                    q.add(new Node1(node.index, back[node.index]));
                }
            }
        }
    }
}

class Node1 implements Comparable<Node1>{

    int index;
    int weight;

    Node1(int i, int w){
        this.index = i;
        this.weight = w;
    }

    @Override
    public int compareTo(Node1 o) {
        return this.weight - o.weight;
    }
    
}