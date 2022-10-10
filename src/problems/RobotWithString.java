package problems;

import java.util.ArrayDeque;
import java.util.Deque;

//6202
//给定输入序列和一个栈，求输出的最小字典序序列
//思想：首先从后往前遍历序列，找到每个元素之后最小的元素（尾部需要加上一个最大的哨兵元素）
//遍历这个序列，将元素放入栈中。比较栈顶元素与后面元素的最小值，如果大于，则不出栈，等待后续更小元素入栈
//如果小于或等于，将栈顶元素出栈
//设当前栈顶元素为c，遍历到的元素为n, n之后的最小元素为m: 如果c <= m, 说明c比之后的最小元素在字典序中更靠前，应优先出栈
//如果c > m, 后续的最小元素应优先出栈，应继续等待后续元素进栈
public class RobotWithString {
    public String robotWithString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        char[] ch = new char[n + 1];
        Deque<Character> dk = new ArrayDeque();
        ch[n] = 'z' + 1;
        for(int i = n - 1; i >= 0; i--) {
            ch[i] = (char)('a' + Math.min(ch[i + 1] - 'a', s.charAt(i) - 'a'));
        }
        for(int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            dk.push(c);
            while(!dk.isEmpty() && dk.peek() <= ch[i + 1]) sb.append(dk.pop());
        }
        return sb.toString();
    }
}
