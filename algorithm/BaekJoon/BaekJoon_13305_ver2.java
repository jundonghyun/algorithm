package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_13305_ver2 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Long dist[] = new Long[N-1];
        Long cost[] = new Long[N];

        for(int i = 0; i < N-1; i++){
            dist[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            cost[i] = Long.parseLong(st.nextToken());
        }

        Long min = cost[0];
        Long result = Long.parseLong("0");

        for(int i = 0; i < N-1; i++){
            if(min > cost[i]){
                min = cost[i];
            }
            result += (min * dist[i]);
        }
        System.out.println(result);
    }
    
}
