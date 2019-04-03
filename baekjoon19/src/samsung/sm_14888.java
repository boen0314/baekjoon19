package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS + 백트래킹 전형적인 문제 (DFS만으로도 풀 수 있음)
public class sm_14888 {

	static int N;
	static int[] A;
	static int[] op;

	static int min, max;

	public static void DFS(int rs, int cnt) {
		if (cnt == N) {
			min = Math.min(min, rs);
			max = Math.max(max, rs);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] != 0) {
				op[i]--;

				if (i == 0)
					DFS(rs + A[cnt], cnt + 1);
				else if (i == 1)
					DFS(rs - A[cnt], cnt + 1);
				else if (i == 2)
					DFS(rs * A[cnt], cnt + 1);
				else if (i == 3)
					DFS(rs / A[cnt], cnt + 1);

				op[i]++; // 백트래킹 (빼먹지 않게 주의)
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N]; // 피연산자
		op = new int[4]; // 연산자

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		DFS(A[0], 1);

		System.out.println(max);
		System.out.println(min);
		br.close();

	}

}
