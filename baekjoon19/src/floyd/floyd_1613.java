package floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class floyd_1613 {

	static int N, K, S; // 사건의 개수, 관계의 개수, 알고 싶은 사건 수
	static boolean[][] matrix;

	public static void Floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (matrix[i][k] && matrix[k][j])
						matrix[i][j] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		matrix = new boolean[N + 1][N + 1]; // 1부터 시작

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			matrix[x][y] = true; // 단방향
		}

		Floyd();

		S = Integer.parseInt(br.readLine());

		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (matrix[x][y] && !matrix[y][x])
				System.out.println(-1);
			else if (matrix[y][x] && !matrix[x][y])
				System.out.println(1);
			else
				System.out.println(0);
		}

		br.close();
	}

}
