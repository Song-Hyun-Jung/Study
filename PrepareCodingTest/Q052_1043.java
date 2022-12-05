import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//유니온파인드
public class Q052_1043 {
	public static boolean[] knowTruth; //진실을 아는지 여부
	public static int[] partyPerson; //unionfind 위한 배열-parent
	public static ArrayList<Integer>[] partyList;//파티 정보
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//사람수 
		int person = Integer.parseInt(st.nextToken());
		//파티수
		int party = Integer.parseInt(st.nextToken());
		
		//배열 초기화
		knowTruth = new boolean[person + 1];
		partyPerson = new int[person + 1];
		partyList = new ArrayList[party];
		
		for(int i = 1; i <= person; i++) {
			partyPerson[i] = i;
		}
		
		//진실을 아는사람 정보 
		st = new StringTokenizer(br.readLine());
		int knowPerson = Integer.parseInt(st.nextToken()); //진실을 아는사람 명수
		for(int i = 0; i < knowPerson; i++) {
			knowTruth[Integer.parseInt(st.nextToken())] = true;
		}
		
		//파티 정보
		for(int i = 0; i < party; i++) {
			partyList[i] = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); 	//2 2 6이면 파티에 참석하는 사람 2명-2번과 6번
			for(int j = 1; j <= n; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				partyList[i].add(tmp);
			}
		}
		
		//파티 union
		for(int i = 0; i < party; i++) {
			int represent = partyList[i].get(0); //2 2 6 일때 맨 앞 사람인 2번이 우선 대표
			for(int j : partyList[i]) { //파티 참석 인원이 2명이상인 경우
				if(knowTruth[j] == true) { //진실을 알고있는 것을 대표로 함
					represent = j;
					break;
				}
			}
			
			for(int j = 0; j < partyList[i].size(); j++) { //대표노드와 union->union하면서 거짓말임을 안다면 true로
				union(represent, partyList[i].get(j));
			}
		}
	
		
		//과장 여부 카운트-참여할 수 있는 파티 수
		int result = 0;
		for(int i = 0; i < party; i++) {
			boolean isPossible = true;
			for(int t : partyList[i]) {
				int root = find(t);
				if(knowTruth[root] == true) {
					isPossible = false;
					break;
				}
			}
			if(isPossible == true)
				result++;
		}
		
		System.out.println(result);
	}
	
	public static void union(int A, int B) {
		int rootA = find(A);
		int rootB = find(B);
		
		if(rootA != rootB) {
			if(knowTruth[rootA] == true) //대표노드가 진실을 알고있다면 합쳐지는 노드도 진실을 알게됨
				knowTruth[rootB] = true;
			if(knowTruth[rootB] == true)  //진실을 알고있는경우 합쳐지는 노드도 진실을 알게됨 (이 코드가 없으면 21%에서 틀림)
				knowTruth[rootA] = true;
			partyPerson[rootB] = rootA;
		}
		
	}
	
	public static int find(int A) {
		if(partyPerson[A] == A)
			return A;
		return partyPerson[A] = find(partyPerson[A]);
			
	}
}
