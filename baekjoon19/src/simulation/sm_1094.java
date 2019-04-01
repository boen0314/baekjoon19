package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class sm_1094 {

	static int X;
	static int minStick;
	static int sum, count;

	public static void Cutting() {
		int tmp = sum - (minStick / 2);

		if (tmp == X) {
			count++; // 마지막으로 자른 막대 포함
			return;
		}

		if (tmp > X) {
			sum = tmp; // 자른 막대 절반 중 하나 버림
			minStick /= 2; // 반으로 나눈게 가장 작은 막대기가 됨
			Cutting();
		} else {
			count++; // 자른 막대 안버리고 X 막대 만들 때 포함
			minStick /= 2;
			Cutting();
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		X = Integer.parseInt(br.readLine());

		sum = 64;
		minStick = 64;

		if (X == 64) {
			System.out.println(1);
			return;
		}

		Cutting();

		System.out.println(count);
		br.close();
	}

}
