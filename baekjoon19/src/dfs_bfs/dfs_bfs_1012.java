package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dfs_bfs_1012 {

	static int T;
	static int M, N, K;

	static int[][] matrix;
	static boolean[][] visited;

	public static void DFS(int x, int y) {
		visited[x][y] = true;

		if (x + 1 < M && matrix[x + 1][y] == 1 && !visited[x + 1][y]) {
			DFS(x + 1, y);
		}
		if (x - 1 >= 0 && matrix[x - 1][y] == 1 && !visited[x - 1][y]) {
			DFS(x - 1, y);
		}
		if (y + 1 < N && matrix[x][y + 1] == 1 && !visited[x][y + 1]) {
			DFS(x, y + 1);
		}
		if (y - 1 >= 0 && matrix[x][y - 1] == 1 && !visited[x][y - 1]) {
			DFS(x, y - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			matrix = new int[M][N];
			visited = new boolean[M][N];

			int x, y;
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());

				matrix[x][y] = 1;
			}

			int count = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && matrix[i][j] == 1) {
						DFS(i, j);
						count++;
					}
				}
			}

			System.out.println(count);
		}

		br.close();
	}
}
