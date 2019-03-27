package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class dfs_bfs_2468 {

	static int N;
	static int[][] matrix;
	static boolean[][] visited;

	static int maxHeight;
	static int max;

	public static void DFS(int x, int y, int h) {
		visited[x][y] = true;

		if (x + 1 < N && matrix[x + 1][y] > h && !visited[x + 1][y])
			DFS(x + 1, y, h);
		if (x - 1 >= 0 && matrix[x - 1][y] > h && !visited[x - 1][y])
			DFS(x - 1, y, h);
		if (y + 1 < N && matrix[x][y + 1] > h && !visited[x][y + 1])
			DFS(x, y + 1, h);
		if (y - 1 >= 0 && matrix[x][y - 1] > h && !visited[x][y - 1])
			DFS(x, y - 1, h);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		matrix = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, matrix[i][j]);
			}
		}

		for (int h = 0; h <= maxHeight; h++) {
			int safeArea = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (matrix[i][j] > h && !visited[i][j]) {
						DFS(i, j, h);
						safeArea++;
					}
				}
			}
			
			max = Math.max(max, safeArea);

			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false); // fill 메소드는 1차원 배열만 사용가능
			}
		}

		System.out.println(max);
		br.close();
	}

}
