package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 드래곤 커브 (문제 이해 & 그려서 규칙 찾기 중요)
// 규칙 찾는게 가장 어렵고, 규칙만 찾으면 구현은 쉬운 문제! (수치화 해보기)
public class rrr_sm_15685 {

	static int N; // 드래곤 커브 개수
	static int x, y, d, g;

	static boolean[][] matrix;
	static LinkedList<Integer> list; // 드래곤 커브 순서

	static int[] dy = { 0, -1, 0, 1 }; // 우0(→)상1(↑)좌2(←)하3(↓)
	static int[] dx = { 1, 0, -1, 0 }; // x가로, y세로

	public static void dragonCurve() {
		list.add(d);
		while (g-- > 0) {
			int last = list.size() - 1;
			for (int i = last; i >= 0; i--) {
				int dir = list.get(i);
				dir = (dir + 1) % 4;
				list.add(dir);
			}
		}
	}

	public static void Draw() {
		int ny = y;
		int nx = x;

		matrix[ny][nx] = true; // 시작점

		for (int i : list) {
			ny += dy[i];
			nx += dx[i];

			matrix[ny][nx] = true;
		}
	}

	public static int Count() {
		int count = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (matrix[i][j] && matrix[i + 1][j] && matrix[i][j + 1] && matrix[i + 1][j + 1])
					count++;
			}
		}
		return count;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		matrix = new boolean[101][101]; // 입력은 격자 밖으로 벗어나지 않음

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()); // 가로 (주의!)
			y = Integer.parseInt(st.nextToken()); // 세로
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());

			list = new LinkedList<>();

			dragonCurve(); // 방향 순서 계산
			Draw(); // 방향 순서대로 배열에 체크
		}

		// 사각형 카운트
		System.out.println(Count());
		br.close();
	}
}

// 드래곤 커브 규칙
// 0세대 : 0(→)
// 1세대 : 0(→)3(↓)
// 2세대 : 0(→)3(↓)2(←)3(↓)
// 3세대 : 0323 / 2123
// 4세대 : 0323 2123 / 2101 2123
// 예를들어 2세대에서 3세대로 갈 경우 0323을 역으로 3230으로 바꿔서 1씩 빼주면
// 2123이 됨. 이걸 뒤에 연결해 주면 0323 2123이 됨. ★그려보면 규칙 찾을 수 있음!

// 그리고 방향 규칙대로 따라가며 '그려주기 위해' 방향을 그림 움직임에 맞게 바꿔줌
// 0세대 : 0(→)
// 1세대 : 0(→)1(↑)
// 2세대 : 0(→)1(↑) / 2(←)1(↑)
// 3세대 : 0(→)1(↑)2(←)1(↑) / 2(←)3(↓)2(←)1(↑)
// 결국 2세대에서 3세대로 갈경우 0121을 역으로 1210으로 바꿔서 1을 더해주면
// 2321이 됨. 이걸 뒤에 연결해 주면 0121 2321이 됨. ★그려보면 규칙 찾을 수 있음!
