import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//그리디
public class Q032_11047 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int N = Integer.parseInt(st.nextToken());
		int price = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[N];
		for(int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		int count = 0;
		for(int i = N - 1; i >= 0; i--) {
			count += price / coins[i];
			price = price % coins[i];
		}
		
		System.out.println(count);
	}
}
