import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RoboticRiddles {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("RoboticRiddlesIN.txt"));
            while (br.ready()) {
                String[] splitted = br.readLine().split("\\s+");
                List<List<Integer>> split = new LinkedList<List<Integer>>();
                for (int i = 0; i < splitted.length; i++) {
                    List<Integer> addthis = new LinkedList<Integer>();
                    addthis.add(Integer.parseInt(splitted[i].split(",")[0]));
                    addthis.add(Integer.parseInt(splitted[i].split(",")[1]));
                    split.add(addthis);
                }
                System.out.println(TestRobots(split) ? "True" : "False");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static int getSign(int a) { 
    	if(a > 0)
    		return 0;
    	return 1;
    }
    public static boolean TestRobots(List<List<Integer>> robots) {
    	Map<Integer, Integer> plus = new HashMap<>();
    	Map<Integer, Integer> nega = new HashMap<>();
    	
    	for(List<Integer> x : robots) { 
    		for(int a : x) { 
    			if(a > 0) { 
    				if(!plus.containsKey(a))
    					plus.put(a, 0);
    				plus.put(a, plus.get(a) + 1);
    			}
    			else {
    				if(!nega.containsKey(a))
    					nega.put(a, 0);
    				nega.put(a, nega.get(a) + 1);
    			}
    		}
    	}
    	HashMap<Integer, Integer> reverse = new HashMap<Integer, Integer>();
    	ArrayList<Integer> indexed = new ArrayList<>();
    	
    	for(int a : plus.keySet())
    		if(nega.containsKey(-a)) {
    			reverse.put(a,indexed.size());
    			indexed.add(a);
    		}
    	int n = indexed.size();
    	for(int i = 0; i < 1 << n; i++) {
    		boolean flag = true;
    		for(List<Integer> list : robots) { 
    			int a  = list.get(0);
    			int b = list.get(1);
    			boolean det1 = true;
    			boolean det2 = true;
    			
    			if(reverse.containsKey((int) Math.abs(a))) { 
    				int index = reverse.get((int) Math.abs(a));
    				if(getSign(a) != ((i >> index) & 1))
    					det1 = false;
    			}
    			
    			if(reverse.containsKey((int) Math.abs(b))) { 
    				int index = reverse.get((int) Math.abs(b));
    				if(getSign(b) != ((i >> index) & 1))
    					det2 = false;
    			}
    			if(!det1 && !det2) { 
    				flag = false;
    				break;
    			}
    				
    		}
    		if (flag)
    			return true;
    	}
        return false;
    }

}