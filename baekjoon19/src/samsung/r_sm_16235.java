package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Tree implements Comparable<Tree> {
	int x, y;
	int age;

	public Tree(int x, int y, int age) {
		super();
		this.x = x;
		this.y = y;
		this.age = age;
	}

	@Override
	public int compareTo(Tree o) {
		if (this.age < o.age) {
			return -1;
		} else if (this.age > o.age) {
			return 1;
		} else {
			return 0;
		}
	}
}

// 우선순위큐와 리스트 활용 잘하면 되기만 하는 시뮬레이션 문제
public class r_sm_16235 {

	static int N, M, K; // 배열 크기, 초기 나무 갯수, K년
	static int x, y, z; // 좌표와 나무의 나이
	static int[][] A; // 양분
	static int[][] matrix;

	static ArrayList<Tree> tr; // 초기 나무 리스트

	static int[] dx = { 1, 0, -1, 0, 1, 1, -1, -1 };
	static int[] dy = { 0, 1, 0, -1, -1, 1, -1, 1 };

	public static void Season() {
		PriorityQueue<Tree> pq = new PriorityQueue<>();
		ArrayList<Tree> live = new ArrayList<>();
		Queue<Tree> breed = new LinkedList<>();
		Queue<Tree> death = new LinkedList<>();

		// 초기 나무 우선순위 큐에 넣음
		for (Tree e : tr) {
			pq.add(e);
		}

		// K년 동안 반복
		while (K-- > 0) {
			// 봄
			while (!pq.isEmpty()) {
				Tree tree = pq.poll();
				int x = tree.x;
				int y = tree.y;
				int age = tree.age;

				// 양분이 충분하면
				if (matrix[x][y] >= age) {
					matrix[x][y] -= age;
					// 살아 남는 나무 리스트에 추가
					live.add(new Tree(x, y, age + 1));
				} else {
					// 죽는 나무 큐에 추가
					death.add(new Tree(x, y, age));
				}
			}

			// 여름
			while (!death.isEmpty()) {
				Tree tree = death.poll();
				int x = tree.x;
				int y = tree.y;
				int age = tree.age;

				// 양분 추가
				matrix[x][y] += age / 2;
			}

			// 가을
			breed.addAll(live); // 번식 큐에 살아있는 나무 넣음

			while (!breed.isEmpty()) {
				Tree tree = breed.poll();
				int x = tree.x;
				int y = tree.y;
				int age = tree.age;

				// 나이 배수가 5인 경우에만 번식
				if (age % 5 == 0) {
					for (int i = 0; i < 8; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];

						if (nx < 1 || ny < 1 || nx > N || ny > N)
							continue;

						// 나이가 1인 나무 새로 생김
						live.add(new Tree(nx, ny, 1)); // 나무 번식
					}
				}
			}

			pq.addAll(live); // 우선순위 큐에 살아있는 나무 다시 넣어줌
			live.clear(); // 리스트 초기화 해줘야함

			// 겨울
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					matrix[i][j] += A[i][j]; // 양분 추가
				}
			}

		}

		System.out.println(pq.size()); // 나무 수
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N + 1][N + 1]; // (1,1) 시작
		matrix = new int[N + 1][N + 1];
		tr = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());

			tr.add(new Tree(x, y, z));
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				matrix[i][j] = 5; // 초기 양분 5
			}
		}

		Season();

		br.close();

	}

}
