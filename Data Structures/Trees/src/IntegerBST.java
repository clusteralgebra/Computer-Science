
public class IntegerBST extends BinarySearchTree {
	
	public int sum() {
		return sumUtil(root);
	}
	
	public int getMax() { 
		return getMaxUtil(root);
	}
	
	public int getMin() { 
		return getMinUtil(root);
	}
	public double average() { 
		return (sum() + 0.0) / count();
	}
	public int sumUtil(TreeNode src) {
		if(src == null)
			return 0;
		return (Integer) src.getValue() + sumUtil(src.getLeft()) + sumUtil(src.getRight());
	}
	
	public int count() { 
		return countUtil(root);
	}
	
	public int height() { 
		return heightUtil(root);
	}
	public int getMaxUtil(TreeNode src) { 
		if(src.getRight() == null)
			return (Integer) src.getValue();
		return getMaxUtil(src.getRight());
	}
	public int getMinUtil(TreeNode src) { 
		if(src.getLeft() == null)
			return (Integer) src.getValue();
		return getMinUtil(src.getLeft());
	}
	
	public int countUtil(TreeNode src) { 
		if(src == null)
			return 0;
		return countUtil(src.getLeft()) + countUtil(src.getRight()) + 1;
	}
	
	public int heightUtil(TreeNode src) { 
		if(src == null)
			return -1;
		return Math.max(heightUtil(src.getLeft()), heightUtil(src.getRight())) + 1;
	}
}
