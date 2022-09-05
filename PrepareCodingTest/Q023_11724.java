import java.io.*;
import java.util.*;
//DFS-재귀
public class Q023_11724 {
	static ArrayList<Integer>[] dfsList; //원소가 ArrayList<Integer>인 배열
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //노드의 개수
		int M = Integer.parseInt(st.nextToken()); //앳지의 개수
		
		
		//인접리스트 초기화 - 연결되어있는 인접 노드들을 각 인덕스의 배열안에 넣기.  ex) 1-2 5라면 인덱스 1에 해당하는 배열에 2와 5 삽입
		//visited는 모두 F 
		dfsList = new ArrayList[N + 1]; //1~N까지 사용하기 위함
		visited = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			dfsList[i] = new ArrayList<Integer>();
			visited[i] = false;
		} 
		
		//리스트에 연결 정보 넣기
		for(int i = 1; i <= M; i++) { //연결엣지의 개수만큼 반복
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); //엣지의 시작노드
			int e = Integer.parseInt(st.nextToken()); //엣지의 종료노드
			
			dfsList[s].add(e);	//방향이 없는 그래프(양방향)->양쪽으로 더해줘야함
			dfsList[e].add(s);  //방향이 없는 그래프(양방향)->양쪽으로 더해줘야함
		}
		
		
		int count = 0; //연결된 부분(덩어리)을 세는 변수
		for(int i = 1; i <= N; i++) {
			if(visited[i] == false) { //방문하지 않았으면->새 덩어리
				count++; 
				DFS(i);
			}
		}
		System.out.println(count);
	}
	
	public static void DFS(int v) {
		if(visited[v] == true) return; //방문했으면 재귀 끝
		visited[v] = true; //방문했으니 true로 변경
		for(int i: dfsList[v]){ //연결된 리스트의 원소들
			if(visited[i] == false) { //연결된 리스트의 원소중에 방문하지 않은 것이 있다면 재귀-dfs
				DFS(i);
			}
		}
	}
	
}
