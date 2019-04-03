package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class rrr_sm_14503 {

	static int N, M;
	static int r, c, d; // 로봇 청소기의 좌표, 바라보는 방향 d
	static int[][] matrix;

	static int[] dx = { -1, 0, 1, 0 }; // 북동남서 순서
	static int[] dy = { 0, 1, 0, -1 }; // 순서는 문제에서 주어짐!(주의!)

	static int cnt;

	public static void Clean(int x, int y, int dir) {
		matrix[x][y] = 2; // 청소 함

		for (int i = 0; i < 4; i++) {
			// 핵심!♡♡♡♡♡ 회전 수식!
			dir = (dir + 3) % 4;

			int nx = x + dx[dir];
			int ny = y + dy[dir];

			// 외곽(벽)
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			// 청소가 되있거나 벽인 경우
			if (matrix[nx][ny] != 0)
				continue;

			// 청소 함
			Clean(nx, ny, dir); // 다음 단계
			cnt++;

			// 핵심!!♡♡♡♡♡
			// 청소 했으면 함수 종료(다음 단계로 넘어감)
			return;
		}

		// 핵심!!♡♡♡♡♡
		// 함수 종료 못했으므로(청소 못함) 후진해야함
		int nx = x - dx[dir];
		int ny = y - dy[dir];

		// 외곽(벽)
		if (nx < 0 || ny < 0 || nx >= N || ny >= M)
			return; // 작동 멈춤

		// 벽인 경우
		if (matrix[nx][ny] == 1)
			return; // 작동 멈춤

		Clean(nx, ny, dir); // 후진

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Clean(r, c, d);

		System.out.println(cnt + 1); // 시작 장소부터 청소함(+1)

	}

}