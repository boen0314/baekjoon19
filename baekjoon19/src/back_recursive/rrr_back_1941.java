package back_recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// x_back_1941 문제에 써놓은 주석 보고, DFS풀이가 왜 틀리는지 잘 알아두기!
// 백트래킹 문제 아니고 재귀(recursive)문제 / DFS+조합+비트마스크 문제
public class rrr_back_1941 {

	static char[][] matrix;
	static boolean[] visited;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int result;

	public static void Solve(int cnt, int s, int masking) {
		if (cnt == 7 && s >= 4) {
			result++;
			return;
		}

		for (int k = 0; k < 25; k++) {
			// 지금까지 왔던 경로 중 하나가 아니면 ♡♡♡♡♡
			// 십자가 형태로도 나아갈 수 있는 방법임!
			if ((masking & (1 << k)) == 0)
				continue;

			// 기존 경로 중 하나 좌표
			int x = k / 5;
			int y = k % 5;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				int index = nx * 5 + ny;
				int next_mk = masking | (1 << index);

				if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
					continue;

				// 방문했었던 경로이면
				if (visited[next_mk])
					continue;

				visited[next_mk] = true;
				if (matrix[nx][ny] == 'S')
					Solve(cnt + 1, s + 1, next_mk);
				else
					Solve(cnt + 1, s, next_mk);

			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		matrix = new char[5][5];
		// 비트마스크 활용(같은 학생 중복 안되도록)♡♡♡♡♡
		visited = new boolean[1 << 25];

		for (int i = 0; i < 5; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < 5; j++) {
				matrix[i][j] = tmp.charAt(j);
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int sy_check = matrix[i][j] == 'S' ? 1 : 0;
				// 시작 학생 비트 마스킹 ♡♡♡♡♡
				int start = 1 << (i * 5 + j);

				visited[start] = true;
				Solve(1, sy_check, start);
			}
		}

		System.out.println(result);
		br.close();

	}

}
