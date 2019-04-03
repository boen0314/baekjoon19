package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 단순 무식하게 푸는게 효율적인 문제!(좌표 이용)
// 큐빙처럼 노가다 문제ㅠ 대체 어디서 틀린지 모르겠음.. 테스트케이스는 맞는데..
// DFS로 풀 수도 있음!(방향 이용 ) -> 시도했다가 복잡해서 노가다로 시도했으나 fail..
public class xx_sm_14500 {

	static int N, M;
	static int[][] m;

	static int sum, result;

	// ㅡ
	public static void Block1() {
		// 가로
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M - 3; j++) {
				sum = m[i][j] + m[i][j + 1] + m[i][j + 2] + m[i][j + 3];
				result = Math.max(result, sum);
			}
		}
		// 세로
		for (int i = 0; i < N - 3; i++) {
			for (int j = 0; j < M; j++) {
				sum = m[i][j] + m[i + 1][j] + m[i + 2][j] + m[i + 3][j];
				result = Math.max(result, sum);
			}
		}
	}

	// ㅁ
	public static void Block2() {
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 1; j++) {
				sum = m[i][j] + m[i][j + 1] + m[i + 1][j] + m[i + 1][j + 1];
				result = Math.max(result, sum);
			}
		}
	}

	// L, z, ㅜ
	public static void Block3() {
		// 가로
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				// L
				sum = m[i][j] + m[i + 1][j] + m[i + 1][j + 1] + m[i + 1][j + 2];
				result = Math.max(result, sum);
				sum = m[i][j + 2] + m[i + 1][j + 2] + m[i + 1][j + 1] + m[i + 1][j];
				result = Math.max(result, sum);
				// Z
				sum = m[i][j] + m[i][j + 1] + m[i + 1][j + 1] + m[i + 1][j + 2];
				result = Math.max(result, sum);
				sum = m[i][j + 2] + m[i][j + 1] + m[i + 1][j + 1] + m[i + 1][j];
				result = Math.max(result, sum);
				// ㅜ
				sum = m[i][j] + m[i][j + 1] + m[i][j + 2] + m[i + 1][j + 1];
				result = Math.max(result, sum);
				sum = m[i][j + 1] + m[i + 1][j] + m[i + 1][j + 1] + m[i + 1][j + 2];
				result = Math.max(result, sum);
			}
		}
		// 세로
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				// L
				sum = m[i][j] + m[i][j + 1] + m[i + 1][j + 1] + m[i + 2][j + 1];
				result = Math.max(result, sum);
				sum = m[i][j] + m[i + 1][j] + m[i + 2][j] + m[i][j + 1];
				result = Math.max(result, sum);
				// Z
				sum = m[i][j] + m[i + 1][j] + m[i + 1][j + 1] + m[i + 2][j + 1];
				result = Math.max(result, sum);
				sum = m[i][j + 1] + m[i + 1][j + 1] + m[i + 1][j] + m[i + 2][j];
				result = Math.max(result, sum);
				// ㅜ
				sum = m[i][j] + m[i + 1][j] + m[i + 2][j] + m[i + 1][j + 1];
				result = Math.max(result, sum);
				sum = m[i][j + 1] + m[i + 1][j + 1] + m[i + 2][j + 1] + m[i + 1][j];
				result = Math.max(result, sum);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		m = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Block1();
		Block2();
		Block3();

		System.out.println(result);
		br.close();

	}

}
