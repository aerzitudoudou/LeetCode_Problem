
import java.util.*;

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


//        int n = 5;
//        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
//        ValidTree validTree = new ValidTree();
//        System.out.println(validTree.validTree(n, edges));

//        int[] array = new int[]{1,1,1,1,1,1,1,1};
//        ContiguousArray contiguousArray = new ContiguousArray();
//        contiguousArray.findMaxLength(array);

//        int a = 131;
//        int b = 64;
//        int c = a % b;
//        System.out.println(c);

//        ListNode a = new ListNode(1);
//        ListNode b = new ListNode(2);
//        a.next = b;
//        RotateLinkedList rotateLinkedList = new RotateLinkedList();
//        ListNode res= rotateLinkedList.rotateRight(a, 0);
//        System.out.println(res);
//          int[][] grid = new int[][]{{0, 0, 0},{0, 0, 0},{0, 0 , 1}};
//          ZombieInMatrix zombieInMatrix = new ZombieInMatrix();
//          zombieInMatrix.zombie(grid);




 /*list and array play ground

          int[] a = new int[]{1, 2, 3};
          int[] b = new int[]{1, 2, 3};

          List<Integer> res = new ArrayList();
          List<Integer> res2 = new ArrayList();
          res.add(1);
          res2.add(1);
          res.add(2);
          res2.add(2);

        Set set = new HashSet();
        set.add(Arrays.asList(0, 0, 0));

        int[] test = {1, 2, 4};

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        Integer[] array = new Integer[]{};
        array = list.toArray(array);

        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
        System.out.println(res.equals(res2));


*/
//       SevenPuzzle sevenPuzzle = new SevenPuzzle();
//       int[] values = new int[]{0,1,3,4,5,7,2,6};
//        System.out.println(sevenPuzzle.numOfSteps(values));
//
//        int i = 0, j = 1;
//        double x = 0;

//        System.out.println(Math.abs(-2147483648));
//
//        int i  = (2 * 2 * 2 * 2 * 2) % 3;
//        int j = (2 * 2) % 3;
//
//        int k = (1 % 3 * 2 % 3) % 3;
//
//        System.out.println("i :" + i + " k :" + k);
//        Integer[] a = new Integer[]{1, 2};
//        Integer[] b = new Integer[]{1, 2};
//        Set<Integer[]> set = new HashSet<>();
//        set.add(a);
//        System.out.println(Arrays.asList(a).hashCode());
//        System.out.println(Arrays.asList(b).hashCode());


//        List<Integer> aList = Arrays.asList(a);
//        List<Integer> bList = Arrays.asList(b);
//        System.out.println(aList.equals(bList));
//        int[] c = new int[]{1, 2};
//        int[] d = new int[]{1, 2};
//        Set<List<Integer>> set = new HashSet<>();
//        set.add(aList);
//        System.out.println(set.contains(bList));
//        set.remove(bList);
//        System.out.println(set);

//        WordSearch wordSearch = new WordSearch();
//        char[][] board = {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaab".toCharArray()};
//        String word = "baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//
//        System.out.println(wordSearch.exist(board, word));
        String a = "i love yahoo hahahahahhaah  jajajajajajaj";
        String b = "i love yahoo hahahahahhaah  jajajajajajaj";
        Set<String> set = new HashSet<>();
        set.add(a);
        System.out.println(set.contains(b));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());



















        // A utility function to find factorial of n






    }


}
