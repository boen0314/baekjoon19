package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dfs_bfs_2573 {

	static int N, M;
	static int[][] matrix;
	static int[][] tmp;
	static boolean[][] visited;

	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static int years; // 빙산 분리 년도

	public static void Melt(int x, int y) {
		for (int n = 0; n < 4; n++) {
			int nextX = x + dx[n];
			int nextY = y + dy[n];

			if (matrix[nextX][nextY] == 0) { // 주변이 바다
				tmp[x][y]++;
			}
		}
	}

	public static int Separate() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] > 0 && !visited[i][j]) {
					DFS(i, j);
					count++;
				}
			}
		}
		return count;
	}

	public static void DFS(int x, int y) {
		visited[x][y] = true;

		for (int n = 0; n < 4; n++) {
			int nextX = x + dx[n];
			int nextY = y + dy[n];

			if (nextX >= N || nextY >= M || nextX < 0 || nextY < 0)
				continue;

			if (visited[nextX][nextY] || matrix[nextX][nextY] == 0)
				continue;

			DFS(nextX, nextY);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			// 빙하 녹을 양 계산
			tmp = new int[N][M];
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					if (matrix[i][j] > 0) {
						Melt(i, j);
					}
				}
			}
			// 빙하 녹임
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					matrix[i][j] -= tmp[i][j]; // 빙하 녹음

					if (matrix[i][j] < 0) { // 음수값이 될 수 없음
						matrix[i][j] = 0;
					}
				}
			}

			years++; // 빙하 1년 녹음

			// 빙하 분리 되었는지
			visited = new boolean[N][M];
			int cnt = Separate();

			if (cnt >= 2) { // 빙하 분리됨
				System.out.println(years);
				return;
			} else if (cnt == 0) { // 빙하가 모두 녹을 때 까지 분리되지 않음
				System.out.println(0);
				return;
			}
		}
	}
}
