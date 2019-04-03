package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dp로도 풀 수 있는 문제! 꼭 dp로도 풀어보기
public class sm_14501 {

	static int N;
	static int[][] matrix;

	static int max;

	public static void DFS(int day, int venue) {
		if (day > N) {
			max = Math.max(max, venue);
			return;
		}

		if (day + matrix[day][0] - 1 <= N) {
			DFS(day + matrix[day][0], venue + matrix[day][1]);
		}

		DFS(day + 1, venue); // 상담 안함

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		matrix = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
		}

		DFS(1, 0);

		System.out.println(max);

		br.close();
	}

}
