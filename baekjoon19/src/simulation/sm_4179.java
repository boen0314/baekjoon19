package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class FJ implements Comparable<FJ> {
	int fj; // 불(0), 지훈(1)
	int x, y;
	int time;

	public FJ(int fj, int x, int y, int time) {
		super();
		this.fj = fj;
		this.x = x;
		this.y = y;
		this.time = time;
	}

	@Override
	public int compareTo(FJ o) {
		if (this.time < o.time) {
			return -1;
		} else if (this.time == o.time) {
			if (this.fj < o.fj) {
				return -1;
			} else if (this.fj > o.fj) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return 1;
		}
	}

}

// sm_bfs_5427, sm_bfs_3055 문제와 유사
public class sm_4179 {

	static int R, C;
	static char[][] matrix;
	static boolean[][] visited;

	static PriorityQueue<FJ> pq;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int result;

	public static void BFS() {

		while (!pq.isEmpty()) {
			FJ node = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= R + 2 || ny >= C + 2)
					continue;

				if (visited[nx][ny])
					continue;

				// 불이면
				if (node.fj == 0) {
					if (matrix[nx][ny] != '.')
						continue;

					matrix[nx][ny] = 'F';
					visited[nx][ny] = true;
					pq.add(new FJ(0, nx, ny, node.time + 1));
				}
				// 지훈이
				else {
					if (matrix[nx][ny] == 'E') {
						result = node.time + 1;
						return;
					} else if (matrix[nx][ny] == '.') {
						visited[nx][ny] = true;
						pq.add(new FJ(1, nx, ny, node.time + 1));
					}
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		matrix = new char[R + 2][C + 2];
		visited = new boolean[R + 2][C + 2];

		pq = new PriorityQueue<>();

		for (int i = 1; i <= R; i++) {
			String tmp = br.readLine();
			for (int j = 1; j <= C; j++) {
				matrix[i][j] = tmp.charAt(j - 1);

				if (matrix[i][j] == 'J') {
					pq.add(new FJ(1, i, j, 0));
					visited[i][j] = true;

					matrix[i][j] = '.';
				} else if (matrix[i][j] == 'F') {
					pq.add(new FJ(0, i, j, 0));
					visited[i][j] = true;
				}
			}
		}

		for (int i = 0; i <= R + 1; i++) {
			for (int j = 0; j <= C + 1; j++) {
				if (i == 0 || j == 0 || i == R + 1 || j == C + 1) {
					matrix[i][j] = 'E'; // 탈출구
				}
			}
		}

		BFS();

		System.out.println(result > 0 ? result : "IMPOSSIBLE");

		br.close();
	}

}
