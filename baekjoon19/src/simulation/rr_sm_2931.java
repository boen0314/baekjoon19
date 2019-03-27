package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파이프 문제는 sw_1953_r 문제와 비슷
// DFS로 파이프를 따라가다가 중간에 빈칸을 발견하면
// 빈칸에 알맞는 파이프를 찾는 방법으로 풀어봤으나 너무 따져야 할 것이 많음!

// 알맞는 파이프를 찾기보다는 빈칸에 파이프를 넣고 Z까지 갈 수 있는지의 여부를 따져야 할 것!
public class rr_sm_2931 {

	static int R, C;
	static char[][] matrix;

	static char[] pipe = { '|', '-', '+', '1', '2', '3', '4' };

	static int[] dx = { -1, 0, 1, 0 }; // 상좌하우
	static int[] dy = { 0, -1, 0, 1 };

	static int pipeCnt;
	static int sx, sy; // start
	static int rx, ry, rp; // result

	static boolean pipe_flag, res_flag;

	private static void DFS(int x, int y, int dir, int depth) {
		// 다음 파이프 좌표
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if (nx <= 0 || ny <= 0 || nx > R || ny > C)
			return;

		// 자그레브(도착점)
		// depth를 세는 이유는 Z.M 에서 .이 올바른 파이프 자리가 아닌데
		// Z-M 으로 이어서 끝내버릴 가능성이 있으므로 파이프를 다 돌았는지 체크해야함
		// depth == pipeCnt 가 아닌 depth >= pipeCnt 인 이유는 (부등호)
		// 파이프라인이 +인 곳에서 회전(?) 하면서 중복으로 방문할 가능성 있으므로
		if (matrix[nx][ny] == 'Z' && depth >= pipeCnt && !res_flag) {
			System.out.println(rx + " " + ry + " " + pipe[rp]);

			// 여러 경우가 나올 수 있는데 그런 경우는 (-, +) 또는 (|, +) 이 경우임.
			// '|' '-' 가 옳은 파이프 인 경우,
			// 파이프를 '+'로 하면 Z까지 도착할 수는 있지만 옳지 않음(가스가 샘)!
			// '|' '-'가 '+'보다 먼저 탐색되므로 처음 답을 출력 한 후 더이상 출력하지 않게 한다.
			res_flag = true;

			return;
		}

		// 빈칸인 경우
		if (matrix[nx][ny] == '.' && !pipe_flag) {
			// 파이프 7개 넣어서 탐색해보기
			for (int p = 0; p < 7; p++) {
				rx = nx;
				ry = ny;
				rp = p;

				matrix[nx][ny] = pipe[p];
				pipe_flag = true; // 파이프 넣음

				Direciton(nx, ny, dir, depth + 1);

				// 백트래킹
				matrix[nx][ny] = '.';
				pipe_flag = false;
			}
		}
		// 파이프인 경우
		else if (matrix[nx][ny] != '.' && matrix[nx][ny] != 'Z') {
			Direciton(nx, ny, dir, depth + 1);
		}

	}

	public static void Direciton(int x, int y, int d, int dep) {
		// 전 파이프가 아래쪽 방향을 향했다면
		// 다음 파이프는 위에서 시작하므로(거꾸로 바꿔줌)
		d = (d + 2) % 4;

		switch (matrix[x][y]) {
		case '|':
			if (d == 0)
				DFS(x, y, 2, dep);
			else if (d == 2)
				DFS(x, y, 0, dep);
			break;
		case '-':
			if (d == 1)
				DFS(x, y, 3, dep);
			else if (d == 3)
				DFS(x, y, 1, dep);
			break;
		case '+':
			if (d == 0)
				DFS(x, y, 2, dep);
			else if (d == 2)
				DFS(x, y, 0, dep);
			else if (d == 1)
				DFS(x, y, 3, dep);
			else if (d == 3)
				DFS(x, y, 1, dep);
			break;
		case '1':
			if (d == 3)
				DFS(x, y, 2, dep);
			else if (d == 2)
				DFS(x, y, 3, dep);
			break;
		case '2':
			if (d == 0)
				DFS(x, y, 3, dep);
			else if (d == 3)
				DFS(x, y, 0, dep);
			break;
		case '3':
			if (d == 0)
				DFS(x, y, 1, dep);
			else if (d == 1)
				DFS(x, y, 0, dep);
			break;
		case '4':
			if (d == 1)
				DFS(x, y, 2, dep);
			else if (d == 2)
				DFS(x, y, 1, dep);
			break;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		matrix = new char[R + 1][C + 1]; // 좌표 1부터 시작

		for (int i = 1; i <= R; i++) {
			String tmp = br.readLine();
			for (int j = 1; j <= C; j++) {
				matrix[i][j] = tmp.charAt(j - 1);

				if (matrix[i][j] != '.')
					pipeCnt++;

				if (matrix[i][j] == 'M') {
					matrix[i][j] = '.';
					sx = i;
					sy = j;
				}
			}
		}

		// 출발지점에서부터 4방향 탐색해서 파이프 찾기
		for (int i = 0; i < 4; i++) {
			// pipeCnt는 정상적인 파이프 개수-1(지워진 블록)+2(M,Z)임
			// depth 1부터 시작하므로 M 도달 직전의 depth는 pipeCnt와 같아짐
			// ex) Z.M (정답 Z-M) 인 경우
			// pipeCtn = 2 이고,
			// . 정점(파이프 자리)의 depth = 2 임
			DFS(sx, sy, i, 1);
		}

		br.close();
	}
}
