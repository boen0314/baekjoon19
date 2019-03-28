package back;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class back_1759 {

	static int L, C;
	static char[] alphabet;
	static boolean[] visited;

	public static void DFS(int n, int lenght, int m, int j) {
		if (lenght == L && m >= 1 && j >= 2) {
			for (int i = 0; i < C; i++) {
				if (visited[i]) {
					System.out.print(alphabet[i]);
				}
			}
			System.out.println();
			return;
		}

		if (n >= C) // 배열 범위 벗어나면
			return;

		char cur = alphabet[n];
		// 모음
		if (cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o' || cur == 'u') {
			visited[n] = true;
			DFS(n + 1, lenght + 1, m + 1, j);
		}
		// 자음
		else {
			visited[n] = true;
			DFS(n + 1, lenght + 1, m, j + 1);
		}

		visited[n] = false; // 백트래킹

		DFS(n + 1, lenght, m, j); // 선택 안하는 경우
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		alphabet = new char[C];
		visited = new boolean[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(alphabet); // 미리 정렬

		DFS(0, 0, 0, 0);

		br.close();
	}

}
