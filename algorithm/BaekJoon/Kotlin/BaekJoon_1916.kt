import BaekJoon_1916.Companion.check
import BaekJoon_1916.Companion.distance
import BaekJoon_1916.Companion.list
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class BaekJoon_1916 {
    companion object {
        lateinit var distance: Array<Int>
        lateinit var check: Array<Boolean>
        lateinit var list : Array<ArrayList<Node>>
    }
}

class Node : Comparable<Node>{
    var index : Int = 0
    var weight : Int = 0

    constructor(i : Int, w : Int){
        this.index = i
        this.weight = w
    }


    override fun compareTo(other: Node): Int {
        return this.weight - other.weight
    }

}

fun main(){
    var br = BufferedReader(InputStreamReader(System.`in`))
    var n = br.readLine().toInt()
    var m = br.readLine().toInt()

    list = Array(n+1){ArrayList<Node>()}

    for(i in 0..n){
        list[i] = ArrayList()
    }

    check = Array(n+1){false}
    distance = Array(n+1){Integer.MAX_VALUE}

    for(i in 0 until m){
        var(index,finish,weight) = br.readLine().split(' ').map { it.toInt() }
        list[index].add(Node(finish, weight))
    }

    var(start, finish) = br.readLine().split(' ').map { it.toInt() }

    dijk(start)

    println(distance[finish])

}

fun dijk(start : Int){
    var pq = PriorityQueue<Node>()

    pq.add(Node(start, 0))
    distance[start] = 0

    while(!pq.isEmpty()){
        var n : Node = pq.poll()
        var now : Int = n.index

        if(check[now] == true) continue
        check[now] = true

        for(n in list[now]){
            if(distance[n.index] > distance[now] + n.weight){
                distance[n.index] = distance[now] + n.weight
                pq.add(Node(n.index, distance[n.index]))
            }
        }
    }
}