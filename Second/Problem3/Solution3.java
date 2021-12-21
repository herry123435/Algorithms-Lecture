import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

class Solution3 {

	static int N, E;
	static int[][] edges;

	public static class edge{
		int end;
		int dis;
		public edge(int e, int d){
			this.end = e;
			this.dis = d;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("input3.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output3.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {

			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			E = Integer.parseInt(stk.nextToken());

			edges = new int[E][3];
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++){
				edges[i][0] = Integer.parseInt(stk.nextToken()); //start
				edges[i][1] = Integer.parseInt(stk.nextToken()); //end
				edges[i][2] = Integer.parseInt(stk.nextToken()); //distance(weight)
			}

			int[] d = new int[N+1];
			for(int i = 2; i <= N; i++)
				d[i] = Integer.MAX_VALUE;
			d[1] = 0; // starting vertex = 1

			long s1 = System.currentTimeMillis();
			for(int i = 1; i <= N-1; i++){
				for(int j = 0; j < E; j++){
					if(d[edges[j][0]] == Integer.MAX_VALUE)
						continue;
					if(d[edges[j][0]]+edges[j][2] < d[edges[j][1]])
						d[edges[j][1]] = d[edges[j][0]]+edges[j][2];
				}
			}
			long s2 = System.currentTimeMillis();

			pw.println("#" + test_case);
			for(int i = 1; i < N; i++){
				pw.print(d[i] + " ");
			}
			pw.println(d[N]);
			pw.println(s2 - s1);

			d = new int[N+1];
			for(int i = 2; i <= N; i++)
				d[i] = Integer.MAX_VALUE;
			d[1] = 0; // starting vertex = 1

			//better bellman-ford using BFS. We just have to perform relaxation in every vertices using edges starting there.
			//in bellman-ford algorithm, we lengthen available path length one by one like BST
			//but we can reverse back the path and perform relaxation again if the reverse path has minus weight
			//so we should modify BST to we can trace the path in the opposite way using nowvisit instead of visited, which make we can re-visit passed vertices.
			//nowvisit = 1 -> end vertices we will visit
			int[] nowvisit = new int[N+1];
			Queue<Integer> q = new LinkedList<Integer>();

			q.add(1); // starting vertex = 1
			nowvisit[1] = 1;

			LinkedList<edge>[] adjList = new LinkedList[N+1];
			for(int i = 0; i <= N; i++){
				adjList[i] = new LinkedList<edge>();
			}
			for(int i = 0; i < E; i++){
				adjList[edges[i][0]].add(new edge(edges[i][1], edges[i][2]));
			}

			int st;
			long s3 = System.currentTimeMillis();
			while(!q.isEmpty()){
				st = q.poll();
				nowvisit[st] = 0;
				for(int i = 0; i < adjList[st].size(); i++){
					if(d[st] + adjList[st].get(i).dis < d[adjList[st].get(i).end]){
						d[adjList[st].get(i).end] = d[st] + adjList[st].get(i).dis;
						if(nowvisit[adjList[st].get(i).end] != 1){
							nowvisit[adjList[st].get(i).end] = 1;
							q.add(adjList[st].get(i).end);
						}
					}
				}
			}
			long s4 = System.currentTimeMillis();

			for(int i = 1; i < N; i++){
				pw.print(d[i] + " ");
			}
			pw.println(d[N]);
			pw.println(s4 - s3);

			pw.flush();
		}

		br.close();
		pw.close();
	}
}