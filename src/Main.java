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
//        String res = stringReplace.replace("laicode", "code", "offer");
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
//
//        String[] ary = new String[]{"eat","tea","tan","ate","nat","bat"};
//        GroupAnagram groupAnagram = new GroupAnagram();
//        List<List<String>> res = groupAnagram.groupAnagrams3(ary);
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

//        System.out.println(a.hashCode());
//        System.out.println(b.hashCode());
//        Set<Integer[]> set = new HashSet<>();
//        set.add(a);


//        Integer a = -909090909;
//        Integer b = -909090909;
//        System.out.println(a.hashCode());
//        System.out.println(b.hashCode());


//
//        List<Integer> aList = Arrays.asList(a);
//        List<Integer> bList = Arrays.asList(b);
//
//        System.out.println(aList.hashCode());
//        System.out.println(bList.hashCode());
//
//        System.out.println(aList.equals(bList));
//
//        int[] c = new int[]{1, 2};
//        int[] d = new int[]{1, 2};
//        System.out.println(c.hashCode());
//        System.out.println(d.hashCode());



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


//        char[][] board = {"abce".toCharArray(),"sfcs".toCharArray(),
//                "adee".toCharArray()};
//        String word = "abab";
//
//        System.out.println(wordSearch.exist2(board, word));
//        String a = "i love yahoo hahahahahhaah  jajajajajajaj";
//        String b = "i love yahoo hahahahahhaah  jajajajajajaj";
//        Set<String> set = new HashSet<>();
//        set.add(a);
//        System.out.println(set.contains(b));
//        System.out.println(a.hashCode());
//        System.out.println(b.hashCode());

//        String test = "Test";
//        System.out.println(test.indexOf('e'));
//
//        System.out.println(((int)('f')));
//        Integer a = 8;
//        int[] steps = new int[9];
//        steps[a] = 9999;
//        for(int i : steps){
//            System.out.println(i);
//        }
//        CourseScheduleII courseScheduleII = new CourseScheduleII();
//        courseScheduleII.findOrder(2, new int[][]{{1,0}});

//        AlienDictionary alienDictionary = new AlienDictionary();
//        alienDictionary.alienOrder(new String[]{"ze","yf","xd","wd","vd","ua","tt","sz","rd", "qd","pz","op","nw","mt","ln","ko","jm","il", "ho","gk","fa","ed","dg","ct","bb","ba"});

//        Set<Character> set = new HashSet<>();
//        set.add('c');
//        set.remove('c');
//        System.out.println(set);
















//        String s1 = String.valueOf(10);
//        String s2 = String.valueOf(10);
//        System.out.println(s1.hashCode());
//        System.out.println(s2.hashCode());





//
//        // A utility function to find factorial of n
//        SerializeAndDeserializeBinaryTree serializeAndDeserializeBinaryTree = new SerializeAndDeserializeBinaryTree();
//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(10);
//        root.right = new TreeNode(10);
//        root.right.left = new TreeNode(28);
//        root.right.right = new TreeNode(3);
//        serializeAndDeserializeBinaryTree.serialize(root);
//        serializeAndDeserializeBinaryTree.deserialize("10,5,28,10_1,3,5,10,10_1,28,3");
//        FactorCombinations factorCombinations = new FactorCombinations();
//        factorCombinations.combinations(12);

//        List<String> test = new ArrayList<>(Arrays.asList(new String[]{"1"}));
//        RestoreIPAddress restoreIPAddress = new RestoreIPAddress();
//        List<String> res = restoreIPAddress.restore("11819111");
//        System.out.println(res);
//        TwoSubsetsWithMinDifference twoSubsetsWithMinDifference  = new TwoSubsetsWithMinDifference();
//        twoSubsetsWithMinDifference.minDifference(new int[]{5,-2,-10,3});
//        GenerateRandomMaze generateRandomMaze = new GenerateRandomMaze();
//        generateRandomMaze.maze(5);
//        Random1000UsingRandom5 random1000UsingRandom5 = new Random1000UsingRandom5();
//        random1000UsingRandom5.random1000();


