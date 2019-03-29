package back_recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 핵심인 부분 잘 익히기!♡♡♡♡♡
public class r_back_2580 {

	static int[][] sudoku;
	static boolean[][] cv, ch, cx;

	public static void DFS(int n) {
		if (n == 9 * 9) { // 스도쿠 다 채움
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(sudoku[i][j] + " ");
				}
				System.out.println();
			}
			// 하나만을 출력하고 프로그램 종료해야함
			// return 은 함수 종료 이므로 여러번 출력될 가능성 있음
			System.exit(0);
		}

		int x = n / 9;
		int y = n % 9;

		if (sudoku[x][y] != 0)
			DFS(n + 1);
		else {
			for (int i = 1; i <= 9; i++) {
				// 핵심 ♡♡♡♡♡
				if (!cv[x][i] && !ch[y][i] && !cx[box(x, y)][i]) {
					cv[x][i] = ch[y][i] = cx[box(x, y)][i] = true;
					sudoku[x][y] = i;

					DFS(n + 1); // 다음 자리 이동

					// 백트래킹
					cv[x][i] = ch[y][i] = cx[box(x, y)][i] = false;
					sudoku[x][y] = 0;
				}
			}
		}

	}

	public static int box(int x, int y) {

		return (x / 3) * 3 + (y / 3); // 핵심 ♡♡♡♡♡

		// 0 1 2 |-> +1 +(y/3)
		// 3 4 5 V
		// 6 7 8 *3 (x/3)*3
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		sudoku = new int[9][9];

		cv = new boolean[9][10]; // vertical (세로, i)
		ch = new boolean[9][10]; // horizontal (가로, j)
		cx = new boolean[9][10]; // 박스

		int tmp;
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = tmp = Integer.parseInt(st.nextToken());

				// 여기가 제일 핵심인 부분!♡♡♡♡♡
				if (sudoku[i][j] != 0) {
					cv[i][tmp] = true;
					ch[j][tmp] = true;
					cx[box(i, j)][tmp] = true;
				}
			}
		}

		DFS(0); // 스도쿠 채우기

		br.close();
	}
}
