package this_is_codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class q38 {
    static int INF = 10001;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken()) + 1;
        int n = Integer.parseInt(st.nextToken());
        int result = 0;
        int[][] arr = new int[size][size];

        for(int i = 1; i < size; i++){
            for(int j = 1; j < size; j++){
                if(i == j){
                    arr[i][j] = 0;
                }
                else{
                    arr[i][j] = INF;
                }
            }
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            
            int a = 0;
            int b = 0;
            
            while(st.hasMoreTokens()){
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                arr[a][b] = 1;
            }
        }

        for(int k = 1; k < size; k++){
            for(int i = 1; i < size; i++){
                for(int j = 1; j < size; j++){
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]); //플로이드 워셜 방법사용
                }
            }
        }

        for(int i = 1; i < size; i++){
            int cnt = 0;
            for(int j = 1; j < size; j++){
                if(arr[i][j] != INF || arr[j][i] != INF){ //학생들이 연결되어있다면
                    cnt += 1;
                }
            }
            if(cnt == size - 1){ //모든 학생들의 경우의 수를 비교가능하다면
                result += 1;
            }
        }

        System.out.println(result);
    }
}
