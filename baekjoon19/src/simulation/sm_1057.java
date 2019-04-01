package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 토너먼트 그림 그려서 번호 매겨보면 규칙이 나옴
public class sm_1057 {

	static int N, J, H; // 참가자 수, 김지민, 임한수

	static int round;

	// 번호가 같아진다는 건 둘이 대결을 했다는 것임
	// 둘이 대결을 했을 경우 둘 중 누가 이기든 같은 번호가 나오기 때문
	public static void Game() {
		while (J != H) {
			if (J % 2 == 0) {
				J /= 2;
			} else {
				J = J / 2 + 1;
			}

			if (H % 2 == 0) {
				H /= 2;
			} else {
				H = H / 2 + 1;
			}

			round++;
		}

		if (J != H) { // 둘이 대결하지 않는다면
			System.out.println(-1);
		} else {
			System.out.println(round);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		J = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		Game();

		br.close();
	}

}
