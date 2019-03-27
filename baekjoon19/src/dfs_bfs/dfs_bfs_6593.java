package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Sang {
	int x, y, z;
	int time;

	public Sang(int x, int y, int z, int time) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.time = time;
	}
}

public class dfs_bfs_6593 {

	static int L, R, C;
	static char[][][] matrix;
	static boolean[][][] visited;

	static int[] dx = { 0, 0, 1, 0, -1, 0 };
	static int[] dy = { 0, 0, 0, 1, 0, -1 };
	static int[] dz = { 1, -1, 0, 0, 0, 0 };

	static int sx, sy, sz;
	static int ax, ay, az;

	static int result;

	public static void BFS() {
		Queue<Sang> q = new LinkedList<>();
		q.add(new Sang(sx, sy, sz, 0));
		visited[sz][sx][sy] = true;

		while (!q.isEmpty()) {
			Sang s = q.poll();

			if (s.x == ax && s.y == ay && s.z == az) {
				result = Math.min(result, s.time);
				continue;
			}

			for (int i = 0; i < 6; i++) {
				int nz = s.z + dz[i];
				int nx = s.x + dx[i];
				int ny = s.y + dy[i];

				if (nz < 0 || nx < 0 || ny < 0 || nz >= L || nx >= R || ny >= C)
					continue;

				if (matrix[nz][nx][ny] == '#' || visited[nz][nx][ny])
					continue;

				q.add(new Sang(nx, ny, nz, s.time + 1));
				visited[nz][nx][ny] = true;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {

			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			if (L == 0 && R == 0 && C == 0)
				break;

			matrix = new char[L][R][C];
			visited = new boolean[L][R][C];

			for (int k = 0; k < L; k++) {
				for (int i = 0; i < R; i++) {
					String tmp = br.readLine();
					for (int j = 0; j < C; j++) {
						matrix[k][i][j] = tmp.charAt(j);

						if (matrix[k][i][j] == 'S') {
							sz = k;
							sx = i;
							sy = j;
							matrix[k][i][j] = '.';

						} else if (matrix[k][i][j] == 'E') {
							az = k;
							ax = i;
							ay = j;
						}
					}
				}
				br.readLine();
			}

			result = Integer.MAX_VALUE;
			BFS();

			if (result != Integer.MAX_VALUE) {
				System.out.println("Escaped in " + result + " minute(s).");
			} else {
				System.out.println("Trapped!");
			}

		}

		br.close();
	}
}
