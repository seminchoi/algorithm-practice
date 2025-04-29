import java.util.*

val dx = arrayOf(0, 1, 0, -1)
val dy = arrayOf(1, 0, -1, 0)
var input1: ArrayList<Int> = ArrayList()
var dp: Array<IntArray> = arrayOf()
var map: Array<IntArray> = arrayOf()
var width = 0
var height = 0

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    readLine().split(' ').forEach { input1.add(it.toInt()) };
    width = input1[1]
    height = input1[0]

    map = Array(input1[0]) { IntArray(input1[1]) };
    dp = Array(input1[0]) { IntArray(input1[1]) { -1 } };

    for (i in 0 until input1[0]) {
        map[i] = readLine().split(' ').map { it.toInt() }.toIntArray();
    }

    dfs(0, 0)

    println(dp[0][0])

}

fun dfs(x: Int, y: Int): Int {
    if (x == width - 1 && y == height - 1) return 1
    if(dp[y][x] != -1) return dp[y][x]

    dp[y][x] = 0
    
    for (i in dx.indices) {
        val nextX = x + dx[i]
        val nextY = y + dy[i]
        if (nextX >= 0 && nextX < input1[1] && nextY >= 0 && nextY < input1[0]) {
            if (map[y][x] > map[nextY][nextX]) {
                dp[y][x] += dfs(nextX, nextY)
            }
        }
    }
    return dp[y][x]
}