package Programmers.JAVA.LV2;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class MakeBigNumber {
    static int depth = 0;
    static int[] check;
    static String t[];
    static int result;
    static int i;
    public static void main(String[] args){
        String number = "4177252841";
        int k = 4;

        solution(number ,k);
    }
    //t배열을 가장뒤에부터 
    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
	    
        int left = 0;
        int right = k;
        int max = -1;
        int idx = 0;
        
        while(sb.length() < number.length() - k) {
        	 max = -1;
             for(int j = left ; j <= right ; ++j){
                 int num = number.charAt(j) - '0';
                 if(num > max){
                     idx = j;
                     max = num;
                 }
             }
             sb.append(number.charAt(idx));
             left = idx + 1;
             right += 1;
        }

        return sb.toString();
    }
}
