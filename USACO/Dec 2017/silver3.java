import java.util.*;
import java.io.*;
public class silver3 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("shuffle.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] parent = new int[n+1];
		boolean[] visited = new boolean[n+1];
		int[] step = new int[n+1];
		
		for(int i = 1; i <= n; i++){
			parent[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		
		for(int i = 1; i < n+1; i++){
			if(!visited[i]){
				boolean[] tempVis = new boolean[n+1];
				int curr = i;
				step[curr] = 1;
				while(!tempVis[parent[curr]] && !visited[parent[curr]]){
					tempVis[curr] = true;
					visited[curr] = true;
					step[parent[curr]] = step[curr] + 1;
					curr = parent[curr];
				}
				if(tempVis[parent[curr]]){
					count = count + (step[curr] - step[parent[curr]] + 1);
				}
			}
		}
		pw.print(count);
		pw.close();
		br.close();		
		
	}
}
