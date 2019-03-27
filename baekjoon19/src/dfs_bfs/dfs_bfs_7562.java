package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Knight {
	int x, y;
	int time;

	Knight(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

public class dfs_bfs_7562 {

	static int T;
	static int I;

	static int cx, cy; // 나이트가 현재 있는 칸
	static int mx, my; // 나이트가 이동하려는 칸

	static boolean[][] visited;

	static int[] dx = { 2, 2, 1, 1, -2, -2, -1, -1 };
	static int[] dy = { 1, -1, 2, -2, 1, -1, 2, -2 };

	static int time;

	public static void BFS(int x, int y) {
		visited[x][y] = true;

		Queue<Knight> q = new LinkedList<>();
		q.add(new Knight(x, y, 0));

		while (!q.isEmpty()) {
			Knight k = q.poll();

			for (int i = 0; i < 8; i++) {
				int nx = k.x + dx[i];
				int ny = k.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= I || ny >= I)
					continue;

				if (visited[nx][ny])
					continue;

				if (nx == mx && ny == my) {
					time = Math.min(time, k.time + 1);
					continue;
				}

				visited[nx][ny] = true;
				q.add(new Knight(nx, ny, k.time + 1));
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			I = Integer.parseInt(br.readLine());

			visited = new boolean[I][I];

			st = new StringTokenizer(br.readLine());
			cx = Integer.parseInt(st.nextToken());
			cy = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			mx = Integer.parseInt(st.nextToken());
			my = Integer.parseInt(st.nextToken());

			time = Integer.MAX_VALUE;
			BFS(cx, cy);

			if (time != Integer.MAX_VALUE)
				System.out.println(time);
			else
				System.out.println(0);
		}
	}

}
