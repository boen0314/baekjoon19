package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sm_2455 {

	static int people;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int down, up = 0;

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			down = Integer.parseInt(st.nextToken());
			up = Integer.parseInt(st.nextToken());

			people -= down;
			people += up;

			max = Math.max(max, people);

		}

		System.out.println(max);
		br.close();
	}

}
