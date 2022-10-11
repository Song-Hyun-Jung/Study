import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//그리디 + priority queue(우선순위 큐는 따로 정렬을 하지 않아도 된다. 기본값->오름차순)
public class Q033_1715 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		/*
		long[] cards = new long[N];
		for(int i = 0; i < N; i++) {
			cards[i] = Long.parseLong(br.readLine());
		}
		
		Arrays.sort(cards); //오름차순 정렬. 

		for(int i = 0; i < N; i++) { //정렬 후 큐에 삽입
			pq.add(cards[i]);
		}
		*/
		
		for(int i = 0; i < N; i++) {
			pq.add(Long.parseLong(br.readLine())); // priority queue를 사용하므로 정렬하지 않고 바로 priority queue에 넣으면 된다. 
		}
	
		long sum = 0;
		while(pq.size() != 1) {
			long tmp1 = pq.poll();
			long tmp2 = pq.poll();
			sum += tmp1 + tmp2;
			pq.add(tmp1 + tmp2);
		}
		System.out.println(sum);
		
	}
	
	/*
	//이렇게 하면 틀림. 반례->20 40 50 55 -> 20 + 40 = 60 > 55. 60 50 55 차례로 합하면 110 + 165 = 275
	// priority queue 적용 필요 -> 50 55 60 차례로 합하면 105 + 165 = 270. 이경우가 더 최소가 됨으로 priority queue를 적용해야한다. 
	long sum = 0;
	long tmpSum = cards[0];
	for(int i = 0; (i + 1) < N; i++) { 
		tmpSum = tmpSum + cards[i + 1];
		sum += tmpSum;
	}
	System.out.println(sum);
	*/
}
