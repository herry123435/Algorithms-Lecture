import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

class Solution2 {

	static int N, E;
	static int[][] edges;
	static int[] parents; //for disjoint sets
	static int Answer;

	public static int find(int x){
		if(parents[x] == x)
			return x;
		else{
			return find(parents[x]);
		}
	}

	public static void union(int x, int y){
		x = find(x);
		y = find(y);

		parents[y] = x;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("input2.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output2.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {

			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			E = Integer.parseInt(stk.nextToken());

			edges = new int[E][3];
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++){
				edges[i][0] = Integer.parseInt(stk.nextToken()); //start
				edges[i][1] = Integer.parseInt(stk.nextToken()); //end
				edges[i][2] = (-1) * (Integer.parseInt(stk.nextToken())); //minus distance(weight) for maximum spanning tree
			}

			//kruskal step 1
			parents = new int[N+1];
			for(int i = 1; i <= N; i++)
				parents[i] = i;

			//kruskal step 2. Sorting in nondecreasing order.
			Arrays.sort(edges, new Comparator<int[]>() {
				@Override
					public int compare(int[] o1, int[] o2){
						return o1[2] - o2[2];
					}
			});

			//kruskal step 3. Number of edges in spanning tree +1 per each iterations.
			Answer = 0;
			for(int i = 0; i < E; i++){
				if(find(edges[i][0]) != find(edges[i][1])){
					Answer += edges[i][2];
					union(edges[i][0], edges[i][1]);
				}
			}
			Answer = -Answer;

			pw.println("#" + test_case + " " + Answer);
			pw.flush();
		}

		br.close();
		pw.close();
	}
}