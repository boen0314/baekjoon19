package samsung;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

class Dummy {
	int x, y;

	public Dummy(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

// DFS + 시뮬레이션 문제 (1시간 만에 품)
// 시작 좌표, 시작 시간 등 초기 조건, 문제 잘 읽고 실수하지 말기!
public class r_sm_3190 {

	static int N; // 보드의 크기
	static int K; // 사과의 개수
	static int L; // 뱀의 방향 변환 횟수

	static int[][] matrix;
	static char[] direction;

	static int[] dx = { -1, 0, 1, 0 }; // 상좌하우
	static int[] dy = { 0, -1, 0, 1 };

	static LinkedList<Dummy> dummy; // 뱀 몸통 리스트

	public static void DFS(int x, int y, int d, int time) {
		int nx = x + dx[d];
		int ny = y + dy[d];

		if (nx < 1 || ny < 1 || nx > N || ny > N) {
			System.out.println(time);
			return; // 벽에 부딪히면 함수 종료
		}

		if (matrix[nx][ny] == 1) {
			System.out.println(time);
			return; // 자기자신 몸과 부딪히면 함수 종료
		}

		if (matrix[nx][ny] == 2) { // 사과
			matrix[nx][ny] = 1;
			dummy.add(new Dummy(nx, ny));
		}
		// 빈칸
		else {
			matrix[nx][ny] = 1;
			dummy.add(new Dummy(nx, ny));

			Dummy tail = dummy.removeFirst();
			matrix[tail.x][tail.y] = 0; // 꼬리가 위치한 칸 비워줌
		}

		if (direction[time] == 'D') { // 오른쪽 회전
			d = (d + 3) % 4;
		} else if (direction[time] == 'L') { // 왼쪽 회전
			d = (d + 1) % 4;
		} else
			; // 회전 안함

		DFS(nx, ny, d, time + 1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		matrix = new int[N + 1][N + 1]; // 좌표 (1,1) 시작!♡♡♡♡♡
		dummy = new LinkedList<>();

		matrix[1][1] = 1; // 뱀
		dummy.add(new Dummy(1, 1)); // 뱀

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			matrix[x][y] = 2; // 사과
		}

		L = Integer.parseInt(br.readLine());

		direction = new char[10001];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			direction[t] = d;
		}

		DFS(1, 1, 3, 1); // 1초부터 시작♡♡♡♡♡
		br.close();

	}

}
