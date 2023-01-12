import java.io.*;
import java.util.*;
public class BaekJoon2143 {
	//부분합 + 투포인터

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long T = Long.parseLong(st.nextToken()); //목표값..5
		
		//배열 A의 크기와 A원소들 넣기..4 {1, 3, 1, 2}
		int ASize = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		int[] A = new int[ASize];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < ASize; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		//배열 B의 크기와 B원소들 넣기..3 {1, 3, 2}
		int BSize = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		int[] B = new int[BSize];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < BSize; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		//부분합 구하기
		//A의 전체 부분합..{1, 4, 5, 7, 3, 4, 6, 1, 3, 2}
		ArrayList<Integer> sumA = new ArrayList<Integer>();
		for(int i = 0; i < ASize; i++) {
			int sum = 0;
			for(int j = i; j < ASize; j++) {
				sum += A[j];
				sumA.add(sum);
			}
		}
		//B의 전체 부분합..{1, 4, 6, 3, 5, 2}
		ArrayList<Integer> sumB = new ArrayList<Integer>();
		for(int i = 0; i < BSize; i++) {
			int sum = 0;
			for(int j = i; j < BSize; j++) {
				sum += B[j];
				sumB.add(sum);
			}
		}
		
		
		//정렬하기
		Collections.sort(sumA); //..{1, 1, 2, 3, 3, 4, 4, 5, 6, 7}
		Collections.sort(sumB); //..{1, 2, 3, 4, 5, 6}
		
		//두 배열의 합이 T가 되는 경우 구하기-투포인터 사용
		int a_idx = 0;//앞에서 부터-작은수 부터 포인터
		int b_idx = sumB.size() - 1; //뒤에서 부터 오기-큰수부터 포인터
		int sum = 0;
		long count = 0; //두 배열의 합이 T가 되는 경우의 수 
		while(a_idx < sumA.size() && b_idx >= 0) {
			long a_cnt = 0; //배열 A에서 원소가 같은 것의 개수
			long b_cnt = 0; //배열 B에서 원소가 같은 것의 개수
			int currentA = sumA.get(a_idx); //현재 인덱스에 해당하는 배열 A의 원소값
			int currentB = sumB.get(b_idx); //현재 인덱스에 해당하는 배열 B의 원소값
			sum = currentA + currentB;
			if(sum < T) { //합이 T 보다 작다면 a_idx 증가. 큰수로 값 조정
				a_idx++;
			}
			else if(sum > T) { //합이 T보다 크다면 b_idx 감소. 작은수로 값 조정
				b_idx--;
			}
			else if(sum == T){ //합이 같을 때
				while(a_idx < sumA.size() && sumA.get(a_idx) == currentA) {
					//현재 인덱스에 해당하는 배열 A의 원소값과 다음 인덱스 원소값이 달라질때까지 반복(같은 값 원소 개수 세기)
					a_cnt++;
					a_idx++;
				}
				while(b_idx >= 0 && sumB.get(b_idx) == currentB) {
					b_cnt++;
					b_idx--;
				}
				count += a_cnt * b_cnt; //같은 값인 원소들을 조합한 개수
			}
		}
		System.out.println(count);
	}
}

/*
 //부분합구하기
		//이렇게 하면 안됨

		int[] sumN = new int[N];
		for(int i = 0; i < N; i++) {
			if(i == 0) sumN[i] = NArray[i];
			else sumN[i] = sumN[i - 1] + NArray[i];
		}
		
		int[] sumM = new int[M];
		for(int i = 0; i < M; i++) {
			if(i == 0) sumM[i] = MArray[i];
			else sumM[i] = sumM[i - 1] + MArray[i];
		}
*/
