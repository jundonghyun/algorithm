import java.util.*
import kotlin.collections.ArrayList

lateinit var list: Array<ArrayList<node>>
lateinit var check: Array<Boolean>
lateinit var distance: Array<Int>
lateinit var a_distance: Array<Int>
lateinit var b_distance: Array<Int>

fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
    var answer: Int = Integer.MAX_VALUE

    list = Array(n + 1) { ArrayList<node>() }
    check = Array(n + 1) { false }
    distance = Array(n + 1) { Int.MAX_VALUE }
    a_distance = Array(n + 1) { Int.MAX_VALUE }
    b_distance = Array(n + 1) { Int.MAX_VALUE }

    for (i in 0 until n + 1) {
        list[i] = ArrayList()
    }

    for (i in 0 until fares.size) {
        list[fares[i][0]].add(node(fares[i][1], fares[i][2]))
        list[fares[i][1]].add(node(fares[i][0], fares[i][2]))
    }

    distance = djikstra(s, distance)
    a_distance = djikstra(a, a_distance)
    b_distance = djikstra(b, b_distance)

    for (i in 1..n) {
        answer = Math.min(answer, distance[i] + a_distance[i] + b_distance[i])
    }

    return answer
}

fun djikstra(start: Int, distance: Array<Int>): Array<Int> {
    var q = PriorityQueue<node>()

    Arrays.fill(distance, Int.MAX_VALUE)

    q.add(node(start, 0))
    distance[start] = 0

    while (!q.isEmpty()) {
        var n: node = q.poll()
        var now = n.index

        if (check[now] == true) continue
        check[now] = true

        for (t in list[now]) {
            if (distance[t.index] > distance[now] + t.weight) {
                distance[t.index] = distance[now] + t.weight
                q.add(node(t.index, distance[t.index]))
            }
        }
    }
    Arrays.fill(check, false)

    return distance
}

class node : Comparable<node> {

    var index = 0
    var weight = 0

    constructor(i: Int, w: Int) {
        this.index = i
        this.weight = w
    }

    override fun compareTo(other: node): Int {
        return weight - other.weight
    }
}
