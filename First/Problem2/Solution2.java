import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

class Solution2 {
	static final int max_n = 100000;

	static int n;
	static String s;
	static int Answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {

			stk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stk.nextToken());
			s = br.readLine();

			int L[][] = new int[5000][5000];

			for(int i = 0; i < n; i++)
			  	L[i][i] = 1;
			for(int i = 0; i < n-1; i++)
			 	L[i][i+1] = (s.charAt(i) == s.charAt(i+1)) ? 2 : 1;
			 
			for(int r = 2; r <= n-1; r++){
				for(int i = 0; i <= n-r-1; i++){
					if(s.charAt(i) == s.charAt(i+r))
					 	L[i][i+r] = L[i+1][i+r-1] + 2;
					else
					 	L[i][i+r] = (L[i][i+r-1] > L[i+1][i+r]) ? L[i][i+r-1] : L[i+1][i+r];
				}
			}

			Answer = L[0][n-1];

			pw.println("#" + test_case + " " + Answer);
			pw.flush();
		}

		br.close();
		pw.close();
	}
}

