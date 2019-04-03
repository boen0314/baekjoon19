package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS로만 푼 문제 (전형적인 DFS 문제)
public class sm_14888_2 {

	static int N;
	static int[] A;
	static int plus, minus, multiple, divide;

	static int min, max;

	public static void DFS(int rs, int pl, int mi, int mu, int di, int cnt) {
		if (cnt == N) {
			min = Math.min(min, rs);
			max = Math.max(max, rs);
			return;
		}

		if (pl != plus) {
			DFS(rs + A[cnt], pl + 1, mi, mu, di, cnt + 1);
		}
		if (mi != minus) {
			DFS(rs - A[cnt], pl, mi + 1, mu, di, cnt + 1);
		}
		if (mu != multiple) {
			DFS(rs * A[cnt], pl, mi, mu + 1, di, cnt + 1);
		}
		if (di != divide) {
			DFS(rs / A[cnt], pl, mi, mu, di + 1, cnt + 1);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N]; // 피연산자

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		plus = Integer.parseInt(st.nextToken());
		minus = Integer.parseInt(st.nextToken());
		multiple = Integer.parseInt(st.nextToken());
		divide = Integer.parseInt(st.nextToken());

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		DFS(A[0], 0, 0, 0, 0, 1);

		System.out.println(max);
		System.out.println(min);
		br.close();

	}

}