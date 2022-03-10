fun main(){

    var lottos = intArrayOf(45, 4, 35, 20, 3, 9)
    val win_nums = intArrayOf(20, 9, 3, 45, 4, 35)

    solution(lottos, win_nums)
}
/*로또번호를 정렬하고 로또배열과 같은 크기의 방문배열을 만든후
* 중복된 값이 count되지 않도록 방문배열이 false인 경우에만 count올리도록함
* 또한 보이지않는 번호인 0이 있을경우 따로 count하도록 함
* 최고순위는 보이지않은 번호를 count한 zeroCount와 당첨번호와 일치하는 값을 count한 count변수를 더한것이고
* 최저순위는 zeroCount값을 버리고 count값으로만 계산하면됨*/

fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
    var answer = IntArray(2)

    var visit = BooleanArray(lottos.size) {false}
    var count = 0
    var zeroCount = 0
    var result = IntArray(2){0}

    lottos.sort()

    for(i in lottos){
        if(i == 0){
            zeroCount++
            continue
        }
        for(j in win_nums.indices){
            if(i == win_nums[j] && !visit[j]){
                count++
                visit[j] = true
                break
            }
        }
    }

    result[0] = count + zeroCount
    result[1] = count

    for(i in result.indices){
        when(result[i]){
            6 -> if(i == 0){answer[0] = 1}
            else{answer[1] = 1}

            5-> if(i == 0){answer[0] = 2}
            else{ answer[1] = 2}

            4-> if(i == 0){answer[0] = 3}
            else{ answer[1] = 3}

            3-> if(i == 0){answer[0] = 4}
            else{ answer[1] = 4}

            2-> if(i == 0){answer[0] = 5}
            else{ answer[1] = 5}

            1-> if(i == 0){answer[0] = 6}
            else{ answer[1] = 6}

            else -> if(i == 0){answer[0] = 6}
            else{answer[1] = 6}
        }
    }

    return answer
}