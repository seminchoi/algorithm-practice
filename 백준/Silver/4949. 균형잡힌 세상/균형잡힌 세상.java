import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(true){
            Stack<Character> cs = new Stack<>();
            boolean result = true;
            s = br.readLine();
            if(s.equals(".")){
                break;
            }
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '(' || s.charAt(i) == '['){
                    cs.push(s.charAt(i));
                }
                else if(s.charAt(i) == ')' || s.charAt(i) == ']'){
                    if(cs.empty()){
                        result = false;
                        break;
                    }
                    else if(!(s.charAt(i) - cs.peek() == 1 || s.charAt(i) - cs.peek() == 2)){
                        result = false;
                        break;
                    }
                    else {
                        cs.pop();
                    }
                }
            }
            if(cs.empty() && result){
                System.out.println("yes");
            }
            else{
                System.out.println("no");
            }
        }
    }
}