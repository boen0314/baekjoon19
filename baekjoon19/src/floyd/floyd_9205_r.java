package floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Point {
	int x, y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class floyd_9205_r {

	static int T;
	static int N;

	static boolean[][] visited;
	static ArrayList<Point> list;

	public static void Floyd() {
		for (int k = 0; k < N + 2; k++) {
			for (int x = 0; x < N + 2; x++) {
				for (int y = 0; y < N + 2; y++) {
					if (visited[x][k] && visited[k][y])
						visited[x][y] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());

			visited = new boolean[102][102]; // 장소 102개 이하
			list = new ArrayList<>();

			for (int n = 0; n < N + 2; n++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				list.add(new Point(x, y));
			}

			for (int i = 0; i < N + 2; i++) {
				visited[i][i] = true;

				for (int j = i + 1; j < N + 2; j++) {

					Point A = list.get(i);
					Point B = list.get(j);

					int d = Math.abs(A.x - B.x) + Math.abs(A.y - B.y);

					// 50m 마다 1병이니까 1000m 까지 갈 수 있음
					if (d <= 1000) {
						visited[i][j] = visited[j][i] = true;
					}
				}
			}

			Floyd();

			System.out.println(visited[0][N + 1] ? "happy" : "sad");
		}
		
		br.close();
	}

}
