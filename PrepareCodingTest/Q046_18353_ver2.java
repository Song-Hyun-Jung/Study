import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS + Graph(방향-인접리스트), visited에 거리정보 넣기
public class Q046_18353_ver2 {
	public static List<Integer>[] graph;
	public static int[] visited; //방문여부 저장, 거리정보 저장
	public static Queue<Integer> bfsQueue;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		visited = new int[N + 1];
		
		bfsQueue = new LinkedList<Integer>();
		
		//인접리스트 초기화, visited 초기화
		graph = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
			visited[i] = -1; //방문하지 않았으면 -1
		}
		
		//값 받기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			graph[start].add(end);
		}
		
		bfs(X); //bfs 탐색
		
		
		int result = 0;
		for(int i = 1; i <= N; i++) { //오름차순 출력 위해
			if(visited[i] == K) {
				result++;
				System.out.println(i);
			}
		}
		
		if(result == 0) { //거리 조건 만족하는게 없으면 -1 출력
			System.out.println(-1);
		}
		
		/*굳이? 
		List<Integer> answer = new ArrayList<Integer>();
		
		for(int i = 1; i <= N; i++) {
			if(visited[i] == K)
				answer.add(i);
		}
		if(answer.isEmpty()) {
			System.out.println(-1);
		}
		else {
			Collections.sort(answer);
			for(int i : answer) {
				System.out.println(i);
			}
		}
		*/
	}
	
	public static void bfs(int start) {
		bfsQueue.add(start);
		visited[start] = 0;
	
		while(!bfsQueue.isEmpty()) {
			int outNode = bfsQueue.poll(); //맨 앞노드 빼기

			for(int i : graph[outNode]) {
				if(visited[i] == -1) { //방문하지 않음
					visited[i] = visited[outNode] + 1;  //노드까지의 거리 = 이전노드까지의 거리 + 1
					
					//System.out.println(i + " " + distance[i]);
					bfsQueue.add(i);
				}
				
			}
			
		}
		
	}
}
