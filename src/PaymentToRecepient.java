import java.util.Arrays;


// At Gusto, we often have to make a number of payments to different recipients from a single pool of money. These payments need to follow many different rules that seem simple individually, but can become complex when considered together. This problem below doesn't reflect any specific real-world set of rules, but it's fairly representative of the types of problems we need to solve.
// Given an amount of money to distribute, a list of recipients and how much money each is owed, you should return the list of recipients and how much each would be paid after following the business logic below:
// 1. no recipient is paid more than they are owed
// 2. the amount is divided as evenly as possible between the recipients
// amount to distribute: 100
//case1
// recipients owed: p1 = 10000 p2 = 100 p3 = 50 p4 = 30 p5 = 10
// recipients paid: p1 = 23 p2 = 23 p3 = 22 p4 = 22 p5 = 10
//case2
// recipients owed: p1 = 50 p2 = 20 p3 = 20 p4 = 20 p5 = 60
// recipients paid: p1 = 20 p2 = 20 p3 = 20 p4 = 20 p5 = 20
//case3
// recipients owed: p1 = 50 p2 = 10 p3 = 30 p4 = 10 p5 = 60
// recipients paid: p1 = 27 p2 = 10 p3 = 27 p4 = 10 p5 = 26


class PaymentToRecepient {
    public static void main(String[] args) {
//        Person p1 = new Person(50, "a");
//        Person p2 = new Person(10, "b");
//        Person p3 = new Person(30, "c");
//        Person p4 = new Person(10, "d");
//        Person p5 = new Person(60, "e");
//        Person[] persons = new Person[5];
//        persons[0] = p1;
//        persons[1] = p2;
//        persons[2] = p3;
//        persons[3] = p4;
//        persons[4] = p5;

        Person p1 = new Person(10000, "a");
        Person p2 = new Person(100, "b");
        Person p3 = new Person(50, "c");
        Person p4 = new Person(30, "d");
        Person p5 = new Person(10, "e");
        Person[] persons = new Person[5];
        persons[0] = p1;
        persons[1] = p2;
        persons[2] = p3;
        persons[3] = p4;
        persons[4] = p5;


        Person[] res = pay(persons, 100);
        for (int i = 0; i < res.length; i++) {
            System.out.println(" Name : " + res[i].name + " got paid : " + res[i].paid);
        }
    }

    public static class Person {
        int owed;
        String name;
        int paid;

        public Person(int owed, String name) {
            this.owed = owed;
            this.name = name;
        }

        public void setPaid(int paid) {
            this.paid = paid;
        }


    }
    /*Comparator 的第一种写法： static class*/

//    static class NumberComparator implements Comparator<Person> {
//        @Override
//        public int compare(Person p1, Person p2) {
//            return Integer.valueOf(p1.owed).compareTo(Integer.valueOf(p2.owed));
//        }
//    }
//
//    static class StringComparator implements Comparator<Person> {
//        @Override
//        public int compare(Person p1, Person p2) {
//            return p1.name.compareTo(p2.name);
//        }
//    }





    /*
    *
static <T> void	sort(T[] a, Comparator<? super T> c)
Sorts the specified array of objects according to the order induced by the specified comparator.
static <T> void	sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c)
Sorts the specified range of the specified array of objects according to the order induced by the specified comparator.
    *
    *
    *
    *
    * */

    public static Person[] pay(Person[] persons, int amount) {
        /*comparator 的第二种写法： lamda
        * */
//        Arrays.sort(persons, Comparator.comparingInt(p -> p.owed));
        Arrays.sort(persons, (p1, p2) -> Integer.valueOf(p1.owed).compareTo(p2.owed));
        int moneyLeft = amount;
        int size = persons.length;
        int i;
        //按照owed多少排序，对于比平均数小于等于的owed, 给足owed
        for (i = 0; i < size; i++) {
            if (persons[i].owed <= moneyLeft / (size - i)) {
                persons[i].setPaid(persons[i].owed);
                moneyLeft -= persons[i].owed;
            } else {
                break;
            }
        }

        //按照字母序排序，对于比平均数大的owed, 向上取整  :  n + (k - 1) / k
        if (i < size) {
//            Arrays.sort(persons, i, persons.length - 1, Comparator.comparing(p -> p.name));
            Arrays.sort(persons, i , persons.length - 1, (p1, p2) -> p1.name.compareTo(p2.name));
        }
        while (i < size) {
            int ceilPay = (moneyLeft + size - i - 1) / (size - i);
            persons[i].setPaid(ceilPay);
            moneyLeft -= ceilPay;
            i++;

        }

        return persons;

    }
//    public static List<Person> pay(Person[] persons, int amount){
//        int cur = amount;
//        int evenAmount = amount / persons.length;
//        List<Person> res = new ArrayList();
//        Set<Person> set = new HashSet<>();
//        for(int i = 0; i < persons.length; i++){
//            int curOwed = persons[i].owed;
//            if(curOwed <= evenAmount){
//                persons[i].setPaid(curOwed);
//                cur -= curOwed;
//                set.add(persons[i]);
//            }
//        }
//        int numberOfPersonNotPaid = persons.length - set.size();
//        int tmp = cur / numberOfPersonNotPaid;
//        for(int i = 0; i < persons.length; i++){
//            if(!set.contains(persons[i])){
//                persons[i].setPaid(tmp);
//                set.add(persons[i]);
//            }
//        }
//        res.addAll(set);
////todo: add alphabetical corner case
//        return res;
//    }


}

