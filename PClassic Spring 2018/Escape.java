import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Escape {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("EscapeIN.txt"));

    while (br.ready()) {
      String[] data = br.readLine().split(" ");
      long N = Long.parseLong(data[0]);
      long M = Long.parseLong(data[1]);
      System.out.println(calculate(N, M));
    }
    br.close();
  }

  public static long calculate(long N, long M) {
	  List<Pair> primes = new ArrayList<>();
	  int count = 0;
		  while (N % 2 == 0) {
			  N /= 2;
			  ++count;
		  }
		  if (count > 0)
	      primes.add(new Pair(2, count));
		  count = 0;
		  while (N % 3 == 0) {
			  N /= 3;
			  ++count;
		  }
		  if (count > 0)
		  primes.add(new Pair(3, count));
	  int prime = 5;
	  while (N > 1) {
		  count = 0;
		  while (N % prime == 0) {
			  N /= prime;
			  ++count;
		  }
		  if (count > 0)
			  primes.add(new Pair(prime, count));
		  if (prime % 6 == 1)
			  prime += 2;
		  prime += 2;
	  }
	  long res = 1;
	  for (Pair i : primes) {
		  res *= ((i.y + 1) * binPow(i.x, i.y, M) % M- i.y * binPow(i.x, i.y - 1, M)%M) % M;
		  res %= M;
	  }
	  return (res + M) % M;
  }

  static long binPow(long x, long power, long mod) { 
	  if(power == 0)
		  return 1L;
	  if(power == 1)
		  return x;
	  if(power %2 == 0) {
		  long t = binPow(x, power/2, mod);
		  return t*t % mod;
	  }
	  return binPow(x, power-1, mod) * x % mod;
  }
  
  static class Pair { 
	  public long x, y;
	  Pair(long x, long y) { 
		  this.x = x;
		  this.y = y;
	  }
	  
  }
}
