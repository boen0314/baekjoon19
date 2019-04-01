package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// Deque(덱, 데큐) 양방향 큐가 떠오르겠지만
// linkedlist 사용하는 것이 더 편한 문제
public class r_sm_1021 {

	static int N, M;
	static int[] select;

	static LinkedList<Integer> list;

	static int cnt;

	public static void Simulation() {
		for (int i = 1; i <= M; i++) {
			while (true) {
				int index = list.indexOf(select[i]);
				int left = index; // 0부터 시작
				int right = list.size() - index - 1; // 그림 그려보면 이해하기 쉬움

				if (left == 0) { // 전단에 위치
					list.removeFirst();
					break; // 다음 뽑아낼 원소 찾으러 돌아감
				} else if (left <= right) { // 왼쪽이 더 가까우면
					list.addLast(list.removeFirst());
					cnt++;
				} else {
					list.addFirst(list.removeLast());
					cnt++;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		select = new int[M + 1];

		list = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			select[i] = Integer.parseInt(st.nextToken());
		}

		Simulation();

		System.out.println(cnt);

		br.close();
	}

}
