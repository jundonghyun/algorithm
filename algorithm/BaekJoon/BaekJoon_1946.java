package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_1946 {
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
            Collections.sort(list[i]);
        }
        find_failure(list, temp);



        // for(int i = 0; i < T; i++){
        //     System.out.println(list[i].size() -  result[i]);
        // }
    }

    static void find_failure(List<Employee>[] list, List<Integer>[] temp){

        for(int i = 0; i < list.length; i++){
            

            for(int j = 0; j < list[i].size(); j++){
                int paper_max = list[i].get(j).paper;
                int interview_max = list[i].get(j).interview;

                for(int k = j+1; k < list[i].size(); k++){
                    if(paper_max < list[i].get(k).paper && interview_max < list[i].get(k).interview){
                        Employee t = new Employee(list[i].get(k).paper, list[i].get(k).interview);

                        if(temp[i].contains(t.paper) == false){
                            temp[i].add(t.paper);
                        }
                    }
                }
            }
            System.out.println(list[i].size() - temp[i].size());

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
