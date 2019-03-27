package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Minsik {
	int x, y;
	int key;
	int time;

	public Minsik(int x, int y, int key, int time) {
		super();
		this.x = x;
		this.y = y;
		this.key = key;
		this.time = time;
	}
}

// 비트 마스크 활용법 제대로 익히기 ♡♡♡♡♡
// sm_bfs_9328 문제와 유사하지만 조금 다른 문제!
public class sm_1194_r {

	static int N, M;
	static int[][] matrix;
	static boolean[][][] visited; // 열쇠 유무 포함

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int sx, sy;

	static int result;

	public static void BFS() {
		Queue<Minsik> q = new LinkedList<>();
		q.add(new Minsik(sx, sy, 0, 0));
		visited[sx][sy][0] = true;

		while (!q.isEmpty()) {
			Minsik m = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = m.x + dx[i];
				int ny = m.y + dy[i];
				int key = m.key;

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				if (matrix[nx][ny] == '#' || visited[nx][ny][key])
					continue;

				// 출구
				if (matrix[nx][ny] == '1') {
					System.out.println(m.time + 1);
					return;
				}

				// 열쇠 획득 (비트 마스크)
				if (matrix[nx][ny] >= 'a' && matrix[nx][ny] <= 'f') {
					key |= (1 << matrix[nx][ny] - 'a');
				}
				// 문
				else if (matrix[nx][ny] >= 'A' && matrix[nx][ny] <= 'F') {
					// 키가 없을 경우
					if ((key & (1 << matrix[nx][ny] - 'A')) == 0) {
						continue;
					}
				}

				q.add(new Minsik(nx, ny, key, m.time + 1));
				visited[nx][ny][key] = true;
			}
		}

		System.out.println("-1");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];
		visited = new boolean[N][M][1 << 7]; // 64(2의 6승), 가능한 키 조합

		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				matrix[i][j] = tmp.charAt(j);

				if (matrix[i][j] == '0') {
					sx = i;
					sy = j;
					matrix[i][j] = '.';
				}
			}
		}

		BFS();

		br.close();
	}
}
