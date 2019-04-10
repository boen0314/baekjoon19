package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sm_17136 {

	static boolean[][] matrix;
	static int[] paper = { 0, 5, 5, 5, 5, 5 }; // 색종이 개수

	static int result;

	public static void DFS(int idx, int cnt) {
		if (idx == 10 * 10) {
			result = Math.min(result, cnt);
			return;
		}

		int x = idx / 10;
		int y = idx % 10;

		// 색종이 둘 수 있는 공간이 있을 경우
		if (matrix[x][y]) {
			// 색종이 사이즈 별로 놓아보기
			for (int size = 5; size >= 1; size--) {
				// 색종이 여분 있는 경우
				if (paper[size] > 0) {
					// 색종이 둘 수 있는 경우
					if (isSquare(x, y, size)) {
						paper[size]--;
						Put(x, y, size);

						DFS(idx + size, cnt + 1);

						Get(x, y, size); // 백트래킹
						paper[size]++;
					}
				}
			}
		}
		// 색종이 둘 수 없는 공간인 경우
		else {
			DFS(idx + 1, cnt);
		}

	}

	public static boolean isSquare(int x, int y, int size) {
		if (x + size > 10 || y + size > 10) {
			return false;
		}
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (!matrix[i][j])
					return false;
			}
		}
		return true;
	}

	public static void Put(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				matrix[i][j] = false;
			}
		}
	}

	public static void Get(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				matrix[i][j] = true;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		matrix = new boolean[10][10];

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				int tmp = Integer.parseInt(st.nextToken());

				if (tmp == 1)
					matrix[i][j] = true;
				else
					matrix[i][j] = false;
			}
		}

		result = Integer.MAX_VALUE;

		DFS(0, 0);

		if (result == Integer.MAX_VALUE)
			result = -1;
		System.out.println(result);

		br.close();

	}

}
