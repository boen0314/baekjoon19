package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class SG {
	int x, y;

	public SG(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

// 너무 어렵당ㅠㅠ ♡♡♡♡♡
public class rr_sm_9328_r {

	static int T;
	static int H, W;
	static char[][] matrix;
	static boolean[][] visited;

	static HashSet<Character> key;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int result;

	public static void BFS() {
		Queue<SG> q = new LinkedList<>();
		q.add(new SG(0, 0)); // 상근이는 바깥 (0,0) 부터 시작
		visited[0][0] = true;

		// ★★★★ 핵심포인트
		ArrayList<ArrayList<SG>> door;
		door = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			door.add(new ArrayList<>());
		}

		while (!q.isEmpty()) {
			SG sg = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = sg.x + dx[i];
				int ny = sg.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= H + 2 || ny >= W + 2)
					continue;

				if (matrix[nx][ny] == '*' || visited[nx][ny])
					continue;

				char next = matrix[nx][ny];

				// 대문자인 경우, contains 메소드 활용 ♡
				if (next >= 'A' && next <= 'Z') {
					if (key.contains(next)) {
						matrix[nx][ny] = '.'; // 문 열림 ★★★★ 핵심포인트

					} else {
						// 문 list에 넣기 ★★★★ 핵심포인트
						door.get(next - 'A').add(new SG(nx, ny));
						continue;
					}
				}
				// 소문자인 경우
				else if (next >= 'a' && next <= 'z') {
					key.add(Character.toUpperCase(next)); // 키 획득

					// BFS큐에 넣기 (문 열렸으므로) ★★★★ 핵심포인트
					// 최소 시간 재는게 아니므로(문으로 돌아가는 시간 까지 재지 않음)
					for (SG e : door.get(next - 'a')) {
						q.add(e);
					}
					door.get(next - 'a').clear();
				}
				// 문서인 경우
				else if (next == '$') {
					matrix[nx][ny] = '.';
					result++;
				}

				q.add(new SG(nx, ny));
				visited[nx][ny] = true;
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
			visited = new boolean[H + 2][W + 2];

			for (int i = 1; i <= H; i++) {
				String tmp = br.readLine();
				for (int j = 1; j <= W; j++) {
					matrix[i][j] = tmp.charAt(j - 1);
				}
			}

			// 상근이는 밖에서 시작함 ★★★★ 핵심포인트
			for (int i = 0; i <= H + 1; i++) {
				for (int j = 0; j <= W + 1; j++) {
					if (i == 0 || j == 0 || i == H + 1 || j == W + 1) {
						matrix[i][j] = '.';
					}
				}
			}

			key = new HashSet<>();

			String tmpKey = br.readLine().toUpperCase(); // 대문자로 저장

			for (int i = 0; i < tmpKey.length(); i++) {
				key.add(tmpKey.charAt(i));
			}

			result = 0;
			BFS();
			System.out.println(result);

		}

		br.close();

	}

}
