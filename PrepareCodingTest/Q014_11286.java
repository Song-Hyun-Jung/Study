import java.util.*;
import java.io.*;
//절댓값 힙, 우선순위 큐
//우선순위 큐는 기본적으로 오름차순 정렬-MinHeap. 역순으로 정렬하려면 PriorityQueue<>(Collections.reverseOrder());
public class Q014_11286 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer bf = new StringBuffer();
		
		int N = Integer.parseInt(br.readLine());
		
		
		 //우선순위 큐 정렬 방법1)
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> {
			int first_abs = Math.abs(o1);
			int sec_abs = Math.abs(o2);
			
			if(first_abs == sec_abs) { //절댓값이 같으면 음수 먼저 정렬
				return o1 > o2 ? 1 : -1;
			}
			else
				return first_abs > sec_abs ? 1 : -1; //절댓값이 다르면 절대값이 작은게 먼저오도록
			
		});
		
		/*
		//우선순위 큐 정렬 방법2)
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if(Math.abs(o1) == Math.abs(o2)) {
					if(o1 > o2) return 1;
					else return -1;
				}
				else {
					if(Math.abs(o1) > Math.abs(o2)) return 1;
					else return -1;
				}

			}
			
		});
		
		*/
		
		for(int i = 0; i < N; i++) {
			int req = Integer.parseInt(br.readLine());
			
			if(req == 0) {
				if(pq.isEmpty()) {
					bf.append("0\n");
				}
				else
					bf.append(pq.poll() + "\n");
			}
			else {
				pq.add(req);
			}
		}
		
		System.out.println(bf.toString());
	}
}
