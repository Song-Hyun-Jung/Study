import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//DFS + 유클리드 호제법(최대공약수, 최소공배수)*
public class Q044_1033 {
	public static class NodeInfo{
		int end;  //연결된 노드
		int p;
		int q;
		
		public NodeInfo() {}
		public NodeInfo(int end, int p, int q) {
			this.end = end;
			this.p = p;
			this.q = q;
		}
		
		public void setEnd(int end) {
			this.end = end;
		}
		public void setP(int p) {
			this.p = p;
		}
		public void setQ(int q) {
			this.q = q;
		}
		
		public int getEnd() {
			return end;
		}
		public int getP() {
			return p;
		}
		public int getQ() {
			return q;
		}
	}
	
	static ArrayList<NodeInfo>[] graph;
	static boolean[] visited;
	static long[] proportion;
	static long lcm;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//비율, 연결정보를 그래프로 나타냄
		//그래프 초기화
		graph = new ArrayList[N];
		visited = new boolean[N]; //방문여부
		proportion = new long[N]; //최소공배수 값 저장->최소공배수의 곱들을 비율로 나눠감
		lcm = 1;//최소공배수
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<NodeInfo>();
			visited[i] = false;
		}
		
		StringTokenizer st;
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			graph[a].add(new NodeInfo(b, p, q));
			graph[b].add(new NodeInfo(a, q, p)); //양방향 연결->비율 주의해서 넣기
			lcm *= ((p * q) / gcd(p, q)); //최소공배수들의 곱
		}
		
		proportion[0] = lcm; //시작점에 최소공배수들의 곱 넣기
		DFS(0);//비율대로 나눔
		
		long mgcd = proportion[0]; //최대공약수
		for(int i = 1; i < N; i++) {
			mgcd = gcd(mgcd, proportion[i]); //모든 값의 최대공약수 구하기-최소 질량을 구하기 위해서
		}
		for(int i = 0; i < N; i++) {
			proportion[i] = proportion[i] / mgcd;
			System.out.print(proportion[i] + " "); //최대공약수로 나눠서 최소 질량 구하기(ex-24 20 ->12 10)
		}
	}
	
	//탐색->유클리드 호제법으로 비율들의 최소공배수와 최대공약수를 구함. 
	public static void DFS(int s) {
		if(visited[s] == true)
			return;
		else {
			visited[s] = true;
			for(NodeInfo i : graph[s]) { //연결된 리스트 탐색
				if(visited[i.getEnd()] == false) {
					proportion[i.getEnd()] = proportion[s] * i.getQ() / i.getP(); //이전 노드의 최소공배수 값을 비율로 나눔. 3:1 -> 1/3
					DFS(i.getEnd());
				}
			}
		}
	}
	//최대 공약수 구하기
	public static long gcd(long A, long B) {
		if(B == 0) {
			return A;
		}
		else {
			if(A > B) {
				return gcd(B, A % B);
			}
			else {
				return gcd(A, B % A);
			}
		}
	}
}
