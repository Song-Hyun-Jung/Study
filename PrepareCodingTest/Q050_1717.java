import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//유니온파인드(unionFind)-union연산 + find연산
//union연산 = 각 노드가 속한 집합을 1개로 합치는 연산 A U B
//find연산 = 특정 노드 a에 대해 a가 속한 집합의 대표 노드 반환. a가 A에 속해있을때 A의 대표노드 반환. 자신의 대표집합의 대표노드를 찾으면서 그래프를 정돈함. 시간복잡도 향상 O(1)정도
/*유니온파인드 구현 방법-1차원 배열 사용
 * 1. 초기화 = 1차원 배열 ->값을 다 index로 (노드가 연결되지 않은 상태임. 각 노드가 대표인것)
 * 2. 2개 연결 = union & 대표값으로 변경
 * 2-1.union(1, 4)라면 부모1이 대표값. 4의 value를 1로 변경 / union(5,6)이라면 6의 value를 5로 변경 => 1 2 3 1 5 5
 * 2-2.union(4, 6)이라면 자식끼리 연결->부모끼리 연결해야함. -> find연산으로 6의 부모를 찾음-5 5의 value를 4의 부모인 1로 변경
 * 2-3.find연산 진행->대상노드 배열 index와 value값이 같은가?
 * 		아니라면 value값이 가리키는 idx로 이동해서 다시 index와 value 값이 같은지 확인. index와 value값이 같을때까지 재귀
 * 		대표노드에 도달하면 재귀함수 빠져나오면서 지나온 모든 노드값을 루트값으로 변경. find연산 속도=>O(1) -> 1 2 3 1 1 1
 * 		*한번의 find 연산으로 모든 노드를 루트에 연결 
 */

public class Q050_1717 {
	public static int[] set;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int node = Integer.parseInt(st.nextToken());
		int question = Integer.parseInt(st.nextToken());
		
		set = new int[node + 1];
		//1.유니온파인드 초기화-value = index. 
		for(int i = 0; i <= node; i++) {
			set[i] = i;
		}
		
		for(int i = 0; i < question; i++) {
			st = new StringTokenizer(br.readLine());
			int isUnion = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(isUnion == 0) {
				unionFind(a, b);
			}
			else if(isUnion == 1) {
				if(find(a) == find(b)) //같은 집합
					System.out.println("YES");
				else //다른집합
					System.out.println("NO");
			}
		}
				
	}
	public static void unionFind(int a, int b) { //합치면서 대표노드 찾아 정리
		int rootA = find(a); //두 원소의 대표노드 찾기
		int rootB = find(b);
		
		if(rootA != rootB)
			set[rootB] = rootA; //두 원소의 대표노드끼리 연결(부모끼리 연결해야함)
	}	
	public static int find(int a) { //대표노드 찾기
		if(a == set[a]) { //index == value -> 루트노드이므로 반환
			return a;
		}
		
		return set[a] = find(set[a]); //재귀함수로 정리->지나온 모든 노드값을 루트값으로 변경. 시간복잡도 향상
	}

}