//        CommonNumbersOfTwoArraysIIWithDuplication commonNumbersOfTwoArraysIIWithDuplication = new CommonNumbersOfTwoArraysIIWithDuplication();
//        commonNumbersOfTwoArraysIIWithDuplication.common(new int[]{3,2,1,5,4}, new int[]{2,9,5,3});

//        Integer a = 130;
//        System.out.println(a.byteValue());
//
//        List<Integer> test = new ArrayList<>();
//        test.add(1);
//        test.add(2);
//        System.out.println(test.subList(0, 1));


        //1->2

//        ListNode a = new ListNode(1);
//        ListNode b = new ListNode(2);
//        a.next = b;
//
//
//        ListNode c = new ListNode(3);
//        ListNode d = new ListNode(4);
//        c.next = d;
//
//        List<ListNode> list = new ArrayList<>();
//        list.add(a);
//        list.add(c);
//
//        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
//        mergeKSortedLists.merge(list);

//        List<Integer> list = new ArrayList<>();
//        list.add(7);
//        list.add(3);
//        list.add(2);
//        list.add(8);
//        list.add(49);
//        list.add(1);
//        MinHeap minHeap = new MinHeap(list);
//        for(int i = 0; i < 6; i++){
//            System.out.println(minHeap.poll());
//            minHeap.offer(100);
//
//        }
//        minHeap.offer(101);
//        minHeap.offer(102);
//        while(!minHeap.isEmpty()){
//            System.out.println(minHeap.poll());
//
//        }
//
//          Point[] points = new Point[]{new Point(0,0), new Point(94911151, 94911150), new Point(94911152, 94911151)};
//          MostPointsOnALine mostPointsOnALine = new MostPointsOnALine();
//          mostPointsOnALine.most(points);

//
//        int[][] points = {{0,0}, {1, -1}, {1, 1}};
//        MostPointsOnALine mostPointsOnALine = new MostPointsOnALine();
//        mostPointsOnALine.maxPoints2(points);
//
//
//        List<Integer[]> list = new ArrayList<>();
//        Integer[] point1 = {1,1};
//        Integer[] point2 = {3,2};
//        Integer[] point3 = {5,3};
//        Integer[] point4 = {4,1};
//        Integer[] point5 = {2,3};
//        Integer[] point6 = {1,4};
//        list.add(point1);
//        list.add(point2);
//        list.add(point3);
//        list.add(point4);
//        list.add(point5);
//        list.add(point6);
//        MostPointsOnALine.calculatePoints(list);


//        List<Integer[]> list = new ArrayList<>();
//        Integer[] point1 = {1,1};
//        Integer[] point2 = {2,2};
//        Integer[] point3 = {3,3};
//
//        list.add(point1);
//        list.add(point2);
//        list.add(point3);
//        MostPointsOnALine.calculatePoints(list);

//        KClosestInSortedArray kClosestInSortedArray = new KClosestInSortedArray();
//        int[] array = {0,0,1,2,3,3,4,7,7,8};
//        int target = 3;
//        int k = 5;
//        int[] res = kClosestInSortedArray.kClosest3(array, target, k);
//        for(int i : res){
//            System.out.println(i);
//        }

//        MajorityNumberII majorityNumberII = new MajorityNumberII();
//        int[] test = {1,1,1,4,3,3};
//        majorityNumberII.majority(test);

