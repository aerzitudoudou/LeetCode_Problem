
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        FindCombination findCombination = new FindCombination();
//        int target = 1;
//        int[] coins = new int[]{25,10,5,1};
//        List<List<Integer>> res = findCombination.combinations(target, coins);
//        System.out.println(res);
//        AllSubSets allSubSets = new AllSubSets();
//        System.out.println(allSubSets.subSets(""));
//        CommonNumberOfTwoSortedArys commonNumberOfTwoSortedArys = new CommonNumberOfTwoSortedArys();
//        commonNumberOfTwoSortedArys.common(new int[]{1,2,3,4,5,6}, new int[]{5,9});
//        ReverseString reverseString = new ReverseString();
//        reverseString.reverse("aaaa");
//        ReverseWords reverseWords = new ReverseWords();
//        reverseWords.reverseWords("An Apple");

//        StringReplace stringReplace = new StringReplace();
//        String res = stringReplace.replace("appleCatapple", "Cat", "DogDog");
//        System.out.println(res);
//        ReOrderArray reOrderArray = new ReOrderArray();
////        reOrderArray.reorder(new int[]{0,1,2,3,4});
//        Integer[] res = new Integer[3];
////        res[0] = 0;
////        res[1] = 1;
////        res[2] = 2;
////        Arrays.asList(res);
//        CountArray countArray = new CountArray();
//        int[] array = new int[]{4,1,6,6,2,5,3};
//        int[] res = countArray.countArray(array);
//        System.out.println(res);
//        ThreeSum threeSum = new ThreeSum();
//        int[] array = new int[]{ 2, 2, 0};
//        threeSum.allTriples(array, 6);
//        TreeNode a = new TreeNode(1);
//        TreeNode b = new TreeNode(2);
//        TreeNode c = new TreeNode(3);
//
//        TreeNode d = new TreeNode(4);
//
//        TreeNode e = new TreeNode(5);
//
//        TreeNode f = new TreeNode(6);
//
//        TreeNode g = new TreeNode(7);
//        a.left = b;
//        a.right = c;
////        b.left = d;
////        b.right = e;
////        c.left = f;
////        c.right = g;
//
//        FlattenBinaryTree flattenBinaryTree = new FlattenBinaryTree();
//        flattenBinaryTree.flatten(a);

//        AllCombinationWithSpace allCombinationWithSpace = new AllCombinationWithSpace();
//        allCombinationWithSpace.allPermutations("ABC");
//        int[][] m = {{1,2,3}, {4,5}, {}};
//        MergeKSortedArray mergeKSortedArray = new MergeKSortedArray();
//        int[] res = mergeKSortedArray.merge(m);
//        for(int i : res){
//            System.out.println(i);
//        }
//        KeepDistanceForIdenticalElements keepDistanceForIdenticalElements = new KeepDistanceForIdenticalElements();
//        int[] res = keepDistanceForIdenticalElements.keepDistance(3);
//
//        for(int i : res){
//            System.out.println(i);
//        }

//        MatrixRestoration matrixRestoration = new MatrixRestoration();
//        int[][] matrix = new int[2][2];
//        matrix[0] =new int[] {1, 3};
//        matrix[1] =new int[] {4, 10};
//        int[][] res = matrixRestoration.matrixRestoration(2, 2, matrix);
//
//        for(int i = 0; i < 2; i++){
//            for(int j = 0; i < 2; j++){
//                System.out.println("i = " + i + "  j = " + j + "res = " + res[i][j]);
//            }
//        }
//        int[] ary = new int[]{0,1,2,3,4};
//        CountNumbers countNumbers = new CountNumbers();
//        int res = countNumbers.countElements(ary);
//        System.out.println(res);

//        List<List<String>> res = new ArrayList<List<String>>();

//        String[] ary = new String[]{"eat","tea","tan","ate","nat","bat"};
//        GroupAnagram groupAnagram = new GroupAnagram();
//        List<List<String>> res = groupAnagram.groupAnagrams(ary);
//        for(List<String> list : res){
//            System.out.println(list);
//
//        }
//
//        String str = new String();
//        str.hashCode();

//        AllAnagrams allAnagrams = new AllAnagrams();
//        String sh = "aab";
//        String lo = "ababacbbaac";
//        List<Integer> list = allAnagrams.allAnagrams(sh, lo);
//        for(Integer a : list){
//            System.out.println(a);
//        }

//        AllSubSetII allSubSetII = new AllSubSetII();
//        int[] nums = new int[]{1,1};
//        allSubSetII.subsetsWithDup(nums);
//        AllValidParenthesesII allValidParenthesesII = new AllValidParenthesesII();
//        List<String> list = allValidParenthesesII.validParentheses(1, 1, 1);
//        System.out.println(list);

//        int[] queries = new int[]{3,1,2,1};
//
//        ProcessQueries processQueries = new ProcessQueries();
//        processQueries.processQueries(queries, 5);


        int n = 5;
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        ValidTree validTree = new ValidTree();
        System.out.println(validTree.validTree(n, edges));







        // A utility function to find factorial of n






    }
}
