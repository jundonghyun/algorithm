package BaekJoon;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BaekJoon_13549 {

    static boolean[] check = new boolean[100001];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int start = sc.nextInt();
        int finish = sc.nextInt();

        djikstra(start, finish);

        sc.close();
    }

    private static void djikstra(int start, int finish){
        PriorityQueue<Point> q = new PriorityQueue<>();
        int answer = 0;

        q.add(new Point(start, answer));

        while(!q.isEmpty()){
            Point p = q.poll();
            int temp = p.current_pos;

            if(check[temp] == true) continue;
            check[temp] = true;

            if(p.current_pos == finish){
                answer = p.distnace;
                System.out.println(answer);
                return;
            }

            if(temp * 2 <= finish+1 && !check[temp * 2]){ //마지막을 finish로 한이유는
                // 어차피 현재위치에서 *2해서 도착지가 넘어가면 필요없는 값이 되기 때문
                q.add(new Point(temp * 2, p.distnace));
            }
            if(temp + 1 <= finish+1 && !check[temp + 1]){
                q.add(new Point(temp + 1, p.distnace + 1));
            }
            if(temp - 1 >= 0 && !check[temp - 1]){
                q.add(new Point(temp - 1, p.distnace + 1));
            }
        }
    }
}

class Point implements Comparable<Point>{
    int distnace;
    int current_pos;

    Point(int c, int d){
        this.current_pos = c;
        this.distnace = d;
    }

    @Override
    public int compareTo(Point o) {
        return distnace - o.distnace;
    }
}