//        MajorityNumberIII majorityNumberIII = new MajorityNumberIII();
//        Integer[] test = new Integer[]{32,125,176,234,170,147,151,243,67,62,20,149,191,129,131,107,126,50,194,63,191,191,13,139,191,164,239,119,234,79,51,160,194,140,191,165,80,191,26,26,191,26,16,252,196,12,191,191,249,52,161,169,94,140,250,75,110,143,57,255,90,143,191,71,16,22,50,252,191,138,191,142,221,104,182,57,47,191,179,63,191,68,91,185,225,183,69,216,146,152,164,172,169,68,245,123,191,191,219,207,244,147,215,42,121,112,241,179,27,162,243,133,148,178,214,191,208,138,45,62,191,56,232,74,197,154,225,31,136,191,244,166,41,48,50,94,245,239,103,191,191,161,180,82,210,191,191,253,163,171,140,249,198,51,85,93,55,76,32,191,191,27,57,231,163,205,134,165,40,11,191,133,183,164,138,75,191,22,232,248,54,136,106,109,229,242,121,210,218,28,72,252,90,177,184,60,229,81,98,36,48,21,230,120,19,202,76,196,236,44,162,94,89,151,72,191,242,187,218,228,62,169,62,187,162,232,24,236,164,28,63,117,212,191,206,15,209,85,37,177,23,250,30,126,246,48,115,96,198,106,198,139,19,118,153};
//
//        majorityNumberIII.majorityNumber(Arrays.asList(test), 9);
//        MinimumCutsForPalindromes minimumCutsForPalindromes = new MinimumCutsForPalindromes();
//        String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        minimumCutsForPalindromes.minCuts(input);
//        LongestCommonSubstring longestCommonSubstring = new LongestCommonSubstring();
//        longestCommonSubstring.longestCommon4("abcdefg", "bbcefgh");
//        TreeNode n3 = new TreeNode(3);
//        TreeNode n5 = new TreeNode(5);
//
//        TreeNode n1 = new TreeNode(1);
//
//        TreeNode n6 = new TreeNode(6);
//
//        TreeNode n2 = new TreeNode(2);
//
//        TreeNode n0 = new TreeNode(0);
//
//        TreeNode n8 = new TreeNode(8);
//
//        TreeNode n7 = new TreeNode(7);
//
//        TreeNode n4 = new TreeNode(4);
//
//        n3.left= n5;
//        n3.right = n1;
//        n5.left = n6;
//        n5.right = n2;
//        n1.left = n0;
//        n1.right = n8;
//        n2.left = n7;
//        n2.right = n4;
//
//        AllNodesDistanceKInBinaryTree allNodesDistanceKInBinaryTree = new AllNodesDistanceKInBinaryTree();
//        allNodesDistanceKInBinaryTree.distanceK(n3, n5, 2);

//        String input = "((***)()((**";
//        String input = "((***)()((**";
//        MinimumParenthesesMatching minimumParenthesesMatching = new MinimumParenthesesMatching();
//        String a = "(())))()(())";
//        String b = "(())()(())";
//        System.out.println(b.compareTo(a));
//        minimumParenthesesMatching.minimumParenthesesMatching(input);
//        Character a = 'f';
//        Character b = 'b';
//        System.out.println( a < b);
//        Integer[] ary = {25,62,51,92,93,40,23,16,36,32};
//        List<Integer> list = Arrays.asList(ary);
//        MinimumCost minimumCost = new MinimumCost();
//        minimumCost.MinimumCost(list);
//        String test = "aaa[\\!?,;.] b    bbb";
//        String test2 = "aaa    aa";
//        String regex1 = "[^a-zA-Z]";
//        String regex2 = " ";
//
//        String a = test.replaceAll(regex1, regex2);
//        String[] ary3 = a.split("\\s+");
//        String[] ary = test.split(regex2);
//        String[] ary2 = test2.split(regex2);
//
//        System.out.println(ary[0]);
//        test.compareTo(test);
//
//        MostCommonWord mostCommonWord = new MostCommonWord();
//        mostCommonWord.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.",
//                new String[]{"hit"});
//        CalPoints calPoints = new CalPoints();
//        calPoints.calPoints(null);

//        List<List<String>> list = new ArrayList<>();
//        list.add(Arrays.asList(new String[]{"David","David0@m.co","David4@m.co","David3@m.co"}));
//        list.add(Arrays.asList(new String[]{"David","David5@m.co","David5@m.co","David0@m.co"}));
//        list.add(Arrays.asList(new String[]{"David","David1@m.co","David4@m.co","David0@m.co"}));
//        list.add(Arrays.asList(new String[]{"David","David0@m.co","David1@m.co","David3@m.co"}));
//        list.add(Arrays.asList(new String[]{"David","David4@m.co","David1@m.co","David3@m.co"}));
//
//
//        AccountMerge accountMerge = new AccountMerge();
//        accountMerge.accountsMerge(list);

