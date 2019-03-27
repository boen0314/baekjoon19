package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Robot {
	int x, y;
	int dir;
	int time;

	public Robot(int x, int y, int dir, int time) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.time = time;
	}
}

public class sm_1726_r {

	static int M, N;
	static int[][] matrix;
	static boolean[][][] visited;

	static int sx, sy, sd;
	static int ax, ay, ad;

	static int[] dx = { 0, 0, 0, 1, -1 }; // 동(1)서(2)남(3)북(4)
	static int[] dy = { 0, 1, -1, 0, 0 }; // x 세로, y 가로

	static int result;

	public static void BFS() {
		Queue<Robot> q = new LinkedList<>();
		q.add(new Robot(sx, sy, sd, 0));
		visited[sx][sy][sd] = true;

		while (!q.isEmpty()) {
			Robot r = q.poll();

			if (r.x == ax && r.y == ay && r.dir == ad) {
				result = Math.min(result, r.time);
				continue;
			}

			// Go k
			for (int k = 1; k <= 3; k++) {
				int nx = r.x + dx[r.dir] * k;
				int ny = r.y + dy[r.dir] * k;

				if (nx < 1 || ny < 1 || nx > M || ny > N)
					break; // 더이상 직진 불가

				if (matrix[nx][ny] == 1)
					break; // 더이상 직진 불가

				if (visited[nx][ny][r.dir])
					continue;

				q.add(new Robot(nx, ny, r.dir, r.time + 1));
				visited[nx][ny][r.dir] = true;
			}

			// Turn dir
			for (int i = 1; i <= 4; i++) {
				// 90도 회전한 방향 아님
				if (r.dir == i || Reverse(r.dir) == i) {
					continue;
				}

				if (visited[r.x][r.y][i])
					continue;

				q.add(new Robot(r.x, r.y, i, r.time + 1));
				visited[r.x][r.y][i] = true;
			}
		}
	}

	public static int Reverse(int d) {
		switch (d) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		default:
			return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		// (1,1)부터 시작 ★★★★ 실수 주의
		matrix = new int[M + 1][N + 1];
		visited = new boolean[M + 1][N + 1][5];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		sd = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		ax = Integer.parseInt(st.nextToken());
		ay = Integer.parseInt(st.nextToken());
		ad = Integer.parseInt(st.nextToken());

		result = Integer.MAX_VALUE;

		BFS();

		System.out.println(result);
		br.close();

	}

}
