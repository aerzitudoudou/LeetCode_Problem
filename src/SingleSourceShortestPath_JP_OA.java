import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/*

Single-Source Shortest Path
Programming challenge description:
Write a program to find the shortest paths from one source vertex to all other reachable vertices in the given undirected graph.

Edges of the graph have no weights and the length of the path is the number of edges between two vertices.

The graph can be disconnected, i.e. consisting of multiple pieces. If a vertex is not reachable from the source vertex the path between them is equal to infinity.

Every vertex in the graph has a unique string name consisting of alphanumeric characters (UTF-8).

Input:
The first line contains the name of the source vertex.

The second line has a list of edges. Every edge is described as a comma-delimited pair of names of adjacent vertices. Edges are separated with a semicolon in the list. For example:

A
A,B;B,C;A,C;B,D;C,D;D,E;F,G;G,H;F,H
See the attachments tab for a diagram of this example

Output:
Each line contains a space-delimited vertex name and a positive integer number, which is equal to the length of the shortest path from the source vertex.

If there is no path to some vertex from the source, print INF instead of the integer length.

Sort lines in the alphabetical order by vertex name and do not include the source vertex in the output. For example:

B 1
C 1
D 2
E 3
F INF
G INF
H INF
Test 1
Test Input
Download Test 1 Input
1
106,212;100,200;101,10;209,104;102,11;c,d;202,203;107,214;100,10;212,106;212,213;103,207;d,c;203,101;213,106;205,204;208,209;b,c;201,100;1,11;a,b;208,104;103,206;209,210;11,103;b,a;12,105;214,213;202,201;206,207;104,208;200,100;102,205;215,200;104,209;213,212;c,b;207,206;214,215;101,203;13,106;206,103;211,210;210,211;10,1;105,211;201,200;d,a;212,211;214,107;12,1;210,105;106,213;103,11;1,10;200,215;10,101;102,204;100,201;209,208;203,202;206,205;204,205;1,12;205,206;211,105;105,12;210,209;204,102;215,107;13,1;13,107;213,214;215,214;202,101;207,103;107,215;201,202;205,102;106,13;203,204;10,100;a,d;11,1;12,104;208,207;107,13;11,102;104,12;207,208;204,203;105,210;1,13;211,212;101,202;200,201
Expected Output
Download Test 1 Input
10 1
100 2
101 2
102 2
103 2
104 2
105 2
106 2
107 2
11 1
12 1
13 1
200 3
201 3
202 3
203 3
204 3
205 3
206 3
207 3
208 3
209 3
210 3
211 3
212 3
213 3
214 3
215 3
a INF
b INF
c INF
d INF
Test 2
Test Input
Download Test 2 Input
A
A,B;B,C;A,C;B,D;C,D;D,E;F,G;G,H;F,H
Expected Output
Download Test 2 Input
B 1
C 1
D 2
E 3
F INF
G INF
H INF
Test 3
Test Input
Download Test 3 Input
broccoli
carrot,potato;onion,potato;carrot,cucumber;potato,carrot;potato,tomato;carrot,tomato;tomato,carrot;cucumber,carrot;cucumber,onion;tomato,potato;potato,onion;onion,cucumber
Expected Output
Download Test 3 Input
carrot INF
cucumber INF
onion INF
potato INF
tomato INF
Test 4
Test Input
Download Test 4 Input
grass
grasshopper,hawk;lizard,hawk;grass,grasshopper;grasshopper,lizard;mouse,hawk;snake,hawk;rabbit,hawk;mouse,snake;grass,rabbit;grass,mouse
Expected Output
Download Test 4 Input
grasshopper 1
hawk 2
lizard 2
mouse 1
rabbit 1
snake 2
Private Test 5
Used for grading - will not be shown to the candidate
Test Input
Download Test 5 Input
9
0,3;9,8;5,0;1,2;10,6;6,7;11,7;4,3;7,8;7,6;8,9;6,8;0,2;7,11;10,11;6,9;2,0;1,5;6,10;7,3;6,11;4,5;5,4;8,7;9,10;0,4;3,0;9,6;3,7;0,5;8,6;0,1;11,10;3,4;11,6;10,9;5,1;3,2;4,0;2,1;1,0;2,3
Expected Output
Download Test 5 Input
0 4
1 5
10 1
11 2
2 4
3 3
4 4
5 5
6 1
7 2
8 1
Private Test 6
Used for grading - will not be shown to the candidate
Test Input
Download Test 6 Input
single_vertex
single_vertex,single_vertex;other_vertex,other_vertex
Expected Output
Download Test 6 Input
other_vertex INF
Private Test 7
Used for grading - will not be shown to the candidate
Test Input
Download Test 7 Input
X
E,Z;A,B;B,C;C,D;Z,X;E,D;B,A;Z,E;D,C;Z,E;D,E;B,A;C,B;C,D;X,Z
Expected Output
Download Test 7 Input
A 6
B 5
C 4
D 3
E 2
Z 1


**/


public class SingleSourceShortestPath_JP_OA {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        String origin = "";
        String adjacency = "";
        int count = 0;
        while ((line = in.readLine()) != null) {
            if (count == 0) {
                origin = line; // read first line as source
            } else {
                adjacency = line; // read second line as adjacent list string
            }
            count++;
        }

        // create adjacent list map from second string
        String[] pairAry = adjacency.split(";");
        Map<String, Set<String>> neisMap = new HashMap<>();
        for (String s : pairAry) {
            String[] pair = s.split(",");
            String key = pair[0];
            String value = pair[1];
            //insert key's neighour
            Set<String> cur = neisMap.getOrDefault(key, new HashSet<>());
            cur.add(value);
            neisMap.put(key, cur);
            //insert value's neighour
            cur = neisMap.getOrDefault(value, new HashSet<>());
            cur.add(key);
            neisMap.put(value, cur);
        }

        // initilize visitied and distance for each edge
        TreeMap<String, Integer> distance = new TreeMap<>();

        Deque<String> queue = new LinkedList<>();
        Map<String, Boolean> visited = new HashMap<>();
        for (String s : neisMap.keySet()) {
            visited.put(s, false);
            distance.put(s, Integer.MAX_VALUE);
        }


        visited.put(origin, true);
        distance.put(origin, 0);
        queue.add(origin);

        while (!queue.isEmpty()) {
            String u = queue.pollFirst();
            int c = distance.get(u);
            Set<String> neis = neisMap.get(u);
            if (neis != null) {
                for (String nei : neis) {
                    if (visited.get(nei) == false) {
                        queue.offerLast(nei);
                        visited.put(nei, true);
                        distance.put(nei, c + 1);
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> entry : distance.entrySet()) {
            if (entry.getValue() == Integer.MAX_VALUE) {
                System.out.println(entry.getKey() + " " + "INF");
            } else if (entry.getValue() != 0) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}
