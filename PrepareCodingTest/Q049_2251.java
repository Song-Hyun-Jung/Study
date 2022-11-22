import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//BFS + Graph(그래프의 원리를 이용해 그래프를 역으로 그리는 방식) *****
public class Q049_2251 {
	//A->B, A->C, B->A, B->C, C->A, C->B
	public static int[] sender = {0, 0, 1, 1, 2, 2}; //ex)sender1 receiver2 => B->C 
	public static int[] receiver = {1, 2, 0, 2, 0, 1};
	public static boolean[] answer;
	public static boolean visited[][]; //A,B의 무게로 C의 무게 계산 가능. 이전에 방문한 무게인지 체크
	public static int now[];
	
	public static class AB{ //하위 클래스는 static 붙여야 미리 생성됨!
		int AWeight;
		int BWeight;
		public AB() {};
		public AB(int AWeight, int BWeight) {
			this.AWeight = AWeight;
			this.BWeight = BWeight;
		}
	}
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		now = new int[3]; //현재 물의 양을 저장하는 배열
		now[0] = sc.nextInt();
		now[1] = sc.nextInt();
		now[2] = sc.nextInt();
		
		visited = new boolean[201][201]; //1<=A, B, C<=200
		answer = new boolean[201];
		
		BFS();
		
		for(int i = 0; i < answer.length; i++) {
			if(answer[i] == true)
				System.out.print(i + " ");
		}
	}
	public static void BFS() {
		Queue<AB> bfsQueue = new LinkedList<>();
		bfsQueue.add(new AB(0, 0));
		visited[0][0] = true;
		answer[now[2]] = true; //A:0, B:0, C:가득
		while(!bfsQueue.isEmpty()) {
			AB p = bfsQueue.poll();
			int A = p.AWeight;
			int B = p.BWeight;
			int C = now[2] - A - B;
			
			for(int i = 0; i < 6; i++) { //A->B, A->C, B->A, B->C, C->A, C->B
				int[] next = {A, B, C};
				next[receiver[i]] += next[sender[i]];
				next[sender[i]] = 0; //보내는 쪽의 무게를 0으로
				if(next[receiver[i]] > now[receiver[i]]) {
					next[sender[i]] = next[receiver[i]] - now[receiver[i]];
					next[receiver[i]] = now[receiver[i]]; //넘친만큼 다시 이전물통에 넣어줌
				}
				if(!visited[next[0]][next[1]]) {
					visited[next[0]][next[1]] = true;
					bfsQueue.add(new AB(next[0], next[1]));
					if(next[0] == 0) {
						answer[next[2]] = true;
					}
				}
			}
		}
	}
}
