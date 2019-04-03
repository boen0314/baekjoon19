package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1,000,000 이 넘어가는 숫자이므로 long형으로 ! ♡♡♡♡♡♡
// 총 감독관은 한방에 1명씩 무조건 있어야 함!
public class sm_13458 {

	static int N; // 시험장 수
	static long[] A; // 응시자 수
	static long B, C; // 총 감독관, 부 감독관

	static long result;

	public static void Solve() {
		for (int i = 0; i < N; i++) {
			if (A[i] <= B) {
				result++;
			} else {
				A[i] -= B;
				result++;

				long p = (A[i] / C);
				result += p;

				long last = A[i] % C;
				if (last != 0) // 더 남아 있으면
					result++; // 한명더
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Long.parseLong(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());

		Solve();
		System.out.println(result);
		br.close();

	}

}
