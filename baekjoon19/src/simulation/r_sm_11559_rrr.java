package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Puyo {
	int x, y;

	public Puyo(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

// gravity() 메소드 잘 익히기
// ss_12100 문제와 유사한 메소드임
public class r_sm_11559_rrr {

	static char[][] matrix;

	static Queue<Puyo> pang; // 터질 뿌요 큐

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static int Game() {
		int gameCnt = 0; // 뿌요 연쇄 횟수

		// 뿌요가 안터질 때 까지 게임 진행
		while (true) {
			boolean flag = false; // 뿌요 터지는지 여부
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (matrix[i][j] != '.') {
						BFS(i, j);
						// 연결된 뿌요가 4개가 넘으면 터뜨림
						if (pang.size() >= 4) {
							while (!pang.isEmpty()) {
								flag = true; // 뿌요가 한번이라도 터지면
								Puyo p = pang.poll();
								matrix[p.x][p.y] = '.';
							}
						} else {
							pang.clear(); // 뿌요 안건드림
						}
					}
				}
			}

			// 뿌요 한번이라도 터트렸으면 중력작용
			if (flag) {
				gameCnt++; // 연쇄 횟수 증가
				Gravity(); // 뿌요 내리기
			} else {
				break; // while문 빠져나옴
			}

		}
		return gameCnt;
	}

	// 너무 중요한 메소드 구현 ♡♡♡♡♡
	public static void Gravity() {
		for (int j = 0; j < 6; j++) {
			for (int i = 11; i > 0; i--) {
				if (matrix[i][j] == '.' && matrix[i - 1][j] != '.') {
					matrix[i][j] = matrix[i - 1][j]; // 자리 바꿈
					matrix[i - 1][j] = '.';
					i = 12; // 이부분이 핵심! (이해안되면 그려서 시뮬레이션 해보기)
				}
			}
		}
	}

	public static void BFS(int x, int y) {
		boolean[][] visited = new boolean[12][6];
		visited[x][y] = true;

		Queue<Puyo> q = new LinkedList<>();
		q.add(new Puyo(x, y));

		while (!q.isEmpty()) {
			Puyo p = q.poll();

			pang.add(p); // 터트릴 뿌요 큐 ♡♡♡♡♡

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6)
					continue;

				if (visited[nx][ny])
					continue;

				if (matrix[nx][ny] != matrix[p.x][p.y])
					continue;

				visited[nx][ny] = true;
				q.add(new Puyo(nx, ny));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		matrix = new char[12][6];

		pang = new LinkedList<>();

		for (int i = 0; i < 12; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < 6; j++) {
				matrix[i][j] = tmp.charAt(j);
			}
		}

		System.out.println(Game());

		br.close();
	}

}
