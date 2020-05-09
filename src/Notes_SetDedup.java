import java.util.Arrays;
import java.util.List;

public class Notes_SetDedup {

    public void notes_setDeDup(){
        Integer[] a = new Integer[]{1, 2};
        Integer[] b = new Integer[]{1, 2};
        //Integer[] hashcode 不一样
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());


//        Set<Integer[]> set = new HashSet<>();
//        set.add(a);


        //Integer array 转化为List<Integer> 以后， hashcode and equals are both the SAME
        List<Integer> aList = Arrays.asList(a);
        List<Integer> bList = Arrays.asList(b);

        System.out.println(aList.hashCode());
        System.out.println(bList.hashCode());

        System.out.println(aList.equals(bList));

        //int[] hashcode 不一样 Arrays.asList(int[]) compiler 会默认返回List<int[]> 类型而不是List<Integer> list
        int[] c = new int[]{1, 2};
        int[] d = new int[]{1, 2};
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());

        //String 只要内容一样，hashcode 就是一样的
        String s1 = "lalala";
        String s2 = "lalala";
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());



    }
}
