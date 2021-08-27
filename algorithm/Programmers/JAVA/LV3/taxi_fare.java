package Programmers.JAVA.LV3;

import java.util.*;

public class taxi_fare {
    static List<node>[] list;
    static boolean check[];
    static int[] distance;
    static int[] a_distance;
    static int[] b_distance;

    public static void main(String[] args) {
        int n = 6; // 지점갯수
        int s = 4; // 출발지
        int a = 6; // a도착지
        int b = 2; // b도착지
        int[][] fares = { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
                { 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };

        solution(n, s, a, b, fares);

    }

    private static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        list = new ArrayList[n + 1];
        check = new boolean[n + 1];
        distance = new int[n + 1];
        a_distance = new int[n + 1];
        b_distance = new int[n + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(a_distance, Integer.MAX_VALUE);
        Arrays.fill(b_distance, Integer.MAX_VALUE);

        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < fares.length; i++) {
            list[fares[i][0]].add(new node(fares[i][1], fares[i][2]));
            list[fares[i][1]].add(new node(fares[i][0], fares[i][2]));
        }

        distance = djikstra(s, distance);
        a_distance = djikstra(a, a_distance);
        b_distance = djikstra(b, b_distance);

        for (int i = 1; i <= n; i++)
            answer = Math.min(answer, distance[i] + a_distance[i] + b_distance[i]);

        return answer;
    }

    private static int[] djikstra(int start, int[] distance) {
        PriorityQueue<node> q = new PriorityQueue<>();

        Arrays.fill(distance, Integer.MAX_VALUE);

        q.add(new node(start, 0));
        distance[start] = 0;

        while (!q.isEmpty()) {
            node n = q.poll();
            int now = n.index;

            if (check[now] == true)
                continue;
            check[now] = true;

            for (node t : list[now]) {
                if (distance[t.index] > distance[now] + t.weight) {
                    distance[t.index] = distance[now] + t.weight;
                    q.add(new node(t.index, distance[t.index]));
                }
            }
        }

        Arrays.fill(check, false);
        return distance;
    }
}

class node implements Comparable<node> {
    int index;
    int weight;

    node(int i, int w) {
        this.index = i;
        this.weight = w;
    }

    @Override
    public int compareTo(node o) {
        return weight - o.weight;
    }
}