import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

class Solution1 {

	static int N, E;
	static int Answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output1.txt");

		int s, e, d; //start, end, distance

		for (int test_case = 1; test_case <= 10; test_case++) {

			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			E = Integer.parseInt(stk.nextToken());

			int[][] adj = new int[200+1][200+1]; //adjacency matrix
			for(int i = 1; i <= N; i++){ // when k = 0
				for(int j = 1; j <= N; j++){
					adj[i][j] = Integer.MAX_VALUE;
					if(i == j)
						adj[i][j] = 0;
				}
			}
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++){ // d_ij, 0 = w_ij
				s = Integer.parseInt(stk.nextToken());
				e = Integer.parseInt(stk.nextToken());
				d = Integer.parseInt(stk.nextToken());
				adj[s][e] = d;
			}
			for(int k = 1; k <= N; k++){
				for(int i = 1; i <= N; i++){
					for(int j = 1; j <= N; j++){ // d_ij, k = min(d_ij, k-1 or d_ik, k-1 + d_kj, k-1). We don't need d_ij, k-1 after computing d_ij, k
						if(adj[i][k] == Integer.MAX_VALUE || adj[k][j] == Integer.MAX_VALUE)
							continue;
						if(adj[i][k] + adj[k][j] < adj[i][j])
							adj[i][j] = adj[i][k] + adj[k][j];
					}
				}
			}
			Answer = 0;
			for(int i = 1; i <= N; i++){
				for(int j = 1; j <= N ; j++){
					if(adj[i][j] != Integer.MAX_VALUE){
						Answer += adj[i][j];
						Answer = Answer % 100000000;
					}
				}
			}

			pw.println("#" + test_case + " " + Answer);
			pw.flush();
		}

		br.close();
		pw.close();
	}
}