package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS + 백트래킹 문제! (백트래킹 부분 잘 익히기)
public class rr_sm_14889 {

	static int N;
	static int[][] matrix;

	static boolean[] start;

	static int ss, sl, min;

	public static void DFS(int s, int len) {
		if (len == N / 2) {
			ss = 0;
			sl = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (start[i] && start[j])
						ss += matrix[i][j];
					if (!start[i] && !start[j])
						sl += matrix[i][j];
				}
			}
			min = Math.min(min, Math.abs(sl - ss));
			return;
		}

		// 핵심!♡♡♡♡♡ i=s 아니고 i=0 이면 시간초과 남
		for (int i = s; i < N; i++) {
			if (!start[i]) {
				start[i] = true;
				DFS(i + 1, len + 1);
				start[i] = false; // 백트래킹
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		matrix = new int[N][N];
		start = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		min = Integer.MAX_VALUE;

		DFS(0, 0);

		System.out.println(min);
		br.close();

	}

}
