package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class FS implements Comparable<FS> {
	int fs; // 불(0), 상근(1)
	int x, y;
	int time;

	public FS(int fs, int x, int y, int time) {
		super();
		this.fs = fs;
		this.x = x;
		this.y = y;
		this.time = time;
	}

	@Override
	public int compareTo(FS o) {
		if (this.time < o.time) {
			// this(새로 들어온 원소)의 time이 적으면 우선순위 높아짐
			return -1;
		} else if (this.time > o.time) {
			return 1;
		} else {
			if (this.fs < o.fs) {
				return -1;
			} else if (this.fs > o.fs) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}

// 3055번 문제와 유사, 4179번 문제와는 거의 비슷
// 탈출구 미리 표시해 놓는 것 포인트
// 우선순위큐 활용하는 것 포인트
public class r_sm_5427_r {

	static int T;
	static int W, H;
	static char[][] matrix;
	static boolean[][] visited;

	static PriorityQueue<FS> pq;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int result;

	public static void BFS() {
		while (!pq.isEmpty()) {
			FS node = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= H + 2 || ny >= W + 2)
					continue;

				if (visited[nx][ny])
					continue;

				if (node.fs == 0) { // 불
					if (matrix[nx][ny] != '.')
						continue;

					matrix[nx][ny] = '*';
					visited[nx][ny] = true;
					pq.add(new FS(0, nx, ny, node.time + 1));

				} else { // 상근이
					if (matrix[nx][ny] == 'E') {
						result = Math.min(result, node.time + 1);
					} else if (matrix[nx][ny] == '.') {
						visited[nx][ny] = true;
						pq.add(new FS(1, nx, ny, node.time + 1));
					}
				}
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			matrix = new char[H + 2][W + 2];
			visited = new boolean[H + 2][W + 2];

			pq = new PriorityQueue<>();

			for (int i = 1; i <= H; i++) {
				String tmp = br.readLine();
				for (int j = 1; j <= W; j++) {
					matrix[i][j] = tmp.charAt(j - 1);

					if (matrix[i][j] == '*') {
						visited[i][j] = true;
						pq.add(new FS(0, i, j, 0));
					} else if (matrix[i][j] == '@') {
						matrix[i][j] = '.'; // 빈공간으로 바꿈
						visited[i][j] = true;
						pq.add(new FS(1, i, j, 0));
					}
				}
			}

			// 상근이는 밖으로 탈출함
			for (int i = 0; i < H + 2; i++) {
				for (int j = 0; j < W + 2; j++) {
					if (i == 0 || j == 0 || i == H + 1 || j == W + 1)
						matrix[i][j] = 'E';
				}
			}

			result = Integer.MAX_VALUE;

			BFS();

			if (result == Integer.MAX_VALUE) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(result);
			}

		}

		br.close();
	}
}
