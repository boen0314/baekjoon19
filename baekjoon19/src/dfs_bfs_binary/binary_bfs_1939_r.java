package dfs_bfs_binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Land {
	int v;
	long w;

	public Land(int v, long w) {
		super();
		this.v = v;
		this.w = w;
	}
}

public class binary_bfs_1939_r {

	static int N, M; // 섬의 개수, 다리 정보
	static int a, b; // 연결돼 있는 두 섬
	static long c; // 중량 제한

	static long max; // 즁량 제한 최대값

	static ArrayList<ArrayList<Land>> list;

	static int con_a, con_b; // 공장이 있는 두 섬

	public static void Binary() {
		long left = 0, right = max;
		long result = 0;

		while (left <= right) {
			long mid = (left + right) / 2;

			if (BFS(mid)) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(result);
	}

	public static boolean BFS(long weight) {
		boolean[] visited = new boolean[N + 1];
		visited[con_a] = true;

		Queue<Integer> q = new LinkedList<>();
		q.add(con_a);

		while (!q.isEmpty()) {
			int cur = q.poll();

			if (cur == con_b)
				return true; // 도착

			for (Land e : list.get(cur)) {
				int next_v = e.v;
				long next_w = e.w;

				// 방문했거나 중량이 많으면
				if (visited[next_v] || next_w < weight)
					continue;

				visited[next_v] = true;
				q.add(next_v);
			}
		}
		return false; // 도착 못하면
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());

			list.get(a).add(new Land(b, c)); // 양방향
			list.get(b).add(new Land(a, c));

			max = Math.max(max, c);
		}

		st = new StringTokenizer(br.readLine());
		con_a = Integer.parseInt(st.nextToken());
		con_b = Integer.parseInt(st.nextToken());

		Binary();
		br.close();
	}

}
