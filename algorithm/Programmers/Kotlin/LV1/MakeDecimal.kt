public class MakeDecimal {
    var result: Int = 0
    var temp: Int = 0
    fun main(){

        var nums = intArrayOf(1,2,3,4)

        var result = solution(nums)
        println(result)
    }

    fun solution(nums: IntArray): Int {
        var answer = -1

        var visit = BooleanArray(nums.size){false}
        var count = 0
        var idx = 0

        answer = dfs(nums, visit, count, idx)

        return answer
    }

    fun dfs(nums: IntArray, visit: BooleanArray, c: Int, idx: Int): Int{
        var count = c
        var index = idx

        if(count == 3){
            if(isPrime(temp)){
                result++
            }
            return -1
        }

        while(index != nums.size){
            if(!visit[index]){ //방문하지 않았다면
                visit[index] = true
                count++
                temp += nums[index]
                index++
                dfs(nums, visit, count, index)
                count--;
                index--
                visit[index] = false
                temp -= nums[index]
                index++
            }
        }
        return result
    }

    fun isPrime(num: Int): Boolean{
        for (i in 2 until num) {
            if (num % i === 0) return false
        }
        return true
    }

}

fun main(){
    var t = MakeDecimal()

    t.main()
}


