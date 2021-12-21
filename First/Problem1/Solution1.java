import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

class Solution1 {
	static int n;                           // string length
	static String s;                        // string sequence
	static long[] Answer = new long[3];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {

			stk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stk.nextToken());
			s = br.readLine();

			long[][] A = new long[30][30];
			long[][] B = new long[30][30];
			long[][] C = new long[30][30];

			for(int i = 0; i < n; i++){
				A[i][i] = (s.charAt(i) == 'a') ? 1 : 0;
				B[i][i] = (s.charAt(i) == 'b') ? 1 : 0;
				C[i][i] = (s.charAt(i) == 'c') ? 1 : 0;
			}
			for(int r = 1; r <= n-1; r++){
				for(int i = 0; i <= n-r-1; i++){
					for(int k = i; k <= (i+r)-1; k++){
						A[i][i+r] += C[i][k] * A[k+1][i+r] + A[i][k] * C[k+1][i+r] + B[i][k] * C[k+1][i+r];
						B[i][i+r] += A[i][k] * A[k+1][i+r] + A[i][k] * B[k+1][i+r] + B[i][k] * B[k+1][i+r];
						C[i][i+r] += C[i][k] * C[k+1][i+r] + C[i][k] * B[k+1][i+r] + B[i][k] * A[k+1][i+r];
					}
				}
			}

			Answer[0] = A[0][n-1];  // the number of a
			Answer[1] = B[0][n-1];  // the number of b
			Answer[2] = C[0][n-1];  // the number of c

			pw.println("#" + test_case + " " + Answer[0] + " " + Answer[1] + " " + Answer[2]);
			pw.flush();
		}

		br.close();
		pw.close();
	}
}

