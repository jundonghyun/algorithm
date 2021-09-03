package this_is_codingTest;

import java.util.Scanner;

public class Make_Team {

    // 노드의 개수(V)와 간선(Union 연산)의 개수(E)
    // 노드의 개수는 최대 100,000개라고 가정
    public static int v, e;
    public static int[] parent = new int[100001]; // 부모 테이블 초기화하기

    // 특정 원소가 속한 집합을 찾기
    public static int findParent(int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if (parent[x] != x){
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    // 두 원소가 속한 집합을 합치기
    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        // Union 연산을 각각 수행
        for (int i = 0; i < e; i++) {
            int make = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(make == 0){
                union(a, b);
            }
            else{
                if(findParent(a) == findParent(b)){
                    System.out.println("YES");
                }
                else{
                    System.out.println("NO");
                }
            }
            
        }
    }
}