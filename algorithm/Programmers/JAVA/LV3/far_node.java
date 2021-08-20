package Programmers.JAVA.LV3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class far_node {
    public static void main(String[] args){
        int[][] edge = {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};
        int n = 6;

        solution(n, edge);
    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        boolean[][] arr = new boolean[n+1][n+1];
        boolean[] check = new boolean[n+1];

        for(int i = 0; i < edge.length; i++){
            int idx = edge[i][0];
            int idx2 = edge[i][1];

            arr[idx][idx2] = true;
            arr[idx2][idx] = true;
        }

        Queue<Integer> q = new LinkedList<>();

        q.add(1);
        check[1] = true;
        int qSize = 0;

        while(!q.isEmpty()){
            qSize = q.size();
            for(int i = 0; i < qSize; i++){
                int idx = q.poll();
                for(int j = 1; j <= n; j++){
                    if(!check[j] && arr[idx][j] == true){
                        check[j] = true;
                        q.add(j);
                    }
                }
            }
        }

        return qSize;
    }
}
