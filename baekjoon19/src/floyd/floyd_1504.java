package floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1->v1->v2->N 또는 1->v1->v2->N 으로 풀면됨
// 다익스트라로 풀면 복잡하므로 이런 문제는 플로이드와샬로 풀기 (long형으로!)
public class floyd_1504 {

	static int N, E;
	static int a, b, c; // 두 정점과 거리
	static int v1, v2; // 반드시 거쳐야 하는 두 정점

	static long[][] matrix;
	static final int INF = Integer.MAX_VALUE;

	static long result;

	public static void Floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		matrix = new long[N + 1][N + 1]; // 1번부터 시작

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j)
					matrix[i][j] = INF;
			}
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			matrix[a][b] = c; // 방향성 없음
			matrix[b][a] = c;
		}

		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		Floyd();

		long v1v2 = matrix[1][v1] + matrix[v1][v2] + matrix[v2][N];
		long v2v1 = matrix[1][v2] + matrix[v2][v1] + matrix[v1][N];

		result = Math.min(v1v2, v2v1);

		if (result >= INF)
			result = -1;

		System.out.println(result);
		br.close();
	}

}
