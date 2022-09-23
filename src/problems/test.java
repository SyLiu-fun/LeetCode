package problems;

import java.util.Random;

//问题测试类
public class test {
    public static void main(String[] args) {
        LinkedListSimulator lls = new LinkedListSimulator();
        lls.addAtHead(1);
        lls.addAtTail(3);
        lls.addAtIndex(1, 2);
        lls.get(1);
        lls.deleteAtIndex(1);
        lls.get(1);
    }
}
