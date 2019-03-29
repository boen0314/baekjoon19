package back_recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class back_1987 {

	static int R, C;
	static char[][] matrix;
	static boolean[] visited;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int max;

	public static void DFS(int x, int y, int cnt) {
		int current = matrix[x][y] - 'A';
		visited[current] = true;

		max = Math.max(max, cnt);

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= R || ny >= C || nx < 0 || ny < 0)
				continue;

			int next = matrix[nx][ny] - 'A';

			if (visited[next])
				continue;

			DFS(nx, ny, cnt + 1);
			visited[next] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		matrix = new char[R][C];
		visited = new boolean[26];

		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				matrix[i][j] = tmp.charAt(j);
			}
		}

		DFS(0, 0, 1);

		System.out.println(max);
		br.close();
	}

}
