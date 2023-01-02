import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 *다익스트라 알고리즘-최단거리 구하기:O(ElogV) 출발 노드와 그 외 모든 노드간의 최단거리를 구한다. Edge는 항상 양수여야한다.
 *1. 그래프 정보를 리스트로 저장
 *2. 최단거리 배열 생성->출발 노드의 값은 0 / 이외 노드는 무한대(최대값)으로 초기화
 *3. 최단 거리 배열에서 값이 가장 작은 노드를 선택한다. -> 처음 시작했을때는 값이 0인 출발 노드를 선택하게 됨
 *4. 최단 거리 배열 업데이트->선택된 노드와 연결된 Edge의 거리값으로 저장하되 더 작은 값일 경우에 업데이트. 
 *		Min(선택노드 최단 거리 배열값 + Edge 가중치, 연결 노드의 최단 거리 배열값)
 *5. 3~4의 과정을 반복한다.
 */
public class Q056_1753 {
	public static class Node implements Comparable<Node>{
		int end; //목적지
 		int dist; //가중치
		
		public Node() {}
		public Node(int end, int dist) {
			this.end = end;
			this.dist = dist;
		}
		
		public int compareTo(Node n) {
			if(this.dist > n.dist) return 1;
			else return -1;
		}
	}
	
	public static ArrayList<Node>[] graph; // 그래프 정보 저장
	public static int[] dijkstra; //다익스트라 최단 거리 정보 저장
	public static PriorityQueue<Node> queue; //다익스트라 탐색 우선순위 큐->거리가 최소인 노드를 우선으로 선택하도록
	public static boolean[] visited;//다익스트라 탐색 방문 여부
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int startLoc = Integer.parseInt(br.readLine()); //출발 지점
		
		//그래프, 최단거리 배열 초기화
		graph = new ArrayList[N + 1];
		dijkstra = new int[N + 1];
		visited = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			 graph[i] = new ArrayList<Node>();
		}
		for(int i = 0; i <= N; i++) {
			dijkstra[i] = Integer.MAX_VALUE;
		}
		
		//그래프 정보 저장
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			graph[start].add(new Node(end, dist));
		}
		
		//다익스트라 탐색 시작
		queue = new PriorityQueue<Node>();
		queue.add(new Node(startLoc, 0)); //출발 위치에서 시작
		dijkstra[startLoc] = 0; //시작지점 거리정보 0으로 
		
		dijkstraSearch(); //다익스트라
		
		//결과 출력
		for(int i = 1; i <= N; i++) {
			if(dijkstra[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(dijkstra[i]);
			}
		}
		
	}
	
	public static void dijkstraSearch() {
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			int nowLoc = now.end;
			if(visited[nowLoc]) continue; //이미 방문한 노드는 패스
			visited[nowLoc] = true; //방문했음으로 표시
			for(Node next : graph[nowLoc]) {
				int nextLoc = next.end;
				int nextDist = next.dist;
				if(dijkstra[nextLoc] > dijkstra[nowLoc] + nextDist) { //합이 현재 목적지까지 최소거리값보다 작다면 갱신
					dijkstra[nextLoc] = dijkstra[nowLoc] + nextDist;
					queue.add(new Node(nextLoc, dijkstra[nextLoc])); //이부분 주의-queue.add(next)하면 시간 초과
				}
			}
		}
	}

}
