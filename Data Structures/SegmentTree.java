// range sum 
/**
 * Segment Tree
 * @author Alan Yan
 *
 */
public class SegmentTree {
		int n;
		int[] lo, hi, sum, delta;
		
		public SegmentTree(int n){
			this.n= n;
			lo = new int[4*n + 1]; 
			hi = new int[4*n + 1];
			sum = new int[4*n+1];
			delta = new int[4*n+1];
			init(1, 0, n-1);
		}
		
		void init(int i, int a, int b){
			lo[i] = a;
			hi[i] = b;
			if(a == b)
				return;
			int m = (a+b)/2;
			init(2*i, a, m);
			init(2*i + 1, m+1, b);
		}
		
		public void increment(int a, int b, int val) {
			increment(1, a, b, val);			
		}

		public int sum(int i, int j) {
			return sum(1, i, j);
		}
		
		void prop(int i){
			delta[2*i] += delta[i];
			delta[2*i+1] += delta[i];
			delta[i] = 0;
		}
		
		void update(int i) {
			sum[i] = sum[2*i] + delta[2*i] +  sum[2*i+1] + delta[2*i+1];
		}
		
		void increment(int i, int a, int b, int val) {
			// |-----| [-------]
			if(b < lo[i] || hi[i] < a)
				return;
			if(a <= lo[i] && hi[i] <= b){
					delta[i] += val;
					return;
			}
			//partial cover case
			prop(i);
			
			increment(2*i, a, b, val);
			increment(2*i+1, a, b, val);
			
			update(i);
		}
		
		int sum(int i, int a, int b) {
			if (b < lo[i] || a > hi[i])
				return 0;
			if (a <= lo[i] && hi[i] <= b)
				return sum[i] + delta[i];
			prop(i);
			int left = sum(2*i, a, b);
			int right = sum(2*i+1, a, b);
			update(i);
			return left + right;
		}
	}