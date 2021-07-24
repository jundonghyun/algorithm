package BaekJoon.AlgorithmSolve.BaekJoon;
import java.util.*;

public class BaekJoon_21610 {
    //오른쪽, 왼쪽대각선위, 위쪽, 오른쪽대각선위
    //왼쪽, 오른쪽대각선아래, 아래, 왼쪽대각선아래
    static int[] move_x = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] move_y = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] arr;
    static int[][] cloud_check;
    static List<Cloud> cloudlist;
    static int N = 0;
    static int M = 0;

    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][N];
        cloud_check = new int[N][N];
        cloudlist = new ArrayList<>();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                arr[i][j] = sc.nextInt();
                cloud_check[i][j] = 0;
            }
        }

        //초기 비바라기 시전
        cloud_check[N-2][0] = 1;
        cloud_check[N-2][1] = 1;
        cloud_check[N-1][0] = 1;
        cloud_check[N-1][1] = 1;

        for(int i = 0; i < M; i++){
            int d = sc.nextInt();
            int s = sc.nextInt();

            move(d, s);
        }

        int sum = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sum += arr[i][j];
            }
        }
        
        System.out.println(sum);
    }

    public static void move(int d, int s){ //초기 구름의 위치를 list에 저장
        for(int i = 0; i < cloud_check.length; i++){
            for(int j = 0; j < cloud_check.length; j++){
                if(cloud_check[i][j] != 0){
                    cloudlist.add(new Cloud(i, j));
                }
            }
        }

        for(int i = 0; i < cloudlist.size(); i++){
            Cloud cloud = new Cloud();
            cloud = cloudlist.get(i);
            cloud_check[cloud.x][cloud.y] = 0; //움직이기전 구름 위치를 없애줌
            cloud.x = value(cloud.x, move_x[d] * s);
            cloud.y = value(cloud.y, move_y[d] * s);
            cloudlist.set(i, cloud); // 1. d방향으로 s만큼 이동한 구름위치를 다시 list에 넣어줌
            arr[cloud.x][cloud.y] += 1; // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
        }

        for(int i = 0; i < cloudlist.size(); i++){
            int count = 0;
            Cloud cloud = new Cloud();
            cloud = cloudlist.get(i);
            
            for(int j = 2; j < 9; j+=2){ // 4. 대각선 확인
                int temp_x = cloud.x + move_x[j];
                int temp_y = cloud.y + move_y[j];
                if(temp_x < 0 || temp_x > N-1 || temp_y < 0 || temp_y > N-1){ //격자를 벗어난다면 넘어감
                    continue;
                }
                else{
                    if(arr[temp_x][temp_y] > 0){ //대각선에서 물의 양이 1보다 많은 경우 
                        count++;
                    }
                }
            }
            arr[cloud.x][cloud.y] += count; //물복사버그 시전
            cloud_check[cloud.x][cloud.y] = 1; //구름의 위치 저장
        }

        for(int i = 0; i < arr.length; i++){ // 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
            for(int j = 0; j < arr.length; j++){
                if(arr[i][j] > 1 && cloud_check[i][j] != 1){
                    arr[i][j] -= 2;
                    cloud_check[i][j] = 1;
                }
            }
        }
        for(int k = 0; k < cloudlist.size(); k++){
            Cloud cloud = new Cloud();
            cloud = cloudlist.get(k);
            cloud_check[cloud.x][cloud.y] = 0;
        }
        cloudlist.clear();
    }

    public static int value(int old, int offset){ // 구름이 이동할때 격자를 벗어나지 못하게함
        int nv = (old + offset) % N; 
        return (nv < 0) ? nv+N : nv; // 음수일 경우 격자의 크기만큼 더해줌
    }
    
}

class Cloud{
    int x;
    int y;

    Cloud(){}

    Cloud(int x, int y){
        this.x = x;
        this.y = y;
    }
}
