package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_1946_ver2 {
    static int[] result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        List<Employee> list[] = new ArrayList[T];
        List<Integer> temp[] = new ArrayList[T];
        result = new int[T];

        for(int i = 0; i < T; i++){
            list[i] = new ArrayList<>();
            temp[i] = new ArrayList<>();
        }

        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            for(int j = 0; j < n; j++){
                st = new StringTokenizer(br.readLine());

                int p = Integer.parseInt(st.nextToken());
                int inter = Integer.parseInt(st.nextToken());

                list[i].add(new Employee(p, inter));
            }
            Collections.sort(list[i]); //서류등수로 오름차순 정렬
        }

        for(int i = 0; i < T; i++){
            int count = 1;//서류 1등은 항상 존재하므로 1부터 카운트 시작
            int idx = 0; //면접등수가 높은 사람을 찾을 변수

            for(int j = 1; j < list[i].size(); j++){
                if(list[i].get(j).interview < list[i].get(idx).interview){
                    // 1. 제일 초기상황 : 1등면접점수보다 현재 면접점수가 높을경우
                    // 2. 이후 상황 : 이전의 면접점수보다 현재 면접점수가 높을경우
                    count++;
                    idx = j; // 현재 면접등수의 위치를 저장(리스트로 저장되어있으므로)
                    continue;
                }
            }
            System.out.println(count);
        }
    }

    
}

class Employee implements Comparable<Employee>{
    int paper;
    int interview;

    Employee(int p, int i){
        this.paper = p;
        this.interview = i;
    }

    @Override
    public int compareTo(Employee o) {
        return paper - o.paper;
    }
}
