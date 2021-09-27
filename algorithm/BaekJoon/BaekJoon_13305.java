package BaekJoon;

import java.util.*;
import java.io.*;


public class BaekJoon_13305{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Long[][] gas_station = new Long[N][2];
        int count = 0;
        while(st.hasMoreTokens()){
            gas_station[count][1] = Long.parseLong(st.nextToken());
            count++; 
        }

        st = new StringTokenizer(br.readLine());
        count = 0;
        while(st.hasMoreTokens()){
            gas_station[count][0] = Long.parseLong(st.nextToken());
            count++;
        }

        Long min = gas_station[0][0];
        Long result = Long.parseLong("0");

        for(int i = 0; i < N - 1; i++){
            if(min > gas_station[i][0]){
                min = gas_station[i][0];
            }
            result += (min * gas_station[i][1]);
        }

        System.out.println(result);
    }
}