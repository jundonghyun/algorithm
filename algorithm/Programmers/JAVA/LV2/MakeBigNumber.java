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

    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
	    
        int left = 0; //시작위치
        int right = k; //종료위치
        int idx = 0; //가장큰 값의 인덱스를 저장
        
        while(sb.length() < number.length() - k) {
            int max = -1; //left와 right구간내에서 가장큰 값을 저장
            for(int j = left ; j <= right ; ++j){
                 int num = number.charAt(j) - '0'; //left부터 시작해서 right까지 값을 가져옴
                 if(num > max){ //가져온 값이 max보다 크다면
                     idx = j; //그 위치를 저장
                     max = num; //max는 큰값으로 변경
                 }
             }
             sb.append(max); //sb에 max값을 추가
             left = idx + 1; //idx앞으로는 탐색할 필요없음 -> max보다 작은수이기때문
             //그래서 시작위치는 left는 idx+1로 변경
             right += 1; //right는 그냥+1해도 어차피 idx는 left와 right안에서만 나오는값이기때문에
             //나중에 left가 right를 넘길 수 없음
        }

        return sb.toString();
    }
}
