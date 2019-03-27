package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Crush {
	int x, y;
	int wall; // 벽 부쉈는지
	int time;

	public Crush(int x, int y, int wall, int time) {
		super();
		this.x = x;
		this.y = y;
		this.wall = wall;
		this.time = time;
	}
}

// visited[][][] 3차원으로 해야하는 것 포인트! (1600번 문제도 마찬가지)
public class dfs_bfs_2206_r {

	static int N, M;
	static int[][] matrix;
	static boolean[][][] visited;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int result;

	public static void BFS() {
		visited[0][0][0] = true;
		Queue<Crush> q = new LinkedList<>();
		q.add(new Crush(0, 0, 0, 1));

		while (!q.isEmpty()) {
			Crush cr = q.poll();

			if (cr.x == N - 1 && cr.y == M - 1) {
				result = Math.min(result, cr.time);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nx = cr.x + dx[i];
				int ny = cr.y + dy[i];
				int w = cr.wall;

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				if (visited[nx][ny][w])
					continue;

				// 벽 존재
				if (matrix[nx][ny] == 1) {
					// 벽을 이미 부쉈으면 패스
					if (w == 1)
						continue;

					// 벽 부수고 지나감
					visited[nx][ny][1] = true;
					q.add(new Crush(nx, ny, 1, cr.time + 1));

				} else {
					visited[nx][ny][w] = true;
					q.add(new Crush(nx, ny, w, cr.time + 1));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Character.getNumericValue(tmp.charAt(j));
			}
		}

		result = Integer.MAX_VALUE;

		BFS();

		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}

		br.close();
	}

}
