import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

class Solution3 {
	static final int max_n = 1000000;

	static int n;
	static int[][] A = new int[3][max_n];
	static int Answer;

	static int w(int i, int p){
		if (p == 0)
			return A[0][i] - A[2][i];
		else if (p == 1)
			return A[0][i] - A[1][i];
		else if(p == 2)
			return A[1][i] - A[2][i];
		else if(p == 3)
			return A[1][i] - A[0][i];
		else if(p == 4)
			return A[2][i] - A[1][i];
		else if(p == 5)
			return A[2][i] - A[0][i];
		return -100000000;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {
			
			stk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stk.nextToken());
			for (int i = 0; i < 3; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					A[i][j] = Integer.parseInt(stk.nextToken());
				}
			}

			int c[] = new int[n*10];
			int max = -2100000000;

			for(int p = 0; p <= 5; p++)
				c[p] = w(0,p);
			for(int i = 1; i <= n-1; i++){
				c[i*10 + 0] = (c[(i-1)*10 + 3] > c[(i-1)*10 + 4]) ? c[(i-1)*10 + 3] + w(i,0) : c[(i-1)*10 + 4] + w(i,0);
				c[i*10 + 1] = (c[(i-1)*10 + 2] > c[(i-1)*10 + 5]) ? c[(i-1)*10 + 2] + w(i,1) : c[(i-1)*10 + 5] + w(i,1);
				c[i*10 + 2] = (c[(i-1)*10 + 1] > c[(i-1)*10 + 5]) ? c[(i-1)*10 + 1] + w(i,2) : c[(i-1)*10 + 5] + w(i,2);
				c[i*10 + 3] = (c[(i-1)*10 + 0] > c[(i-1)*10 + 4]) ? c[(i-1)*10 + 0] + w(i,3) : c[(i-1)*10 + 4] + w(i,3);
				c[i*10 + 4] = (c[(i-1)*10 + 0] > c[(i-1)*10 + 3]) ? c[(i-1)*10 + 0] + w(i,4) : c[(i-1)*10 + 3] + w(i,4);
				c[i*10 + 5] = (c[(i-1)*10 + 1] > c[(i-1)*10 + 2]) ? c[(i-1)*10 + 1] + w(i,5) : c[(i-1)*10 + 2] + w(i,5);
			}
			for(int p = 0; p <= 5; p++){
				if(c[(n-1)*10 + p] > max)
					max = c[(n-1)*10 + p];
			}
			Answer = max; // largest in c[n-1][0,1,2,3,4,5] = c[(n-1) * 10 + 0,1,2,3,4,5]

			pw.println("#" + test_case + " " + Answer);
			pw.flush();
		}

		br.close();
		pw.close();
	}
}

