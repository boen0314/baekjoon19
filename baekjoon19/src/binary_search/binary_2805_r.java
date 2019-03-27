package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000) 
// 입력의 범위가 매우 크니, 완전탐색으로 푸는 것은 불가능하고, 이분탐색으로 접근해야한다.
// 1654번, 2869번 문제랑 거의 같음
public class binary_2805_r {

	static int N; // 나무의 수
	static long M; // 나무의 길이
	static long[] tree;

	static long max; // 절단기 최대 높이

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());

		tree = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Long.parseLong(st.nextToken());

			max = Math.max(max, tree[i]);
		}

		long left = 0, right = max;
		long result = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			long sum = 0; // 총 자른 나무의 길이

			for (int i = 0; i < N; i++) {
				if (mid < tree[i])
					sum += tree[i] - mid;
			}

			// 너무 많이 잘랐음
			if (sum >= M) {
				if (result < mid)
					result = mid;

				left = mid + 1; // 더 높여야, 총 자른 나무 길이 작아짐
			}
			// 부족함
			else {
				right = mid - 1; // 더 낮춰야, 총 자른 나무 길이 커짐
			}

		}

		System.out.println(result);
		br.close();
	}

}
