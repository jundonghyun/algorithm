package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;


import java.io.*;
import java.util.*;
public class BaekJoon_1938 {
    static char map[][];
    static int n;
    static int dx[]= {-1,-1,-1,1,1,1,0,0};
    static int dy[] = {-1,0,1,-1,0,1,-1,1};
    static boolean visited[][][];
    static Node end = new Node(0,0,0,0);
    static Queue<Node> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        map = new char[n][n];
        visited = new boolean[n][n][2];
        for(int i=0; i<n; i++) {
            String str = br.readLine();
            for(int j=0; j<n; j++) {
                map[i][j]=str.charAt(j);
            }
        }
        
        boolean flag= true;
        
        for(int i=0; i<n; i++) {
            if(!flag) {
                break;
            }
            for(int j=0; j<n; j++) {
                if(map[i][j]=='B') {
                    if(i+1<n && map[i+1][j]=='B') {
                        q.add(new Node(i+1,j,0,0));
                        flag = false;
                        break;
                    }
                    else if(j+1<n && map[i][j+1]=='B') {
                        q.add(new Node(i,j+1,1,0));
                        flag = false;
                        break;
                    }
                }
            }
        }
        
        flag=true;
        for(int i=0; i<n; i++) {
            if(!flag) {
                break;
            }
            for(int j=0; j<n; j++) {
                if(map[i][j]=='E') {
                    if(i+1<n &&map[i+1][j]=='E') {
                        end.x=i+1;
                        end.y=j;
                        end.shape=0;
                        flag = false;
                        break;
                    }
                    else if(j+1<n && map[i][j+1]=='E') {
                        end.x=i;
                        end.y=j+1;
                        end.shape=1;
                        flag = false;
                        break;
                    }
                }
            }
        }
        bfs();
    }
    public static void bfs() {
        while(!q.isEmpty()) {
            Node a = q.poll();
            int x=a.x;
            int y= a.y;
            int rotate_cnt=0;
            int shape =a.shape;
            if(a.x==end.x && a.y==end.y && a.shape == end.shape) {
                System.out.println(a.cnt);
                return;
            }
            for(int i=0; i<8; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx>=0 && ny>=0 && nx<n && ny<n && map[nx][ny]!='1') {
                    rotate_cnt++;
                }
            }
            if(rotate_cnt==8) {
                if(shape==0) {
                    if(!visited[x][y][1]) {
                        q.add(new Node(x,y,1,a.cnt+1));
                        visited[x][y][1]=true;
                    }
                }
                else {
                    if(!visited[x][y][0]) {
                        q.add(new Node(x,y,0,a.cnt+1));
                        visited[x][y][0]=true;
                    }
                }
            }
            
            if(shape ==0) { // 세로 일 때
                if(x-2>=0 && !visited[x-1][y][shape] && map[x-2][y]!='1') { // U
                    q.add(new Node(x-1,y,shape,a.cnt+1));
                    visited[x-1][y][shape]=true;
                }
                if(x+2<n && !visited[x+1][y][shape] && map[x+2][y]!='1') {  // D
                    q.add(new Node(x+1,y,shape,a.cnt+1));
                    visited[x+1][y][shape]=true;
                }
                if(y-1>=0 && !visited[x][y-1][shape] && map[x-1][y-1]!='1' && map[x][y-1]!='1' && map[x+1][y-1]!='1') { //L
                    q.add(new Node(x,y-1,shape,a.cnt+1));
                    visited[x][y-1][shape]=true;
                }
                if(y+1<n && !visited[x][y+1][shape] && map[x-1][y+1]!='1' && map[x][y+1]!='1' && map[x+1][y+1]!='1') { //L
                    q.add(new Node(x,y+1,shape,a.cnt+1));
                    visited[x][y+1][shape]=true;
                }
            }
            else {  // 가로일때
                if(x-1>=0 && !visited[x-1][y][shape] && map[x-1][y]!='1' && map[x-1][y+1]!='1' && map[x-1][y-1]!='1') { //U
                    q.add(new Node(x-1,y,shape,a.cnt+1));
                    visited[x-1][y][shape]=true;
                }
                if(x+1<n && !visited[x+1][y][shape] && map[x+1][y]!='1' && map[x+1][y+1]!='1' && map[x+1][y-1]!='1') {  //D
                    q.add(new Node(x+1,y,shape,a.cnt+1));
                    visited[x+1][y][shape]=true;
                }
                if(y-2>=0 && !visited[x][y-1][shape] && map[x][y-2]!='1') { //L
                    q.add(new Node(x,y-1,shape,a.cnt+1));
                    visited[x][y-1][shape]=true;
                }
                if(y+2<n && !visited[x][y+1][shape] && map[x][y+2]!='1') {//R
                    q.add(new Node(x,y+1,shape,a.cnt+1));
                    visited[x][y+1][shape]=true;
                }
            }
        }
        System.out.println(0);
    }
  }
class Node{
    int x,y,shape,cnt;
    Node(int x, int y, int shape, int cnt){
        this.x=x;
        this.y=y;
        this.shape=shape;
        this.cnt=cnt;
    }
}