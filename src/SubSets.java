import java.util.ArrayList;
import java.util.List;

public class SubSets {


    public List<String> subSets(String set) {
        //TODO:corner case:

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        dfs(list, sb, set, 0);
        return list;
    }

    private void dfs(List<String> list, StringBuilder sb, String set, int index) {
        if (index == set.length()) {
            list.add(sb.toString());
            return;
        }
        char tmp = set.charAt(index);
        sb.append(tmp);
        dfs(list, sb, set, index + 1);
        sb.deleteCharAt(sb.length() - 1);
        dfs(list, sb, set, index + 1);
    }

}