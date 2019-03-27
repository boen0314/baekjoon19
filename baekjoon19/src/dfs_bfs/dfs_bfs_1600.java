package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Monkey {
	int x, y;
	int k;
	int time;

	public Monkey(int x, int y, int k, int time) {
		super();
		this.x = x;
		this.y = y;
		this.k = k;
		this.time = time;
	}
}

// visited 3차원 배열 활용이 포인트!
public class dfs_bfs_1600 {

	static int K;
	static int W, H;
	static int[][] matrix;
	static boolean[][][] visited;

	static int[] dx = { 0, 1, 0, -1, -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dy = { 1, 0, -1, 0, -2, -1, 1, 2, -2, -1, 1, 2 };

	public static int BFS() {
		visited[0][0][0] = true;
		Queue<Monkey> q = new LinkedList<>();
		q.add(new Monkey(0, 0, 0, 0));

		while (!q.isEmpty()) {
			Monkey m = q.poll();

			if (m.x == W - 1 && m.y == H - 1)
				return m.time;

			for (int i = 0; i < 4; i++) {
				int nx = m.x + dx[i];
				int ny = m.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= W || ny >= H)
					continue;

				if (visited[nx][ny][m.k] || matrix[nx][ny] == 1)
					continue;

				visited[nx][ny][m.k] = true;
				q.add(new Monkey(nx, ny, m.k, m.time + 1));
			}

			for (int i = 4; i < 12; i++) {
				int nx = m.x + dx[i];
				int ny = m.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= W || ny >= H)
					continue;

				if (m.k == K) // 빼먹으면 안됨
					continue;

				if (visited[nx][ny][m.k + 1] || matrix[nx][ny] == 1)
					continue;

				visited[nx][ny][m.k + 1] = true;
				q.add(new Monkey(nx, ny, m.k + 1, m.time + 1));
			}
		}

		return -1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		matrix = new int[W][H];
		visited = new boolean[W][H][K + 1];

		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < H; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(BFS());
		br.close();

	}

}
