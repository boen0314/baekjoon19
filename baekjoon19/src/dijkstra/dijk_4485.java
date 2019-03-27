package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Rupee {
	int x, y;

	public Rupee(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class dijk_4485 {

	static int N;
	static int[][] matrix;
	static int[][] visited;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void BFS() {
		Queue<Rupee> q = new LinkedList<>();
		q.add(new Rupee(0, 0));
		visited[0][0] = matrix[0][0];

		while (!q.isEmpty()) {
			Rupee cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (visited[nx][ny] > visited[cur.x][cur.y] + matrix[nx][ny]) {
					visited[nx][ny] = visited[cur.x][cur.y] + matrix[nx][ny];
					q.add(new Rupee(nx, ny));
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());

			if (N == 0)
				break;

			matrix = new int[N][N];
			visited = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());

					visited[i][j] = Integer.MAX_VALUE;
				}
			}

			BFS();
			System.out.println("Problem " + tc++ + ": " + visited[N - 1][N - 1]);
		}
		br.close();
	}

}
