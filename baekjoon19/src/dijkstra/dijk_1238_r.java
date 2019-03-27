package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Town implements Comparable<Town> {
	int v, w;

	public Town(int v, int w) {
		super();
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(Town o) {
		if (this.w < o.w) { // 가중치가 적으면 우선순위 높음
			return -1;
		} else {
			return 1;
		}
	}
}

// 단방향 연결이므로 오고가는길이 다르다는 것이 핵심인 문제! ♡♡♡♡♡
// 가는길은 오는길의 방향을 바꿔서 계산하면 간단함!(그려보면 이해가 쉬움) ♡♡♡♡♡
public class dijk_1238_r {

	static int N, M, X; // 마을수, 도로수, 모일 장소
	static int u, v, w; // 시작점, 끝점, 소요시간

	static int[] dist, dist_rv;
	static ArrayList<ArrayList<Town>> town, town_rv;

	static int result;

	// 배열은 call-by-reference로 전달(매개변수) ♡♡♡♡♡
	private static void Dijkstra(int[] dis, ArrayList<ArrayList<Town>> tw) {
		dis[X] = 0;
		PriorityQueue<Town> pq = new PriorityQueue<>();
		pq.add(new Town(X, dis[X]));

		while (!pq.isEmpty()) {
			Town cur = pq.poll();
			int cur_v = cur.v;

			for (Town t : tw.get(cur_v)) {
				int next_v = t.v;
				int next_w = t.w;

				if (dis[next_v] > dis[cur_v] + next_w) {
					dis[next_v] = dis[cur_v] + next_w;
					pq.add(new Town(next_v, dis[next_v]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		dist = new int[N + 1]; // 마을 번호 1부터 시작
		dist_rv = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(dist_rv, Integer.MAX_VALUE);

		town = new ArrayList<>();
		town_rv = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			town.add(new ArrayList<>());
			town_rv.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			town.get(u).add(new Town(v, w));
			town_rv.get(v).add(new Town(u, w)); // 반대로
		}

		Dijkstra(dist, town); // 집에 가는길 (X부터 시작하므로)
		Dijkstra(dist_rv, town_rv); // 파티 오는길

		result = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			result = Math.max(result, dist[i] + dist_rv[i]);
		}

		System.out.println(result);
		br.close();
	}

}
