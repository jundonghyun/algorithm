import java.util.LinkedList

class DevelopFunction {

    /*
    문제풀이 방법
    1.  배열을 뒤에서부터 조사해야함 이유는 앞에서부터 조사하게되면
        인덱스가 0일때도 처리해줘야하고 작업진도가 100인것이 앞에서부터 확인하면
        작업이 100인 뒷부분과 앞부분을 따로 확인해야 하므로 굉장이 복잡해짐

    2.  배열을 뒤에서 부터 확인하면서 현재인덱스의 값이 100보다 크다면 resutl++를 해주고 visit에 true
        인덱스를 --해서 0번째까지 확인함 이때 내 앞에있는것이 100보다 작다면 작업이 배포될수 없으므로
        이 인덱스 이후부터 다시 방문한것도 방문하지 않은것으로 처리하고 result값도 0으로 바꿔야함

    3.  만약에 현재인덱스 값이 100이상이고 그 앞에 아무것도 없다면(인덱스가 0일때)는 result값을
        answer에 넣어주고 종료조건인 count값에 result값을 더해줌

    4.  2번과 3번의 과정을 반복하면 결국에는 count값이 작업의 개수과 같으므로 프로그램이 종료됨
    */

    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        var answer = mutableListOf<Int>()

        var count = 0
        var day = 0

        var progressesTemp = progresses
        var speedsTemp = speeds
        var visit = BooleanArray(progresses.size){false}

        var index = progresses.size - 1

        while(count != progresses.size){
            var result = 0

            //작업 진도더하기
            for(i in progresses.indices){
                progressesTemp[i] += speedsTemp[i]
            }

            for(i in index downTo 0){
                if(progressesTemp[i] >= 100 && !visit[i]){
                    result++
                    visit[i] = true

                    var idx = i-1

                    while(idx != -1 && !visit[idx]){
                        if(progressesTemp[idx] < 100){
                            for(j in idx until progressesTemp.size){
                                visit[j] = false
                            }
                            result = 0
                            break
                        }
                        else{
                            result++
                            visit[idx] = true
                            idx--
                        }
                    }
                }
            }

            if(result != 0){
                answer.add(result)
                count+=result
            }

            day++
        }

        return answer.toIntArray()
    }
}

fun main(){
    var developFunction = DevelopFunction()
    var progresses = intArrayOf(93, 30, 55)
    var speeds = intArrayOf(1, 30, 5)
    developFunction.solution(progresses, speeds)

}