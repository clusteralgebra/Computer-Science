
public class TreeTester {
	public static void main(String[] args) { 
		IntegerBST t = new IntegerBST();
		t.insert(2);
		t.insert(5);
		t.insert(3);
		t.insert(4);
		t.insert(11);
		t.insert(9);
		t.insert(7);
		t.insert(12);
		t.insert(19);
		t.inOrder();
		System.out.println("COUNT:");
		System.out.println(t.count());
		System.out.println("Sum:");
		System.out.println(t.sum());
		System.out.println("Max Value:");
		System.out.println(t.getMax());
		System.out.println("Max Value Recursive:");
		System.out.println(t.max());
		System.out.println("Min Value Recursive:");
		System.out.println(t.min());
		System.out.println("AVEARGE:");
		System.out.println(t.average());
		System.out.println("HEIGHT:");
		System.out.println(t.height());
	}
}
