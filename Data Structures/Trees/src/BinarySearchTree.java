public class BinarySearchTree extends BinaryTree{

	@Override
	public void insert(Comparable item) {
		if(root == null) {
			root = new TreeNode(item);
			return;
		}
		insert(root, item);
	}
	public BinarySearchTree balance(int[] arr, int start, int end) {
		BinarySearchTree ret = new BinarySearchTree();
		if(start > end)
			return null;
		if(start == end) {
			ret.setRoot(new TreeNode(arr[start]));
			return ret;
		}
		int mid = (start + end)/2;
		BinarySearchTree one = balance(arr, start, mid-1);
		BinarySearchTree two = balance(arr, mid+1, end);
		ret.setRoot(new TreeNode(arr[mid]));
		ret.root.setLeft(one.root);
		ret.root.setRight(two.root);
		return ret;
	}
	@Override
	public TreeNode find(Comparable key) {
		return null;
	}
	public TreeNode find(TreeNode src, Comparable key) { 
		if(src == null)
			return null;
		if(src.getValue().equals(key))
			return src;
		int check = key.compareTo(src.getValue());
		if(check >= 0) { 
			return find(src.getRight(), key);
		}
		return find(src.getLeft(), key);
	}
	
	public Object max() {
		TreeNode curr = root;
		TreeNode next = curr.getRight();
		while(next != null) {
			curr = next;
			next = next.getRight();
		}
		return curr.getValue();
	}
	
	public Object min() { 
		TreeNode curr = root;
		TreeNode next = curr.getLeft();
		while(next != null) {
			curr = next;
			next = next.getLeft();
		}
		return curr.getValue();
	}
	
	public void insert(TreeNode src, Comparable item) {
		int check = item.compareTo(src.getValue());
		if(check >= 0) {
			if(src.getRight() == null)
				src.setRight(new TreeNode(item));
			else
				insert(src.getRight(), item);
			return;
		}
		if(src.getLeft() == null) { 
			src.setLeft(new TreeNode(item));
			return;
		}
		insert(src.getLeft(), item);
	}
	
	public void inOrder() { 
		inOrderUtil(root);
		System.out.println();
	}
	
	public void inOrderUtil(TreeNode src) {
		if(src == null)
			return;
		inOrderUtil(src.getLeft());
		System.out.print(src + " ");
		inOrderUtil(src.getRight());
	}

}