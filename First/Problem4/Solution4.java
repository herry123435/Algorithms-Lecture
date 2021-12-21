import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

class Solution4 {
	static final int max_n = 1000;

	static int n, H;
	static int[] h = new int[max_n], d = new int[max_n-1];
	static int Answer;

	static int[] A = new int[10010001]; //10000 * k + ht
	static int[] B = new int[10010001];
	static int[] delta = new int[max_n];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {
			/*
			   n is the number of blocks, and H is the max tower height
			   read each height of the block to h[0], h[1], ... , h[n-1]
			   read the heights of the holes to d[0], d[1], ... , d[n-2]
			 */
			stk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stk.nextToken()); H = Integer.parseInt(stk.nextToken());
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				h[i] = Integer.parseInt(stk.nextToken());
			}
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < n-1; i++) {
				d[i] = Integer.parseInt(stk.nextToken());
			}
			
			for(int i = 1; i < n; i++){
				delta[i] = -d[i-1] + h[i];
			}
			//k=0
			for(int i = 1; i < h[0]; i++){
				A[i - 1] = 0;
			}
			for(int i = h[0]; i <= H; i++){
				A[i - 1] = 1;
			}
			for(int i = 1; i <= H; i++){
				B[i - 1] = 0;
			}

			for(int k = 1; k < n; k++){
				for(int ht = 1; ht <= H; ht++){
					A[10000*k + ht - 1] = 0;
					if(ht - delta[k] >= 1)
						A[10000*k + ht - 1] += A[10000*(k-1) + (ht-delta[k]) - 1];
					if(ht - h[k] >= 1)
						A[10000*k + ht - 1] += B[10000*(k-1) + (ht-h[k]) - 1];
					if(ht >= h[k])
						A[10000*k + ht - 1] += 1;
					B[10000*k + ht - 1] = A[10000*(k-1) + ht - 1] + B[10000*(k-1) + ht - 1];
					if(A[10000*k + ht - 1] >= 1000000)
						A[10000*k + ht - 1] = A[10000*k + ht - 1] % 1000000;
					if(B[10000*k + ht - 1] >= 1000000)
						B[10000*k + ht - 1] = B[10000*k + ht - 1] % 1000000;
				}
			}
			Answer = (A[10000*(n-1) + H - 1] + B[10000*(n-1) + H - 1]) % 1000000;

			pw.println("#" + test_case + " " + Answer);
			pw.flush();
		}

		br.close();
		pw.close();
	}
}

