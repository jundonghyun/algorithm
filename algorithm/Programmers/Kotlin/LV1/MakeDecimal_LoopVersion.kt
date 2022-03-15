

class MakeDecimal_LoopVersion {
    fun solution(nums: IntArray): Int {
        var answer = 0

        for(i in nums.indices){
            for(j in i+1 until nums.size){
                for(k in j+1 until nums.size){
                    var temp = nums[i] + nums[j] + nums[k]
                    if(isPrime(temp)){
                        answer++
                    }
                }
            }
        }
        return answer
    }

    fun isPrime(num: Int): Boolean{
        for (i in 2 until num) {
            if (num % i === 0) return false
        }
        return true
    }
}

fun main(){
    var m = MakeDecimal_LoopVersion()

    var nums = intArrayOf(1,2,3,4)
    m.solution(nums)
}