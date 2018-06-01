import java.io.*;
import java.util.*;

class QuickMaths {
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(new FileReader("QuickMathsIN.txt"));
		while (sc.hasNext()) {
			String s = sc.nextLine();
			System.out.println(quickMaths(s));
		}

	}
	
	public static int priority(String s) {
		switch (s) {
		case "+":
		case "-":
			return 1;
		case "*":
		case "/":
			return 2;
		case "(":
		case ")":
			return 0;
		default:
			return -1;
		}
	}
	
	public static void operate(Stack<Integer> values, Stack<String> operators) {
		int right = values.pop();
		int left = values.pop();
		switch (operators.pop()) {
		case "*":
			values.push(left * right);
			break;
		case "/":
			values.push(left / right);
			break;
		case "-":
			values.push(left - right);
			break;
		case "+":
			values.push(left + right);
			break;
		}
	}

	public static int quickMaths(String s) {
		Stack<String> operators = new Stack<>();
		Stack<Integer> values = new Stack<>();
		for (String i : s.split(" ")) {
			try {
				int val = Integer.parseInt(i);
				values.push(val);
			} catch (NumberFormatException e) {
				int p = priority(i);
				if (i.equals("(") || operators.empty() || priority(operators.peek()) < p) {
					operators.push(i);
				} else {
					while (!operators.empty() && priority(operators.peek()) >= p) {
						if (operators.peek().equals("(") && i.equals(")")) {
							operators.pop();
							break;
						}
						operate(values, operators);
					}
					if (!i.equals(")"))
						operators.push(i);
				}
			}
		}
		while (!operators.empty()) {
			operate(values, operators);
		}
		return values.pop();
	}

}