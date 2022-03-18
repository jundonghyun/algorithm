class TraningClothes {

    /*
    잃어버린 사람과 빌려줄 수 있는 사람을 처음부터 걸러내고 문제를 풀었어야 한다.
    그리고 check배열로는 확인이 잘 안되서 그냥 배열 값 자체를 0으로 만들었다.
    */
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var answer = 0
        var check = BooleanArray(reserve.size){false}

        lost.sort()
        reserve.sort()

        answer = n - lost.size

        for(i in lost.indices){
            for(j in reserve.indices){
                if(lost[i] == reserve[j]){
                    check[j] = true
                    lost[i] = 0
                    reserve[j] = 0
                    answer++
                }
            }
        }

        for(i in lost.indices){
            if(lost[i] == 0){
                continue
            }
            var left = lost[i] - 1
            var right = lost[i] + 1

            for(j in reserve.indices){
                if(lost[i] == reserve[j]){
                    check[j] = true
                    break
                }

                if(left == reserve[j] && !check[j]){
                    check[j] = true
                    answer++
                    break
                }
                if(right == reserve[j] && !check[j]){
                    check[j] = true
                    answer++
                    break
                }
            }
        }

        return answer
    }
}

fun main(){
    var n = 7
    var lost = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    var reserve = intArrayOf(1, 2, 3)

    var t = TraningClothes();
    t.solution(n, lost, reserve)
}