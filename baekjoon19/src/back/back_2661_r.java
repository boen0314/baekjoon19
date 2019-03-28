package back;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class back_2661_r {

	static int N;

	public static void DFS(String s, int len) {
		if (len == N) {
			System.out.println(s);
			System.exit(0);// 제일 작은 수가 제일 먼저 출력되므로 바로 '프로그램 종료'
			// return; 하면 함수만 종료 되므로 여러개 출력됨
		}

		for (int i = 1; i <= 3; i++) { // 1, 2, 3
			if (isGood(s + i)) { // String은 +하면 뒤에 붙여지는 원리이용
				DFS(s + i, len + 1);
			}
		}

	}

	public static boolean isGood(String s) {
		int len = s.length(); // 길이 8 (인덱스는 0~7까지)
		int half = len / 2; // 4
		int start = len - 1; // 시작 7 (7번째 인자값 부터)
		int end = len; // 끝 8 (8-1 = 7 번째 인자값까지 리턴, 8번째 제외)

		// 핵심!♡♡♡♡♡
		String tmp1, tmp2;
		for (int i = 1; i <= half; i++) { // i글자씩 비교
			tmp1 = s.substring(start, end);
			tmp2 = s.substring(start - i, end - i);

			if (tmp1.equals(tmp2)) // 이퀄 함수 써야함
				return false;

			start--; // start 위치 변경
		}
		return true; // false로 반환안되면 좋은 수열
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		DFS("1", 1); // 1로 시작하는게 2,3으로 시작하는 것보다 작으므로

		br.close();
	}
}
