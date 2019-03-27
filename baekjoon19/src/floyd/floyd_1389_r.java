package floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class floyd_1389_r {

	static int N, M;
	static int[][] matrix;
	static int[] bacon;

	static int min;
	static int minUser;

	public static void Floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j)
						continue;
					if (matrix[i][k] == 0 || matrix[k][j] == 0)
						continue; // 여기 중요

					if (matrix[i][j] == 0) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					} else if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new int[N + 1][N + 1];
		bacon = new int[N + 1];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			matrix[a][b] = 1;
			matrix[b][a] = 1;
		}

		Floyd();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				bacon[i] += matrix[i][j];
			}
		}

		min = bacon[1]; // 초기화
		minUser = 1; // 초기화

		for (int i = 1; i <= N; i++) { // 최저 베이컨 찾기
			if (bacon[i] < min) {
				min = bacon[i];
				minUser = i;
			}
		}

		System.out.print(minUser);
	}

}
