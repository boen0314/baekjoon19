package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Land {
	int x, y;
	int time;

	public Land(int x, int y, int time) {
		super();
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

public class dfs_bfs_2146_r {

	static int N;
	static int[][] matrix;
	static boolean[][] visited;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int landCnt;
	static int min;

	public static void Numbering(int x, int y) {
		matrix[x][y] = landCnt;
		visited[x][y] = true;

		Queue<Land> q = new LinkedList<>();
		q.add(new Land(x, y, 0));

		while (!q.isEmpty()) {
			Land land = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = land.x + dx[i];
				int ny = land.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (matrix[nx][ny] == 0 || visited[nx][ny])
					continue;

				matrix[nx][ny] = landCnt;
				visited[nx][ny] = true;
				q.add(new Land(nx, ny, 0));

			}
		}
	}

	public static void BFS(int x, int y, int n) {
		boolean[][] visit = new boolean[N][N];
		visit[x][y] = true;

		Queue<Land> q = new LinkedList<>();
		q.add(new Land(x, y, 0));

		while (!q.isEmpty()) {
			Land land = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = land.x + dx[i];
				int ny = land.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (matrix[nx][ny] == n || visit[nx][ny])
					continue;

				// 다른 대륙을 만났을 때
				if (matrix[nx][ny] != n && matrix[nx][ny] != 0) {
					min = Math.min(min, land.time);
					continue;
				}

				// 대륙 확장
				if (matrix[nx][ny] == 0) {
					visit[nx][ny] = true;
					q.add(new Land(nx, ny, land.time + 1));
				}
			}
		}

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
			}
		}
		// 대륙 넘버링
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (matrix[i][j] == 1 && !visited[i][j]) {
					landCnt++;
					Numbering(i, j);
				}
			}
		}

		min = Integer.MAX_VALUE;

		// 대륙 확장
		for (int n = 1; n <= landCnt; n++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (matrix[i][j] == n) {
						BFS(i, j, n); // 대륙 확장
					}
				}
			}
		}

		System.out.println(min);
		br.close();
	}

}
