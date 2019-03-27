package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Micky {
	int x, y;

	public Micky(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class sm_3184 {

	static int R, C;
	static int[][] matrix;
	static boolean[][] visited;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int sheep, wolf;
	static int O, V; // 양, 늑대 최종 결과값

	public static void BFS(int x, int y) {
		Queue<Micky> q = new LinkedList<>();
		q.add(new Micky(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Micky mc = q.poll();

			if (matrix[mc.x][mc.y] == 'o') {
				sheep++;
			} else if (matrix[mc.x][mc.y] == 'v') {
				wolf++;
			}

			for (int i = 0; i < 4; i++) {
				int nx = mc.x + dx[i];
				int ny = mc.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;

				if (matrix[nx][ny] == '#' || visited[nx][ny])
					continue;

				visited[nx][ny] = true;
				q.add(new Micky(nx, ny));
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		matrix = new int[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				matrix[i][j] = tmp.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (matrix[i][j] != '#' && !visited[i][j]) {
					BFS(i, j);

					if (sheep > wolf) {
						O += sheep;
					} else {
						V += wolf;
					}

					sheep = 0;
					wolf = 0;
				}
			}
		}

		System.out.println(O + " " + V);
		br.close();
	}

}
