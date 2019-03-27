package line_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test_1 {

	static int N;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		result = Integer.MAX_VALUE;

		for (int w = 1; w <= N; w++) {
			for (int h = 1; h <= N; h++) {
				if (w * h == N) {
					result = Math.min(result, Math.abs(w - h));
				}
			}
		}

		System.out.println(result);
		br.close();
	}
}
