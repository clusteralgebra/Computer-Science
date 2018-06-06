import java.io.IOException;
import java.util.Scanner;
public class MorseRunner {
	public static void main(String[] args) throws IOException { 
		MorseCode x = new MorseCode();
		Scanner sc = new Scanner(System.in);
		System.out.print("Translate >>> ");
		String response = sc.nextLine();
		System.out.print(x.getSentence(response));
		
		sc.close();
	}
}
