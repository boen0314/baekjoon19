package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// low_bound 와 high_bound 를 찾는 문제로
// 기본 이분 탐색에서, 찾는 값이 여러개일때 사용하는 방법이다.
// 잘 알아두면 유용하다.
// 복습 꼭 다시 하기
public class r_binary_10816 {

	static int N, M;
	static int[] card;

	public static int BinaryLow(int beg, int end, int target) {
		while (beg <= end) {
			int mid = (beg + end) / 2;

			if (card[mid] < target)
				beg = mid + 1;
			else if (mid == end && mid == beg)
				break;
			else
				end = mid - 1;
		}
		return beg;
	}

	public static int BinaryHigh(int beg, int end, int target) {
		while (beg <= end) {
			int mid = (beg + end) / 2;

			if (card[mid] <= target) // 여기만 차이남!
				beg = mid + 1;
			else if (mid == end && mid == beg)
				break;
			else
				end = mid - 1;
		}
		return beg;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		card = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(card); // 배열 정렬(퀵정렬)

		M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			int low = BinaryLow(0, N - 1, tmp);
			int high = BinaryHigh(0, N - 1, tmp);

			System.out.print(high - low + " ");

		}

		br.close();
	}

}
