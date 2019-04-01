package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 문제 이해가 가장 중요!★★★★★ DFS, 백트래킹 익히기 좋은 문제!
// 처음에 문제 자체를 이해를 못했음
// 핀을 한번 움직이고 나면 다시 처음부터 핀 탐색 해서 핀을 또 움직임(재귀-DFS)
// 핀을 움직이지 못하는 경우까지 재귀 탐색 후 백트래킹
public class rrr_sm_back_9207 {

	static int T;
	static char[][] matrix;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int pin, move;
	static int minPin, minMove;

	// 핀 탐색
	public static void DFS_S() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if (matrix[i][j] == 'o')
					DFS_M(i, j); // 핀 찾았으면 이동
			}
		}

		// 이동 가능한 핀 모두 이동 후 (재귀 탐색 끝)
		if (minPin > pin) {
			minPin = pin;
			minMove = move;
		} else if (minPin == pin && minMove > move) {
			minMove = move;
		}

	}

	// 핀 이동
	public static void DFS_M(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int nnx = nx + dx[i];
			int nny = ny + dy[i];

			if (nx < 0 || ny < 0 || nx >= 5 || ny >= 9)
				continue;
			if (nnx < 0 || nny < 0 || nnx >= 5 || nny >= 9)
				continue;

			// 인접한 곳에 핀이 있고 그 핀의 다음 칸이 빈칸이면
			if (matrix[nx][ny] == 'o' && matrix[nnx][nny] == '.') {
				matrix[x][y] = matrix[nx][ny] = '.'; // 인접한 핀 제거됨
				matrix[nnx][nny] = 'o'; // 핀 이동
				pin--;
				move++;

				DFS_S(); // 처음부터 다시 핀 탐색

				// 백트래킹
				matrix[x][y] = matrix[nx][ny] = 'o';
				matrix[nnx][nny] = '.';
				pin++;
				move--;

			}

		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			matrix = new char[5][9];

			int num = 0;
			for (int i = 0; i < 5; i++) {
				String tmp = br.readLine();
				for (int j = 0; j < 9; j++) {
					matrix[i][j] = tmp.charAt(j);

					if (matrix[i][j] == 'o') {
						num++;
					}
				}
			}

			// 초기화
			pin = num;
			move = 0;
			minPin = num;
			minMove = Integer.MAX_VALUE;

			DFS_S();

			System.out.println(minPin + " " + minMove);

			if (tc != T - 1)
				br.readLine();
		}

		br.close();
	}
}
