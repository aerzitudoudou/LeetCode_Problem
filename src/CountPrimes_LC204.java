import java.util.Arrays;

public class CountPrimes_LC204 {
    //sol2, from discussion session https://leetcode-cn.com/problems/count-primes/solution/ji-shu-zhi-shu-by-leetcode-solution/
    //O(nloglogn), O(n)
    public int countPrimes(int n) {
        if (n <= 1) return 0;
        int count = 0;
        //if index = i is a prime number
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 0; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = 2; j * i < n; j++) {
                    isPrime[i * j] = false;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }
}