//        List<PairString> list = new ArrayList<>();
//        list.add(new PairString("item1", "item2"));
//        list.add(new PairString("item3", "item4"));
//        list.add(new PairString("item4", "item5"));
//        // item3 item4 item5
//
//
//        list = new ArrayList<>();
//        list.add(new PairString("z", "b"));
//        list.add(new PairString("z", "c"));
//        list.add(new PairString("c", "d"));
//        list.add(new PairString("e", "f"));
//        // b c z d
//
//
//        list = new ArrayList<>();
//        list.add(new PairString("y", "x"));
//        list.add(new PairString("a", "y"));
//        list.add(new PairString("d", "e"));
//        list.add(new PairString("e", "f"));
//        // a x y
//
//
//        list = new ArrayList<>();
//        list.add(new PairString("a", "b"));
//        list.add(new PairString("b", "a"));
//        list.add(new PairString("c", "e"));
//        list.add(new PairString("e", "f"));
//        list.add(new PairString("f", "c"));
//        // c e f
//
//
//        list = new ArrayList<>();
//        list.add(new PairString("a", "b"));
//        list.add(new PairString("b", "a"));
//        list.add(new PairString("a", "c"));
//        list.add(new PairString("d", "e"));
//        list.add(new PairString("e", "f"));
//        list.add(new PairString("f", "e"));
//        list.add(new PairString("b", "e"));
//
//        list.add(new PairString("w", "x"));
//        list.add(new PairString("x", "y"));
//        list.add(new PairString("y", "z"));
//        // a b c d e f
//
//
//        list = new ArrayList<>();
//
//        list.add(new PairString("item1","item2"));
//        list.add(new PairString("item2","item3"));
//
//        list.add(new PairString("item3","item4"));
//        list.add(new PairString("item6","item7"));
//        list.add(new PairString("item5","item6"));
//        list.add(new PairString("item9","item7"));
//
//
//        LargestItemAssociation largestItemAssociation = new LargestItemAssociation();
//        largestItemAssociation.largestItemAssociation(list);


//        KSubstringWithKDifferentCharacters kSubstringWithKDifferentCharacters = new KSubstringWithKDifferentCharacters();
//        kSubstringWithKDifferentCharacters.KSubstring("abacabc", 3);
//          SlidingWindowFramework slidingWindowFramework = new SlidingWindowFramework();
//          slidingWindowFramework.slidingWindow("str1", "str2");
//          SlidingWindowFramework.test();

//        KClosest kClosest = new KClosest();
//        kClosest.kClosest(new int[]{1,3,3,6,9,9,12,15}, 10, 5);
//
//        int target = 1;
//        int[] coins = {25,10,5,1};
//        CombinationOfCoins combinationOfCoins = new CombinationOfCoins();
//        combinationOfCoins.combinations(target, coins);

//        CourseScheduleIIAllCourses courseScheduleIIAllCourses = new CourseScheduleIIAllCourses();
////        courseScheduleIIAllCourses.findCourses(4, new int[][]{{1,0},{2,0},{3,2},{3,1}});
//        courseScheduleIIAllCourses.findCourses(2, new int[][]{});
//        Atoi atoi = new Atoi();
//        atoi.myAtoi("9223372036854775808");
//
//        int a = -123;
//
//        System.out.println(a / -10 + "; "  + a % -10);

//        StringBuilder sb = new StringBuilder();
//        sb.append(12 + "->");
//        System.out.println(sb.substring(0, sb.length() - 3));

//        FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();
//        findDuplicateNumber.findDuplicate(new int[]{1,3,4,2,2});
//
//          WordSearchII wordSearchII = new WordSearchII();
////          char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
////          String[] words = {"oath","pea","eat","rain"};
//          char[][] board = {{'a'}};
//          String[] words = {"a"};
//
//        wordSearchII.findWords(board, words);

//        VerifyingAnAlienDictionary verifyingAnAlienDictionary = new VerifyingAnAlienDictionary();
//        String[] words = {"hello","l"};
//        String order = "hlabcdefgijkmnopqrstuvwxyz";
//        verifyingAnAlienDictionary.isAlienSorted(words, order);

