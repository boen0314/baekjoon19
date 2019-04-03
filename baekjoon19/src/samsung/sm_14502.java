package samsung;

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

// 백트래킹과 임시 배열 저장해서 바이러스 퍼뜨려야하는 것이 핵심
public class sm_14502 {

	static int N, M;
	static int[][] matrix;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int max;

	public static void Walls(int cnt) {
		if (cnt == 3) {
			BFS();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][j] = 1;
					Walls(cnt + 1);
					matrix[i][j] = 0; // 백트래킹!♡♡♡♡♡
				}
			}
		}

	}

	public static void BFS() {
		Queue<Virus> q = new LinkedList<>();
		int[][] tmp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = matrix[i][j]; // 임시 배열에 저장!♡♡♡♡♡

				if (tmp[i][j] == 2)
					q.add(new Virus(i, j));
			}
		}

		while (!q.isEmpty()) {
			Virus v = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				if (tmp[nx][ny] != 0)
					continue;

				tmp[nx][ny] = 2;
				q.add(new Virus(nx, ny));
			}
		}

		int safety = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmp[i][j] == 0)
					safety++;
			}
		}

		max = Math.max(max, safety);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Walls(0);

		System.out.println(max);

		br.close();

	}
}
