package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11725{
    static int INF = 100001;
    static int[][] arr;
    public static void main(String[] args) throws NumberFormatException, IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][n+1];
        int[] result = new int[n+1];
        boolean[][] check = new boolean[n+1][n+1];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                arr[i][j] = 0;
            }
        }
        StringTokenizer st;


        

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());

                arr[n2][n1] = 1;
                arr[n1][n2] = 1;
            }
        }

        br.close();

        for(int i = 2; i <= n; i++){
            dfs(i, result, arr, check);
            check = new boolean[n+1][n+1];
        }
        System.out.println();

        for(int i = 2; i < result.length; i++){
            System.out.println(result[i]);
        }
    }

    static int dfs(int idx, int[] result, int[][] arr, boolean[][] check){

        
        for(int i = 1; i < result.length; i++){
            if(check[idx][i] == true){
                return 0;
            }
            if(arr[idx][1] == 1){
                if(i == 1){
                    result[idx] = 1;
                }
                return idx;
            }
            if(arr[idx][i] == 1 && !check[idx][i]){
                check[idx][i] = true;
                result[idx] = dfs(i, result, arr, check);
                return idx;
            }
        }
        
        return idx;
    }
}

