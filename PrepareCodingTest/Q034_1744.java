import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

//그리디 + priority queue(오름차순, 내림차순)
public class Q034_1744 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//양수와 음수를 따로 저장하고 0의개수와 1의 개수를 따로 센다. 
		PriorityQueue<Integer> plusPq = new PriorityQueue<Integer>(Collections.reverseOrder()); //양수는 내림차순 우선순위 큐 5 4 3 2 1 
		PriorityQueue<Integer> minusPq = new PriorityQueue<Integer>(); //음수는 오름차순 우선순위 큐 -5 -4 -3 -2 -1
		int one = 0; //1의 개수
		int zero = 0; //0의 개수
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			if(tmp > 1) { //양수큐에 넣기
				plusPq.add(tmp);
			}
			else if(tmp < 0) { //음수큐에 넣기
				minusPq.add(tmp);
			}
			else if(tmp == 0) { //0의 개수
				zero++;
			}
			else if(tmp == 1) { //1의 개수
				one++;
			}
		}
		
		//양수 큐 계산
		int plusSum = 0;
		while(plusPq.size() > 1) { //while(plusPq.size() != 1) 이렇게하면 런타임에러남
			int tmp1 = plusPq.poll();
			int tmp2 = plusPq.poll();
			plusSum += tmp1 * tmp2;
		}
		if(!plusPq.isEmpty()) { //양수 큐에 남은 수 다 더하기
			plusSum += plusPq.remove();
		}
		plusSum += one; //1은 그냥 더해주기
		
		//음수 큐 계산
		int minusSum = 0;
		while(minusPq.size() > 1) {
			int tmp1 = minusPq.poll();
			int tmp2 = minusPq.poll();
			minusSum += tmp1 * tmp2; //음수 * 음수 = 양수
			
		}
		if(!minusPq.isEmpty()) {
			if(zero > 0) { //0이 남았으면 0과 마이너스를 곱해 마이너스 없애기
				minusSum += minusPq.remove() * 0;
				zero--;
			}
			else { //0이 부족해 남은 마이너스값은 그냥 합에 더하기
				minusSum += minusPq.remove();
			}
		}
		
		System.out.println(plusSum + minusSum);
	}
	
	

}
