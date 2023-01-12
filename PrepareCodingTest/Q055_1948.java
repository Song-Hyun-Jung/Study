import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//위상정렬-에지 뒤집기(역방향 인접리스트도 저장)***** 다익스트라로도 다시 풀어볼것
//위상정렬을 2번
public class Q055_1948 {
	//1. 출발 도시에서 출발한 후 도착 도시에서 만나기까지 걸리는 최소 시간->경로까지의 최대 시간
	//2. 1분도 쉬지 않고 달려야 하는 사람들이 지나는 도로의 수->도착지까지 걸리는 최대 시간의 경로에 있는 모든 도로의 개수
	public static class RoadInfo{
		int destination;
		int time;
		
		public RoadInfo() {}
		public RoadInfo(int destination, int time) {
			this.destination = destination;
			this.time = time;
		}
		public int getTime() {	return time; }
		public void setTime(int time) { this.time = time; }
		public int getDestination() { return destination; }
		public void setDestination(int destination) { this.destination = destination; }
		
	}
	
	public static ArrayList<RoadInfo>[] mapInfo; //지도 정보를 저장할 arrayList 배열
	public static ArrayList<RoadInfo>[] reverseMap; //역방향으로 저장한 arrayList
	public static int[] entryArray; //진입 차수를 저장할 배열
	public static Queue<Integer> queue; 
	public static int[] result; //결과를 저장할 배열
	public static int resultCount;
	public static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int city = Integer.parseInt(br.readLine());
		int road = Integer.parseInt(br.readLine());
		
		//초기화
		mapInfo = new ArrayList[city + 1];
		reverseMap = new ArrayList[city + 1];
		entryArray = new int[city + 1];
		result = new int[city + 1];
		visited = new boolean[city + 1];
		for(int i = 0; i < city + 1; i++) {
			mapInfo[i] = new ArrayList<RoadInfo>();
			reverseMap[i] = new ArrayList<RoadInfo>();
		}
		
		//그래프 정보 삽입
		for(int i = 0; i < road; i++) {
			st = new StringTokenizer(br.readLine());
			int startCity = Integer.parseInt(st.nextToken());
			int destination = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			mapInfo[startCity].add(new RoadInfo(destination, time));
			reverseMap[destination].add(new RoadInfo(startCity, time)); //역방향 정보 저장
			entryArray[destination]++; //목적지 도시의 진입차수 증가
		}
		

		//출발지, 목적지
		st = new StringTokenizer(br.readLine());
		int startLoc = Integer.parseInt(st.nextToken());
		int endLoc = Integer.parseInt(st.nextToken());
		
		//위상정렬 준비-진입차수가 0인 노드 큐에 삽입
		queue = new LinkedList<Integer>(); //위상 정렬에 사용할 큐
		queue.add(startLoc); //진입차수가 0인 도시는 출발지밖에 없음
		/*
		for(int i = 1; i <= city; i++) {
			if(entryArray[i] == 0)
				queue.add(i);
		}
		*/
		
		topologicalSort(); //위상정렬
		
		//위상 정렬 reverse
		resultCount = 0;
		queue = new LinkedList<>();
		queue.add(endLoc); //역방향 인접리스트 사용->도착지부터 시작
		visited[endLoc] = true;
		
		reverseTopologicalSort();
		
		System.out.println(result[endLoc]);
		System.out.println(resultCount); //1분도 쉬지 않고 달려야하는 사람들이 지나는 도로의 수=최대 시간이 걸리는 모든 경로에서 지나는 도로의 개수 
		
	}
	
	//위상정렬
	public static void topologicalSort() { //위상정렬 하면서 임계경로(시작점부터 목적지까지 가는데 최대 시간) 저장
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(RoadInfo rI : mapInfo[now]) {
				entryArray[rI.destination]--; //진입차수 감소
				//타깃 노드의 현재 경로 값 vs 현재노드의 경로값 + 도로 시간값 중 큰값으로 저장. 
				result[rI.destination] = Math.max(result[rI.destination], result[now] + rI.time);
				if(entryArray[rI.destination] == 0) {
					queue.add(rI.destination);
				}
			}
		}
	}
	
	//역방향 위상정렬(역방향 리스트 사용)
	public static void reverseTopologicalSort() {
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			//1분도 쉬지 않는 도로 체크
			for(RoadInfo rI : reverseMap[now]) {
				//도시의 임계 경로값 + 도로 시간(에지) == 이전 도시의 임계 경로값이면 이 도로를 1분도 쉬지 않고 달려야함
				//->이 도시를 카운팅하고 큐에 삽입
				//1분도 쉬지 않고 달려야 하는 도로로 이어진 노드와 연결된 다른 도로만이 1분도 쉬지 않고 달려야하는 도로의 후보가 됨
				if(result[rI.destination] + rI.time == result[now]) {
					resultCount++;
					//중복 카운트 방지를 위해 이미 방문한 적이 있는 노드 제외
					if(visited[rI.destination] == false) {
						visited[rI.destination] = true;
						queue.add(rI.destination);
					}
				}
			}
		}
	}
	
}
