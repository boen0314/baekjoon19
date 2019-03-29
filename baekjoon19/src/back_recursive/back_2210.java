package back_recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class back_2210 {

	static String[][] matrix;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static HashSet<String> hs;

	public static void DFS(int x, int y, String s, int len) {
		if (len == 6) {
			hs.add(s);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
				continue;

			DFS(nx, ny, s + matrix[nx][ny], len + 1);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		matrix = new String[5][5];

		hs = new HashSet<>();

		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				matrix[i][j] = st.nextToken();
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				DFS(i, j, matrix[i][j], 1);
			}
		}

		System.out.println(hs.size());
		
		br.close();
	}

}
