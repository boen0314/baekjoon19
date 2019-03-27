package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Graph implements Comparable<Graph> {
	int v, w; // 정점, 가중치

	public Graph(int v, int w) {
		super();
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(Graph o) {
		if (this.w < o.w) { // 가중치 적으면 우선순위 높아짐
			return -1;
		} else {
			return 1;
		}
	}
}

// 우선순위 큐 사용 안하면 시간초과 남!♡♡♡♡♡
public class dijk_1753_r {

	static int V, E, K; // 정점 개수, 간선 개수, 시작 정점 번호
	static int u, v, w; // u->v (가중치 w)

	static int[] dist;
	static ArrayList<ArrayList<Graph>> graph;

	public static void Dijkstra() {
		dist[K] = 0;

		PriorityQueue<Graph> pq = new PriorityQueue<>();
		pq.add(new Graph(K, dist[K]));

		while (!pq.isEmpty()) {
			Graph cur = pq.poll();
			int cur_v = cur.v;

			// 현재 정점과 연결된 정점들
			for (Graph g : graph.get(cur_v)) {
				int next_v = g.v;
				int next_w = g.w;

				// 여기가 핵심! 누적 가중치 중 값이 작은 것 저장
				if (dist[next_v] > dist[cur_v] + next_w) {
					dist[next_v] = dist[cur_v] + next_w;
					pq.add(new Graph(next_v, dist[next_v]));
				}

			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		K = Integer.parseInt(br.readLine());

		dist = new int[V + 1]; // 정점 1부터 시작
		Arrays.fill(dist, Integer.MAX_VALUE);

		graph = new ArrayList<>();
		for (int i = 0; i < V + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			graph.get(u).add(new Graph(v, w));
		}

		Dijkstra();

		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}

		br.close();

	}

}
