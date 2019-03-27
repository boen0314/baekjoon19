package dfs_bfs_binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//class Swan {
//	int x, y;
//
//	public Swan(int x, int y) {
//		super();
//		this.x = x;
//		this.y = y;
//	}
//}

// 이 풀이는 시간초과 뜸!!
// 이분 탐색 공부해야함 ♡♡♡♡♡
public class binary_bfs_3197_x {

	static int R, C;
	static char[][] matrix;
	static boolean[][] visited;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static boolean s_flag;
	static int sx, sy, ax, ay; // 시작, 도착 백조

	static ArrayList<Swan> water_list;

	static boolean res_flag;
	static int day;

	public static void SwanBFS() {
		boolean[][] visit = new boolean[R][C];
		visit[sx][sy] = true;

		Queue<Swan> s = new LinkedList<>();
		s.add(new Swan(sx, sy));

		while (!s.isEmpty()) {
			Swan swan = s.poll();

			if (swan.x == ax && swan.y == ay) {
				System.out.println(day);
				res_flag = true; // 결과 나옴
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = swan.x + dx[i];
				int ny = swan.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;

				if (matrix[nx][ny] == 'X' || visit[nx][ny])
					continue;

				visit[nx][ny] = true;
				s.add(new Swan(nx, ny));
			}
		}
	}

	public static void WaterBFS() {
		Queue<Swan> q = new LinkedList<>();
		q.addAll(water_list);
		water_list.clear();

		while (!q.isEmpty()) {
			Swan water = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = water.x + dx[i];
				int ny = water.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;

				if (matrix[nx][ny] != 'X' || visited[nx][ny])
					continue;

				matrix[nx][ny] = '.';
				visited[nx][ny] = true;
				water_list.add(new Swan(nx, ny));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		matrix = new char[R][C];
		visited = new boolean[R][C];

		water_list = new ArrayList<>();

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
					water_list.add(new Swan(i, j));
				}
			}
		}

		while (!res_flag) {
			SwanBFS(); // 백조 만날 수 있는지 조사
			WaterBFS(); // 물 퍼뜨리기
			day++;
		}

		br.close();
	}
}
