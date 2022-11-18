import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS + Graph(Q46_18352와 유사)
public class Q047_1325 {
	public static int[] answer; //거리 누적 정보(신뢰도)
	public static boolean[] visited; //방문 여부
	public static Queue<Integer> bfsQueue; 
	public static List<Integer>[] graph;	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
	
		graph = new List[N + 1];
		visited = new boolean[N + 1];
		answer = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
			answer[i] = 0;
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			graph[s].add(e);
		}
		
		for(int i = 1; i <= N; i++) { //모든 노드에서 bfs 탐색. 신뢰도 누적
			visited = new boolean[N + 1];
			bfs(i);
		}
		
		int maxVal = 0;
		for(int i = 1; i <= N; i++) { //최대값 선택
			maxVal = Math.max(maxVal, answer[i]);
		}
		
		
		for(int i = 1; i <= N; i++) { //최대값인 모든 노드 오름차순 출력
			if(answer[i] == maxVal) {
				System.out.print(i + " ");
			}
		}
	}
	public static void bfs(int start) {
		bfsQueue = new LinkedList<Integer>();
		visited[start] = true;
		bfsQueue.add(start);
		
		while(!bfsQueue.isEmpty()) {
			int outNode = bfsQueue.poll();
			for(int n : graph[outNode]) {
				if(visited[n] == false) {
					bfsQueue.add(n);
					answer[n]++; //신뢰도 누적
					visited[n] = true;
				}
			}
		}
	}
}
