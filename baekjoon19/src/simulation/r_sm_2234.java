package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Castle {
	int x, y;

	public Castle(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

// 비트마스크 활용 (남동북서 순)
// 벽 부쉈을 때 방 최대 넓이 값 구하는 것 유형 익히기
public class r_sm_2234 {

	static int N, M;
	static int[][] matrix;
	static int[][] visited;

	static int[] dx = { 0, -1, 0, 1 }; // 서북동남(거꾸로)
	static int[] dy = { -1, 0, 1, 0 };

	static int room_cnt, space_max, crush_max;

	static int[] space;

	public static void BFS(int x, int y) {
		Queue<Castle> q = new LinkedList<>();
		q.add(new Castle(x, y));
		visited[x][y] = room_cnt;

		int cnt = 1;
		while (!q.isEmpty()) {
			Castle c = q.poll();

			for (int i = 0; i < 4; i++) {
				// 벽 존재 하지 않음 (비트 마스크)
				if ((matrix[c.x][c.y] & (1 << i)) == 0) {
					int nx = c.x + dx[i];
					int ny = c.y + dy[i];

					if (nx < 0 || ny < 0 || nx >= M || ny >= N)
						continue;

					if (visited[nx][ny] != 0)
						continue;

					visited[nx][ny] = room_cnt; // 방 번호 기록
					q.add(new Castle(nx, ny));
					cnt++;
				}
			}
		}
		space[room_cnt] = cnt; // (방 번호,넓이) 기록
		space_max = Math.max(space_max, cnt);
	}

	public static void Crush() {

		// 인접한 방 번호 두개의 넓이를 더해서 최댓값을 저장함
		for (int x = 0; x < M; x++) {
			for (int y = 0; y < N; y++) {
				// 인접한 방향
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || ny < 0 || nx >= M || ny >= N)
						continue;

					if (visited[x][y] == visited[nx][ny])
						continue;

					int union = space[visited[x][y]] + space[visited[nx][ny]];
					crush_max = Math.max(crush_max, union);
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 세로

		matrix = new int[M][N];
		visited = new int[M][N];

		// 런타임 에러 조심 (+1 반드시 해줘야 함, room_cnt = 1 부터 시작하므로)
		space = new int[M * N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == 0) {
					room_cnt++;
					BFS(i, j);
				}
			}
		}

		Crush();

		System.out.println(room_cnt);
		System.out.println(space_max);
		System.out.println(crush_max);

		br.close();

	}

}
