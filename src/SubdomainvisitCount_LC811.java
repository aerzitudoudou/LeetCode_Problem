import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubdomainvisitCount_LC811 {
    public List<String> subdomainVisits(String[] input) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(String s : input){
            String[] cur = s.split(" ");
            List<String> domains = getDomain(cur[1]);
            for(String domain : domains){
                int a = map.getOrDefault(domain, 0);
                a += Integer.valueOf(cur[0]);
                map.put(domain, a);
            }

        }

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            res.add(entry.getValue() + " " + entry.getKey());
        }
        return res;

    }

    private List<String> getDomain(String s){
        List<String> res = new ArrayList<>();
        String[] tmp = s.split("\\.");
        for(int i = tmp.length - 1; i >= 0; i--){
            StringBuilder sb = new StringBuilder();
            int j = i;
            while(j <= tmp.length - 1){
                sb.append(tmp[j]);
                sb.append(".");
                j++;
            }
            res.add(sb.substring(0, sb.length() - 1).toString());
        }
        return res;
    }

    //!!!!sol2, from Lee
    public List<String> subdomainVisits1(String[] input) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(String s : input){
            String[] cur = s.split(" ");
            int count = Integer.valueOf(cur[0]);
            String domain = cur[1];
            for(int i = 0; i < domain.length(); i++){
                if(domain.charAt(i) == '.' || i == 0){
                    String subDomain = i == 0 ? domain : domain.substring(i + 1);
                    int a = map.getOrDefault(subDomain, 0);
                    a += count;
                    map.put(subDomain, a);
                }

            }


        }

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            res.add(entry.getValue() + " " + entry.getKey());
        }
        return res;
    }
}
