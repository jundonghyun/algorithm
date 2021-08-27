package com.example.kotlinpratice

import java.util.*

var check : Array<Boolean> = Array(100001){false}
var sc : Scanner = Scanner(System.`in`)

fun main(args : Array<String>){
    var start = sc.nextInt()
    var finish = sc.nextInt()

    sc.close()

    djikstra(start, finish)

}

fun djikstra(start : Int, finish : Int){
    var q : PriorityQueue<Point> = PriorityQueue()

    var answer = 0

    q.add(Point(start, 0))

    while(!q.isEmpty()){
        var p : Point = q.poll()
        var temp = p.current_pos

        if(check[temp] == true){
            continue
        }
        check[temp] = true

        if(p.current_pos == finish){
            answer = p.distance
            println(answer)
            return
        }
        if(temp * 2 <= finish + 1 && !check[temp * 2]){
            q.add(Point(temp * 2, p.distance))
        }
        if(temp + 1 <= finish + 1 && !check[temp + 1]){
            q.add(Point(temp + 1, p.distance + 1))
        }
        if(temp - 1 >= 0 && !check[temp - 1]){
            q.add(Point(temp - 1, p.distance + 1))
        }
    }

}

class Point : Comparable<Point>{
    var distance = 0
    var current_pos = 0

    constructor(c : Int, d : Int){
        this.current_pos = c
        this.distance = d
    }

    override fun compareTo(other: Point): Int {
        return distance - other.distance
    }

}