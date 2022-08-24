import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
//퀵정렬-Collections.sort()-TimeSort(삽입정렬 + 합병정렬) 평균과 최악 모두 O(nlogn)
public class Q019_11004Collections {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		ArrayList<Integer> A = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			A.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(A);
		
		System.out.println(A.get(K - 1));
	}

}
