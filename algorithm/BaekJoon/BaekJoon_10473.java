package BaekJoon;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;


public class BaekJoon_10473{
    static pos start;
    static pos end;
    static pos[] Coordinate;
    static ArrayList<Edge> adjList[];
    static boolean check[];
    static float times[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        start = new pos(Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        end = new pos(Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Coordinate = new pos[n+2];
        check = new boolean[n+2];
        times = new float[n+2];
        adjList = new ArrayList[n+2];

        for(int i = 0; i < n+2; i++){
            adjList[i] = new ArrayList<>();
        }

        Coordinate[0] = start;
        Coordinate[n+1] = end;
        
        for(int i = 1; i < n+1; i++){
            st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            Coordinate[i] = new pos(x, y);
        }

        //1. 첫대포까지 거리구하기
        for(int i = 1; i < n+2; i++){
            float dis = (float) Math.sqrt(Math.pow(Coordinate[0].x - Coordinate[i].x, 2) +
            Math.pow(Coordinate[0].y - Coordinate[i].y, 2));
            adjList[0].add(new Edge(i, (float) ((float)dis/5.0)));
        }

        for(int i = 1; i < n+2; i++){
            for(int j = 0; j < n+2; j++){
                float dis = (float) Math.sqrt(Math.pow(Coordinate[i].x - Coordinate[j].x, 2)
                + Math.pow(Coordinate[i].y - Coordinate[j].y, 2));
                adjList[i].add(new Edge(j, (float) Math.min(dis / 5.0, Math.abs(dis-50) / 5.0 + 2)));
            }
        }

        dijkstra(0);

        System.out.println(times[n+1]);
    }

    private static void dijkstra(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(times, Integer.MAX_VALUE);
        pq.add(new Edge(start, 0));
        times[start] = (float) 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int destination = edge.destination;

            if (check[destination]) {
                continue;
            } else {
                check[destination] = true;
            }

            for (Edge next : adjList[destination]) {
                if (times[next.destination] >= times[destination] + next.time) {
                    times[next.destination] = times[destination] + next.time;
                    pq.add(new Edge(next.destination, times[next.destination]));
                }
            }
        }
    }
}

class pos{
    float x;
    float y;

    public pos(float x, float y){
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge>{
    int destination;
    float time;

    public Edge(int destination, float time){
        this.destination = destination;
        this.time = time;
    }
    @Override
    public int compareTo(Edge o) {
        return Float.compare(this.time, o.time);
    }
    
}
