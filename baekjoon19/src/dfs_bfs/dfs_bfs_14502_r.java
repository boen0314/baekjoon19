package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus {
	int x, y;

	public Virus(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class dfs_bfs_14502_r {

	static int N, M;
	static int[][] matrix;
	static int[][] tmp;
	// 방문 여부는 필요 없음. 최단거리를 구하는게 아닌 바이러스를 퍼트리기만 하면 되므로

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int max;

	public static void Wall(int cnt) {
		if (cnt == 3) {
			BFS();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][j] = 1; // 벽 설치
					Wall(cnt + 1);
					matrix[i][j] = 0; // 벽 초기화 (백트래킹)
				}
			}
		}

	}

	public static void BFS() {
		Queue<Virus> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = matrix[i][j];

				if (tmp[i][j] == 2) { // 바이러스가 있다면
					q.add(new Virus(i, j)); // 큐에 추가
				}
			}
		}

		while (!q.isEmpty()) {
			Virus v = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];

				if (nx >= N || ny >= M || nx < 0 || ny < 0)
					continue;

				if (tmp[nx][ny] != 0)
					continue;

				tmp[nx][ny] = 2;
				q.add(new Virus(nx, ny));
			}
		}

		MaxSafety();

	}

	public static void MaxSafety() {
		int safeCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmp[i][j] == 0)
					safeCount++;
			}
		}
		max = Math.max(max, safeCount);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];
		tmp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Wall(0);

		System.out.println(max);
	}
}
