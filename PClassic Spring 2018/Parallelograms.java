import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parallelograms {

    static class MyPoint implements Comparable<MyPoint>{

	    public int x;
	    public int y;
	    
	    public MyPoint(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	   
        @Override
        public int compareTo(MyPoint v) {
            if( this.x != v.x ) 
                return this.x - v.x;
            return this.y - v.y;
        }
        
        @Override
        public int hashCode() {
        	return Objects.hash(x, y);
        }
        
        @Override 
        public boolean equals(Object o) { 
        	if(o == null || o.getClass() != this.getClass())
        		return false;
        	MyPoint temp = (MyPoint) o;
        	return x == temp.x && y == temp.y;
        	
        }

    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner fileInput = new Scanner(new File("ParallelogramsIN.txt"));

        List<MyPoint> points = null;
        while(fileInput.hasNext()) {
            points = new ArrayList<MyPoint>();
            int N = Integer.parseInt(fileInput.nextLine());
            
            for(int i = 0; i < N; i++) {
                String[] nextRow = fileInput.nextLine().trim().split(" ");
                int x = Integer.parseInt(nextRow[0]);
                int y = Integer.parseInt(nextRow[1]);
                points.add(new MyPoint(x, y));
            }
            System.out.println(solver(points));
        }
        fileInput.close();
    }

    public static int solver(List<MyPoint> points) {
    	int n = points.size();
    	Map<MyPoint, Integer> map = new HashMap<>();
    	for(int i = 0; i < n-1; i++) { 
    		for(int j = i+1; j < n; j++) { 
    			int newX = points.get(i).x + points.get(j).x;
    			int newY = points.get(i).y + points.get(j).y;
    			MyPoint x = new MyPoint(newX, newY);
    			if(!map.containsKey(x))
    				map.put(x, 0);
    			map.put(x,  map.get(x) + 1);
    		}
    	}
    	int ret =0 ;
    	for(int x : map.values()) 
    		ret += x * (x-1) / 2;
        return ret;
    }
    
}