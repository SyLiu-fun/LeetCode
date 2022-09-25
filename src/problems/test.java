package problems;

import java.util.Random;

//问题测试类
public class test {
    public static void main(String[] args) {
        Game g = new Game();
        g.ballGame(6, new String[]{"....", ".EE.", "O.E.", "...."});
        System.out.println();
    }
}
