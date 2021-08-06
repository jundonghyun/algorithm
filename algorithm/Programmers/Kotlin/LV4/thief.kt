fun main() {
    var money = arrayOf(91, 90, 5, 7, 5, 7)
    println(solution(money))
}

fun solution(money: Array<Int>) : Int{

    var answer = 0
    var length = money.size
    var dp = IntArray(length-1) { 0 }
    var dp2 = IntArray(length) { 0 }

    dp[0] = money[0]
    dp[1] = money[0]
    dp2[0] = 0
    dp2[1] = money[1]

    for(i in 2 until length-1){
        dp[i] = Math.max(dp[i-2] + money[i], dp[i-1])
    }
    for(i in 2 until length){
        dp2[i] = Math.max(dp2[i-2] + money[i], dp2[i-1])
    }

    return Math.max(dp[length-2], dp2[length-1])

}