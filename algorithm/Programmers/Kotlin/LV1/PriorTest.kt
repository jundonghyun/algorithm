import java.util.*
import kotlin.math.max

/*
if문을 중복으로쓰니 나도 헷갈린다. 정답맞추고 인터넷 찾아보니
for문으로 단순하게 쓰는 방법도있는것 같다.
*/

class PriorTest {
    fun solution(answers: IntArray): IntArray {
        var answer = LinkedList<Int>()

        val one = intArrayOf(1,2,3,4,5)
        val two = intArrayOf(2,1,2,3,2,4,2,5)
        val three = intArrayOf(3,3,1,1,2,2,4,4,5,5)

        var oneCorrect = 0
        var twoCorrect = 0
        var threeCorrect = 0

        var maxCorrect = Int.MIN_VALUE

        for(i in 1..3){ //3번반복
            var idx = 0
            var correct = 0
            when(i){
                1 -> { //1번 수포자부터 비교
                    for(j in answers.indices){ //정답배열만큼 반복하고
                        if(idx == one.size){ //idx가 수포자의 패턴배열보다 크기가 커진다면 idx를 0으로 초기화하고
                            idx = 0

                            if(one[idx] == answers[j]){ //수포자0번째 배열과 정답배열을 비교
                                oneCorrect++
                            }
                        }
                        else{ //idx가 수포자패턴 배열보다 크지 않다면 정답배열과 바로 비교
                            if(one[idx] == answers[j]){
                                oneCorrect++
                            }
                        }
                        idx++
                    }
                    maxCorrect = max(oneCorrect, maxCorrect) //수포자1의 정답이 max값으로 변경
                                                             //수포자3까지 max값과 비교해서 3명중 가장 많이 맞춘값이 max값이됨
                }

                2-> {
                    for(j in answers.indices){
                        if(idx == two.size){
                            idx = 0

                            if(two[idx] == answers[j]){
                                twoCorrect++
                            }
                        }
                        else{
                            if(two[idx] == answers[j]){
                                twoCorrect++
                            }
                        }
                        idx++
                    }
                    maxCorrect = max(twoCorrect, maxCorrect)
                }

                3-> {
                    for(j in answers.indices){
                        if(idx == three.size){
                            idx = 0

                            if(three[idx] == answers[j]){
                                threeCorrect++
                            }
                        }
                        else{
                            if(three[idx] == answers[j]){
                                threeCorrect++
                            }
                        }
                        idx++
                    }
                    maxCorrect = max(threeCorrect, maxCorrect)
                }
            }
        }

        if(oneCorrect == maxCorrect && oneCorrect != 0){ //수포자1의 정답개수가 3명과 비교한 최대정답개수와 같다고 정답개수가 0이 아니라면
            answer.add(1)                                //수포자1을 list에 넣음
        }
        if(twoCorrect == maxCorrect && twoCorrect != 0){
            answer.add(2)
        }
        if(threeCorrect == maxCorrect && threeCorrect != 0){
            answer.add(3)
        }

        return answer.toIntArray()
    }
}

fun main(){
    val p = PriorTest()

    var answers = intArrayOf(1,2,3,4,5,4,3,2,1)
    p.solution(answers)
}