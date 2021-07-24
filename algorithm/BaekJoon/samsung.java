package BaekJoon;

import java.util.*;

public class samsung {
    static int answer = 0;
    static boolean[][] check;
    static int day = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = 0;
        int L, R;
        
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        int[][] arr = new int[N][N];
        check = new boolean[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                solution(N, L, R, arr, check, 0, 0, answer);
                if(day == 1){
                    answer++;
                    day = 0;
                }
            }
        }

        System.out.println(answer);
    }

    public static int solution(int N, int L, int R, int[][]arr, boolean[][] check, int i, int j, int answer){
        int pos = 0;

        for(i = 0; i < N; i++){
            for(j = 0; j < N; j++){
                pos = arr[i][j];
                //인구이동이 가능할때
                int temp = 0;

                if(check[i][j] != true){
                    if(j+1 < N){ //오른쪽 끝
                        temp = Math.abs(pos - arr[i][j+1]);
    
                        if(temp <= R && temp >= L){
                            check[i][j] = true;
                            //check[i][j+1] = true;
                            check = new boolean[N][N];
                            break;
                        }
                    }
                    if(i+1 < N){ //아래 끝
                        temp = Math.abs(pos - arr[i+1][j]);
    
                        if(temp <= R && temp >= L){
                            check[i][j] = true;
                            //check[i+1][j] = true;
                            check = new boolean[N][N];
                            break;
                        }
                    }
                    if(i-1 >= 0){ //위쪽 끝
                        temp = Math.abs(pos - arr[i-1][j]);
    
                        
                        if(temp <= R && temp >= L){
                            check[i][j] = true;
                            //check[i-1][j] = true;
                            check = new boolean[N][N];
                            break;
                        }
                    }
                    if(j-1 >= 0){ //왼쪽 끝
                        temp = Math.abs(pos - arr[i][j-1]);
    
                        if(temp <= R && temp >= L){
                            check[i][j] = true;
                            //check[i][j-1] = true;
                            check = new boolean[N][N];
                            break;
                        }
                    }
                }                
            }
        }

        int add = 0;
        int count = 0;
        for(int k = 0; k < N; k++){
            for(int f = 0; f < N; f++){
                if(check[k][f] == true){
                    add += arr[k][f];
                    count++;
                }
            }
        }
        int result = 0;
            if(add != 0 && count != 0){
                result = add / count;
            }
                
            if(result != 0){
                answer++;
                add = 0;
                count = 0;
                for(int k = 0; k < N; k++){
                    for(int f = 0; f < N; f++){

                        if(check[k][f] == true){
                            arr[k][f] = result;
                        }
                    }
                }
                check = new boolean[N][N];
                day = 1;
                return 1;
            }

        return 0;
    }
}
