package Programmers.JAVA.LV3;

import java.util.*;

public class School_Route_DFS {
    static int[] nx = {1, 0}; //아래, 오른쪽
    static int[] ny = {0, 1};
    
    public static void main(String[] args){

        int m,n;
        m = 4;
        n = 3;

        int[][] puddles = {{2,2}};

        solution(m, n, puddles);


    }

    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] arr = new int[n][m];
        boolean[][] check = new boolean[n][m];

        arr[n-1][m-1] = 1;

        for(int i = 0; i < puddles.length; i++){
            arr[puddles[i][1]-1][puddles[i][0]-1] = 9;
        }
        
        for(int i = 0; i < 2; i++){
            int pos_x = 0;
            int pos_y = 0;
            pos_x += nx[i];
            pos_y += ny[i];
            answer = dfs(pos_x, pos_y, arr, check ,answer);
        }

        return answer;
    }

    public static int dfs(int x, int y, int[][] arr, boolean[][] check, int answer){
        
        if(x < arr.length && y < arr[0].length){
            if(arr[x][y] != 9){
                if(arr[x][y] == 1){
                    return answer+1 % 1000000007;
                }
                answer = dfs(x+1, y, arr, check, answer);
                answer = dfs(x, y+1, arr, check, answer);
            }
        }
        

        return answer % 1000000007;
    }
}
