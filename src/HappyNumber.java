public class HappyNumber {
    //o(n) o(1)
    public boolean isHappy(int n) {
        int slow = n, fast = nextNumber(nextNumber(n));
        while(fast != 1 && slow != fast){
            slow = nextNumber(slow);
            fast = nextNumber(nextNumber(fast));
        }
        return fast == 1;

    }

    private int nextNumber(int n){
        int sum = 0;
        while(n != 0){
            int d = n % 10;
            sum += d * d;
            n = n / 10;
        }
        return sum;
    }
}
