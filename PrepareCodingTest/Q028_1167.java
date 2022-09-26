import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS(BFS 2번 사용, 거리-클래스 사용)
public class Q028_1167 {
	
	public static ArrayList<Info>[] arr;
	public static boolean[] visited;
	public static int[] distance;
	
	
	public static class Info{ //연결 노드와 거리정보를 저장하는 클래스
		public Info(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
		int node;
		int dist;
		public int getNode() {
			return node;
		}
		public void setNode(int node) {
			this.node = node;
		}
		public int getDist() {
			return dist;
		}
		public void setDist(int dist) {
			this.dist = dist;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//초기화
		int N = Integer.parseInt(br.readLine());
		arr = new ArrayList[N + 1];
		visited = new boolean[N + 1]; //방문
		distance = new int[N + 1]; //거리 누적
		
		//초기화
		for(int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<Info>();
			visited[i] = false;
			distance[i] = 0;
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				int n = Integer.parseInt(st.nextToken());
				if(n == -1) break;
				int d = Integer.parseInt(st.nextToken());
				Info connectInfo = new Info(n, d);
				arr[index].add(connectInfo);
			}
		}
		
		BFS(1); //한 정점에서 거리 계산
		
		
		int max = 1;
		for(int i = 2; i <= N; i++) {
			if(distance[max] < distance[i])
				max = i;
		} //한 정점에서 가장 먼거리에 있는 정점 찾기
		
		//다시 초기화->앞선 bfs에서 가장 먼거리에 있는 정점에서 다시 가장 먼거리에 있는 정점 찾기->지름
		distance = new int[N + 1];
		visited = new boolean[N + 1];
		BFS(max);

		//가장 먼거리 출력
		int maxDist = 0;
		for(int i = 1; i <= visited.length - 1; i++) {
			if(maxDist < distance[i])
				maxDist = distance[i];
		}
		System.out.println(maxDist);
		
	}
	public static void BFS(int start) {
		Queue bfsQueue = new LinkedList<Info>();
		bfsQueue.add(new Info(start, 0)); //시작점 지정. 거리는 0
		visited[start] = true;
		
		while(!bfsQueue.isEmpty()) {
			Info pollInfo = (Info) bfsQueue.poll();
			for(Info n : arr[pollInfo.getNode()]) {
				if(visited[n.getNode()] == false) {
					bfsQueue.add(n);
					visited[n.getNode()] = true;
					distance[n.getNode()] = n.getDist() + distance[pollInfo.getNode()]; //거리 누적
					//System.out.println(n.getNode() + " " + distance[n.getNode()]);
					
				}
			}
		}
	
	}

}
