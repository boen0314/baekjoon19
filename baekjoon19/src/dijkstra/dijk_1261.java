package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Room {
	int x, y;

	public Room(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class dijk_1261 {

	static int N, M;
	static int[][] matrix;
	static int[][] visited;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void BFS() {
		Queue<Room> q = new LinkedList<>();
		q.add(new Room(0, 0));
		visited[0][0] = 0;

		while (!q.isEmpty()) {
			Room cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				if (visited[nx][ny] > visited[cur.x][cur.y] + matrix[nx][ny]) {
					visited[nx][ny] = visited[cur.x][cur.y] + matrix[nx][ny];
					q.add(new Room(nx, ny));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Character.getNumericValue(tmp.charAt(j));

				visited[i][j] = Integer.MAX_VALUE; // 초기화
			}
		}

		BFS();

		System.out.println(visited[N - 1][M - 1]);
		br.close();

	}
}
