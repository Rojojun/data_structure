package custom_collection;

public class StackExample {
    public static Stack back = new Stack();
    public static Stack forward = new Stack();

    public static void main(String[] args) {
        goUrl("1. 네이트");
        goUrl("2. 야후");
        goUrl("3. 다음");
        goUrl("4. 네이버");

        printStatus();

        goBack();
        System.out.println("뒤로 버튼 누르기");
        printStatus();

        goBack();
        System.out.println("뒤로 버튼 누르기");
        printStatus();

        goForward();
        System.out.println("앞으로 버튼 누르기");
        printStatus();

        goUrl("new.com");
        System.out.println("새로운 주소로 이동");
        printStatus();
    }

    public static void printStatus() {
        System.out.println("back : " + back);
        System.out.println("forward : " + forward);
        System.out.println("현재화면은 '" + back.peek() +"' 입니다.");
        System.out.println();
    }

    public static void goUrl(String url) {
        back.push(url);
        if(!forward.empty()) forward.clear();
    }

    public static void goForward() {
        if(!forward.empty()) back.push(forward.pop());
    }

    public static void goBack() {
        if(!back.empty()) forward.push(back.pop());
    }
}
