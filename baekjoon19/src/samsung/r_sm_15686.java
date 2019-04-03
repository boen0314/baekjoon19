package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백트래킹 문제!
// list 이용해서 실행시간 줄이기!
public class r_sm_15686 {

	static int N, M;

	// matrix 배열로 일일이 다 탐색하는 것보다 list로 방문하는게 더 효율적
	static ArrayList<Integer> home;
	static ArrayList<Integer> chicken;
	static ArrayList<Integer> select;

	static int result;

	public static void Select(int n, int cnt) {
		if (n == chicken.size() || cnt == M) {
			if (cnt == 0)
				return; // 치킨집 적어도 1개 존재

			Distance(); // 치킨거리 계산
			return;
		}

		int cur = chicken.get(n);
		select.add(cur); // 치킨집 선택
		Select(n + 1, cnt + 1);
		// remove는 인덱스로 하는거! 조심해
		select.remove(select.indexOf(cur)); // 백트래킹

		Select(n + 1, cnt);
	}

	public static void Distance() {
		int sum = 0;

		for (int h : home) {
			int hx = h / N;
			int hy = h % N;

			int min = Integer.MAX_VALUE;
			for (int s : select) {
				int sx = s / N;
				int sy = s % N;

				int dis = Math.abs(hx - sx) + Math.abs(hy - sy);
				min = Math.min(min, dis);
			}
			sum += min; // 도시의 치킨거리
		}
		result = Math.min(result, sum);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		home = new ArrayList<>();
		chicken = new ArrayList<>();
		select = new ArrayList<>();

		int tmp = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				tmp = Integer.parseInt(st.nextToken());

				if (tmp == 1) {
					home.add(i * N + j); // 인덱스로 저장
				} else if (tmp == 2) {
					chicken.add(i * N + j); // 인덱스로 저장
				}
			}
		}

		result = Integer.MAX_VALUE;
		Select(0, 0);
		System.out.println(result);

		br.close();
	}

}
