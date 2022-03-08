package Programmers.JAVA.LV2

fun main(args: Array<String>){

    val id_list = arrayOf("muzi", "frodo", "apeach", "neo")
    val report = arrayOf("muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi")
    val k = 3

    solution(id_list, report, k)
}

fun solution(id_list: Array<String>, report: Array<String>, k: Int): Array<Int> {
    var answer = Array<Int>(id_list.size, {0})

    var count = mutableListOf<Pair<String, Int>>() //신고자의 이름와 배열에서 사용할 인덱스를 매칭하기 위해 Pair사용

    var idx = 0

    for(i in id_list){
        count.add(Pair(i, idx)) //idx값을 1씩늘리면서 인덱스를 생성함
        idx++;
    }

    var list = mutableListOf<Report>() //Report클래스를 리스트형태로 받음(신고자가 여러명이 있기 때문)


    for(i in id_list){
        var result = mutableListOf<String>()
        list.add(Report(i, 0, result)) ///id_list는 사람들이름이 담겨있으므로 이 배열을 이용해서 list의 이름을 만들어줌
    }

    for(i in report){
        val temp = i.split(" ") //report배열에는 신고자, 신고당한사람 형태로 들어있으므로 split으로 앞 뒤를 나눔

        for(j in list){
            if(j.name == temp[1]){ //현재 list의 이름과 신고당한사람의 이름이 같다면
                if(j.result.contains(temp[0])){ //중복으로 신고되었는지 검사
                    continue;
                }
                j.count++; //신고당한사람의 count를 늘려주고
                j.result.add(temp[0]) //신고한 사람의 이름을 신고당한 사람의 list안에 넣어줌
                break;
            }
        }
    }

    for(i in list){
        if(i.count >= k){ //신고당한사람의 count가 k보다 크거나 같다면
            while(!i.result.isEmpty()){ //신고한사람의 이름이 list에 있을것이므로 list가 빌때까지 while문 동작
                var name = i.result.removeAt(0) //list에서 하나씩 빼서 신고자의 이름을 가져옴
                for(j in count){
                    if(j.first == name){ //신고한 사람의 이름이 Pair로 만들어둔 count변수와 첫번째(이름)이 같다면
                        answer[j.second]++ //두번째(index)가 answer의 인덱스로 활용할 수 있으므로 answer값을+1 해줌
                        break
                    }
                }
            }
        }
    }

    return answer
}

class Report constructor(name: String, count: Int, result: MutableList<String>){
    val name: String
    var count: Int
    var result = mutableListOf<String>()

    init {
        this.name = name
        this.count = count
        this.result = result
    }
}