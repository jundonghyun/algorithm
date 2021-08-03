
fun main(){
    var m = 4;
    var n = 3;

    var puddles = arrayOf(intArrayOf(2, 2)) //2차원배열에 첫번째 값으로 2,2넣음


    println(solution(m,n,puddles))
}

fun solution(m:Int, n:Int, puddles: Array<IntArray>):Int{
    var answer = 0
    var arr = Array(n) { Array(m) { 0 } } //2차원배열의 크기를 n,m으로 만든후 0으로 초기화

    arr[0][0] = 1

    for(i in puddles.indices){ //puddles배열의 최소 인덱스와 최대 인덱스 만큼 i를 반복
        arr[puddles[i][1]-1][puddles[i][0]-1] = 9
    }

    for(i in 0 until n){
        for(j in 0 until m){
            if(arr[i][j] == 9){
                arr[i][j] = 0
                continue
            }
            else{
                if(i != 0){
                    arr[i][j] += arr[i-1][j] % 1000000007
                }
                if(j != 0){
                    arr[i][j] += arr[i][j-1] % 1000000007
                }
            }
        }
    }
    return arr[n-1][m-1] % 1000000007
}