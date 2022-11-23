import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//유니온파인드
public class Q051_1976 {
	public static int[] cities;
	public static int[] trip;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int tripData = Integer.parseInt(br.readLine());
		
		trip = new int[tripData];
		cities = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			cities[i] = i;
		}
		
		
		for(int i = 1; i <= N; i++) { //도시 연결정보를 인접행렬로 줌. 저장하지 않고 바로 union함
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if(val == 1) {
					unionFind(i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine()); //여행 경로 저장
		for(int i = 0; i < tripData; i++) {
			trip[i] = Integer.parseInt(st.nextToken());
		}
		
		//여행 경로 하나씩 보면서 root값이 다 같은지 확인. 중간에 root값이 다르다면 여행경로 대로 갈수없음.
		boolean result = true;
		int val = find(trip[0]);
		for(int i = 1; i < tripData; i++) {
			if(val != find(trip[i])) {
				result = false;
				break;
			}
			val = find(trip[i]);
		}
		
		
		if(result)
			System.out.println("YES");
		else
			System.out.println("NO");
		
	}
	
	public static void unionFind(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA != rootB) {
			cities[rootB] = rootA;
		}
	}
	public static int find(int a) {
		if(a == cities[a])
			return a;
		return cities[a] = find(cities[a]);
	}
}
