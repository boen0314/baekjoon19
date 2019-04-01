package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sm_14499 {

	static int N, M;
	static int x, y, K; // 주사위를 놓은 곳 좌표, 명령의 개수

	static int[][] matrix;
	static int[] dice; // 주사위

	static int[] dx = { 0, 0, -1, 1 }; // 동서북남
	static int[] dy = { 1, -1, 0, 0 };

	public static void Move(int order) {
		int nx = x + dx[order - 1];
		int ny = y + dy[order - 1];

		if (nx < 0 || ny < 0 || nx >= N || ny >= M)
			return; // 명령 무시

		changeDice(order);

		if (matrix[nx][ny] == 0) {
			matrix[nx][ny] = dice[6];
		} else {
			dice[6] = matrix[nx][ny];
			matrix[nx][ny] = 0;
		}

		x = nx;
		y = ny;

		System.out.println(dice[1]);
	}

	public static void changeDice(int order) {
		// 인스턴스 복사본 생성 및 참조값 반환, 원본 배열과 다른 참조 주소값을 가진 배열을 가리킴!
		// 인스턴스가 객체를 또 가지고 있는 경우는 깊은 복사 해야함
		// https://blog.naver.com/lnamil24/220909369715 참조
		int[] tmp = dice.clone();

		switch (order) {
		case 1: // 동
			dice[1] = tmp[4];
			dice[4] = tmp[6];
			dice[3] = tmp[1];
			dice[6] = tmp[3];
			break;
		case 2: // 서
			dice[1] = tmp[3];
			dice[4] = tmp[1];
			dice[3] = tmp[6];
			dice[6] = tmp[4];
			break;
		case 3: // 북
			dice[2] = tmp[1];
			dice[1] = tmp[5];
			dice[5] = tmp[6];
			dice[6] = tmp[2];
			break;
		case 4: // 남
			dice[2] = tmp[6];
			dice[1] = tmp[2];
			dice[5] = tmp[1];
			dice[6] = tmp[5];
			break;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		K = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];
		dice = new int[7]; // 문제에 나와있는 인덱스 그대로

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int order = Integer.parseInt(st.nextToken());
			Move(order);
		}

		br.close();

	}
}
