package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class binary_2869 {

	static long A, B, V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		V = Long.parseLong(st.nextToken());

		long up = A - B;
		long left = 0, right = 1000000000;
		long result = 0;

		if (A == V) {
			System.out.println(0);
			return;
		}

		while (left <= right) {
			long mid = (left + right) / 2; // mid == day

			// 너무 많이 올라감
			if ((up * mid) + A >= V) {
				right = mid - 1;
				result = mid + 1;
			} else {
				left = mid + 1;
			}
		}

		System.out.println(result);
		br.close();

	}

}
