import java.util.*

fun main(){
    solution("z-+.^.")
}

/*
* 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.*/

/*
* ex
* ...!@BaT#*..y.abcdefghijklm
* 1. ...!@bat#*..y.abcdefghijklm
* 2. ...bat..y.abcdefghijklm
* 3. .bat.y.abcdefghijklm
* 4. bat.y.abcdefghijklm
* 5. bat.y.abcdefghijklm
* 6. bat.y.abcdefghij*/

fun solution(new_id: String): String {
    var answer: String = ""

    val string_length = new_id.length
    var string_temp = new_id

    //1단계
    string_temp = string_temp.toLowerCase()

    //2단계
    val phase_two = Regex("[^a-z0-9-_.]")
    /*
    ^ : 문자열 시작
    a-z : 알파벳 소문자
    0-9 : 숫자*/
    string_temp = phase_two.replace(string_temp, "")

    //3단계
    val phase_three = Regex("[.]{2,}")
    /*
    {2,} : 2번이상 반복
    * */
    string_temp = phase_three.replace(string_temp, ".")

    //4단계
    val phase_four = Regex("^[.]|[.]$")
    /* $ : 문자열의 끝*/
    string_temp = phase_four.replace(string_temp, "")

    //5단계
    if(string_temp == ""){
        string_temp += "a"
    }

    //6단계
    if(string_temp.length >= 16){
        string_temp = string_temp.slice(0..14)
        string_temp = phase_four.replace(string_temp, "")
    }

    //7단계
    if(string_temp.length <= 2){
        while (string_temp.length < 3){
            string_temp += string_temp[string_temp.lastIndex]
        }
    }

    answer = string_temp


    return answer
}