import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//DFS + graph, 이분그래프***
//이분그래프=인접한 정점끼리 서로 다른 색으로 칠해서 모든 정점을 두가지 색으로만 칠할 수 있는 그래프. 같은 그룹에 속한 정점끼리는 서로 인접하지 않도록 하는것
//이분그래프는 BFS 할때 같은 레벨 정점끼리는 같은색으로 칠함.
//1. BFS, DFS로 탐색하면서 정점 방문할때마다 두가지 색중 하나를 칠함.
//2. 다음 정점을 방문하면서 자신과 인접한 정점은 자신과 다른색으로 칠함.
//3. 자신과 인접한 정점의 색이 자신과 동일하면 이분그래프가 아님. 
public class Q048_1707 {
	public static List<Integer>[] graph;	
	public static boolean[] visited;
	public static int[] check;
	public static boolean result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		for(int i = 0; i < testCase; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node = Integer.parseInt(st.nextToken());
			int edge = Integer.parseInt(st.nextToken());
			
			graph = new List[node + 1];
			visited = new boolean[node + 1];
			check = new int[node + 1];
			result = true;
			
			for(int j = 1; j <= node; j++) {
				graph[j] = new ArrayList<Integer>();
			}
			
			for(int j = 0; j < edge; j++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				graph[start].add(end);
				graph[end].add(start);
			}
			
			//주어진 그래프가 1개로 연결되어있다는 보장이 없으므로 모든 노드에서 탐색
			for(int j = 1; j <= node; j++) { //각 노드에서 DFS 실행 -> 결과가 이분 그래프가 아니면 종료
				if(result == true)
				 dfs(j);
				else
					break;
			}
			
			if(result == true) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
	}
	public static void dfs(int start) {
		if(visited[start] == true) {
			return;
		}
		visited[start] = true;
		for(int n : graph[start]) {
			if(visited[n] == false) {
				check[n] = (check[start] + 1) % 2; //현재 노드와 다른 집합으로 연결 노드 집합 저장. 현재 노드와 다른색 표시
				dfs(n);
			}
			else if(check[n] == check[start]){ //이분그래프가 아님. 인접한 정점이 같은색일 경우 이분그래프가 아니다.
				result = false;
			}
		}
	}
}
