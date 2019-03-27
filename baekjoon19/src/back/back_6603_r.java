package back;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class back_6603_r {

	static int K;
	static int[] S;
	static boolean[] visited;

	// 단일 스레드 환경에서는 StringBuilder, 멀티 스레드 환경에서는 StringBuffer를 사용 (속도 차이)
	static StringBuilder sb;

	private static void DFS(int idx, int cnt) {
		if (cnt == 6) {
			for (int i = 0; i < K; i++) {
				if (visited[i]) {
					// System.out.print(S[i] + " ");
					sb.append(S[i] + " ");
				}
			}
			sb.append("\n");
			// System.out.println();
			return;
		}

		if (idx >= K) {
			return;
		}

		visited[idx] = true;
		DFS(idx + 1, cnt + 1);
		visited[idx] = false;

		DFS(idx + 1, cnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());

			if (K == 0) {
				break;
			}

			S = new int[K];
			visited = new boolean[K];

			sb = new StringBuilder();

			for (int k = 0; k < K; k++) {
				S[k] = Integer.parseInt(st.nextToken());
			}

			DFS(0, 0);
			System.out.println(sb);
		}
	}

}
