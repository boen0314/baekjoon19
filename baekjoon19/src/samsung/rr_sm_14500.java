package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 주석 달아놓은 부분 잘 익혀두기!♡♡♡♡♡
public class rr_sm_14500 {

	static int N, M;
	static int[][] matrix;
	static boolean[][] visited;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int result;

	// ㅗ 제외한 블록은 모두 DFS경로와 같음(모든 블록 경우의 수=모든 DFS경로)
	// 직접 그려보면 이해가 쉬움
	public static void DFS(int x, int y, int len, int sum) {
		if (len == 4) {
			result = Math.max(result, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			if (visited[nx][ny])
				continue;

			visited[nx][ny] = true;
			DFS(nx, ny, len + 1, sum + matrix[nx][ny]);
			visited[nx][ny] = false; // 백트래킹
		}

	}

	// ㅗ 블럭은 BFS경로 중 한 방향만 빠진 것
	public static void BFS(int x, int y, int s) {

		for (int d = 0; d < 4; d++) {
			int sum = s;
			for (int i = 0; i < 4; i++) {
				if (d == i) // 한 방향 제외!♡♡♡♡
					continue;

				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					break;

				sum += matrix[nx][ny];

				// 이부분 빼먹으면 안됨!!♡♡♡♡♡
				// 빼먹으면 d가 3일 경우는 i가 2까지만 진행돼서 결과값 갱신 안됨
				if (d == 3 && i == 2) { // 블록이 온전한 경우
					result = Math.max(result, sum);
					break;
				}

				if (i == 3) { // 블록이 온전한 경우
					result = Math.max(result, sum);
					break;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				DFS(i, j, 1, matrix[i][j]);
				visited[i][j] = false;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				BFS(i, j, matrix[i][j]);
			}
		}

		System.out.println(result);
		br.close();

	}

}
