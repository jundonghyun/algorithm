import java.util.LinkedList;

public class FindPrimeNum {

    static LinkedList<Integer> list = new LinkedList<Integer>();
    static int num = 0;
    static String t = "";
    static String[] arr;

    public static void main(String[] args){
        solution("17");
    }

    public static int solution(String numbers){
        int answer = 0;

        String[] temp = numbers.split("");
        boolean visited[] = new boolean[temp.length];

        for(int i = 0; i < temp.length; i++){ //한자리부터 n번째 자리까지 숫자를 생성
            arr = new String[i+1];            //배열을 한개씩 늘려줌
            dfs(temp, visited, i, 0);
        }

        for(int i = 0; i < list.size(); i++){ //리스트안에 값이 소수인지 확인
            int result = list.get(i);
            boolean isPrime = false;

            for(int j = 2; j < result; j++){
                if (result % j == 0) {
                    // 나누어지는 수가 있을 경우 isPrime의 값을 true로 바꾼다.
                    isPrime = true;
                    // 한 번이라도 이 조건문이 실행될 경우 num은 소수가 아니므로 반복문을 빠져나온다.
                    break;
                }
            }
            if(isPrime == false){
                answer++;
            }
        }

        return answer;
    }

    static void dfs(String[] temp, boolean[] visited, int depth, int idx){
        if(depth + 1 == idx){ // dfs의 종료조건
            for(int i = 0; i < arr.length; i++){
                t += arr[i];
            }
            num = Integer.parseInt(t);
            if(!list.contains(num) && num != 0 && num != 1){ //조합한 수가 1,0이거나 이미 리스트에 있는경우 넣지않음
                list.add(num);
            }
            t = "";
            return;
        }

        for(int i = 0; i < temp.length; i++){
            if(visited[i] != true){ //방문하지 않았다면
                visited[i] = true;  //방문하고
                arr[idx] = temp[i]; //배열에 넣음
                dfs(temp, visited, depth, idx+1);
                visited[i] = false;
            }
        }
    }

}
