import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
//위상 정렬*
public class Q054_1516 {
	public static List<Integer>[] buildInfoList; //건물 연결 정보 저장 리스트
	public static int[] timeList; //자기 자신을 짓는데 걸리는 시간을 저장하는 배열
	public static int[] entryArray; //진입 차수 저장 배열
	public static Queue<Integer> queue; //위상 정렬 위한 큐
	public static int[] answer; //정답(시간) 저장할 배열
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int variety = Integer.parseInt(br.readLine()); //건물 종류 수
		
		//건물 연결 정보 저장 리스트 초기화
		buildInfoList = new List[variety + 1];
		timeList = new int[variety + 1];
		answer = new int[variety + 1];
		entryArray = new int[variety + 1];
		for(int i = 1; i <= variety; i++) {
			buildInfoList[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= variety; i++) {
			st = new StringTokenizer(br.readLine());
			timeList[i] = Integer.parseInt(st.nextToken()); //자기 자신을 짓는데 걸리는 시간 저장
			int next = Integer.parseInt(st.nextToken());
			while(next != -1) {
				buildInfoList[next].add(i);//4번째줄 4 3 1 -1일때 시간:4 buildInfoList[3].add(4); 1->4
				entryArray[i]++; //4의 진입차수++
				next = Integer.parseInt(st.nextToken());
			}
		}
		
		queue = new LinkedList<Integer>();
		for(int i = 1; i <= variety; i++) {
			if(entryArray[i] == 0) {
				queue.add(i);
			}
		}
		
		topologicalSort();
		
		for(int i = 1; i <= variety; i++) { //자기 자신을 짓는데 걸리는 시간을 더해줘야함
			answer[i] += timeList[i];
		}
		
		for(int i = 1; i <= variety; i++) {
			System.out.println(answer[i]);
		}
	}
	public static void topologicalSort() { //정렬하면서 시간 계산
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int i : buildInfoList[now]) {
				entryArray[i]--;
				answer[i] = Math.max(answer[i], answer[now] + timeList[now]); //현재 건물에 저장된 최대시간과 이전 건물 최대시간 + 현재 건물 건설 시간 비교해서 최대값 저장. 이전 건물 지어야 지을 수 있기 때문
				if(entryArray[i] == 0)
					queue.add(i);
			}
		}
	}
}
