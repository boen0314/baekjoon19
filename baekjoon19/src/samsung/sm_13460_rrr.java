package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Bead {
	int r_x, r_y;
	int b_x, b_y;
	int cnt;

	Bead(int r_x, int r_y, int b_x, int b_y, int cnt) {
		super();
		this.r_x = r_x;
		this.r_y = r_y;
		this.b_x = b_x;
		this.b_y = b_y;
		this.cnt = cnt;
	}
}

public class sm_13460_rrr {

	static int N, M;
	static char[][] matrix;
	static boolean[][][][] visited;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void BFS(int rx, int ry, int bx, int by) {
		Queue<Bead> q = new LinkedList<>();
		q.add(new Bead(rx, ry, bx, by, 1)); // 1회 이동
		visited[rx][ry][bx][by] = true;

		while (!q.isEmpty()) {
			Bead b = q.poll();

			for (int i = 0; i < 4; i++) {
				int nrx = b.r_x;
				int nry = b.r_y;
				int nbx = b.b_x;
				int nby = b.b_y;

				int rCnt = 0;
				int bCnt = 0;

				// 빨간 구슬 끝까지 이동 (또는 구멍 앞 까지)
				while (matrix[nrx + dx[i]][nry + dy[i]] != '#' && matrix[nrx + dx[i]][nry + dy[i]] != 'O') {
					nrx += dx[i];
					nry += dy[i];
					rCnt++;
				}
				// 파란 구슬 끝까지 이동 (또는 구멍 앞 까지)
				while (matrix[nbx + dx[i]][nby + dy[i]] != '#' && matrix[nbx + dx[i]][nby + dy[i]] != 'O') {
					nbx += dx[i];
					nby += dy[i];
					bCnt++;
				}

				// 빨간 구슬, 파란 구슬 만날 경우
				if (nrx == nbx && nry == nby) {
					// 구멍 앞인 경우 (빨간 구슬, 파란 구슬 둘다 구멍에 빠질 경우)
					if (matrix[nbx + dx[i]][nby + dy[i]] == 'O') {
						continue; // 다른 방향으로 진행
					}

					// 빨간 구슬이 더 움직인 경우(파란 구슬이 앞에 있었음) 한칸 뒤로
					if (rCnt > bCnt) {
						nrx -= dx[i];
						nry -= dy[i];
					} else {
						nbx -= dx[i];
						nby -= dy[i];
					}
				}

				// 파란 구슬만 구멍에 빠질 경우
				if (matrix[nbx + dx[i]][nby + dy[i]] == 'O') {
					continue;
				}

				// 빨간 구슬만 구멍에 빠질 경우 (파란 구슬 빠지는 경우는 위에서 걸러짐)
				if (matrix[nrx + dx[i]][nry + dy[i]] == 'O') {
					System.out.println(b.cnt);
					return; // 종료
				}

				if (visited[nrx][nry][nbx][nby])
					continue; // 방문 했다면 다른 방향으로 진행

				// 현재 9회 다음 10회 까지 이동 가능
				if (b.cnt < 10) {
					q.add(new Bead(nrx, nry, nbx, nby, b.cnt + 1));
					visited[nrx][nry][nbx][nby] = true;
				}
			}
		}
		System.out.println("-1"); // 10회 안에 구슬 못 뺀 경우
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new char[N][M];
		visited = new boolean[N][M][N][M];

		String tmp;
		int rx = 0, ry = 0, bx = 0, by = 0;
		for (int i = 0; i < N; i++) {
			tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				matrix[i][j] = tmp.charAt(j);

				if (matrix[i][j] == 'R') {
					rx = i;
					ry = j;
				}
				if (matrix[i][j] == 'B') {
					bx = i;
					by = j;
				}
			}
		}

		BFS(rx, ry, bx, by);

		br.close();
	}

}
