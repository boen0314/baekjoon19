package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dfs_bfs_11724 {

	static int N, M;
	static int u, v;

	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited;

	public static void DFS(int n) {
		visited[n] = true;

		for (int e : list.get(n)) {
			if (!visited[e]) {
				DFS(e);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
		}

		visited = new boolean[N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());

			// 문제에서 정점 1부터 시작
			list.get(u - 1).add(v - 1);
			list.get(v - 1).add(u - 1);
		}

		int count = 0;
		for (int n = 0; n < N; n++) {
			if (!visited[n]) {
				DFS(n);
				count++;
			}
		}

		System.out.println(count);

		br.close();
	}

}
