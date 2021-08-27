import BaekJoon_1238.Companion.distance
import BaekJoon_1238.Companion.check
import BaekJoon_1238.Companion.forward
import BaekJoon_1238.Companion.reverse
import BaekJoon_1238.Companion.list
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class BaekJoon_1238 {
    companion object{
        lateinit var check : Array<Boolean>
        lateinit var distance : Array<Int>
        lateinit var forward : Array<Int>
        lateinit var reverse : Array<Int>
        lateinit var list : Array<ArrayList<Node1>>
    }
}

class Node1 : Comparable<Node1>{
    var index : Int = 0
    var weight : Int = 0

    constructor(i:Int, w:Int){
        this.index = i;
        this.weight = w;
    }

    override fun compareTo(other: Node1): Int {
        return weight - other.weight
    }
}

fun djikstra(n : Int, finish: Int){
    for(i in 1..n){
        Arrays.fill(distance, Int.MAX_VALUE)
        var pq = PriorityQueue<Node1>()

        pq.add(Node1(i, 0))
        distance[i] = 0

        while(!pq.isEmpty()){
            var temp = pq.poll()
            var now = temp.index

            if(check[temp.index] == true) continue
            check[temp.index] = true

            for(n in list[now]){
                if(distance[n.index] > distance[now] + n.weight){
                    distance[n.index] = distance[now] + n.weight
                    pq.add(Node1(n.index, distance[n.index]))
                }
            }
        }
        Arrays.fill(check, false)
        forward[i] = distance[finish]
    }
}

fun reverse_djikstra(finish : Int){
    Arrays.fill(reverse, Int.MAX_VALUE)
    var pq = PriorityQueue<Node1>()

    pq.add(Node1(finish, 0))
    reverse[finish] = 0

    while(!pq.isEmpty()){
        var temp = pq.poll()
        var now = temp.index

        if(check[now] == true) continue
        check[now] = true

        for(temp in list[now]){
            if(reverse[temp.index] > reverse[now] + temp.weight){
                reverse[temp.index] = reverse[now] + temp.weight
                pq.add(Node1(temp.index, reverse[temp.index]))
            }
        }
    }
}

fun main(){
    var br = BufferedReader(InputStreamReader(System.`in`))

    var(n, m, point) = br.readLine().split(' ').map { it.toInt() }

    check = Array(n+1){false}
    distance = Array(n+1){Int.MAX_VALUE}
    forward = Array(n+1){0}
    reverse = Array(n+1){0}
    list = Array(n+1){ArrayList<Node1>()}

    for(i in 0..n){
        list[i] = ArrayList()
    }

    for(i in 0 until m){
        var(index,finish,weight) = br.readLine().split(' ').map { it.toInt() }
        list[index].add(Node1(finish, weight))
    }

    djikstra(n, point)
    reverse_djikstra(point)

    var result : Int = forward[1] + reverse[1]

    for(i in 2 until n+1){
        result = Math.max(result, forward[i] + reverse[i])
    }

    println(result)
}