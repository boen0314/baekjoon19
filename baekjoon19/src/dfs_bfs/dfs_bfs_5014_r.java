package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class dfs_bfs_5014_r {

	static int F, S, G, U, D;
	static int[] dir;
	static int[] visited;

	public static void BFS() {
		visited[S] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(S);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 0; i < 2; i++) {
				int next = cur + dir[i];

				if (next < 1 || next > F)
					continue;

				if (visited[next] > 0)
					continue;

				// 중요 포인트
				visited[next] = visited[cur] + 1;
				q.add(next);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		dir = new int[] { U, -D };
		visited = new int[F + 1];

		BFS();

		if (visited[G] == 0) {
			System.out.println("use the stairs");
		} else {
			System.out.println(visited[G] - 1);
		}

	}

}
