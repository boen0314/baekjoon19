package line_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test_4 {

	static int N;
	static int[] building;

	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		building = new int[N];

		result = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			building[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < N; i++) {
			int j = i + 1;
			int count = 1;

			if (j >= N)
				break;

			while (building[i] > building[j]) {
				count++;
				j++;

				if (j >= N) {
					count = -1;
					break;
				}
			}

			result = Math.max(result, count);
		}

		if (result == -1) {
			System.out.println(0);
		} else {
			System.out.println(result);
		}

		br.close();
	}
}
