package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int v, p;

	public Node(int v, int p) {
		super();
		this.v = v;
		this.p = p;
	}

	@Override
	public int compareTo(Node o) {
		if (this.p < o.p) {
			return -1;
		} else {
			return 1;
		}
	}
}

// 문제 이해 잘하기 (최단 경로를 모두 끊어줘야함)
// "이 경로는 최단 경로에 포함되지 않은 도로로 이루어진 경로 중 가장 짧은 경로이다."
// 핵심 부분 잘 알아두기! ★★★★★ 최단경로 역추적
public class rr_dijkstra_5719 {

	static int N, M; // 장소의 수, 도로의 수
	static int S, D; // 시작점, 도착점
	static int u, v, p; // 정점, 도로의길이 (단방향)

	static int[] dist; // 시작점 부터의 최소 도로 길이
	static ArrayList<ArrayList<Node>> list, r_list;

	static boolean[][] visited; // 최단 경로 체크
	static final int INF = Integer.MAX_VALUE;

	public static void Dijkstra() {
		Arrays.fill(dist, INF); // 초기화
		dist[S] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(S, dist[S]));

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int cur_v = node.v;

			for (Node e : list.get(cur_v)) {
				int next_v = e.v;
				int next_p = e.p;

				if (dist[next_v] > dist[cur_v] + next_p) {
					// 처음 다익스트라 돌 때는 모두 기록 함 (visited 모두 false 이므로)
					if (!visited[cur_v][next_v]) { // 최단 경로가 아닐 경우만
						dist[next_v] = dist[cur_v] + next_p;
						pq.add(new Node(next_v, dist[next_v]));
					}
				}
			}
		}
	}

	// 이게 핵심!★★★★★ 최단경로 역추적
	public static void LoadChk() {
		Queue<Integer> q = new LinkedList<>();
		q.add(D);

		while (!q.isEmpty()) {
			int cur = q.poll();

			if (cur == S)
				continue;

			for (Node before : r_list.get(cur)) {
				if (dist[cur] == dist[before.v] + before.p) { // 최단 경로일 때
					visited[before.v][cur] = true; // 최단경로 체크
					q.add(before.v);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0)
				break;

			dist = new int[N];
			visited = new boolean[N][N];

			list = new ArrayList<>();
			r_list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				list.add(new ArrayList<>());
				r_list.add(new ArrayList<>());
			}

			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				p = Integer.parseInt(st.nextToken());

				list.get(u).add(new Node(v, p));
				r_list.get(v).add(new Node(u, p));
			}

			Dijkstra(); // 최단경로 추적
			LoadChk(); // 최단경로 체크
			Dijkstra(); // 거의 최단경로 추적

			if (dist[D] == INF) {
				System.out.println(-1);
			} else {
				System.out.println(dist[D]);
			}
		}

		br.close();
	}
}
