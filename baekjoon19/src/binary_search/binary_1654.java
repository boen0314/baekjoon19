package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2805번 문제랑 거의 같음
public class binary_1654 {

	static int K, N;
	static long[] LAN;

	static long max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		LAN = new long[K];

		for (int i = 0; i < K; i++) {
			LAN[i] = Long.parseLong(br.readLine());

			max = Math.max(max, LAN[i]); // 최대로 절단
		}

		long left = 1, right = max;
		long result = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			long cnt = 0; // 자른 갯수

			for (int i = 0; i < K; i++) {
				cnt += LAN[i] / mid;
			}

			// 랜선 개수가 너무 많음
			if (cnt >= N) {
				left = mid + 1; // 길이를 더 늘려야, 랜선 개수가 적게 나옴
				result = Math.max(result, mid); // 가능한 길이 중 가장 큰 값
			}
			// 랜선 개수가 부족함
			else {
				right = mid - 1;
			}
		}

		System.out.println(result);
		br.close();
	}

}
