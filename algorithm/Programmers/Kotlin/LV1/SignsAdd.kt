fun main(){

    var absolutes = intArrayOf(1,2,3)
    var signs = booleanArrayOf(false,false,true)

    solution(absolutes, signs)

}

fun solution(absolutes: IntArray, signs: BooleanArray): Int {
    var answer: Int = 0

    for(i in absolutes.indices){
        if(signs[i]){
            answer += absolutes[i]
        }
        else{
            answer -= absolutes[i]
        }
    }


    return answer
}