
public abstract class BinaryTree {
	TreeNode root;
	
	public BinaryTree() { 
		root = null;
	}
	
	public void setRoot(TreeNode theNewNode) { 
		root = theNewNode;
	}
	
	public TreeNode getRoot() { 
		return root;
	}
	
	boolean isEmpty() { 
		return(root == null);
	}
	
	public abstract void insert(Comparable item);
	public abstract TreeNode find(Comparable key);
}
