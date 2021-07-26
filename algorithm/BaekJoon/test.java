package BaekJoon;

import java.util.*;

public class test {
 
    static int n;
    static int l;
    static int r;
    static int map[][];
    static int union[][];
    static int visited[][];
    static int dx[] = { 1, 0, -1, 0 };
    static int dy[] = { 0, -1, 0, 1 };
    static int unionNum = 1;
    static int peopleNum = 0;
    static int unionCnt = 0;
    static boolean keepMoving = true;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();
 
        map = new int[n][n];
        union = new int[n][n];
        visited = new int[n][n];
 
        for (int i = 0; i < n; i++) { // map[][] init
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
 
        int howManyMove = 0;
 
        while (keepMoving) {
            keepMoving = false;
 
            for (int i = 0; i < n; i++) { // visited init -> 0
                for (int j = 0; j < n; j++) {
                    visited[i][j] = 0;
                }
            }
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) {
                        peopleNum = 0;
                        unionCnt = 0;
                        DFS(i, j);
                        peopleMove();
                        unionNum++;
                    }
                }
            }
 
            howManyMove++;
        }
 
        System.out.println(howManyMove - 1);
 
    }
 
    public static void DFS(int x, int y) {
        visited[x][y] = 1;
        union[x][y] = unionNum;
        peopleNum += map[x][y];
        unionCnt += 1;
 
        for (int k = 0; k < 4; k++) {
            int next_x = x + dx[k];
            int next_y = y + dy[k];
 
            if (0 <= next_x && next_x < n && 0 <= next_y && next_y < n) {
                if (visited[next_x][next_y] == 0) {
                    int gap = Math.abs(map[next_x][next_y] - map[x][y]);
 
                    if (l <= gap && gap <= r) {
                        DFS(next_x, next_y);
                    }
                }
            }
 
        }
 
    }
 
    public static void peopleMove() {
        if (unionCnt != 1) {
            int afterMoving = peopleNum / unionCnt;
            keepMoving = true;
    
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (union[i][j] == unionNum) {
                        map[i][j] = afterMoving;
                    }
    
                }
            }
            
        }
    }
 
}
