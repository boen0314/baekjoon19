package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Subin {
	int x;
	int time;

	public Subin(int x, int time) {
		super();
		this.x = x;
		this.time = time;
	}
}

public class dfs_bfs_1697 {

	static int N, K;

	static int[] dir = { 1, -1, 2 };
	static boolean[] visited;

	public static void BFS() {
		Queue<Subin> q = new LinkedList<>();
		q.add(new Subin(N, 0));
		visited[N] = true;

		while (!q.isEmpty()) {
			Subin su = q.poll();

			if (su.x == K) {
				System.out.println(su.time);
				return;
			}

			for (int i = 0; i < 3; i++) {
				int nextX;

				if (dir[i] != 2) {
					nextX = su.x + dir[i];
				} else {
					nextX = su.x * dir[i];
				}

				if (nextX < 0 || nextX > 100000)
					continue;

				if (visited[nextX])
					continue;

				q.add(new Subin(nextX, su.time + 1));
				visited[nextX] = true;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];

		if (N >= K) { // 수빈의 위치가 동생보다 크면
			System.out.println(N - K);
			return;
		}

		BFS();
	}

}
