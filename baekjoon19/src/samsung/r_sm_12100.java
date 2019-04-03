package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 뿌요뿌요(11559)문제 풀 줄 알면 풀 수 있는 문제! + 백트래킹(임시배열 저장)
public class r_sm_12100 {

	static int N;
	static int[][] matrix;

	static int max;

	public static void BackTracking(int cnt) {
		if (cnt == 5) {
			// 블럭 최대값 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(max, matrix[i][j]);
				}
			}
			return;
		}

		int[][] tmp = new int[N][N]; // 백트래킹을 위한 임시 배열

		// 현재상태 임시배열에 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmp[i][j] = matrix[i][j];
			}
		}

		// 4방향, 왼(0) 오(1) 위(2) 아래(3)
		for (int m = 0; m < 4; m++) {
			// 왼쪽
			if (m == 0) {
				for (int i = 0; i < N; i++) {
					// 세로(i)고정, 가로(j)만 움직임, 0 밀기
					for (int j = 0; j < N - 1; j++) {
						if (matrix[i][j] == 0 && matrix[i][j + 1] != 0) {
							matrix[i][j] = matrix[i][j + 1]; // 자리 바꿈
							matrix[i][j + 1] = 0;
							// 앞쪽에 있는 0까지 계속 바꿔줘야함★★
							// 다시 처음부터 시작
							j = -1; // 핵심!♡♡♡♡♡ (뿌요뿌요 문제랑 똑같음)
						}
					}
					// 탐색 (같은 숫자 합치기)
					for (int j = 0; j < N - 1; j++) {
						if (matrix[i][j] != 0 && matrix[i][j] == matrix[i][j + 1]) {
							matrix[i][j] += matrix[i][j + 1];
							matrix[i][j + 1] = 0;
						}
					}
					// 다시 0 밀기
					for (int j = 0; j < N - 1; j++) {
						if (matrix[i][j] == 0 && matrix[i][j + 1] != 0) {
							matrix[i][j] = matrix[i][j + 1];
							matrix[i][j + 1] = 0;
							j = -1;
						}
					}
				}
			}
			// 오른쪽
			else if (m == 1) {
				for (int i = 0; i < N; i++) {
					// 0 밀기
					for (int j = N - 1; j >= 1; j--) {
						if (matrix[i][j] == 0 && matrix[i][j - 1] != 0) {
							matrix[i][j] = matrix[i][j - 1];
							matrix[i][j - 1] = 0;
							j = N;
						}
					}
					// 탐색
					for (int j = N - 1; j >= 1; j--) {
						if (matrix[i][j] != 0 && matrix[i][j] == matrix[i][j - 1]) {
							matrix[i][j] += matrix[i][j - 1];
							matrix[i][j - 1] = 0;
						}
					}
					// 다시 0 밀기
					for (int j = N - 1; j >= 1; j--) {
						if (matrix[i][j] == 0 && matrix[i][j - 1] != 0) {
							matrix[i][j] = matrix[i][j - 1];
							matrix[i][j - 1] = 0;
							j = N;
						}
					}
				}
			}
			// 위
			else if (m == 2) {
				for (int j = 0; j < N; j++) {
					// 가로(j)고정, 세로(i)만 움직임, 0 밀기
					for (int i = 0; i < N - 1; i++) {
						if (matrix[i][j] == 0 && matrix[i + 1][j] != 0) {
							matrix[i][j] = matrix[i + 1][j];
							matrix[i + 1][j] = 0;
							i = -1;
						}
					}
					// 탐색
					for (int i = 0; i < N - 1; i++) {
						if (matrix[i][j] != 0 && matrix[i][j] == matrix[i + 1][j]) {
							matrix[i][j] += matrix[i + 1][j];
							matrix[i + 1][j] = 0;
						}
					}
					// 다시 0 밀기
					for (int i = 0; i < N - 1; i++) {
						if (matrix[i][j] == 0 && matrix[i + 1][j] != 0) {
							matrix[i][j] = matrix[i + 1][j];
							matrix[i + 1][j] = 0;
							i = -1;
						}
					}
				}
			}
			// 아래
			else if (m == 3) {
				for (int j = 0; j < N; j++) {
					// 0 밀기
					for (int i = N - 1; i >= 1; i--) {
						if (matrix[i][j] == 0 && matrix[i - 1][j] != 0) {
							matrix[i][j] = matrix[i - 1][j];
							matrix[i - 1][j] = 0;
							i = N;
						}
					}
					// 탐색
					for (int i = N - 1; i >= 1; i--) {
						if (matrix[i][j] != 0 && matrix[i][j] == matrix[i - 1][j]) {
							matrix[i][j] += matrix[i - 1][j];
							matrix[i - 1][j] = 0;
						}
					}
					// 다시 0 밀기
					for (int i = N - 1; i >= 1; i--) {
						if (matrix[i][j] == 0 && matrix[i - 1][j] != 0) {
							matrix[i][j] = matrix[i - 1][j];
							matrix[i - 1][j] = 0;
							i = N;
						}
					}
				}
			}

			BackTracking(cnt + 1); // 다음 단계

			// 백트래킹, 이전 상태로 되돌림
			// 다시 돌려놓고 for문 다시 돌려야 함
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = tmp[i][j];
				}
			}

		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		matrix = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		BackTracking(0);

		System.out.println(max);

		br.close();
	}

}
