package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11404{
    static int INF = 10000000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine())+1;

        int[][] arr = new int[n][n];
        int size = Integer.parseInt(br.readLine());

        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                if(i!=j && arr[i][j] == 0){
                    arr[i][j] = INF;
                }
            }
        }


        for(int i = 0; i < size; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                int val1 = Integer.parseInt(st.nextToken());
                int val2 = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                if(arr[val1][val2] != 0){
                    arr[val1][val2] = Math.min(arr[val1][val2], value);
                }
                else{
                    arr[val1][val2] = value;
                }
            }
        }

        floyd(arr);

        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j < arr.length; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void floyd(int[][]arr){
        for(int k = 1; k < arr.length; k++){
            for(int i = 1; i < arr.length; i++){
                for(int j = 1; j < arr.length; j++){
                    arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
                }
            }
        }
        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j < arr.length; j++){
                if(arr[i][j] == INF){
                    arr[i][j] = 0;
                }
            }
        }
    }
}