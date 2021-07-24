package com.example.kotlinpratice

class coloringbook {
    companion object {
        var temp: Int = 0
        var numberOfArea: Int = 0
        var maxSizeOfOneArea: Int = 0
        val nx = arrayOf(0, -1, 0, 1)
        val ny = arrayOf(-1, 0, 1, 0)

        fun solution() {
            val m = 6
            val n = 4

            val check = Array<IntArray>(m, { IntArray(n) })
            val picture =
                    arrayOf(
                            arrayOf(1, 1, 1, 0),
                            arrayOf(1, 2, 2, 0),
                            arrayOf(1, 0, 0, 1),
                            arrayOf(0, 0, 0, 1),
                            arrayOf(0, 0, 0, 3),
                            arrayOf(0, 0, 0, 3)
                    )

            for (i in 0..m - 1) {
                for (j in 0..n - 1) {
                    if (check[i][j] != 1 && picture[i][j] != 0) {
                        numberOfArea++
                        dfs(i, j, picture, check)
                    }
                    if (temp > maxSizeOfOneArea) {
                        maxSizeOfOneArea = temp
                    }
                    temp = 0
                }
            }
            var answer = Array(2, { 0 })
            answer[0] = numberOfArea
            answer[1] = maxSizeOfOneArea

            print("${answer[0]},${answer[1]}")
        }

        fun dfs(m: Int, n: Int, picture: Array<Array<Int>>, check: Array<IntArray>) {
            if (check[m][n] == 1) {
                return
            }

            check[m][n] = 1
            temp++

            for (i in 0..3) {
                var x = m + nx[i]
                var y = n + ny[i]

                if (x >= picture.size || y >= picture[0].size || x < 0 || y < 0) {
                    continue
                }
                if (picture[m][n] == picture[x][y] && check[x][y] == 0) {
                    dfs(x, y, picture, check)
                }
            }
        }
    }
}

fun main() {
    coloringbook.solution()
}
