package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_11399 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int result = 0, temp = 0;
        
        temp = arr[0];
        result += temp;
        for(int i = 1; i < arr.length; i++){
            temp += arr[i];
            result += temp;
        }

        System.out.println(result);
    }
    
}
