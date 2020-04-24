public class OuterClassInnerClassTest {
    public static void main(String args[]){
        //create instance of nested static class
        OuterClassInnerClass.NestedStaticClass printer = new OuterClassInnerClass.NestedStaticClass();
        //call non static method of nested static class
        printer.printMessage();;
        OuterClassInnerClass.NestedStaticClass.printOut();

        /*
        * In order to create instance of Inner class we need an Outer class instance.
        * Let us create Outer class instance for creating non-static nested class.
        *
        *
        *
        * */
        OuterClassInnerClass outer = new OuterClassInnerClass();
        OuterClassInnerClass.InnerClass inner = outer.new InnerClass();
        //calling non-static method of Inner class
        inner.display();

        //we can also combine above steps in one step to create instance of Inner Class
        OuterClassInnerClass.InnerClass innerObject = new OuterClassInnerClass().new InnerClass();

        //similarly we can now call Inner class method
        innerObject.display();


    }
}

