package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사다리 그림을 표로 만들면 이해가 쉬움
// 백트래킹 + 시뮬레이션 문제 (사다리 가로선 표현 방법, 가로선 방향성 표시)
public class rr_sm_15684 {

	static int N, M, H; // 문제 잘 읽어야 함
	static int a, b;

	static int[][] matrix;

	static int result;

	public static void Ladder(int n, int cnt) {
		if (n == H * N) {
			if (Game()) { // i번 세로선 결과가 i이면
				result = Math.min(result, cnt);
			}
			return;
		}
		if (cnt > 3) // 3개 이상이면 안됨
			return;

		int x = (n / N) + 1; // 좌표 변환
		int y = (n % N) + 1; // (1, 1) 부터 시작

		// 연결된 사다리가 없으면
		if (y + 1 <= N && matrix[x][y] == 0 && matrix[x][y + 1] == 0) {
			matrix[x][y] = 1;
			matrix[x][y + 1] = 2;
			Ladder(n + 1, cnt + 1);
			// 백트래킹
			matrix[x][y] = 0;
			matrix[x][y + 1] = 0;
		}

		// 연결된 사다리가 없더라도 사다리 추가 안함
		// 또는 이미 연결된 사다리가 있으면(접해서도 안됨)
		Ladder(n + 1, cnt); // 다음칸으로 넘어감
	}

	public static boolean Game() {
		for (int i = 1; i <= N; i++) { // i번 세로선
			if (!Find(i))
				return false; // 하나라도 틀리면 false
		}
		return true;
	}

	public static boolean Find(int start) {
		int x = 1; // 상하
		int y = start; // 좌우

		while (x <= H) {
			// 가로선 시작점
			// 오른쪽 이동 후 한칸 내려간다
			if (matrix[x][y] == 1) {
				x++;
				y++;
			}
			// 가로선 끝점
			// 왼쪽으로 이동 후 한칸 내려간다
			else if (matrix[x][y] == 2) {
				x++;
				y--;
			}
			// 가로선이 없으면
			// 아래로 내려간다
			else {
				x++;
			}
		}

		if (y == start)
			return true;
		else
			return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		matrix = new int[H + 1][N + 1]; // (1, 1) 부터 시작

		for (int i = 0; i < M; i++) { // M개의 가로선
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			// 핵심! 방향성 때문에 시작점, 끝점 다르게 표시 ♡♡♡♡♡
			matrix[a][b] = 1; // 사다리 시작점
			matrix[a][b + 1] = 2; // 사다리 끝점
		}

		result = 10; // 초기화

		Ladder(0, 0);

		if (result == 10)
			System.out.println(-1);
		else
			System.out.println(result);

		br.close();

	}

}
