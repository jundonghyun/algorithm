fun main(){
    solution("123")
}

/*
정규표현식을 적용해서 문자열의 현재위치가 숫자가 아니라면
현재위치의 문자와 그다음위치의 문자를 더해서 하나의 문자열로 생성
생성한 문자열을 when절에 넣어 비교(문자2개를 만드는 이유는 three와 two같은경우 첫번째 t가 같으므로 비교할 수 없기때문)
대응하는 문자열의 수만큼 index를 늘림
만약 숫자라면 그냥 결과변수에다 더해주면됨
* */

fun solution(s: String): Int {
    var answer: Int = 0

    var result = ""
    var pattern = Regex("[0-9]")

    var temp = s.split("")

    var idx = 1
    while(idx != temp.size-1){
        if(!pattern.matches(temp[idx])){ //숫자가 아니라면
            var middle = temp[idx]+temp[idx+1]

            when(middle){
                "ze" -> {result += "0"
                        idx+=4}
                "on" -> {result += "1"
                        idx+=3}
                "tw" -> {result += "2"
                    idx+=3}
                "th" -> {result += "3"
                    idx+=5}
                "fo" -> {result += "4"
                    idx+=4}
                "fi" -> {result += "5"
                    idx+=4}
                "si" -> {result += "6"
                    idx+=3}
                "se" -> {result += "7"
                    idx+=5}
                "ei" -> {result += "8"
                    idx+=5}
                "ni" -> {result += "9"
                    idx+=4}
            }
        }
        else{
            result += temp[idx]
            idx++
        }
    }
    answer = result.toInt()

    return answer
}