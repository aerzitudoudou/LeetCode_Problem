package amazonOA;

import java.util.List;

/*
https://leetcode.com/discuss/interview-question/762546/
https://www.lintcode.com/problem/buy-fruits/description


*/
//T: O(M + N), M is the number of code in the codeList, N is the number of words in the shopping cart
//S: O(1)
public class AmazonFreshPromotion {
    public int buyFruits(List<List<String>> codeList, List<String> shoppingCart) {
        //corner cases
        if (codeList == null || codeList.size() == 0) {
            return 1;
        }
        if (shoppingCart == null || shoppingCart.size() == 0) {
            return 0;
        }
        //two pointers, i points to the sub-list in the code List, j points to the word in the shopping cart
        int i = 0, j = 0;
        while (i < codeList.size() && j + codeList.get(i).size() <= shoppingCart.size()) {
            boolean matched = true;
            for (int k = 0; k < codeList.get(i).size(); k++) {
                if (!codeList.get(i).get(k).equals("anything") && !codeList.get(i).get(k).equals(shoppingCart.get(j + k))) {
                    matched = false;
                    break;
                }
            }
            if (matched) {
                //if matched, j skips the number of words in the current list of code, i points to next list of code in the codeList
                j += codeList.get(i).size();
                i++;
            } else {
                //if not matched, j jumps to the next word
                j++;
            }
        }


        return i == codeList.size() ? 1 : 0;

    }
}
