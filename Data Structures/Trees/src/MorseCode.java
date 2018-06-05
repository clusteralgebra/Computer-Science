import java.util.*;
import java.io.*;

public class MorseCode {
	
	BinarySearchTree morseTree;
	
	public MorseCode(){ 
		morseTree = new BinarySearchTree();
		morseTree.setRoot(new TreeNode(null));
	}
	
	//insert 
	
	void insert(char val, LinkedList<Character> message) {
		insert(morseTree.root, val, message);
	}
	
	void insert(TreeNode src, char val, LinkedList<Character> message) { 
		if(message.isEmpty()) {
			src.setValue(val);
			return;
		}
		char test = message.pop();
		if(test == '.') {
			if(src.getRight() == null) 
				src.setRight(new TreeNode(null));
			insert(src.getRight(), val, message);
			return;
		}
		
		if(src.getLeft() == null) 
			src.setLeft(new TreeNode(null));
		insert(src.getLeft(), val, message);
	}
	
	//build the tree
	
	void build(Map<Character, String> dictionary) {
		for(char key : dictionary.keySet()) {
			insert(key, stringToList(dictionary.get(key)));
		}
	}
	
	//Split a Word to a linkedlist 
	
	LinkedList<Character> stringToList(String x) { 
		LinkedList<Character> ret = new LinkedList<Character>();
		for(char t : x.toCharArray()) {
			if(t == '.' || t == '_')
				ret.add(t);
		}
		return ret;
	}
	
	// Get letter given ciphertext x
	
	char getLetter(String x) { 
		TreeNode curr = morseTree.root;
		for(char t: x.toCharArray()) {
			if(t == '.')
				curr = curr.getRight();
			else {
				curr = curr.getLeft();
			}
		}
		return (char) curr.getValue();
	}
	
	//Get Word given ciphertext x
	String translateWord(String x) {
		String ret = "";
		String[] tokens = x.split(" ");
		for(String test : tokens) {
			ret += getLetter(test);
		}
		return ret;
	}
	
	//Get sentence given ciphertext x
	
	String translateSentence(String x) { 
		String ret = "";
		String[] tokens = x.split("/");
		for(String words : tokens) { 
			ret += translateWord(words);
			ret += " ";
		}
		ret = ret.substring(0, ret.length()-1);
		return ret;
	}
	
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new FileReader("morse.txt"));
		Map<Character, String> dictionary = new HashMap<>();
		String s = br.readLine();
		while(s != null) { 
			String[] tokens = s.split(" ");
			dictionary.put(tokens[0].charAt(0), tokens[1]);
			s = br.readLine();
		}
		MorseCode test = new MorseCode();
		
		test.build(dictionary);
		String m = "._";
		String message = "__ _.__/ .._. . . _/ ... __ . ._.. ._../ ._ _. _../ __ _.__/ _. ___ ... . / ._. .._ _. ...";
		System.out.print(test.translateWord(m));
		br.close();
	}
}
