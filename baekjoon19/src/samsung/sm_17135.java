package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Shot {
	int x, y;
	int d;

	public Shot(int x, int y, int d) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

class Rival implements Comparable<Rival> {
	int x, y;
	int dist;

	public Rival(int x, int y, int dist) {
		super();
		this.x = x;
		this.y = y;
		this.dist = dist;
	}

	@Override
	public int compareTo(Rival o) {
		if (this.dist < o.dist) {
			return -1;
		} else if (this.dist > o.dist) {
			return 1;
		} else {
			if (this.y < o.y) {
				return -1;
			} else if (this.y > o.y) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}

public class sm_17135 {

	static int N, M, D; // 세로, 가로, 궁수의 공격 거리 제한
	static int[][] matrix; // 적들

	static ArrayList<Shot> list; // 궁수 리스트

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int count, result;

	public static void DFS(int idx, int cnt) {
		if (idx == M + 1) {
			// 지금 idx가 M+1이란 것은 전 단계에 idx에 M이 들어갔다는 뜻
			// 좌표는 M-1 까지이므로 idx에 M이 들어가서는 안됌!
			// 그러므로 (idx == M+1) 까지 조회하는 것
			return;
		}

		if (cnt == 3) {

			int[][] tmp = new int[N + 1][M]; // 임시 배열
			for (int h = 0; h < N + 1; h++) {
				tmp[h] = matrix[h].clone();
			}

			count = 0;
			for (int h = 0; h < N; h++) {
				BFS(); // 저격
				Puyo(); // 적들 내려옴
			}

			result = Math.max(result, count);

			for (int h = 0; h < N + 1; h++) { // 백트래킹
				matrix[h] = tmp[h].clone();
			}

			return;
		}

		Shot shot = new Shot(N, idx, 0);
		list.add(shot);
		DFS(idx + 1, cnt + 1);
		list.remove(list.indexOf(shot)); // 백트래킹

		DFS(idx + 1, cnt);
	}

	public static void Puyo() {
		for (int w = 0; w < M; w++) {
			for (int h = N - 1; h >= 1; h--) {
				matrix[h][w] = matrix[h - 1][w];
			}
			matrix[0][w] = 0;
			matrix[N][w] = 0;
		}
	}

	public static void BFS() {
		ArrayList<Rival> die = new ArrayList<>();

		for (Shot s : list) {
			PriorityQueue<Rival> pq = new PriorityQueue<>();

			Queue<Shot> q = new LinkedList<>();
			q.add(s);

			boolean[][] visited = new boolean[N + 1][M];
			visited[s.x][s.y] = true;

			while (!q.isEmpty()) {
				Shot cur = q.poll();

				if (cur.d == D)
					continue;

				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];

					if (nx < 0 || ny < 0 || nx >= N + 1 || ny >= M)
						continue;

					if (visited[nx][ny])
						continue;

					if (matrix[nx][ny] == 1) {
						int distance = Math.abs(s.x - nx) + Math.abs(s.y - ny); // 주의! 초기 궁수 위치와 비교해야 함
						pq.add(new Rival(nx, ny, distance));
					}

					visited[nx][ny] = true;
					q.add(new Shot(nx, ny, cur.d + 1));
				}
			}

			if (!pq.isEmpty()) {
				die.add(pq.poll());
			}
		}

		if (!die.isEmpty()) {
			for (Rival e : die) {
				if (matrix[e.x][e.y] == 1) {
					matrix[e.x][e.y] = 0;
					count++;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		matrix = new int[N + 1][M];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = Integer.MIN_VALUE;
		DFS(0, 0); // 궁수 선택

		System.out.println(result);
		br.close();

	}

}
