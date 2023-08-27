package custom_collection;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;

public class QueueExample {
    static Queue queue = new LinkedList();
    static final int MAX_SIZE = 5; // 최대 5까지만 저장되도록함

    public static void main(String[] args) {
        System.out.println("help를 입력하면 도움말을 볼 수 있습니다.");

        while (true) {
            System.out.println(">>");
            try {
                // 화면으로부터 라인단위로 입력받음
                Scanner s = new Scanner(System.in);
                String input = s.nextLine().trim();

                if ("".equals(input)) continue;

                if (input.equalsIgnoreCase("q")) {
                    System.exit(0);
                } else if (input.equalsIgnoreCase("help")) {
                    System.out.println("help - 도움말을 보여줍니다.");
                    System.out.println("q 또는 Q - 프로그램을 종료합니다.");
                    System.out.println("history - 최근에 입력한 명령어를 " + MAX_SIZE + "개 보여줍니다.");
                } else if (input.equalsIgnoreCase("history")) {
                    int i = 0;
                    save(input);

                    LinkedList tmp = (LinkedList) queue;
                    ListIterator it = tmp.listIterator();

                    while (it.hasNext())
                        System.out.println(++i + "." + it.hasNext());
                } else {
                    save(input);
                    System.out.println(input);
                } // if(input.equalsIgnoreCase("q) {
            } catch (Exception e) {
                System.out.println("입력 오류입니다.");
            }
        }
    }

    public static void save(String input) {
        if (!"".equals(input)) {
            queue.offer(input);
        }

        if (queue.size() > MAX_SIZE) {
            queue.remove();
        }
    }
}
