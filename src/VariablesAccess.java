import java.util.Map;

//static variable, instance variable, local variable, block variable
/*
In Java, when variable is declared as field (static or instance variable inside class), then initialization of that variable is optional.
When you declare any local/block variable, they didn’t get the default values. They must assigned some value before accessing it other wise compiler will throw an error.

* */
public class VariablesAccess {
    int a; //instance variable
    int b;
    static int i; //static variable
    public void test1(String s, String t){
        Map<Character, Integer> need, window; //local variable
        int o; //local variable
        //System.out.println(need);--> wrong
        System.out.println(a);
        //System.out.println(o);-> wrong
        if(true){
            int d;//block variable/local variable
            int e;
           // System.out.println(d);-> wrong
            //System.out.println(e);-> wrong
        }

    }
    public static void test2(){
        int f;//local variable
        System.out.println(i);
        //System.out.println(a); //non-static field cannot be referenced from static method
        //System.out.println(f);-> wrong
        Map<String, String> map;
        if(true){
            int g;
            int h;
           // System.out.println(g);-> wrong
            //System.out.println(h);-> wrong
        }

    }
}
