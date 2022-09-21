package problems;

import java.util.ArrayList;
import java.util.List;

public class deepCopyTest {

    public void deepCopyTest(){
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> alist = new ArrayList<>();
        int a=1;
        list.add(a);
        alist.add(list);
        System.out.println(list);
        System.out.println(alist);
        System.out.println("**************************");
        a=2;
        list.add(a);
        alist.add(list);
        System.out.println(list);
        System.out.println(alist);
    }
}
