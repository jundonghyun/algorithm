package BaekJoon.AlgorithmSolve.BaekJoon;

import java.util.Scanner;

public class BaekJoon_Palindrome {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        System.out.println(solution(temp));
    }

    public static int solution(String t){
        
        if(t.equals(new StringBuilder(t).reverse().toString())){
            return t.length();
        }
        for(int i = 1; i < t.length(); i++){
            StringBuffer re = new StringBuffer(t); 
            //선언을 for문 안에 하는 이유
            //input이 abcd일때 1.abcda, 2.abcdba, 3.abcdcba 
            //이렇게 0부터 i번째까지 reverse한것을 더하기 위함

            re.append(new StringBuilder(t.substring(0,i)).reverse());
            if(re.toString().equals(re.reverse().toString())){
                return re.length();
            }
        }

        return 0;
    }
}
