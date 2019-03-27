package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class WG implements Comparable<WG> {
	int wg; // 물(0), 고슴도치(1)
	int x, y;
	int time;

	public WG(int wg, int x, int y, int time) {
		super();
		this.wg = wg;
		this.x = x;
		this.y = y;
		this.time = time;
	}

	@Override
	public int compareTo(WG o) {
		if (this.time < o.time) {
			return -1;
		} else if (this.time > o.time) {
			return 1;
		} else {
			if (this.wg < o.wg) {
				return -1;
			} else if (this.wg > o.wg) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}

public class sm_3055 {

	static int R, C;
	static char[][] matrix;
	static boolean[][] visited;

	static PriorityQueue<WG> pq;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int result;

	public static void BFS() {
		while (!pq.isEmpty()) {
			WG node = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;

				if (visited[nx][ny])
					continue;

				if (node.wg == 0) {
					if (matrix[nx][ny] != '.')
						continue;

					matrix[nx][ny] = '*';
					visited[nx][ny] = true;
					pq.add(new WG(0, nx, ny, node.time + 1));

				} else {
					if (matrix[nx][ny] == 'D') {
						result = Math.min(result, node.time + 1);
						continue;
					} else if (matrix[nx][ny] != '.') {
						continue;
					}

					visited[nx][ny] = true;
					pq.add(new WG(1, nx, ny, node.time + 1));
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		matrix = new char[R][C];
		visited = new boolean[R][C];

		pq = new PriorityQueue<>();

		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				matrix[i][j] = tmp.charAt(j);

				if (matrix[i][j] == 'S') { // 고슴도치
					matrix[i][j] = '.'; // 빈공간으로 바꿈
					visited[i][j] = true;
					pq.add(new WG(1, i, j, 0));
				} else if (matrix[i][j] == '*') { // 물
					pq.add(new WG(0, i, j, 0));
				}
			}
		}

		result = Integer.MAX_VALUE;

		BFS();

		if (result == Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(result);
		}

		br.close();
	}

}
