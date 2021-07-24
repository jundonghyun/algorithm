package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Solution{
    static int[] nx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] ny = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[][] store_food;
    static List<Tree> list;
    static List<Tree> live;
    static int[][] check;
    static int[][] tree_pos;
    static List<Tree> dead;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = 0;
        int M = 0;
        int K = 0;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        store_food = new int[N][N];
        list = new ArrayList<>();
        check = new int[N][N];
        tree_pos = new int[N][N];

        for(int i = 0; i < store_food.length; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < store_food.length; j++){
                store_food[i][j] = Integer.parseInt(st.nextToken());
                tree_pos[i][j] = 5;
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            check[x][y]++;
            Tree temp = new Tree(x,y,z,false);
            list.add(temp);
            //priorityQueue.add(temp);
        }

        while(K-- != 0){
            live = new ArrayList<>();
            dead = new ArrayList<>();
            
            spring_summer();
            authum();
            winter();
        }

        System.out.println(list.size());
    }
    private static void winter() {
        for(int j = 0; j < tree_pos.length; j++){
            for(int k = 0; k < tree_pos.length; k++){
                int value = store_food[j][k];
                tree_pos[j][k] += value;
            }
        }
    }
    private static void authum() {
        int c = live.size();
        for(int j = 0; j < c; j++){
            Tree temp = live.get(j);

            if(temp.year % 5 == 0){
                for(int k = 0; k < nx.length; k++){
                    int dx = temp.x + nx[k];
                    int dy = temp.y + ny[k];
                    if(dx < 0 || dy < 0 || dx >= tree_pos.length || dy >= tree_pos.length){
                        continue;
                    }
                    else{
                        Tree nTree = new Tree(dx, dy, 1, false);
                        list.add(nTree);
                    }
                }
            }
        }
    }
    private static void spring_summer() {
        Collections.sort(list);
        dead = new ArrayList<>();
        int size = list.size();
        for(int j = 0; j < size; j++){
            Tree temp = list.get(j);
            int dx = temp.x;
            int dy = temp.y;
            
            if(tree_pos[dx][dy] - temp.year < 0){ // 양분이 부족한 경우
                check[dx][dy]--;
                temp.dead = true;
                temp.year = temp.year / 2;
                dead.add(temp);
            }
            else{
                tree_pos[dx][dy] -= temp.year; // 양분을 먹은 경우
                check[dx][dy]--;
                temp.year++;
                live.add(temp);
            }
        }
        list.clear();;
        list = new ArrayList<Tree>(live);

        for(Tree t : dead){                        
            tree_pos[t.x][t.y] += t.year;
        }
        dead.clear();
    }
}

class Tree implements Comparable<Tree>{
    int x;
    int y;
    int year;
    boolean dead;

    Tree(){}

    Tree(int x, int y, int z, boolean d){
        this.x = x;
        this.y = y;
        this.year = z;
        this.dead = d;
    }

    @Override
    public int compareTo(Tree o) {
        return Integer.compare(this.year, o.year);
    }
}