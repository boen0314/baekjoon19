package back_recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 이 풀이는 틀린 풀이! 왜 틀렸는지는 주석으로 설명해놨음
// 중요한 핵심 문제!!! ★★★★★★★★★★ 복습 요망
// 이 풀이는 중복되는 경로가 생김! 중복되는 경로 제거해야함
// 또한 DFS는 "십자가 모양"으로 퍼져나가면서 검색할 수 없음!★★★★★
// (직접 그래프 그려서 손디버깅 해보면 알 수 있음 - 밑에 그림 그려놓음)
public class x_back_1941 {

	static char[][] matrix;
	static boolean[][] visited;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int result;

	public static void DFS(int x, int y, int cnt, int s) {
		if (cnt == 7) {
			if (s >= 4) { // 이다솜파 4명 이상
				result++;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
				continue;

			if (visited[nx][ny])
				continue;

			if (matrix[nx][ny] == 'S') {
				visited[nx][ny] = true;
				DFS(nx, ny, cnt + 1, s + 1);
				visited[nx][ny] = false;
			} else if (matrix[nx][ny] == 'Y') {
				visited[nx][ny] = true;
				DFS(nx, ny, cnt + 1, s);
				visited[nx][ny] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		matrix = new char[5][5];
		visited = new boolean[5][5];

		for (int i = 0; i < 5; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < 5; j++) {
				matrix[i][j] = tmp.charAt(j);
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				visited[i][j] = true;
				if (matrix[i][j] == 'S')
					DFS(i, j, 1, 1);
				else if (matrix[i][j] == 'Y')
					DFS(i, j, 1, 0);
				visited[i][j] = false;
			}
		}

		System.out.println(result);
		br.close();

	}
}


// Y   Y   Y   Y   Y
//'S' 'Y' 'S' 'Y' 'S'
// Y  "Y"  Y   Y  'Y'
// Y  "S"  Y   Y  'S'
// Y   Y   Y   Y   Y

// DFS로 탐색을 했을 때 표시해 놓은 경로를 보면
// (1,0) 좌표부터 시작해서 (3,4) 좌표에서 끝나는 경로 1개와
// (3,4) 좌표 부터 시작해서 (1,0) 좌표에서 끝나는 경로 1개
// 총 2개의 경우의 수를 찾을 수 있음 (심지어 같은 학생들이여서 중복되는 경우임)
// 하지만 십자가로 탐색(DFS는 불가능)을 하면 다른 경우의 수도 찾을 수 있음 (""표시 한 부분 참고)
// 그러므로 이러한 유형 문제는 DFS로 탐색해서 찾으면 안됨