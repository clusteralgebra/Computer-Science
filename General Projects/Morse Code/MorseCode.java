import java.io.*;
import java.util.*;

/**
 *  Morse Code Translator Logic
 *  @author Alan Yan
 *	Implementation: Binary tree where we record
 *	values in the nodes. To retrieve a value, we 
 *	interpret the ciphertext as a set of directions
 *	where . is a left traversal and - is right traversal.
 */

public class MorseCode {
	private Node root;
	
	public MorseCode() throws IOException{ 
		root = new Node("");
		//initialize
		BufferedReader br = new BufferedReader(new FileReader("morse"));
		String s = br.readLine();
		while(s != null) { 
			// be careful with split
			construct(s.split("\\s+")[0], s.split("\\s+")[1]);
			s = br.readLine();
		}
		br.close();
	}
	
	public String getLetter(String cipher) { 
		LinkedList<Character> directions = new LinkedList<Character>();
		for(char x : cipher.toCharArray())
			directions.push(x);
		return get(root, directions);
	}
	
	public String getWord(String cipher) {
		String[] word = cipher.split("\\s+");
		String ret = "";
		for(String w : word)
			ret += getLetter(w);
		return ret.toLowerCase();
	}
	
	public String getSentence(String cipher) { 
		String[] s = cipher.split("/");
		String ret = "";
		for(String word : s)
			ret += getWord(word) + " ";
		return ret.substring(0, ret.length() - 1);
	}
	public void construct(String val, String directions) {
		LinkedList<Character> list = new LinkedList<Character>();
		for(char x : directions.toCharArray())
			list.push(x);
		insert(root, val, list);
	}
	
	public void insert(Node src, String val, LinkedList<Character> list) { 
		
		if(list.isEmpty()) {
			src.setVal(val);
			return;
		}
		if(list.pop() == '.') {
			if(!src.hasLeft()) 
				src.setLeft(new Node(""));
			insert(src.getLeft(), val, list);
			return;
		}
		if(!src.hasRight())
			src.setRight(new Node(""));
		insert(src.getRight(), val, list);
	}
	
	public String get(Node src, LinkedList<Character> list) {
		if(list.isEmpty())
			return src.getVal();
		if(list.pop() == '.') 
			return get(src.getLeft(), list);
		return get(src.getRight(), list);
	}
}

class Node { 
	private String val;
	private Node left, right;
	
	public Node(String val) {
		this.val = val;
		left = null;
		right = null;
	}
	
	public String getVal() { 
		return val;
	}
	public Node getLeft() { 
		return left;
	}
	
	public Node getRight() { 
		return right;
	}
	
	public void setLeft(Node child) { 
		left = child;
	}
	
	public void setRight(Node child) { 
		right = child;
	}
	
	public void setVal(String val) { 
		this.val = val;
	}
	
	public boolean hasLeft() { 
		return getLeft() != null;
	}
	
	public boolean hasRight() { 
		return getRight() != null;
	}
	@Override
	public String toString() { 
		return val;
	}
}
