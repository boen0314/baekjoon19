package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Document {
	int index;
	int priority;

	public Document(int index, int priority) {
		super();
		this.index = index;
		this.priority = priority;
	}
}

// 우선순위 큐가 생각나는 문제이나, 우선순위 큐로 풀면 안됨
// 시뮬레이션 해보면 왜 안되는지 알게 됨 (앞에 있는 걸 맨 뒤로 보내야 하기 때문에)
public class sm_1966 {

	static int T;
	static int N, M; // 문서의 수, 궁금한 문서 위치

	static Queue<Document> print;
	static LinkedList<Integer> priority_seq;

	public static void Print(int cnt) {
		Document cur = print.poll();

		// 현재 문서의 중요도가 가장 높은 경우
		if (cur.priority == priority_seq.get(0)) {
			// 현재 문서가 궁금한 문서인 경우
			if (cur.index == M) {
				System.out.println(cnt + 1); // 프린트 하고 종료
				return;
			}
			// 궁금한 문서가 아닌 경우 프린트 함
			else {
				priority_seq.remove(0); // 중요도 삭제
				Print(cnt + 1);
			}
		}
		// 현재 문서의 중요도보다 높은 중요도 문서가 있는 경우
		else {
			print.add(cur); // 다시 프린터 큐 뒤에 추가
			Print(cnt);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			print = new LinkedList<>();
			priority_seq = new LinkedList<>();

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				print.add(new Document(i, tmp));
				priority_seq.add(tmp); // 우선순위
			}

			// 우선순위 내림차순 정렬 (오름차순 X)
			Collections.sort(priority_seq, Collections.reverseOrder());

			Print(0);
		}

		br.close();
	}

}
