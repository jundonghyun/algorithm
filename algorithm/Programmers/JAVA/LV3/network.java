package Programmers.JAVA.LV3;

import java.util.*;

public class network {
    int answer = 0;
    static HashMap<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args){
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        solution(3, computers);
    }


    public static int solution(int n, int[][] computers) {
        int answer = 0;

        boolean[][] checked = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!checked[i][j] && computers[i][j] != 0){
                    dfs(i,j,computers, checked);
                    
                }
            }
        }
        
        return answer++;
    }
    public static void dfs(int i, int j ,int[][]computers, boolean[][] checked){
        checked[i][j] = true;
        
        if(j+1 < computers.length){
            if(computers[i][j+1] == 1 && !checked[i][j+1]){
                checked[i][j+1] = true;

                dfs(j+1,j+1, computers, checked);           
            }
        }
        return;
    }
}
