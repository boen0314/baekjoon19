package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Person {
	int x, y;

	public Person(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

// 새로운 유형! 잘 익혀두기! ♡♡♡♡♡
public class rrr_sm_9376 {

	static int T;
	static int H, W;

	static char[][] matrix;
	static int[][][] visited; // x, y, who(외부인, 죄수1, 죄수2) // 문 연 갯수

	static int psx1, psy1, psx2, psy2; // 죄수들 시작 위치

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int result;

	public static void BFS(int who, int x, int y) {
		Queue<Person> q = new LinkedList<>();
		q.add(new Person(x, y));
		visited[who][x][y] = 0;

		while (!q.isEmpty()) {
			Person p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= H + 2 || ny >= W + 2)
					continue;

				if (matrix[nx][ny] == '*')
					continue;

				// 빈공간
				if (matrix[nx][ny] == '.') {
					if (visited[who][nx][ny] > visited[who][p.x][p.y]) {
						q.add(new Person(nx, ny));
						visited[who][nx][ny] = visited[who][p.x][p.y];
					}
				}
				// 문
				else if (matrix[nx][ny] == '#') {
					if (visited[who][nx][ny] > visited[who][p.x][p.y] + 1) {
						q.add(new Person(nx, ny));
						visited[who][nx][ny] = visited[who][p.x][p.y] + 1;
					}
				}

			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			matrix = new char[H + 2][W + 2];
			visited = new int[3][H + 2][W + 2];

			// visited 초기화 ♡♡♡♡♡
			for (int w = 0; w < 3; w++) {
				for (int i = 0; i <= H + 1; i++)
					Arrays.fill(visited[w][i], Integer.MAX_VALUE);
			}

			boolean p_check = false;
			for (int i = 1; i <= H; i++) {
				String tmp = br.readLine();
				for (int j = 1; j <= W; j++) {
					matrix[i][j] = tmp.charAt(j - 1);

					// 죄수
					if (matrix[i][j] == '$') {
						if (!p_check) {
							psx1 = i;
							psy1 = j;
							matrix[i][j] = '.';

							p_check = true;
						} else {
							psx2 = i;
							psy2 = j;
							matrix[i][j] = '.';
						}

					}
				}
			}

			for (int i = 0; i <= H + 1; i++) {
				for (int j = 0; j <= W + 1; j++) {
					if (i == 0 || j == 0 || i == H + 1 || j == W + 1) {
						matrix[i][j] = '.';
					}
				}
			}

			result = Integer.MAX_VALUE;

			// BFS 3개다 돌려줌 ♡♡♡♡♡
			BFS(0, 0, 0); // 외부인
			BFS(1, psx1, psy1); // 죄수1
			BFS(2, psx2, psy2); // 죄수2

			// 탈출이 불가능한 경우는 없어서 셋이 만나지 못하는 경우는 체크하지 않는 걸까? (의문)
			for (int i = 1; i <= H; i++) {
				for (int j = 1; j <= W; j++) {
					// 셋이 만난 정점이 빈공간인 경우
					if (matrix[i][j] == '.') {
						int door = 0;
						for (int w = 0; w < 3; w++) {
							door += visited[w][i][j];
						}
						result = Math.min(result, door);
					}
					// 셋이 만난 정점이 문 인 경우 (문 갯수 2번 중복되므로 빼줘야함)
					else if (matrix[i][j] == '#') {
						int door = 0;
						for (int w = 0; w < 3; w++) {
							door += visited[w][i][j];
						}
						result = Math.min(result, door - 2);
					}
				}
			}

			System.out.println(result);
		}

		br.close();

	}
}

// 풀이♡♡♡♡♡
// 1. 상근이가 감옥 밖에서 문을 열고 들어가는 경우.
// 2. 죄수 1이 문을 열고 나가는 경우.
// 3. 죄수 2가 문을 열고 나가는 경우.
//
// 이렇게 3가지로 나눌 수 있다.
//
// 결국 이 3명이 서로 각각 문을 최소로 열고 만나는 한 지점이 생긴다.
// 그 지점에 도달하기까지 3명이 문을 연 횟수를 전부 더한 값이 정답이다.
// 만나는 지점이 문이라면, 문은 한번만 열면 되기 때문에 -2를 해줘야한다.
