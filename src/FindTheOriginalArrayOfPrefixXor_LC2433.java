public class FindTheOriginalArrayOfPrefixXor_LC2433 {
    /*


    xor: x^0 = x, x^x = 0
  x^y = z => x^z = y => y^z = x
  proof:    x^y = z
           x^x^y = x^z
            0^y = x^z
            y=x^z
        e.g.
        i = 1:
        pref    5,2,0,3,1
        res     5,?
        5^? = 2 => ? = 5 ^ 2 = 7 = pref[0] ^ pref[1]

        i = 2:
            5,2,0,3,1
            5,7,?
        5^7^?=0 => ? = 0^5^7 = 0 ^ 2 = 2 = pref[1] ^ pref[2]

        so res[i] = pref[i - 1] ^ pref[i]



*/
    //sol1, o(n), O(1)
    public int[] findArray(int[] pref) {
        int n = pref.length;
        int[] res = new int[n];
        res[0] = pref[0];
        for(int i = 1; i < n; i++){
            res[i] = pref[i - 1] ^ pref[i];
        }
        return res;

    }
}
