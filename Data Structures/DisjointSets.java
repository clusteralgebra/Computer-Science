/**
 * Disjoint Union Sets
 * @author Alan Yan
 *
 */

//NEEDS TESTING
 
public class DisjointSets {
	
    int[] rank, parent;
    int n;
 
    public DisjointSets(int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        init();
    }
 
    // Creates n sets with single item in each
    void init() {
        for (int i=0; i<n; i++)
        	parent[i] = i;
    }
 
    // Amortized O(1)
    int find(int x) {
        if (parent[x]!=x)
            parent[x] = find(parent[x]);
        return parent[x];
    }
 
    // O(1)
    void union(int x, int y) {
    	
        int xRoot = find(x);
        int yRoot = find(y);
        
        if (xRoot == yRoot)
            return;
        
        if (rank[xRoot] < rank[yRoot])
            parent[xRoot] = yRoot;
        
        else if (rank[yRoot] < rank[xRoot])
            parent[yRoot] = xRoot;
 
        else {
            parent[yRoot] = xRoot;
            rank[xRoot] = rank[xRoot] + 1;
        }
    }
}
