fun main(){

    var number = intArrayOf(5,8,4,0,6,7,9)

    solution(number)
}

/*
1. 주어진 numbers를 정렬
2. 방문배열을 10개를 만들고 numbers내의 값이 0이 아니면 방문한것으로 체크
3. 방문배열중 false가 있다면 answer에 인덱스 값을 더함
* */

fun solution(numbers: IntArray): Int {
    var answer: Int = 0

    numbers.sort()
    var visit = BooleanArray(10){false}

    for(i in numbers){
        if(i != 0){
            visit[i] = true
        }
    }

    for(i in 0 until visit.size){
        if(!visit[i]){
            answer += i
        }
    }

    return answer
}