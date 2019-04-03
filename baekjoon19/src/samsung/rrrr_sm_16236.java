package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Fish implements Comparable<Fish> {
	int x, y;
	int time;

	public Fish(int x, int y, int time) {
		super();
		this.x = x;
		this.y = y;
		this.time = time;
	}

	@Override
	public int compareTo(Fish o) {
		if (this.time < o.time) {
			return -1;
		} else if (this.time > o.time) {
			return 1;
		} else {
			if (this.x < o.x) {
				return -1;
			} else if (this.x > o.x) {
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
}

public class rrrr_sm_16236 {

	static int N;
	static int[][] matrix;

	static int sx, sy, ssize, eat; // 아기 상어 정보

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int result;

	private static void BFS() {
		while (true) {
			PriorityQueue<Fish> pq = new PriorityQueue<>(); // 먹이 풀
			
			Queue<Fish> q = new LinkedList<>();
			q.add(new Fish(sx, sy, 0)); // 아기 상어 처음 위치

			boolean[][] visited = new boolean[N][N];
			visited[sx][sy] = true;

			while (!q.isEmpty()) {
				Fish shark = q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = shark.x + dx[i];
					int ny = shark.y + dy[i];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;

					if (visited[nx][ny])
						continue; // 아기 상어 이미 방문한 자리라면

					// 물고기가 자신의 크기보다 크면 못 지나감
					if (matrix[nx][ny] > ssize)
						continue;

					// 물고기가 자신의 크기보다 작으면 잡아 먹을 수 있음 (먹이 풀)
					if (matrix[nx][ny] > 0 && matrix[nx][ny] < ssize) {
						pq.add(new Fish(nx, ny, shark.time + 1));
					}

					// 아기 상어 더 움직일 수 있음
					q.add(new Fish(nx, ny, shark.time + 1));
					visited[nx][ny] = true;
				}
			}

			// 먹이 풀이 비었음 = 더이상 물고기가 없음
			if (pq.isEmpty())
				break; // 함수 종료

			// 잡아 먹을 물고기
			Fish fish = pq.poll();
			sx = fish.x;
			sy = fish.y;

			matrix[sx][sy] = 0; // 잡아먹은 물고기 자리 빈칸으로 초기화
			result += fish.time; // 걸린시간 누적
			eat++;

			if (ssize == eat) {
				ssize++; // 아기 상어 사이즈 늘어남
				eat = 0;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		matrix = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());

				if (matrix[i][j] == 9) { // 아기상어
					sx = i;
					sy = j;
					ssize = 2;
					eat = 0;

					matrix[i][j] = 0;
				}
			}
		}

		BFS();
		System.out.println(result);

		br.close();

	}

}
