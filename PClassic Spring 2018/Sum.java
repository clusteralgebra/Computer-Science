import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sum {

	 public static void main(String[] args) {
		    try {
		      BufferedReader br = new BufferedReader(new FileReader("SumIN.txt"));
		      while (br.ready()) {
		        String[] splitted = br.readLine().split("\\s+");
		        int[] arr = new int[splitted.length];
		        for (int i = 0; i < splitted.length; i++) {
		          arr[i] = Integer.parseInt(splitted[i]);
		        }
		        System.out.println(maxSum(arr));
		      }
		      br.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		}

  public static int maxSum(int[] arr) {
    int n = arr.length;
    int[] A = new int[n];
    int[] B = new int[n];
    if(n <= 1) { 
    	return Math.max(0, arr[0]);
    }
    A[0] = arr[0];
    B[0] = 0;
    
    A[1] = arr[1];
    B[1] = Math.max(A[0], B[0]);
    for(int i = 2; i < n; i++) { 
    	A[i] = arr[i] + Math.max(A[i-2], B[i-2]);
    	B[i] = Math.max(A[i-1], B[i-1]);
    }
    return Math.max(A[n-1], B[n-1]);
  }
}
