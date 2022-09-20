import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//DFS(재귀) & BFS(큐)
public class Q026_1260 {
	public static ArrayList<Integer>[] connectInfo;
	public static Queue<Integer> bfsQueue;
	public static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		
		//초기화
		connectInfo = new ArrayList[v + 1];
		visited = new boolean[v + 1];
		bfsQueue = new LinkedList<Integer>();
		for(int i = 1; i <= v; i++) {
			connectInfo[i] = new ArrayList<Integer>();
			visited[i] = false;
		}
		
		//연결 정보
		for(int i = 1; i <= e; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
					
			connectInfo[n1].add(n2);
			connectInfo[n2].add(n1);
		}
		
		//탐색시 작은 수부터 탐색되도록 사전 정렬
		for(int i = 1; i <= v; i++) {
			Collections.sort(connectInfo[i]);
		}
		
		DFS(start);
		System.out.println();
		
		//bfs를 위해 방문정보 다시 초기화
		for(int i = 1; i <= v; i++) {
			visited[i] = false;
		}
		BFS(start);
		System.out.println();
	}
	public static void BFS(int start) {
		bfsQueue.add(start); //큐에 첫 시작 노드 삽입
		visited[start] = true; //첫 시작노드 방문했음
		
		while(!bfsQueue.isEmpty()) { //큐가 비었을때가지 반복
			int outNode = bfsQueue.poll(); //맨 앞노드 빼내기
			System.out.print(outNode + " ");
			for(int i : connectInfo[outNode]) { //맨 앞노드에 연결된 리스트의 원소들 방문. 큐에 삽입
				if(visited[i] == false) {
					visited[i] = true;
					bfsQueue.add(i);
				}
			}
		}
	}
	public static void DFS(int start) {
		System.out.print(start + " ");
		if(visited[start] == true) {
			return;
		}
		visited[start] = true;
		
		for(int i : connectInfo[start]) {
			if(visited[i] == false) {
				DFS(i);
			}
		}
		
	}
}
