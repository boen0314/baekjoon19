package back_recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class back_2023 {

	static int N;

	public static void DFS(String s, int len) {
		if (len == N) {
			System.out.println(s);
			return;
		}

		// 끝자리 0 들어가면 짝수이므로 소수가 아님
		for (int i = 1; i <= 9; i++) {
			String tmp = s + i; // 문자열 뒤에 붙임
			// 소수이면
			if (isPrime(Integer.parseInt(tmp))) {
				DFS(tmp, len + 1);
			}
		}
	}

	public static boolean isPrime(int num) {
		if (num == 1)
			return false;

		if (num == 2) // 2는 소수
			return true;

		if (num % 2 == 0) // 짝수는 소수 아님
			return false;

		// 홀수로 나눠 떨어지는지 확인
		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0)
				return false;
		}

		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		DFS("", 0);

		br.close();
	}

}
