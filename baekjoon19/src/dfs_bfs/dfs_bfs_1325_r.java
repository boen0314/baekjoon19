package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class dfs_bfs_1325_r {

	static int N, M;
	static ArrayList<ArrayList<Integer>> list;
	static int maxHack;

	static StringBuilder sb;

	public static void BFS(int v) {
		boolean visited[] = new boolean[N + 1];
		visited[v] = true;

		Queue<Integer> q = new LinkedList<>();
		q.add(v);

		int cnt = 0;
		while (!q.isEmpty()) {
			int node = q.poll();
			cnt++; // 해킹되는 컴퓨터 개수 (중요 포인트)

			for (int e : list.get(node)) {
				if (!visited[e]) {
					visited[e] = true;
					q.add(e);
				}
			}
		}

		// 중요 포인트
		if (cnt > maxHack) {
			maxHack = cnt;

			sb = new StringBuilder();
			sb.append(v + " ");

		} else if (cnt == maxHack) {
			sb.append(v + " ");
		}

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
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// A->B 신뢰하면 B->A 해킹 됨
			list.get(b).add(a);
		}

		for (int i = 1; i <= N; i++) {
			BFS(i);
		}

		System.out.println(sb);
		br.close();
	}

}
