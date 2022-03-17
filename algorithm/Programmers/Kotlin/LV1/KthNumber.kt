import java.util.*

class KthNumber {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        var answer = LinkedList<Int>()

        for(i in commands.indices){

            var start = commands[i][0]
            var finish = commands[i][1]
            var target = commands[i][2]

            var arr = Array<Int>((finish - start) + 1){ 0 }


            var idx = 0

            for(j in start-1 until finish){
                arr[idx] = array[j]
                idx++
            }
            Arrays.sort(arr)

            answer.add(arr[target-1])
        }

        return answer.toIntArray()
    }
}

fun main(){
    var k = KthNumber()
    var array = intArrayOf(1, 5, 2, 6, 3, 7, 4)
    var commands = arrayOf(intArrayOf(2, 5, 3), intArrayOf(4, 4, 1), intArrayOf(1, 7, 3))
    k.solution(array, commands)
}