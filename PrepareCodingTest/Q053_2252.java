import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//위상 정렬-사이클이 없는 방향 그래프에서 노드 순서를 찾는 알고리즘 O(V + E). 항상 유일한 값으로 정렬되지 않음
//진입차수-자기 자신을 가리키는 에지의 개수
/*1. ArrayList로 그래프를 표현
 *2. 진입차수 배열을 만든다.
 *3. 진입차수가 0인 노드 선택->시작점
 *4. 진입차수가 0이라면 정렬 배열에 넣고 연결된 노드들의 진입차수를 -1 한다. -> 0이 되면 위상정렬배열에 넣고 반복한다.
 *5. 진입차수가 0인 것중에 어떤것을 먼저 선택하느냐에 따라서 결과가 달라질 수 있다.
 */
public class Q053_2252 {
	public static List<Integer>[] studentHeight; //키 비교 정보
	public static int[] entryArray; //진입차수 저장 배열
	public static Queue<Integer> queue;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int node = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());
		
		//배열 초기화
		queue = new LinkedList<Integer>(); //위상정렬시 사용할 큐
		entryArray = new int[node + 1]; //진입차수 저장할 배열
		studentHeight = new List[node + 1]; //키 비교 정보
		for(int i = 1; i <= node; i++) {
			studentHeight[i] = new ArrayList<Integer>();
		}
		
		//키 비교 정보 받기 -> 4 2 라면 4가 앞에 선다.
		for(int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			studentHeight[front].add(back);
			entryArray[back]++; //진입차수++ 4->2 이므로 2의 진입차수 증가
		}
	
		//진입차수가 0인 배열을 큐에 넣는다.
		for(int i = 1; i <= node; i++) {
			if(entryArray[i] == 0)
				queue.add(i);
		}
		
		topologicalSort(); //위상정렬
	}
	public static void topologicalSort() {
		while(!queue.isEmpty()) { //큐가 빌때까지 수행
			int now = queue.poll();
			System.out.print(now + " ");
			for(int i : studentHeight[now]) {
				entryArray[i]--; //진입차수 감소
				if(entryArray[i] == 0) { //진입차수가 0이된다면 큐에 넣기
					queue.add(i);
				}
			}
			
		}
	}
}
