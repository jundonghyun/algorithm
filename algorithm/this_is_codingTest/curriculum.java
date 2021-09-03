package this_is_codingTest;

import java.io.*;
import java.util.*;


public class curriculum {
    static List<subject> sb[];
    static int save_time[];
    static int result[];
    static int indegree[];
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        sb = new ArrayList[N+1];
        save_time = new int[N+1];
        result = new int[N+1];
        indegree = new int[N+1];

        for(int i = 1; i < N+1; i++){
            sb[i] = new ArrayList<>();
        }

        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            save_time[i] = time;
            result[i] = time;
            while(st.hasMoreTokens()){
                int before = Integer.parseInt(st.nextToken());
                if(before == -1){
                    continue;
                }
                else{
                    indegree[i] += 1;
                    sb[i].add(new subject(before));
                }
            }
        }
        
        curriculum_sort();

        for(int i = 1; i< N+1; i++){
            System.out.println(result[i]);
        }
    }

    static void curriculum_sort(){
        Deque<Integer> q = new LinkedList<>();
        for(int i = 1; i < N + 1; i++){
            if(indegree[i] != 0){ //진입차수가 0이아닌 인덱스를 큐에 넣음
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();
            
            for(subject n : sb[now]){
                result[now] = Integer.max(result[now], result[n.before_subject] + save_time[now]);
                //result배열에는 각 수강과목별 소요시간이 저장되어있는데
                //이때 수강과목마다 걸리는 시간과 result배열에 저장된 이전 선수과목의 시간 + 현재과목의 걸리는시간 두개중
                //큰값을 result배열에 넣음
            }
        }
    }
}

class subject{
    int before_subject;

    subject(int b){
        this.before_subject = b;
    }
}