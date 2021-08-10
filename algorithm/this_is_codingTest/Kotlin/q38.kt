import java.util.*

private const val INFINITE = 100001

fun main() = with(System.`in`.bufferedReader()){
    var st = StringTokenizer(readLine())

    var size = st.nextToken().toInt() + 1
    var n = st.nextToken().toInt()
    var arr = Array(size){Array(size){0} }
    var result = 0

    for(i in 1 until size){
        for(j in 1 until size){
            if(i == j){
                arr[i][j] = 0
            }
            else arr[i][j] = INFINITE
        }
    }

    for(i in 0 until n){
        st = StringTokenizer(readLine())
        var a = 0
        var b = 0

        while(st.hasMoreTokens()){
            a = st.nextToken().toInt()
            b = st.nextToken().toInt()
            arr[a][b] = 1
        }
    }

    for(k in 1 until size){
        for(i in 1 until size){
            for(j in 1 until size){
                arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]) //플로이드 워셜 방법사용
            }
        }
    }

    for(i in 1 until size){
        var cnt = 0
        for(j in 1 until size){
            if(arr[i][j] != INFINITE || arr[j][i] != INFINITE){ //학생들이 연결되어있다면
                cnt += 1
            }
        }
        if(cnt == size - 1) result += 1 //모든 학생들의 경우의 수를 비교가능하다면
    }
    println(result)
}


