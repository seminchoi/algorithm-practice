import java.util.LinkedList

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    // 4와 7을 사용하는 이진수라고 이해할 수 있다.
    // 단, 0, 00 같은 숫자가 포함되므로 각 자리수를 계산할때 1을 빼준다.
    var num = readLine().toInt()

    val list = LinkedList<Char>()
    while (num > 0) {
        num--
        val c = if(num % 2 == 0) '4' else '7'
        num /= 2
        list.addFirst(c)
    }

    println(list.joinToString(""));
}