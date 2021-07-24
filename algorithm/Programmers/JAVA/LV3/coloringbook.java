package BaekJoon.AlgorithmSolve.Programmers.JAVA.LV3;

public class coloringbook {
    static int temp = 0;
    static int block = 0;
    static int[] nx = {0, -1, 0, 1}; //왼,위,오,아
    static int[] ny = {-1, 0, 1, 0};
    static int numberOfArea;
    static int maxSizeOfOneArea;
    public static void main(String[] args){
        int m = 6;
        int n = 4;
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        int[][] check = new int[m][n];
        

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(check[i][j] != 1 && picture[i][j] != 0){
                    numberOfArea++;
                    solution(i, j, picture, check);
                }
                if(temp > maxSizeOfOneArea){
                    maxSizeOfOneArea = temp;
                }
                temp = 0;
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

    }


    public static void solution(int m, int n, int[][] picture, int[][] check) {
        if(check[m][n] == 1){
            return;
        }

        check[m][n] = 1;
        temp++;
        for(int i = 0; i < 4; i++){
            int x = m + nx[i];
            int y = n + ny[i];
            if(x >= picture.length || y >= picture[0].length || 
            x < 0 || y < 0){ //격자를 넘어간다면
                continue;
            }
            if(picture[m][n] == picture[x][y] && check[x][y] == 0){
                solution(x, y, picture, check); //같은 색깔에 방문하지 않은곳이라면
            }
        }
    }
}
