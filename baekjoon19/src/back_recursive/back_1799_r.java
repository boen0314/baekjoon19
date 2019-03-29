package back_recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//체스판의 하얀칸 검은칸을 분리하지 않고 백트래킹하면 시간초과난다. 
//하얀칸 검은칸은 대각선으로 나아가도 서로의 공간을 침범하지 않기때문에 분리해서 생각할 수 있다.
//따로 계산하면 시간초과 면할 수 있음!
//체스판의 원리를 알아야 풀 수 있는 문제!
public class back_1799_r {

	static int N;
	static int[][] matrix;
	static boolean[][] bishop;

	static int maxBishop;
	static int result;

	public static void White(int x, int y, int cnt) {
		maxBishop = Math.max(maxBishop, cnt);

		if (y > N) { // x가 범위를 넘었을 경우
			x += 1; // 다음 행으로 넘어감
			if (x % 2 == 0) // 짝수행은 흰색 칸부터 시작
				y = 1;
			else
				y = 2;
		}

		if (x > N) // y가 범위를 넘었을 경우
			return;

		if (matrix[x][y] == 1 && isGood(x, y)) { // 비숍을 두었을 경우

			bishop[x][y] = true;
			White(x, y + 2, cnt + 1);
			bishop[x][y] = false; // 백트래킹
		}

		// 비숍을 둘 수 있지만 안둔 경우와 둘 수 없는 경우
		White(x, y + 2, cnt);

	}

	public static void Black(int x, int y, int cnt) {
		maxBishop = Math.max(maxBishop, cnt);

		if (y > N) {
			x += 1;
			if (x % 2 == 1) // 홀수행은 검은색 칸부터 시작
				y = 1;
			else
				y = 2;
		}
		if (x > N)
			return;

		if (matrix[x][y] == 1 && isGood(x, y)) { // 비숍을 두었을 경우
			bishop[x][y] = true;
			Black(x, y + 2, cnt + 1);
			bishop[x][y] = false; // 백트래킹
		}

		// 비숍을 둘 수 있지만 안둔 경우와 둘 수 없는 경우
		Black(x, y + 2, cnt);

	}

	public static boolean isGood(int x, int y) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 대각선(|행1-행2|=|열1-열2|)인 경우 비숍 놓을 수 없음
				if (bishop[i][j] && Math.abs(i - x) == Math.abs(j - y)) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		matrix = new int[N + 1][N + 1];
		bishop = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Black(1, 1, 0); // 첫번째 행 첫번째 칸부터 시작
		result = maxBishop;
		maxBishop = 0;

		bishop = new boolean[N + 1][N + 1];
		White(1, 2, 0); // 첫번째 행 두번째 칸부터 시작
		result += maxBishop;

		System.out.println(result);

		br.close();
	}

}
