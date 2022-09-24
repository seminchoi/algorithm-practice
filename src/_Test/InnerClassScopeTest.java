package _Test;

public class InnerClassScopeTest {
    int n = 1;
    class innerClass{
        int n = 2;
        void printN(){
            System.out.println("outer.n = " + InnerClassScopeTest.this.n);
            System.out.println("this.n = " + this.n);
        }
    }
}
