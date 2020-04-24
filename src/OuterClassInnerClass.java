public class OuterClassInnerClass {
    private static String msg = "Message";
    private static boolean isRich = false;

    //the behavior ipo() belongs to the whole class, but not any instance.it can only
    //operate fields that belong to the class, but not instances(static fields).
    public static void ipo(){
        isRich = true;
    }


    //why nested class?
    /*
    * 1. logically group classes that are only used in one place
    * 2. increase encapsulation: inner class can access outer class's private members
    * 3. more readable and maintainable code
    * */

    //prefer static nested class to inner class since it uses less memory
    public static class NestedStaticClass{
        /*only a nested class can be declared static
        * only static members of Outer class is directly accessible in nested static class
        *static内部类好处就是可以有non static和static两种method
        *缺点就是只能access外部类的static member
        *
        * */
        public void printMessage(){//static class里为什么可以有non-static method?
            System.out.println("message from nested static class: " + msg); //if msg is non-static, it cannot be acceess here
        }
        public static void printOut(){}

    }

    protected class InnerClass{
        /*
        * non-static 类正好相反 优点： 可以access外部类的static member & non-static member
        * 缺点： 内部只能有non-static method
        * */
        //Both static and non-static members of outer class are accessible in this inner class
        public void display(){
            System.out.println("Message from non-static nested class: " + msg);
        }

        //can't have static method or variable here. static method can only be declared in a static class or top-level class
        public OuterClassInnerClass getOuterClass(){
            return OuterClassInnerClass.this;
        }

    }
}


//mutiple top-level class in the same java file:
/*
* only one top-level class can be declared public in java file. the others have no access modifier
* (private, protected) nor static. Usually these are helper classes. The purpose of including multiple
* classes in one source file is to bundle related support functionality(internal data structures, support classes, etc)
* together with the main public class.
*
*
* */
//nested class 可以被declare 成public, 但是HelperClass 不行
class HelperClass{//这个HelperClass 可能不止是helper 仅仅OuterClassInnerClass一个class的，也可以帮助同一个package的别的class

    private int key;
    public HelperClass(int key){
        this.key = key;
    }
    public void testHelper(){
        //System.out.println(msg);
        /*
        * nested class可以access outer class 的private member(这里的msg), helper class不可以
        *
        * */
    }
}
