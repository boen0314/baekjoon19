package dfs_bfs_binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Swan {
	int x, y;

	public Swan(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

// 이분탐색 + BFS 제대로 공부할 수 있는 문제! 재복습 요망
// 이분 탐색 공부해야함 ♡♡♡♡♡
// 유니온파인드(Disjoint-set)로도 풀어보기
public class rrr_binary_bfs_3197 {

	static int R, C;
	static char[][] matrix;
	static int[][] predict;
	static boolean[][] visited;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static boolean s_flag;
	static int sx, sy, ax, ay; // 시작, 도착 백조

	static ArrayList<Swan> w;

	static int max;

	public static boolean SwanBFS(int day) {
		boolean[][] visit = new boolean[R][C];
		visit[sx][sy] = true;

		Queue<Swan> s = new LinkedList<>();
		s.add(new Swan(sx, sy));

		while (!s.isEmpty()) {
			Swan swan = s.poll();

			if (swan.x == ax && swan.y == ay) {
				return true;
			}

			for (int i = 0; i < 4; i++) {
				int nx = swan.x + dx[i];
				int ny = swan.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;

				// 방문 했거나 며칠 더 걸리는 정점이라면
				if (visit[nx][ny] || predict[nx][ny] > day)
					continue;

				visit[nx][ny] = true;
				s.add(new Swan(nx, ny));
			}
		}
		return false;
	}

	public static int WaterBFS() {
		Queue<Swan> q = new LinkedList<>();

		int day = 0;
		while (!w.isEmpty()) {
			q.addAll(w);
			w.clear();
			day++;

			while (!q.isEmpty()) {
				Swan water = q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = water.x + dx[i];
					int ny = water.y + dy[i];

					if (nx < 0 || ny < 0 || nx >= R || ny >= C)
						continue;

					if (matrix[nx][ny] != 'X' || visited[nx][ny])
						continue;

					predict[nx][ny] = day;
					visited[nx][ny] = true;
					w.add(new Swan(nx, ny));
				}
			}
		}
		return day;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		matrix = new char[R][C];
		predict = new int[R][C];
		visited = new boolean[R][C];

		w = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				matrix[i][j] = tmp.charAt(j);

				// 백조
				if (matrix[i][j] == 'L') {
					if (!s_flag) {
						matrix[i][j] = '.';
						sx = i;
						sy = j;
						s_flag = true;
					} else {
						matrix[i][j] = '.';
						ax = i;
						ay = j;
					}
				}
				// 물 공간
				if (matrix[i][j] != 'X') {
					visited[i][j] = true;
					w.add(new Swan(i, j));
				}
			}
		}

		max = WaterBFS(); // 물 퍼뜨리기 (최대 오래 걸리는 날 반환)
		int left = 0, right = max;
		int result = 0;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (SwanBFS(mid)) {
				result = mid;
				right = mid - 1; // 더 빠른 시간 있는지 확인
			} else {
				left = mid + 1;
			}
		}

		System.out.println(result);

		// for (int i = 0; i < R; i++) {
		// for (int j = 0; j < C; j++) {
		// System.out.print(predict[i][j]);
		// }
		// System.out.println();
		// }

		br.close();
	}
}
