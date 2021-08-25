package Programmers.JAVA.LV3;

public class School_Route {
    public static void main(String[] args){

        int m,n;
        m = 4;
        n = 3;

        int[][] puddles = {{2,2}};

        solution(m, n, puddles);


    }

    public static int solution(int m, int n, int[][] puddles) {
        int[][] arr = new int[n][m];

        
        arr[0][0] = 1;

        for(int i = 0; i < puddles.length; i++){
            arr[puddles[i][1]-1][puddles[i][0]-1] = 9;
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 9){
                    arr[i][j] = 0;
                    continue;
                }
                else{
                    if(i != 0){
                        arr[i][j] += arr[i-1][j] % 1000000007;
                    }
                    if(j != 0){
                        arr[i][j] += arr[i][j-1] % 1000000007;
                    }
                }
            }
        }

        return arr[n-1][m-1] % 1000000007;
    }
}
