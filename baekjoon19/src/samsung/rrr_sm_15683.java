package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Camera {
	int number;
	int x, y;

	public Camera(int number, int x, int y) {
		super();
		this.number = number;
		this.x = x;
		this.y = y;
	}
}

// DFS + 백트래킹으로 풀기! (카메라 방향 표현)
public class rrr_sm_15683 {

	static int N, M;
	static int[][] matrix;

	static ArrayList<Camera> list;

	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 };

	static int result;

	private static void Back(int cnt) {
		if (cnt == list.size()) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (matrix[i][j] == 0)
						count++;
				}
			}
			result = Math.min(result, count);
			return;
		}

		int[][] tmp = new int[N][M]; // 임시 배열 필요(백트래킹)
		Camera c = list.get(cnt);

		// 핵심!♡♡♡♡♡ 카메라 방향 표현하고, 임시 배열 활용하여 백트래킹 하는 것이 핵심
		switch (c.number) {
		case 1:
			// 1방향으로 4가지
			for (int i = 0; i < 4; i++) {
				Copy(tmp, matrix); // 배열 복사 ♡♡♡♡♡
				Draw(c.x, c.y, i);
				Back(cnt + 1);
				Copy(matrix, tmp); // 백트래킹 ♡♡♡♡♡
			}
			break;
		case 2:
			// 2방향으로 2가지
			for (int i = 0; i <= 2; i += 2) {
				Copy(tmp, matrix);
				Draw(c.x, c.y, i);
				Draw(c.x, c.y, i + 1);
				Back(cnt + 1);
				Copy(matrix, tmp);
			}
			break;
		case 3:
			// 2방향으로 4가지
			for (int i = 0; i <= 1; i++) { // 상하
				for (int j = 2; j <= 3; j++) { // 좌우
					Copy(tmp, matrix);
					Draw(c.x, c.y, i); // 상하
					Draw(c.x, c.y, j); // 좌우
					Back(cnt + 1);
					Copy(matrix, tmp);
				}
			}
			break;
		case 4:
			// 3방향으로 4가지
			for (int i = 0; i < 4; i++) {
				Copy(tmp, matrix);
				for (int j = 0; j < 4; j++) {
					if (j != i) { // 한방향만 뺌
						Draw(c.x, c.y, j);
					}
				}
				Back(cnt + 1);
				Copy(matrix, tmp);
			}
			break;
		case 5:
			// 4방향으로 1가지
			Copy(tmp, matrix);
			for (int i = 0; i < 4; i++) {
				Draw(c.x, c.y, i);
			}
			Back(cnt + 1);
			Copy(matrix, tmp);
			break;
		}

	}

	// 얕은 복사 (ex. 매개변수의 a와 tmp의 주소를 공유하므로 a의 값이 바뀌면 tmp의 값도 바뀜)
	// 그래서 따로 배열 return 안해줘도 됨
	// 깊은 복사는 clone 함수 이용하면 값이 복사된 새로운 객체 생성 가능 (배열만, 객체는 따로 오버라이딩 필요)
	public static void Copy(int[][] a, int[][] b) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				a[i][j] = b[i][j];
			}
		}
	}

	public static void Draw(int x, int y, int i) {
		int nx = x;
		int ny = y;
		while (true) {
			nx = nx + dx[i];
			ny = ny + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				break;

			if (matrix[nx][ny] == 6)
				break;

			// 빈칸이면
			if (matrix[nx][ny] == 0) {
				matrix[nx][ny] = 7; // '#' 대신 7
			}
			// CCTV 또는 '#'이면 표시 안하고 계속 진행
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());

				// 감시 카메라인 경우! 핵심!♡♡♡♡♡
				if (matrix[i][j] >= 1 && matrix[i][j] <= 5) {
					list.add(new Camera(matrix[i][j], i, j));
				}
			}
		}

		result = Integer.MAX_VALUE;

		Back(0);

		System.out.println(result);

		br.close();
	}

}
