import java.util.*;
import java.io.*;
public class Q10_11003 {
	//슬라이딩 윈도우
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); //이게 System.out.print보다 시간이 더 빠름
		
		int N = Integer.parseInt(st.nextToken()); //숫자 개수
		int L = Integer.parseInt(st.nextToken()); //슬라이딩 윈도우 크기
		
		
		st = new StringTokenizer(br.readLine());
		
		//Deque<Node> myDeq = new LinkedList<Node>(); //new Deque로 선언X ->arrayDeque, linkedlist
		Deque<Node> myDeq = new ArrayDeque<Node>(); //ArrayDeque가 LinkedList보다 시간이 빠르다고 함. 
		
		for(int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			
			while(!myDeq.isEmpty() && myDeq.getLast().value > now) { 
				//덱이 비어있지 않고 덱의 마지막 노드의 값이 현재의 값보다 크다면 마지막 노드 삭제->자동 오름차순 정렬
				myDeq.removeLast();
			}
			
			Node n = new Node(i, now); //마지막에 넣을 현재 값 노드
			myDeq.addLast(n);
			//myDeq.addLast(new Node(i, now));
			
			//if(myDeq.getFirst().index <= i - L) { //덱안에 있는 인덱스 범위가 슬라이드 윈도우의 크기를 초과할 경우 맨 앞 노드 삭제
			if(myDeq.getLast().index - myDeq.getFirst().index + 1 > L) {
				myDeq.removeFirst();
			}
			
			bw.write(myDeq.getFirst().value + " ");
		}
		bw.flush();
		bw.close();
		
	}
	
	static class Node{
		int index;
		int value;
		
		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}

}