//        int test = 123;
//        IntegerToEnglishWords integerToEnglishWords = new IntegerToEnglishWords();
//        integerToEnglishWords.numberToWords(test);

//          MaximumLengthOfRepeatedSubarray_LC718 maximumLengthOfRepeatedSubarray_lc718 = new MaximumLengthOfRepeatedSubarray_LC718();
//          maximumLengthOfRepeatedSubarray_lc718.findLength(new int[]{1,2,3,2,1},new int[]{3,2,1,4,7} );
//
//          AddBinary_LC67 addBinary_lc67 = new AddBinary_LC67();
//          addBinary_lc67.addBinary2("11", "1");

//        ValidPalindrome_LC125 validPalindrome_lc125 = new ValidPalindrome_LC125();
//        validPalindrome_lc125.isPalindrome("A man, a plan, a canal: Panama");
//
//          int[][] res = {{1,3},{-2,2}};
//        KClosestPointsToOrigin kClosestPointsToOrigin = new KClosestPointsToOrigin();
//        kClosestPointsToOrigin.kClosest2(res, 1);
//
//        int[] nums = {2,3,1,5,6,4};
//        KthLargestElementInAnArray kthLargestElementInAnArray = new KthLargestElementInAnArray();
//        kthLargestElementInAnArray.findKthLargest(nums, 2);

//          TreeNode one = new TreeNode(1);
//          TreeNode two = new TreeNode(2);
//          TreeNode three = new TreeNode(3);
//          TreeNode four = new TreeNode(4);
//          TreeNode five = new TreeNode(5);
//
//          one.left = two;
//          one.right = three;
//          three.left = four;
//          three.right = five;
//
//
//          SerializeAndDeserializeBinaryTree_LC297 serializeAndDeserializeBinaryTree_lc297 = new SerializeAndDeserializeBinaryTree_LC297();
//          String se = serializeAndDeserializeBinaryTree_lc297.serialize(one);
//          TreeNode res = serializeAndDeserializeBinaryTree_lc297.deserialize(se);
//
//          String c = new String("a");
//          String b = new String("a");
//
////          int test = Integer.valueOf(a);
//          System.out.println(c==b);

//            BasicCalculatorII_LC227 basicCalculatorII_lc227 = new BasicCalculatorII_LC227();
//            String s= "3+2*2";
//            basicCalculatorII_lc227.calculate(s);
//
//            BestTimeToBuyAndSellStock_LC121 bestTimeToBuyAndSellStock_lc121 = new BestTimeToBuyAndSellStock_LC121();
//            bestTimeToBuyAndSellStock_lc121.maxProfit(new int[]{1,2});
//
//            HouseRobberII_LC213 houseRobberII_lc213 = new HouseRobberII_LC213();
//            houseRobberII_lc213.rob1(new int[]{0});

//        NumberOfIslands numberOfIslands = new NumberOfIslands();
//        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
//        numberOfIslands.numIslandsDfs(grid);

//        Node one = new Node(1);
//        Node two = new Node(2);
//        Node three = new Node(3);
//        Node four = new Node(4);
//        Node five = new Node(5);
//
//        four.left = two;
//        four.right = five;
//        two.left = one;
//        two.right = three;
//
//        ConvertBinarySearchTreeToSortedDoublyLinkedList_LC426 convertBinarySearchTreeToSortedDoublyLinkedList_lc426 = new ConvertBinarySearchTreeToSortedDoublyLinkedList_LC426();
//        convertBinarySearchTreeToSortedDoublyLinkedList_lc426.treeToDoublyList(four);

//
//        ExpressionAddOperators_LC282 expressionAddOperators_lc282 = new ExpressionAddOperators_LC282();
//        expressionAddOperators_lc282.addOperators("1234", 6);

//          FBMockInterview_CountSticker fbMockInterview_countSticker = new FBMockInterview_CountSticker();
////          fbMockInterview_countSticker.calculate("boooook");
//
//        TreeNode ten = new TreeNode(10);
//        TreeNode five = new TreeNode(5);
//        TreeNode fifteen = new TreeNode(15);
//        TreeNode three = new TreeNode(3);
//        TreeNode seven = new TreeNode(7);
//        TreeNode eighteen = new TreeNode(18);
//
//        ten.left = five;
//        ten.right = fifteen;
//        five.left = three;
//        five.right = seven;
//        fifteen.right = eighteen;

