/**
 * Binary Index Tree
 * @author Alan Yan
 * getSum complexity: O(log n)
 * update complexity: O(log n)
 * construct complexity: O(n)
 */
public class BIT {   
    final static int MAX = 10000;        
 
    static int BITree[] = new int[MAX];
    
    int getSum(int index) {
        int sum = 0; 
        index++;
        while(index>0) {
            sum += BITree[index];
            index -= index & (-index);
        }
        return sum;
    }
    
    public static void update(int n, int index, int val) {
        index++;
        while(index <= n) {
           BITree[index] += val;
           index += index & (-index);
        }
    }
    
    void construct(int arr[], int n) {
    	reset(n);
        for(int i = 0; i < n; i++)
            update(n, i, arr[i]);
    }
    
    void reset(int n) { 
    	for(int i = 1; i <= n; i++)
    		BITree[i] = 0;
    }
 
   
}
