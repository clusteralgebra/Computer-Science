
import java.util.*;
/**
 * Merge Sort Tree (not tested)
 * @author Alan Yan
 *
 */
public class MergeSortTree {
	public ArrayList<Integer>[] tree;
	int[] list; //index starts from 1
	int n;
	
	@SuppressWarnings("unchecked")
	public MergeSortTree(int[] list) { 
		this.list = list;
		n = list.length - 1;
		tree = new ArrayList[5*n];
		for(int i = 0; i < tree.length; i++)
			tree[i] = new ArrayList<Integer>();
		buildTree(1, 1, n);
	}
	void buildTree(int curr, int L, int R) { 
		if(L == R) { 
			tree[curr].add(list[L]);
			return;
		}
		int mid = L + (R - L)/2;
		buildTree(2*curr, L, mid); // Build left tree 
	    buildTree(2*curr+1 , mid+1 , R ); // Build right tree
	    tree[curr] = merge(tree[2*curr], tree[2*curr+1]); //Merging the two sorted arrays
	}
	
	//K is lower bound
	int query(int curr, int L, int R, int x, int y, int K) {
		if(R < x || L > y) {
			return 0;
	    }
	    if(x <= L && R <= y) {
	    	return tree[curr].size() - lowerBound(tree[curr], K);
	    }
	    int mid = L +(R - L)/2;
	    return query(2*curr, L, mid, x, y, K) + query( 2*curr+1, mid+1, R, x, y, K);
	}
	int query(int k, int K) { 
		return query(1, 1, n, 1, k, K);
	}
	public static int lowerBound(ArrayList<Integer> array, int value) {
        int low = 0;
        int high = array.size();
        while (low < high) {
            final int mid = (low + high) / 2;
            if (value <= array.get(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
	}
	
	static ArrayList<Integer> merge(ArrayList<Integer> array1, ArrayList<Integer> array2) { 
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int index1 = 0, index2 = 0;
		for(int i = 0; i < array1.size() + array2.size(); i++) { 
			if(index1 >= array1.size()) { 
				ret.add(array2.get(index2));
				index2++;
			}
			else if(index2 >= array2.size()) { 
				ret.add(array1.get(index1));
				index1++;
			}
			else { 
				if(array1.get(index1) < array2.get(index2)) {
					ret.add(array1.get(index1));
					index1++;
				}
				else {
					ret.add(array2.get(index2));
					index2++;
				}
			}
		}
		return ret;
	}
	public static void main(String[] args) { 
		int[] array = {0, 1, 5, 2, 8, 2, 6, 3};
		MergeSortTree test =new MergeSortTree(array);
		System.out.print(test.query(3, 1));
	}

}
