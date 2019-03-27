package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class dfs_bfs_2667 {

	static int N;
	static int[][] matrix;
	static boolean[][] visited;

	static int[] count;
	static int total;

	public static void DFS(int x, int y) {
		visited[x][y] = true;
		count[total]++;

		if (x + 1 < N && matrix[x + 1][y] == 1 && !visited[x + 1][y]) {
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

		N = Integer.parseInt(br.readLine());

		matrix = new int[N][N];
		visited = new boolean[N][N];
		count = new int[N * N];

		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Character.getNumericValue(tmp.charAt(j));
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && matrix[i][j] == 1) {
					DFS(i, j);
					total++;
				}
			}
		}

		System.out.println(total);

		Arrays.sort(count);

		for (int e : count) {
			if (e != 0) {
				System.out.println(e);
			}
		}

		br.close();

	}

}
