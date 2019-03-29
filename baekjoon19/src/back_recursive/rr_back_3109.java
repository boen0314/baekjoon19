package back_recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Greedy + Recursive
// 핵심!♡♡♡♡♡
// 가장 중요한 건 ↗(우상향) 방향으로 빵집에 도달하는 게 최적의 방법이다.
// 파이프라인을 최대 몇개 설치하냐가 관건이기 때문에 최대한 빵집에 도달한 방향이 위쪽일수록 좋다.
// 빵집에 도달한 방향이 ↗면 다른 방향은 안가도 된다.

// 빵집에 최적으로 도달하면 바로 다음 파이프라인 시작점으로 넘어가야 한다. (return)
// 이 문제는 DFS를 이용하되 최적으로 도달하면 바로 중단해야 하는 게 포인트다.
// ★★★ 중단하려면 return을 써야하므로 어떻게 해야 바로 중단해야 하는지만 고민해보면 된다.
public class rr_back_3109 {

	static int R, C;
	static char[][] matrix;
	static boolean[][] visited;

	static int[] dx = { -1, 0, 1 }; // 순서 중요!!

	static int result;

	public static int DFS(int x, int y) {
		visited[x][y] = true;

		if (y == C - 1)
			return 1;

		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + 1; // 앞으로만 전진

			if (nx < 0 || ny < 0 || nx >= R || ny >= C)
				continue;

			if (matrix[nx][ny] != '.' || visited[nx][ny])
				continue;

			// ★★★ 핵심!
			// 끝까지 도달했을 경우 모든 재귀 함수 리턴 1 해서 종료시키기

			// 헷갈리면 안되는게 DFS(깊이 우선), BFS(넓이 우선)
			// DFS이므로 여러 방향으로 흩어져서 탐색하지 않음! 끝까지 탐색!

			// 파이프 라인이 마지막 열까지 연결되지 않았을 경우에도,
			// 어차피 그 쪽으로 이동하면 마지막까지 도달할 수 없기 때문에 백트래킹 하지 않아도 된다.
			// visited[x][y] = false (X)
			// 정답이 아닌 것을 한번 탐색하여 계산 안해도 된다고 표시한 것
			if (DFS(nx, ny) == 1)
				return 1;
		}

		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		matrix = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				matrix[i][j] = tmp.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			result += DFS(i, 0);
		}

		System.out.println(result);
		br.close();

	}

}
