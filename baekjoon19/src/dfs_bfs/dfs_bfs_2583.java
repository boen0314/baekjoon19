package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class dfs_bfs_2583 {

	static int M, N, K;
	static int[][] matrix;
	static boolean[][] visited;

	static int[] count;
	static int total;

	public static void DFS(int x, int y) {
		visited[x][y] = true;
		count[total]++;

		if (x + 1 < N && !visited[x + 1][y] && matrix[x + 1][y] == 0) {
			DFS(x + 1, y);
		}
		if (x - 1 >= 0 && !visited[x - 1][y] && matrix[x - 1][y] == 0) {
			DFS(x - 1, y);
		}
		if (y + 1 < M && !visited[x][y + 1] && matrix[x][y + 1] == 0) {
			DFS(x, y + 1);
		}
		if (y - 1 >= 0 && !visited[x][y - 1] && matrix[x][y - 1] == 0) {
			DFS(x, y - 1);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];
		visited = new boolean[N][M];
		count = new int[N * M];

		for (int k = 0; k < K; k++) {
			int x1, x2, y1, y2;
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());

			for (int i = x1; i < x2; i++) {
				for (int j = y1; j < y2; j++) {
					matrix[i][j] = 1;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && matrix[i][j] == 0) {
					DFS(i, j);
					total++;
				}
			}
		}

		System.out.println(total);

		Arrays.sort(count);

		for (int e : count) {
			if (e != 0) {
				System.out.print(e + " ");
			}
		}

	}

}
