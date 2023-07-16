package _test;

import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d).*$");
        System.out.println(pattern.matcher("4q").matches());
    }
}
