package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Star {
	int x, y;

	public Star(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class rr_sm_back_3967 {

	static char[][] matrix;
	static boolean[] visited;

	static ArrayList<Star> list; // 핵심!♡♡♡♡♡

	static int cntX;
	static boolean isFinish;

	public static void DFS(int cnt, int idx) {
		if (isFinish) {
			return; // 한번만 출력하기 위해
		}

		if (cnt == cntX) {
			if (Check()) {
				isFinish = true;
				Print();
			}
			return;
		}

		// 핵심!♡♡♡♡♡
		int x = list.get(idx).x;
		int y = list.get(idx).y;

		for (int i = 0; i < 12; i++) {
			if (!visited[i]) {
				visited[i] = true;
				matrix[x][y] = (char) (i + 'A');

				DFS(cnt + 1, idx + 1);

				// 백트래킹
				visited[i] = false;
				matrix[x][y] = 'x';
			}
		}

	}

	// 수작업 해줘야함ㅠ
	public static boolean Check() {
		int sum = (matrix[0][4] - 'A') + (matrix[1][3] - 'A') + (matrix[2][2] - 'A') + (matrix[3][1] - 'A');
		if (sum != 22) {
			return false;
		}

		sum = (matrix[3][1] - 'A') + (matrix[3][3] - 'A') + (matrix[3][5] - 'A') + (matrix[3][7] - 'A');
		if (sum != 22) {
			return false;
		}

		sum = (matrix[3][7] - 'A') + (matrix[2][6] - 'A') + (matrix[1][5] - 'A') + (matrix[0][4] - 'A');
		if (sum != 22) {
			return false;
		}

		sum = (matrix[1][1] - 'A') + (matrix[2][2] - 'A') + (matrix[3][3] - 'A') + (matrix[4][4] - 'A');
		if (sum != 22) {
			return false;
		}

		sum = (matrix[4][4] - 'A') + (matrix[3][5] - 'A') + (matrix[2][6] - 'A') + (matrix[1][7] - 'A');
		if (sum != 22) {
			return false;
		}

		sum = (matrix[1][7] - 'A') + (matrix[1][5] - 'A') + (matrix[1][3] - 'A') + (matrix[1][1] - 'A');
		if (sum != 22) {
			return false;
		}

		return true;
	}

	public static void Print() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		matrix = new char[5][9];
		visited = new boolean[12];

		list = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < 9; j++) {
				matrix[i][j] = tmp.charAt(j);

				// 핵심!♡♡♡♡♡
				if (matrix[i][j] >= 'A' && matrix[i][j] <= 'L') {
					visited[matrix[i][j] - 'A'] = true;
				}
				// 핵심!♡♡♡♡♡ list에 좌표 넣기
				else if (matrix[i][j] == 'x') {
					list.add(new Star(i, j));
					cntX++;
				}
			}
		}

		DFS(0, 0);

		br.close();
	}

}
