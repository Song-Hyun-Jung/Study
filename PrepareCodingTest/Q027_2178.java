import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS(배열 bfs 탐색을 큐를 이용해서)
public class Q027_2178 {
	static int[][] array;
	static boolean[][] visited;
	static int N;
	static int M;
	static int depth = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		array = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			String oneLine = br.readLine();
			for(int j = 1; j <= M; j++) {
				array[i][j] = Integer.parseInt(oneLine.substring(j - 1, j));
				visited[i][j] = false;
			}
		}
		
		BFS(1, 1);
		System.out.println(array[N][M]);
	}
	
	public static void BFS(int startX, int startY) {
		int[] dx = {0, 1, 0, -1}; //방향 이동을 위한 배열
		int[] dy = {1, 0, -1, 0};
	
		Queue<int[]> bfsQueue = new LinkedList<int[]>(); //원소가 배열타입인 큐-하나의 배열에 x, y 넣기
		bfsQueue.offer(new int[] {startX, startY}); //queue에서 추가-add(큐 꽉찬경우 예외발생 성공시 true 반환), offer(값 리턴. 값 추가 실패시 false반환 성공시 true반환) / 삭제-remove(예외발생), poll(값 리턴) / 검사-element(예외발생), peek(값 리턴)
		visited[startX][startY] = true;
		
		while(!bfsQueue.isEmpty()) {
			int[] now = bfsQueue.poll();
			
			for(int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];
				int y = now[1] + dy[i];
				//System.out.println(x + " " + y);
				if((x >= 1 && y >= 1) && (x <= N && y <= M)){ //범위가 배열 이내인지 확인
					if(visited[x][y] == false && array[x][y] != 0) { //방문한적이 없고 갈수 있는 길인지 확인
						visited[x][y] = true;
						array[x][y] = array[now[0]][now[1]] + 1; //깊이 업데이트
						bfsQueue.offer(new int[] {x, y}); //큐에 추가
					}
				}
			}
		}
		
	}
}
