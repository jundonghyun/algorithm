import java.util.Collections
import java.util.LinkedList
import java.util.Map
import java.util.Queue
import java.util.StringTokenizer

class MenuRenual {
    var map = HashMap<String, Int>()

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        var answer = arrayOf<String>()
        var temp = arrayListOf<String>()

        for(i in orders){
            var visited = BooleanArray(i.length)
            var result = ""

            for(j in course){
                combination(i, visited, 0, i.length, j, result) //모든 조합을 다 찾아야함
            }
        }

        var list = map.toList()
        list = list.sortedByDescending { //내림차순으로 맵 정렬
            it.second
        }

        for(j in course){
            var max = 2 //중복값이 2이상인 메뉴만 선정가능

            for(i in list){
                if(i.first.length == j && i.second >= max){ //내림차순이라 가장큰값이 앞에 있으므로
                                                            //최대값이 바로 나옴
                                                            //중복된 최대값이 있을수도 있어서 >= 사용
                    max = i.second
                    temp.add(i.first)
                }
            }
        }
        temp.sort() //알파벳순서로 정렬

        answer = temp.toTypedArray()

        return answer
    }

    private fun combination(split: String, visited: BooleanArray, start: Int, n: Int, r: Int,result: String) {
        var tmp = result
        if(r == 0){
            var arr = tmp.toCharArray() //배열의 각 원소에 저장된 문자열 또한 알파벳 오름차순으로 정렬되어야 합니다.
                                        //이 조건 맞추기위해 다시 배열로 변경

            arr.sort()
            tmp = String(arr)

            if(map.containsKey(tmp)){ //같은 문자열이 있다면 value + 1
                map[tmp] = map.getValue(tmp)+1
            }
            else{
                map[tmp] = 1 //처음 들어온 문자열이라면 맵에 저장
            }
            return
        }
        for(i in start until n){
            visited[i] = true
            tmp += split[i]
            combination(split, visited, i+1, n, r-1, tmp)
            tmp = result
            visited[i] = false
        }
    }
}

fun main(){
    var order = arrayOf("XYZ", "XWY", "WXA")
    var courses = intArrayOf(2,3,4)

    MenuRenual().solution(order, courses)
}