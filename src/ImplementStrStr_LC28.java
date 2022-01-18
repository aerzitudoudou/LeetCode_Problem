public class ImplementStrStr_LC28 {
    //!sol1, my, O(nm), O(1)
    public int strStr(String a, String b) {
        if(b == "") return 0;
        if(a.length() < b.length()) return -1;
        //0
        //ababbbbb
        //bbb

        for(int i = 0; i <= a.length() - b.length(); i++){
            int j = 0;
            while(j < b.length()){
                if(a.charAt(i + j) != b.charAt(j)) break;
                else j++;
            }
            if(j == b.length()) return i;
        }

        return -1;
    }

    //!!!sol2, from jh
    // use a hashmap to reduce the alphanumeric count check time complexity to O(1): remove one old char and add one new char into map, and loop through the map O(26) to compare the count
    // T:O(n + m), worst caseO(nm), S:O(m), n is length of a, m is length of b
    public int strStr1(String a, String b) {

        int la = a.length(), lb = b.length();
        if(b == "") return 0;
        if(la < lb) return -1;
        //i = 0 <a,2>,<b,2>
        //i = 1 <a, 1>, <b, 2>
        //aabba bbbb
        //ababa
        //map<a, 2><b, 2>



        int[] dictA = new int[26];
        int[] dictB = new int[26];
        //init dict A and dictB
        for(int i = 0; i < lb; i++){
            dictA[a.charAt(i) - 'a']++;
            dictB[b.charAt(i) - 'a']++;
        }

        for(int i = 0; i <= la - lb; i++){
            if(i != 0){
                dictA[a.charAt(i - 1) - 'a']--;
                dictA[a.charAt(i + lb - 1) - 'a']++;
            }
            if(compareDict(dictA, dictB) && compareString(i, a, b)){
                return i;
            }
        }

        return -1;
    }
    //O(1)
    private boolean compareDict(int[] dictA, int[] dictB){
        for(int i = 0; i < 26; i++){
            if(dictA[i] != dictB[i]) return false;
        }

        return true;
    }

    //O(m)
    private boolean compareString(int i, String a, String b){
        for(int j = 0; j < b.length(); j++){
            if(a.charAt(i + j) != b.charAt(j)) return false;
        }

        return true;
    }



    //sol3, Robin-Karp with possible integer overflow, O(n), O(1)
    public int strStr2(String a, String b) {

        int la = a.length(), lb = b.length();
        if(b == "") return 0;
        if(la < lb) return -1;
        //i = 0 <a,2>,<b,2>
        //i = 1 <a, 1>, <b, 2>
        //aabba bbbb
        //ababa

        //include power calculation, set as long to prevent integer overflow
        long aKey = 0, bKey = 0;

        long pow26 = 1;
        //init 2134
        for(int i = 0; i < lb; i++){
            aKey = aKey * 26 + (a.charAt(i) - 'a');
            bKey = bKey * 26 + (b.charAt(i) - 'a');
            //think about base 10 calculation:
            //4 ： 10 * 0 + 4 = 4  pow10: 1
            //41:  (10 * 0 + 4) * 10 + 1  pow10: 10
            //413: ((10 * 0 + 4) * 10 + 1) * 10 + 3   pow10: 100
            //4132: (((10 * 0 + 4) * 10 + 1) * 10 + 3) * 10 + 2   pow10: 1000
            //4132 -> 1325: add postA: 4132 * 10 + 5, minus preA: 41325 - 4 * 10000 (10000 is the 10 ^ 4132's length)
            pow26 = pow26 * 26; //similarly, pow26 is 26 * lb's length
        }

        for(int i = 0; i <= la - lb; i++){
            //            preA   postA
            // a:           5 213 4
            // b:5213-> 2134  2134
            if(i != 0){
                int postA = a.charAt(i + lb - 1) - 'a'; //the new char needs to be added
                int preA = a.charAt(i - 1) - 'a'; //the old char needs to be removed
                aKey =  aKey * 26 + postA - preA * pow26; //add postA, minus preA
            }
            if(aKey == bKey){ //each key is uniquely mapped to a string, both for numbers of each char and their order
                return i;
            }
        }

        return -1;
    }


    //sol4, Robin-Karp, T: O(n + m), hash collision possibility is very low, can ignore worst case
    //S:O(1)
    public int strStr3(String a, String b) {
        int MOD = 2000;
        int la = a.length(), lb = b.length();
        if(b == "") return 0;
        if(la < lb) return -1;
        //i = 0 <a,2>,<b,2>
        //i = 1 <a, 1>, <b, 2>
        //aabba bbbb
        //ababa

        //include power calculation, set as long to prevent integer overflow
        long aKey = 0, bKey = 0;

        long pow26 = 1;
        //init 2134
        for(int i = 0; i < lb; i++){
            aKey = ((aKey * 26) % MOD + (a.charAt(i) - 'a')) % MOD;
            //(a*b)%c = ((a%c)*(b%c)) % c;   aKey*26 might overflow so do mod as well
            bKey = ((bKey * 26) % MOD + (b.charAt(i) - 'a')) % MOD;
            //think about base 10 calculation:
            //4 ： 10 * 0 + 4 = 4  pow10: 1
            //41:  (10 * 0 + 4) * 10 + 1  pow10: 10
            //413: ((10 * 0 + 4) * 10 + 1) * 10 + 3   pow10: 100
            //4132: (((10 * 0 + 4) * 10 + 1) * 10 + 3) * 10 + 2   pow10: 1000
            //4132 -> 1325: add postA: 4132 * 10 + 5, minus preA: 41325 - 4 * 10000 (10000 is the 10 ^ 4132's length)
            pow26 = (pow26 * 26) % MOD; //similarly, pow26 is 26 * lb's length
        }

        for(int i = 0; i <= la - lb; i++){
            //            preA   postA
            // a:           5 213 4
            // b:5213-> 2134  2134
            if(i != 0){
                int postA = a.charAt(i + lb - 1) - 'a'; //the new char needs to be added
                int preA = a.charAt(i - 1) - 'a'; //the old char needs to be removed
                //A % C = (A + kC) % C  "+MOD" is the kC, reason for + MOD is to make sure MOD "- (preA * pow26) % MOD" is > 0
                aKey =  ((aKey * 26) % MOD + postA + MOD - (preA * pow26) % MOD) % MOD; //add postA, minus preA
            }
            //once use MOD, akey and bkey cannot garantee both alphanumber count and their orders
            if(aKey == bKey && compareString(i, a, b)){ //each key is uniquely mapped to a string, both for numbers of each char and their order
                return i;
            }
        }

        return -1;
    }





}
