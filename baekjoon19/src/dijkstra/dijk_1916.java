package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class City implements Comparable<City> {
	int v, w;

	public City(int v, int w) {
		super();
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(City o) {
		if (this.w < o.w) { // 가중치가 더 적으면 우선순위 높아짐
			return -1;
		} else {
			return 1;
		}
	}

}

// dijk_1753과 거의 똑같은 문제
public class dijk_1916 {

	static int N, M; // 도시의 개수, 버스의 개수
	static int u, v, w; // 출발, 도착, 비용
	static int S, E; // 출발점, 도착점 도시번호

	static int[] dist; // 출발점 부터의 최소 비용
	static ArrayList<ArrayList<City>> city;

	public static void Dijkstra() {
		dist[S] = 0;
		PriorityQueue<City> pq = new PriorityQueue<>();
		pq.add(new City(S, dist[S]));

		while (!pq.isEmpty()) {
			City cur = pq.poll();
			int cur_v = cur.v;

			for (City c : city.get(cur_v)) {
				int next_v = c.v;
				int next_w = c.w;

				if (dist[next_v] > dist[cur_v] + next_w) {
					dist[next_v] = dist[cur_v] + next_w;
					pq.add(new City(next_v, dist[next_v]));
				}

			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		dist = new int[N + 1]; // 도시 번호 1부터 시작
		Arrays.fill(dist, Integer.MAX_VALUE);

		city = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			city.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			city.get(u).add(new City(v, w));
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		Dijkstra();

		System.out.println(dist[E]);

		br.close();
	}

}
