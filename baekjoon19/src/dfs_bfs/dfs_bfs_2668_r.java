package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//DFS + "사이클 찾기" 문제 (생소한 문제였음ㅠㅠ)
public class dfs_bfs_2668_r {

	static int N;
	static int[] matrix;
	static int[] visited;

	static int cnt;

	// 사이클 찾는 전형적인 유형임. 익혀놓기
	public static void DFS(int idx) {
		if (visited[idx] == 2)
			return;

		visited[idx]++;
		DFS(matrix[idx]);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		matrix = new int[N + 1];
		visited = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			matrix[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= N; i++) {
			DFS(i);

			// visited = 2 인 부분 제외하고 초기화 (사이클 존재)
			for (int j = 1; j <= N; j++) {
				if (visited[j] == 1)
					visited[j] = 0;
			}
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i] == 2) {
				sb.append(i + "\n");
				cnt++;
			}
		}

		System.out.println(cnt);
		System.out.println(sb);

		br.close();
	}

}
