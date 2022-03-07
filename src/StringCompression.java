public class StringCompression {

    public static void main(String[] args){
        int result = Solution("ababcdcdababcdcd");
        System.out.println(result);
    }

    static public int Solution(String s) {
        int answer = s.length();

        for(int i = 1; i <= s.length() / 2; i++){ //문자열의 반까지만 비교하면됨 그 이상 넘어가면 효율이 떨어짐
            int zip = 1; //압축할 문자열의 길이
            String zipStr = s.substring(0, i); //문자열 자르기(a, aa, aaa...)
            StringBuilder result = new StringBuilder();

            for(int j = i; j <= s.length(); j += i){ //다른 문자열을 비교할 반복문
                String next = "";
                if(j + i > s.length()){ //문자열 자르기 시작지점은 i, 끝지점은 j + i인데 끝지점이 s.length()보다 크면 안되므로 if문 넣음
                    next = s.substring(j, s.length());
                }
                else next = s.substring(j, i + j); //next = s.substring(j, j + i > s.length() ? s.length() : j + i);

                if(zipStr.equals(next)){ //처음 자른문자 zipStr과 현재 자른문자 next가 같다면 zip++
                    zip++;
                }
                else{ //문자열이 다르다면
                    result.append((zip != 1 ? zip : "") + zipStr);  //결과값에 zip이 1보다 크다면 같은 문자열이 있었던 것이므로
                                                                    //zip에다가 처음 자른문자를 더해줌(2ab 이런식)
                    zipStr = next;                                  //이미 next는 비교할 문자로 바뀐상태이므로 처음 자른문자를 next의 값으로 변경
                    zip = 1;                                        //ababcdcd일때 zipStr = ab이고 next = cd인상태일때 zipStr = cd로 하는 것
                }
            }

            result.append(zipStr);
            answer = Math.min(answer, result.length());
        }
        return answer;
    }
}
