import java.util.*
var INF = 10000000
fun main() = with(System.`in`.bufferedReader()){
    var n = readLine().toInt() + 1

    var arr = Array(n) { Array(n) { 0 } }

    var size = readLine().toInt()

    for(i in 1 until n){
        for(j in 1 until n){
            if(i!=j && arr[i][j] ==0){
                arr[i][j] = INF
            }
        }
    }

    for(i in 0 until size){
        var st = StringTokenizer(readLine())
        while(st.hasMoreTokens()){
            var val1 = st.nextToken().toInt()
            var val2 = st.nextToken().toInt()
            var value = st.nextToken().toInt()

            if(arr[val1][val2] != 0){
                arr[val1][val2] = Math.min(arr[val1][val2], value)
            }
            else arr[val1][val2] = value
        }
    }
    floyd(arr)

    for(i in 1 until arr.size){
        for(j in 1 until arr.size){
            print("${arr[i][j]} ")
        }
        println()
    }
}

fun floyd(arr: Array<Array<Int>>){
    for(k in 1 until arr.size){
        for(i in 1 until arr.size){
            for(j in 1 until arr.size){
                arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j])
            }
        }
    }

    for(i in 1 until arr.size){
        for(j in 1 until arr.size){
            if(arr[i][j] == INF){
                arr[i][j] = 0
            }
        }
    }
}