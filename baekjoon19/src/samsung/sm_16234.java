package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Nation {
	int x, y;

	public Nation(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class sm_16234 {

	static int N, L, R;
	static int[][] matrix;
	static boolean[][] visited;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static boolean flag;
	static int count;

	public static void Move() {
		flag = true;
		count = -1; // 초기화 (인구이동 한번도 안하는 경우도 while문은 한번 돌기 때문)

		while (flag) { // 인구이동 안할 때 까지 반복
			visited = new boolean[N][N];
			flag = false; // 인구 이동 여부 초기화

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						BFS(i, j);
					}
				}
			}
			count++;
		}
	}

	public static void BFS(int x, int y) {
		Queue<Nation> q = new LinkedList<>();
		q.add(new Nation(x, y));
		visited[x][y] = true;

		ArrayList<Nation> union = new ArrayList<>(); // 핵심!

		while (!q.isEmpty()) {
			Nation nt = q.poll();
			union.add(nt); // 핵심!

			for (int i = 0; i < 4; i++) {
				int nx = nt.x + dx[i];
				int ny = nt.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (visited[nx][ny])
					continue;

				int gap = Math.abs(matrix[nt.x][nt.y] - matrix[nx][ny]);
				if (gap >= L && gap <= R) {
					flag = true; // 인구이동 함!
					visited[nx][ny] = true;
					q.add(new Nation(nx, ny));
				}
			}
		}

		int sum = 0;
		for (Nation e : union) {
			sum += matrix[e.x][e.y];
		}
		for (Nation e : union) {
			matrix[e.x][e.y] = sum / union.size();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		matrix = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Move();
		System.out.println(count);
		br.close();
	}

}
