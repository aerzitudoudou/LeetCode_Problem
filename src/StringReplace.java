public class StringReplace {

//    public String replace(String input, String source, String target) {
//        int s1 = source.length();
//        int s2 = target.length();
//        char[] ary = input.toCharArray();
//        if(s1 >= s2){
//            //move from left
//            return replaceI(ary, source, target);
//        }
//        //move from right + spaces
//        return replaceII(ary, source, target);
//    }
//    private String replaceI(char[] ary, String s, String t){
//        //s1 >= s2
//        //slow: letter left to s are letters to be kept
//        //fast: letter being processed
//        //initialization:
//        //slow = fast = 0
//        //termination condition: f = ary.length
//        //for each fast position, check if fast matches the pattern
//        //matches: s copy each letter from t, f+= t.length
//        //doesn't match: ary[s] = ary[f], s++, f++
//        int slow = 0, fast = 0, totalLength = ary.length, s1 = s.length(), s2 = t.length();
//
//        while(fast < totalLength){
//            if(fast + s2 <= totalLength && matchPattern(ary, fast, s)){
//                copyPattern(ary, slow, t);
//                slow += s2;
//                fast += s1;
//            }else{
//                ary[slow++] = ary[fast++];
//            }
//        }
//        return new String(ary, 0, slow);
//
//    }
//
//    private String replaceII(char[] ary, String s, String t){
//        //s1 < s2
//        //slow: all the letters to the right of slow are kept
//        //fast: letter being processed
//        //step1: calculate how many times the source pattern has appeared = k
//        //spaces need to add at the end is k * (|target length| - |source length|)
//        //initialization:
//        //fast: ary.length - 1
//        //slow: newAry.length - 1
//        //if fast is the end index: copy from slow, f-= source.length, s-= target.length
//        //if fast is not the end index: ary[s] = ary[f] s-- f--
//        //termination condition: f < 0
//        List<Integer> sEndIndexes = getStringEndIndexes(ary, s);
//        int s1 = s.length(), s2 = t.length(), totalLength = ary.length, endIndex = sEndIndexes.size() - 1, fast = totalLength - 1;
//        int newAryLength = totalLength + sEndIndexes.size() * (s2 - s1), slow = newAryLength - 1;
//        char[] newAry = new char[newAryLength];
//
//        while(fast >= 0){
//            if(endIndex >= 0 && fast == sEndIndexes.get(endIndex)){
//                copyPattern(newAry, slow - s2 + 1, t);
//                slow -= s2;
//                fast -= s1;
//                endIndex--;
//            }else{
//                newAry[slow--] = ary[fast--];
//            }
//        }
//        return new String(newAry, slow + 1, newAryLength);
//
//    }
//
//    private List<Integer> getStringEndIndexes(char[] ary, String s){
//        List<Integer> indexList = new ArrayList<>();
//        int index = 0;
//        while(index + s.length() <= ary.length){
//            if(matchPattern(ary, index, s)){
//                indexList.add(index + s.length() - 1);
//                index += s.length();
//            }else{
//                index++;
//            }
//        }
//        return indexList;
//
//    }
//
//    private boolean matchPattern(char[] ary, int index, String t){
//        for(int i = 0; i < t.length(); i++){
//            if(ary[index] != t.charAt(i)){
//                return false;
//            }else{
//                index++;
//            }
//        }
//        return true;
//    }
//
//    private void copyPattern(char[] ary, int index, String t){
//        for(int i = 0; i < t.length(); i++){
//            ary[index] = t.charAt(i);
//            index++;
//        }
//    }


    //update 2021.04.10
    //input length M     source length N
    //T: O(M * N)
    //S: O(M)

    public String replace(String input, String source, String target) {
        char[] ary = input.toCharArray();
        if(source.length() >= target.length()){
            return helperI(ary, source, target);
        }
        return helperII(ary, source, target);

    }

    private String helperI(char[] ary, String src, String tar){
        int lT = tar.length();
        int lS = src.length();
        int l = ary.length;
        //f: cur value being examed
        //s: all the elements left to s(not including s) is the up to current result
        int s = 0, f = 0;
        while(f <= l - 1){
            if(f <= l - lS && isMatched(ary, f, src)){
                //copy
                for(int i = 0; i < lT; i++){
                    ary[s++] = tar.charAt(i);

                }
                f += lS;
            }else{
                ary[s++] = ary[f++];
            }

        }
        return new String(ary, 0, s);
    }


    /*
    s: aa    2
    t: bbb   3
              f
    ary: cccaaccc

               s
    ary: cccbbb---

    */
    private String helperII(char[] ary, String src, String tar){
        int lT = tar.length();
        int lS = src.length();
        int l = ary.length;
        //how much s is in ary
        char[] ary2 = new char[ary.length + (lT - lS) * countMatch(ary, src)];
        int s = 0, f = 0;
        while(f <= l - 1){
            if(f <= l - lS && isMatched(ary, f, src)){
                //copy
                for(int i = 0; i < lT; i++){
                    ary2[s++] = tar.charAt(i);

                }
                f += lS;
            }else{
                ary2[s++] = ary[f++];
            }

        }
        return new String(ary2, 0, s);


    }
    //0123456
    //baaabaa
    private int countMatch(char[] ary, String s){

        int c = 0, count = 0;
        while(c <= ary.length - s.length()){
            if(isMatched(ary, c, s)){
                count++;
                c += s.length();
            }else{
                c++;
            }
        }
        return count;

    }

    private boolean isMatched(char[] ary, int start, String s){

        for(int i = 0; i < s.length(); i++){
            if(ary[start + i] != s.charAt(i)){
                return false;
            }
        }
        return true;

    }
}
