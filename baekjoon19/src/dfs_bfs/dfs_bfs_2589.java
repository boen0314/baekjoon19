package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Treasure {
	int x, y;
	int time;

	public Treasure(int x, int y, int time) {
		super();
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

public class dfs_bfs_2589 {

	static int N, M;
	static char[][] matrix;
	static boolean[][] visited;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static int max;

	public static void BFS(int x, int y) {
		visited[x][y] = true;

		Queue<Treasure> q = new LinkedList<>();
		q.add(new Treasure(x, y, 0));

		while (!q.isEmpty()) {
			Treasure t = q.poll();

			max = Math.max(max, t.time);

			for (int i = 0; i < 4; i++) {
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}

				if (visited[nx][ny] || matrix[nx][ny] == 'W') {
					continue;
				}

				q.add(new Treasure(nx, ny, t.time + 1));
				visited[nx][ny] = true;
			}
		}

		for (int i = 0; i < N; i++) { // 방문 초기화
			for (int j = 0; j < M; j++) {
				visited[i][j] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new char[N][M];
		visited = new boolean[N][M];

		String tmp;
		for (int i = 0; i < N; i++) {
			tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				matrix[i][j] = tmp.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] == 'L') {
					BFS(i, j);
				}
			}
		}

		System.out.println(max);
		br.close();
	}

}
