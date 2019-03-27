package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class binary_10815_r {

	static int N, M;
	static int[] card;

	public static void Binary(int beg, int end, int target) {
		int mid = (beg + end) / 2; // 가운데 값

		if (card[mid] == target) {
			System.out.print(1 + " ");
			return;
		}

		if (beg >= end) {
			System.out.print(0 + " ");
			return;
		}

		if (card[mid] > target)
			Binary(beg, mid - 1, target);
		else if (card[mid] < target)
			Binary(mid + 1, end, target);
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
			Binary(0, N - 1, tmp);
		}

		br.close();
	}

}
