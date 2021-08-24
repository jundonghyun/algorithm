import java.util.*
import kotlin.collections.ArrayList

fun main() = with(System.`in`.bufferedReader()){
    var start : pos
    var end : pos
    var Coordinate : Array<pos>
    var check : Array<Boolean>
    var times : Array<Float>

    var st = StringTokenizer(readLine())
    start = pos(st.nextToken().toFloat(), st.nextToken().toFloat())
    st = StringTokenizer(readLine())
    end = pos(st.nextToken().toFloat(), st.nextToken().toFloat())
    st = StringTokenizer(readLine())
    var n = st.nextToken().toInt()

    Coordinate = Array<pos>(n+2){pos(0.0f,0.0f)}
    check = Array<Boolean>(n+2){false}
    times = Array<Float>(n+2){0.0f}
    var adjList = Array(n+2){ArrayList<Edge>()}

    for (i in 0 until n + 2) {
        adjList[i] = ArrayList()
    }

    Coordinate[0] = start
    Coordinate[n+1] = end

    for(i in 1 until n+1){
        st = StringTokenizer(readLine())
        var x : Float = st.nextToken().toFloat()
        var y : Float = st.nextToken().toFloat()
        Coordinate[i] = pos(x,y)
    }

    for(i in 1 until n+2){
        val dis = Math.sqrt(
            Math.pow((Coordinate[0].x - Coordinate[i].x).toDouble(), 2.0) +
                    Math.pow((Coordinate[0].y - Coordinate[i].y).toDouble(), 2.0)
        ).toFloat()
        adjList[0].add(Edge(i, (dis / 5.0).toFloat()))
    }

    for(i in 1 until n+2){
        for(j in 0 until n+2){
            var dis = Math.sqrt(
                Math.pow((Coordinate[i].x - Coordinate[j].x).toDouble(), 2.0) +
                        Math.pow((Coordinate[i].y - Coordinate[j].y).toDouble(), 2.0)
            ).toFloat()
            adjList[i].add(Edge(j,Math.min(dis / 5.0, Math.abs(dis-50) / 5.0 + 2).toFloat()))
        }
    }

    djikstra(0, times, check, adjList);

    println(times[n+1])

}

fun djikstra(start: Int, times: Array<Float>, check: Array<Boolean>, adjList: Array<ArrayList<Edge>>){
    var pq = PriorityQueue<Edge>()
    Arrays.fill(times, Integer.MAX_VALUE.toFloat())

    pq.add(Edge(start, 0.0f))
    times[start] = 0.0f

    while(!pq.isEmpty()){
        var edge : Edge = pq.poll()
        var destination = edge.destination

        if(check[destination]) continue
        else check[destination] = true

        for(next in adjList[destination]){
            if(times[next.destination] >= times[destination] + next.time){
                times[next.destination] = times[destination] + next.time
                pq.add(Edge(next.destination, times[next.destination]))
            }
        }
    }
}

class pos(){
    var x : Float = 0.0f
    var y : Float = 0.0f

    constructor(x : Float, y : Float) : this(){
        this.x = x;
        this.y = y;
    }
}

class Edge : Comparable<Edge>{
    var destination : Int = 0
    var time : Float = 0.0f

    constructor(destination : Int, time : Float){
        this.destination = destination;
        this.time = time;
    }

    override fun compareTo(other: Edge): Int =
        when{
            this.time > other.time -> 1
            this.time < other.time -> -1
            else -> 0
        }
}