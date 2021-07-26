package Programmers.JAVA.LV3;

public class triangle {
    public static void main(String[] args){
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        
        System.out.println(solution(triangle));
    }

    public static int solution(int[][] triangle) {
        int answer = 0;

        for(int i = 0; i < triangle.length; i++){
            for(int j = 0; j < triangle[i].length; j++){
                if(i == 0 && j == 0){ //가장꼭대기
                    continue;
                }
                else if(j == 0 || j == triangle[i].length - 1){ //가장왼쪽
                    if(j == 0){
                        triangle[i][j] = triangle[i][j] + triangle[i-1][j];
                        answer = Math.max(triangle[i][j], answer);
                        continue;
                    }
                    else{ //가장오른쪽
                        triangle[i][j] = triangle[i][j] + triangle[i-1][j-1];
                        answer = Math.max(triangle[i][j], answer);
                        continue;
                    }
                }
                else{ //중간
                    triangle[i][j] = Math.max(triangle[i-1][j-1] + triangle[i][j], triangle[i-1][j] + triangle[i][j]);
                    answer = Math.max(triangle[i][j], answer);
                }
            }
        }

        return answer; //끝
    }
}
