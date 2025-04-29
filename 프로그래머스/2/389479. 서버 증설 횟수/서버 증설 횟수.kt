import java.util.*

class Solution {
   
    fun solution(players: IntArray, m: Int, k: Int): Int {
        // 증설 상태를 기록하는 자료구조 생성
        // 증설이 끝나는 시간을 기록하는 큐 생성
        // 증설 여부를 계산하고 증설한 뒤 카운트 증가시키기
        var count = 0
        val list = LinkedList<Int>()
        
        for(time in players.indices) {
            while(list.peek() == time) {
                list.poll()
            }
            
            val playerNumber = players[time]
            val requiredServerCount = playerNumber / m
            
            while(list.size < requiredServerCount) {
                list.add(time + k)
                count++
            }
            
        }

        return count
    }

}