//        RangeSumOfBST rangeSumOfBST = new RangeSumOfBST();
//        rangeSumOfBST.rangeSumBST(ten, 7, 15);
//        BinaryTreeVerticalOrderTraversal_LC314 binaryTreeVerticalOrderTraversal_lc314 = new BinaryTreeVerticalOrderTraversal_LC314();
//        binaryTreeVerticalOrderTraversal_lc314.verticalOrder(ten);
//        DesignAddAndSearchWordsDataStructure_LC211 designAddAndSearchWordsDataStructure_lc211 = new DesignAddAndSearchWordsDataStructure_LC211();
//        designAddAndSearchWordsDataStructure_lc211.addWord("ran");
//        designAddAndSearchWordsDataStructure_lc211.addWord("rune");
//        designAddAndSearchWordsDataStructure_lc211.addWord("runner");
//        designAddAndSearchWordsDataStructure_lc211.addWord("runs");
//        designAddAndSearchWordsDataStructure_lc211.addWord("add");
//        designAddAndSearchWordsDataStructure_lc211.addWord("adds");
//        designAddAndSearchWordsDataStructure_lc211.addWord("adder");
//        designAddAndSearchWordsDataStructure_lc211.addWord("addee");
//
//
//        designAddAndSearchWordsDataStructure_lc211.search("r.n");
//          AlienDictionary_LC269 alienDictionary_lc269 = new AlienDictionary_LC269();
//          alienDictionary_lc269.alienOrder(new String[]{"wrt","wrf","er","ett","rftt"});

//          DivideTwoIntegers_LC29 divideTwoIntegers_lc29 = new DivideTwoIntegers_LC29();
//          divideTwoIntegers_lc29.divide(-2147483648, -1);
//            System.out.println(Math.abs(Integer.MIN_VALUE));
//
//            int a = 'c';
//            System.out.println(a);
//            ShortestDistanceFromAllBuildings_LC317 shortestDistanceFromAllBuildings_lc317 = new ShortestDistanceFromAllBuildings_LC317();
//            shortestDistanceFromAllBuildings_lc317.shortestDistance(new int[][]{{1,0,2,0,1},{0,0,0,0,0,},{0,0,1,0,0}});
//            MaxPoints_LC5815_Contest250 maxPoints_lc5815_contest250 = new MaxPoints_LC5815_Contest250();
//
//            maxPoints_lc5815_contest250.maxPoints(new int[][]{{1,2,3}, {1,5,1}, {3,1,1}});
//        TrappingRainWater_LC42 trappingRainWater_lc42 = new TrappingRainWater_LC42();
//        trappingRainWater_lc42.trap(new int[]{4,2,0,3,2,5});

//        LargestRectangleInHistogram_LC84 largestRectangleInHistogram_lc84 = new LargestRectangleInHistogram_LC84();
//        largestRectangleInHistogram_lc84.largestRectangleArea(new int[]{2,1,5,6,2,3});
//
//        SumOfDigitsOfStringAfterConvert_LC1945 one = new SumOfDigitsOfStringAfterConvert_LC1945();
//        one.getLucky("a", 1);
//          WordLadder_LC127 wordLadderLC127 = new WordLadder_LC127();
//          String[] ary = new String[]{"hot","dot","dog","lot","log","cog"};
//          List<String> dict = Arrays.asList(ary);
//          wordLadderLC127.ladderLength2("hit", "dot", dict);

//            WordLadderII_LC126 wordLadderII_lc126 = new WordLadderII_LC126();
//            wordLadderII_lc126.findLadders4("hit", "cog", dict);

        SearchInRotatedSortedArrayII_LC81 searchInRotatedSortedArrayII_lc81 = new SearchInRotatedSortedArrayII_LC81();
        searchInRotatedSortedArrayII_lc81.search(new int[]{2,5,6,0,0,1,2}, 0);
    }


}
