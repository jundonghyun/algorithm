package Programmers.JAVA.LV2;

public class JoyStick {

    public static void main(String[] args){
        String name = "JAZ";

        solution(name);
    }

    public static int solution(String name) {
        int answer = 0;
        //위,아래 min값
        //왼쪽,오른쪽 min값
        int maxLength = name.length() - 1;
        int min = maxLength;
        
        for(int i = 0; i < name.length(); i++){
            
            if(name.charAt(i) != 'A'){
                answer += Math.min(name.charAt(i) - 'A', 'Z'-name.charAt(i) + 1);
            }
            
            int next = i + 1;
            while(name.charAt(next) == 'A' && next < name.length()){
                next++;
            }
            min = Math.min(min, (i*2) + name.length() - next);
        }
        answer += min;

        return answer;
    }
    
}
