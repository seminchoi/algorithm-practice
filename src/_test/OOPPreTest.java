package _test;

//외부 클래스는 자동으로 static 이 선언되어 있습니다.
public class OOPPreTest {
    //하나의 코드로 제출하기 위해 inner class 로 선언하였습니다.
    public static class Hello {
        public void sayHello(){
            System.out.println("영어로 인사");
        }
    }

    public static class HelloKorean extends Hello{
        @Override
        public void sayHello() {
            System.out.println("한국어로 인사");
        }
    }

    public static class HelloChinese extends Hello{
        @Override
        public void sayHello() {
            System.out.println("중국어로 인사");
        }
    }

    public static void main(String[] args) {
        // koHello 와 chHello 에서는 자식 클래스를 Hello 클래스로 업캐스팅합니다.
        Hello enHello = new Hello();
        Hello koHello = new HelloKorean();
        Hello chHello = new HelloChinese();

        enHello.sayHello();
        koHello.sayHello();
        chHello.sayHello();
    }
}
