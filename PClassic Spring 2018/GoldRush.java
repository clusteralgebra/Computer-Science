import java.io.*;
import java.util.*;
 
public class GoldRush {
     
	public static void main(String[] args) throws IOException {
		 
        Scanner sc = new Scanner(new FileReader("GoldRushIN.txt"));
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] t = s.split(" ");
            int[] arr = new int[t.length];
            for (int i = 0; i < t.length; i++) {
                arr[i] = Integer.parseInt(t[i]);
            }
            System.out.println(goldRush(arr));
        }
    }
    public static int Max(int[] array, int start) { 
    	int maxIndex = start;
    	for(int i = start; i < array.length; i++) { 
    		if(array[i] >= array[maxIndex])
    			maxIndex = i;
    	}
    	return maxIndex;
    }
    public static String goldRush(int[] L) {
    	boolean sorted = true;
    	for (int i = 1; i < L.length; ++i) {
    		if (L[i] > L[i - 1]) {
    			sorted = false;
    			break;
    		}
    	}
    	if (sorted) {
        	String sum = "";
    		for (int j = 0; j < L.length; j++) {
    			sum += L[j];
    		}
    		return sum;
    	}
    	for(int i = 0 ; i < L.length; i++) { 
    		int m = Max(L, i);
    		if(L[m] != L[i]) {
    			String sum = "";
    			for (int j = 0; j < L.length; j++) {
    				if (j == i) {
    					sum += L[m];
    				} else if (j == m) {
    					sum += L[i];
    				} else {
    					sum += L[j];
    				}
    			}
    			return sum;
    		}
    	}
    	return "";
    }
 
}