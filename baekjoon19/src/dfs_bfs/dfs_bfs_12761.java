package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class DG {
	int x;
	int time;

	public DG(int x, int time) {
		super();
		this.x = x;
		this.time = time;
	}
}

public class dfs_bfs_12761 {

	static int A, B;
	static int N, M;

	static boolean[] visited;

	static int[] dx;

	private static void BFS() {
		Queue<DG> q = new LinkedList<>();
		q.add(new DG(N, 0));
		visited[N] = true;

		while (!q.isEmpty()) {
			DG dg = q.poll();

			if (dg.x == M) {
				System.out.println(dg.time);
				return;
			}

			for (int i = 0; i < 6; i++) {
				int nx = dg.x + dx[i];

				if (nx >= 0 && nx <= 100000 && !visited[nx]) {
					visited[nx] = true;
					q.add(new DG(nx, dg.time + 1));
				}
			}

			for (int i = 6; i < 8; i++) {
				int nx = dg.x * dx[i];

				if (nx >= 0 && nx <= 100000 && !visited[nx]) {
					visited[nx] = true;
					q.add(new DG(nx, dg.time + 1));
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];

		dx = new int[] { 1, -1, A, -A, B, -B, A, B };

		BFS();

		br.close();
	}

}
