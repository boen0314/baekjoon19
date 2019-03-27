package line_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class test_5 {

	static int C, B;
	static boolean[][] visited;

	static int[] dx = { -1, 1, 2 };

	static int result;

	public static int Speed(int t) {
		int speed = 0;
		while (t != 0) {
			speed += t;
			t--;
		}
		return speed;
	}

	public static void BFS() {
		Queue<Brown> q = new LinkedList<>();
		q.add(new Brown(B, 0));
		visited[B][0] = true;

		while (!q.isEmpty()) {
			Brown b = q.poll();

			for (int i = 0; i < 3; i++) {
				int nx = b.x;
				int time = b.time;
				int cony = C + Speed(time + 1);

				if (i != 2) {
					nx += dx[i];
				} else {
					nx *= dx[i];
				}

				if (nx < 0 || nx >= 200001 || cony >= 200001)
					continue;

				if (visited[nx][time + 1])
					continue;

				if (nx == cony) {
					result = Math.min(result, time + 1);
					return;
				}

				q.add(new Brown(nx, time + 1));
				visited[nx][time + 1] = true;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		visited = new boolean[200001][700];

		result = Integer.MAX_VALUE;

		BFS();

		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}

		br.close();

	}

}

class Brown {
	int x;
	int time;

	public Brown(int x, int time) {
		super();
		this.x = x;
		this.time = time;
	}
}
