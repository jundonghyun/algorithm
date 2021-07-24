package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author     : junsang Hwang
 * @Date       : 2021.04.29
 * @DESC       : 마법사 상어와 비바라기
 * @see        : https://www.acmicpc.net/problem/21610
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] basket;          // 바구니
    static int sideLength;          // 변의 길이
    static int magicPerformCount;   // 마법 수행 카운트

    // 구름 위치
    static List<int[]> cloudLocation = new ArrayList<>();

    // 방향계
    static int[][] dir = {
              { 0,-1}   // 방향1 : [←]
            , {-1,-1}   // 방향2 : [↖]
            , {-1, 0}   // 방향3 : [↑]
            , {-1, 1}   // 방향4 : [↗]
            , { 0, 1}   // 방향5 : [→]
            , { 1, 1}   // 방향6 : [↘]
            , { 1, 0}   // 방향7 : [↓]
            , { 1,-1}   // 방향8 : [↙]
    };


    public static void main(String[] args) throws IOException {
        // 입력
        problemInput();

        // 로직
        while(magicPerformCount-- > 0) {
            st = new StringTokenizer(br.readLine());
            int moveDir = Integer.parseInt(st.nextToken());    // 이동 방향
            int moveNum = Integer.parseInt(st.nextToken());    // 이동 횟수

            // 1. 구름을 {moveDir} 방향으로 {moveNum} 만큼 이동시키기
            cloudMove(moveDir, moveNum);

            // 2. 구름이 있는 칸에 비가 내리고, 구름이 사라진다 (1씩 증가)
            rainAndCloudClear();

            // 3. 비바라기를 시전 (대각선 물 수만큼 증가)
            waterCopyBugMagic();

            // 4. 구름 위치를 제외하고 물의 양이 2이상이라면, 구름으로 만들기 (2씩 감소)
            newCloudInit();
        }

        // 출력
        problemOutput();
    }


    /**
     * 입력
     */
    private static void problemInput() throws IOException {
        st = new StringTokenizer(br.readLine());
        sideLength = Integer.parseInt(st.nextToken());
        magicPerformCount = Integer.parseInt(st.nextToken());

        basket = new int[sideLength][sideLength];
        for(int row = 0; row< sideLength; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col< sideLength; col++) {
                basket[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 구름 위치 저장
        cloudLocation.add(new int[] {sideLength-1, 0});
        cloudLocation.add(new int[] {sideLength-1, 1});
        cloudLocation.add(new int[] {sideLength-2, 0});
        cloudLocation.add(new int[] {sideLength-2, 1});
    }


    /**
     * 구름을 {moveDir} 방향으로 {moveNum} 만큼 이동시키기
     */
    private static void cloudMove(int moveDir, int moveNum) {

        // 클론 구름
        List<int[]> cloneCloudLocation = new ArrayList<>();

        // moveNum(구름 이동 횟수)가 sideLength(변의 길이)를 초과할 수 있으므로, 나머지 계산
        moveNum %= sideLength;

        // 구름 size 만큼 루프
        for(int i=0;  i<cloudLocation.size(); i++) {
            int nextX = cloudLocation.get(i)[0] + (dir[moveDir-1][0] * moveNum);
            int nextY = cloudLocation.get(i)[1] + (dir[moveDir-1][1] * moveNum);

            if (nextX < 0) nextX += sideLength;
            if (nextY < 0) nextY += sideLength;
            if (nextX >= sideLength) nextX -= sideLength;
            if (nextY >= sideLength) nextY -= sideLength;

            // 클론에 추가 해놓음
            cloneCloudLocation.add(new int[] {nextX, nextY});
        }

        // 원본 초기화 후, clone 을 copy 함 (구름 위치 업데이트)
        cloudLocation.clear();
        cloudLocation.addAll(cloneCloudLocation);
        cloneCloudLocation.clear();
    }


    /**
     * 구름이 있는 칸에 비가 내리고, 구름이 사라진다. (1씩 증가)
     */
    private static void rainAndCloudClear() {
        for(int i = 0; i< cloudLocation.size(); i++) {
            int curX = cloudLocation.get(i)[0];
            int curY = cloudLocation.get(i)[1];

            // 1씩 증가 !!!
            basket[curX][curY] += 1;
        }
    }


    /**
     * 비바라기(물 복사 버그 마법)를 시전
     */
    private static void waterCopyBugMagic() {
        for(int i = 0; i< cloudLocation.size(); i++) {
            int curX = cloudLocation.get(i)[0];
            int curY = cloudLocation.get(i)[1];
            int diagonalCheck = 0;

            // 방향계가 총 8개 이므로 8번 루프
            for(int j=0; j<8; j++) {
                if (j%2 == 0)   // 홀수만 대각선이므로 pass
                    continue;

                // 대각선 방향에 물이 차있는지 체크
                int nextX = curX + dir[j][0];
                int nextY = curY + dir[j][1];

                if (nextX < 0 || nextY < 0 || nextX >= sideLength || nextY >= sideLength)
                    continue;

                if (basket[nextX][nextY] <= 0)
                    continue;

                diagonalCheck++;
            }

            // 대각선 방향에 물이 차있는 만큼 증가
            basket[curX][curY] += diagonalCheck;
        }
    }


    /**
     * 구름 위치를 제외하고 물의 양이 2이상이라면, 구름으로 만들기 (새로운 구름은 2감소)
     */
    private static void newCloudInit() {

        // 클론 구름
        List<int[]> cloneCloudLocation = new ArrayList<>();

        // 모든 좌표 탐색
        for(int row=0; row<sideLength; row++) {
            for(int col = 0; col< sideLength; col++) {
                // 현재 바구의 물 양이 2 미만일 경우는 제외
                if (basket[row][col] < 2)
                    continue;

                // 기존 구름의 위치 인지 체크
                boolean flag = false;
                for(int k = 0; k< cloudLocation.size(); k++) {
                    int curX = cloudLocation.get(k)[0];
                    int curY = cloudLocation.get(k)[1];

                    if (curX == row && curY == col)
                        flag = true;
                }

                // 기존 구름의 위치라면 제외
                if (flag)
                    continue;

                // 클론에 새로운 구름 위치 저장
                basket[row][col] -= 2;  // 2 감소 !!!
                cloneCloudLocation.add(new int[] {row, col});
            }
        }

        // 원본 초기화 후, clone 을 copy 함 (구름 위치 업데이트)
        cloudLocation.clear();
        cloudLocation.addAll(cloneCloudLocation);
        cloneCloudLocation.clear();
    }

    /**
     * 출력
     */
    private static void problemOutput() {
        int answer = 0;
        for(int row = 0; row< sideLength; row++) {
            for(int col = 0; col< sideLength; col++) {
                answer += basket[row][col];
            }
        }

        System.out.print(answer);
    }
}