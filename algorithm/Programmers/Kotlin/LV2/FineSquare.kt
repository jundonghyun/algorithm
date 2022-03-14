fun main(){
    solution(7, 8)
}

/*
https://taesan94.tistory.com/55 참조함
2*3칸으로 뭔가를 계속하려했는데 가로가 더 넓은 예제을 생각하지 못하고 있었음
*/

fun solution(w: Int, h: Int): Long {
    var answer: Long = 0
    var temp: Long = 0

    answer = w.toLong() * h.toLong()
    temp = w.toLong() + h.toLong()
    temp -= gcd(w, h)

    answer -= temp

    return answer
}

fun gcd(a: Int, b: Int): Int = if(b != 0) gcd(b, a % b) else a