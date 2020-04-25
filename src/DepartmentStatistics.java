/*
*
* lintcode 1903
*Description

You are given infomations of employees in a company, with their IDs, names and the department they belong to.
And their friendships, every friendship contains two IDs, "1, 2" means employees ID 1 and ID 2 are friends.
The friendships are not transitive, for example, AandBare bothC's friend, howeverBandCmay not be friend.
Please count the number of employees that have a friend in another department for every department.

All input is followed by a space after the comma, and the output of your program must be in the same format as the sample.
The returned list has no order requirements.
The size of employee infomation is no more than 50.
The size of friendships is no more than 1000.
The IDs are numbers no more than 100.
The number of different department is no more than 20.

Have you met this question in a real interview?
Clarification
In the example，employee 1 in Engineer and employee 2 in HR are friends，employee 3 in Engineer and employee 4 in Businessare friends，so Engineer has 2 employees with other department，return "Engineer: 2 of 3“。Besides HR has 1， Business has 1.

Example
Sample Input:
employees = [
  "1, Bill, Engineer",
  "2, Joe, HR",
  "3, Sally, Engineer",
  "4, Richard, Business",
  "6, Tom, Engineer"
]

friendships = [
  "1, 2",
  "1, 3",
  "3, 4"
]
Sample Output:
"Engineer: 2 of 3"
"HR: 1 of 1"
"Business: 1 of 1"
*
* */

import java.util.*;

public class DepartmentStatistics {
//my way:
    /**
     * @param employees: information of the employees
     * @param friendships: the friendships of employees
     * @return: return the statistics
     */
    public List<String> departmentStatistics(List<String> employees, List<String> friendships) {
        List<String> res = new ArrayList<>();
        Map<Integer, String> dataMap = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();
        Map<String, Integer> friend = new HashMap<>();
        Map<Integer, String> nameMap = new HashMap();
        Set<String> name = new HashSet<>();

        //loop employee to populate data and count, name
        for(String s : employees){
            String[] data = s.split(", ");
            /*
            1, Bill, Engineer
            0    1      2

            */
            nameMap.put(Integer.valueOf(data[0]), data[1]);
            dataMap.put(Integer.valueOf(data[0]), data[2]);
            if(!count.containsKey(data[2])){
                count.put(data[2], 1);
            }else{
                count.put(data[2], count.get(data[2]) + 1);
            }
        }


        //init friend map using count
        Set<String> dep = count.keySet();
        for(String s : dep){
            friend.put(s, 0);
        }

        //loop friend input , use data populate friend map
        for(String s : friendships){
            String[] data = s.split(", ");
            /*
            "1, 2"
             0  1
            */
            //not same deparment, update friend
            String dep1 = dataMap.get(Integer.valueOf(data[0]));
            String dep2 = dataMap.get(Integer.valueOf(data[1]));
            String name1 = nameMap.get(Integer.valueOf(data[0]));
            String name2 = nameMap.get(Integer.valueOf(data[1]));


            if(!dep1.equals(dep2)){
                if(!name.contains(name1)){
                    friend.put(dep1, friend.get(dep1) + 1);
                    name.add(name1);

                }
                if(!name.contains(name2)){
                    friend.put(dep2, friend.get(dep2) + 1);
                    name.add(name2);

                }
            }

            //same department do nothing
        }

        //use count and friend get the final result
        for(Map.Entry<String, Integer> entry : friend.entrySet()){
            String depName = entry.getKey();
            Integer friendNum = entry.getValue();
            Integer countNum = count.get(depName);
            res.add(generateOutput(depName, friendNum, countNum));
        }
        return res;
    }
    private String generateOutput(String dep, Integer friend, Integer total){
        StringBuilder sb = new StringBuilder();
        sb.append(dep);
        sb.append(": ");
        sb.append(String.valueOf(friend));
        sb.append(" of ");
        sb.append(String.valueOf(total));
        return sb.toString();
    }

    //other people's way
    //用java 8 的新method putIfAbsent   getOrDefault
    //TODO: 总结




}
