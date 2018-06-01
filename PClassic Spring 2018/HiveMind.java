import java.io.*;
import java.util.*;

public class HiveMind {
	
    static class Event {
        int type;
        int robot1;
        int robot2;
    
        public Event(int type, int robot1, int robot2) {
            this.type = type;
            this.robot1 = robot1;
            this.robot2 = robot2;
        }
        
        int getType() {
            return type;
        }
        
        int getRobot1() {
            return robot1;
        }
        
        int getRobot2() {
            return robot2;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {

        Scanner fileInput = new Scanner(new FileReader("HiveMindIN.txt"));

        while(fileInput.hasNext()) {
            List<Event> events = new ArrayList<Event>();
            int N = Integer.parseInt(fileInput.nextLine());
            for(int i = 0; i < N; i++) {
                String[] nextRow = fileInput.nextLine().split(" ");
                int type = Integer.parseInt(nextRow[0]);
                int robot1 = Integer.parseInt(nextRow[1]);
                int robot2 = Integer.parseInt(nextRow[2]);
                events.add( new Event(type, robot1, robot2) );
            }
            List<Boolean> answer = solver( events );
            for(Boolean b : answer) {
                System.out.println(b ? "True" : "False");
            }
            System.out.println();
        }
        fileInput.close();
    }
    
    private static List<Boolean> solver(List<Event> events) {
    	Set<Integer> robots = new HashSet<>();
    	
    	for(Event e : events) { 
    		robots.add(e.robot1);
    		robots.add(e.robot2);    		
    	}
    	DisjointSets hives = new DisjointSets(robots.size());
    	List<Boolean> ans = new ArrayList<>();
    	Map<Integer, Integer> parents = new HashMap<>();
    	int nextParent = 0;
    	for(int r : robots) { 
    		parents.put(r, nextParent);
    		nextParent++;
    	}
    	for(Event e : events) { 
    		int type = e.type;
    		int robot1 = e.robot1;
    		int robot2 = e.robot2;
    		
    		if(type == 0) { 
    			hives.union(parents.get(robot1), parents.get(robot2));
    		}
    		else if(type == 1) { 
    			if(robot1 == robot2) { 
    				hives.add(nextParent);
    				parents.put(robot1, nextParent);
    				nextParent++;
    			}
    			else { 
    				parents.put(robot1, parents.get(robot2));
    			}
    		}
    		else { 
    			int x1 = parents.get(robot1);
    			int y1 = parents.get(robot2);
    			ans.add(hives.siblings(x1, y1));
    		}
    	}
        return ans;
    }
    static class DisjointSets {
    	
        ArrayList<Integer> rank, parent;
        int n;
     
        public DisjointSets(int n) {
            rank = new ArrayList<Integer>();
            parent = new ArrayList<Integer>();
            this.n = n;
            for (int i=0; i<n; i++) {
            	parent.add(i);
            	rank.add(0);
            }
            
        }
        int find(int x) {
            if (parent.get(x)!=x)
                parent.set(x, find(parent.get(x)));
            return parent.get(x);
        }
        //you only call this on new stuff
        void add(int h) { 
        	parent.add(h);
        	rank.add(0);
        }
        
        boolean siblings(int x, int y) { 
        	return x == y || find(x) == find(y);
        }
        void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);
            if (xRoot == yRoot)
                return;
            if (rank.get(xRoot)< rank.get(yRoot))
                parent.set(xRoot, yRoot);
            else if (rank.get(xRoot)> rank.get(yRoot))
                parent.set(yRoot, xRoot);
            else {
                parent.set(yRoot, xRoot);
                rank.set(xRoot, rank.get(xRoot) + 1);
            }
        }
    }

}