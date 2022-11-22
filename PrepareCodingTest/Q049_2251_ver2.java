import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS + Graph(그래프의 원리를 이용해 그래프를 역으로 그리는 방식) ***
//ver1보다 이방법이 더 쉬움
public class Q049_2251_ver2 {
	public static boolean visited[][][];
	public static int ASize;
	public static int BSize;
	public static int CSize;
	public static boolean answer[];
	public static Queue<ABC> bfsQueue;
	public static class ABC {
		int A;
		int B;
		int C;
		public ABC() {};
		public ABC(int A, int B, int C) {
			this.A = A;
			this.B = B;
			this.C = C;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		ASize = Integer.parseInt(st.nextToken());
		BSize = Integer.parseInt(st.nextToken());
		CSize = Integer.parseInt(st.nextToken());
		
		visited = new boolean[201][201][201];
		answer = new boolean[201];
		
		BFS();
		
		for(int i = 0; i < 201; i++) { //1부터 시작하면 에러
			if(answer[i] == true) {
				System.out.print(i + " ");
			}
		}
		
	}
	public static void BFS() {
		bfsQueue = new LinkedList<ABC>();
		bfsQueue.add(new ABC(0, 0, CSize));
		
		while(!bfsQueue.isEmpty()) {
			ABC outNode = bfsQueue.poll();
			if(visited[outNode.A][outNode.B][outNode.C]) {
				continue;
			}
			if(outNode.A == 0) {
				answer[outNode.C] = true;
			}
			visited[outNode.A][outNode.B][outNode.C] = true;
			
			//B->A
			if(outNode.A + outNode.B <= ASize) {
				bfsQueue.add(new ABC(outNode.A + outNode.B, 0, outNode.C)); //B의 물을 A에 넣음. 넘치지 X
			}
			else {
				bfsQueue.add(new ABC(ASize, outNode.B + outNode.A - ASize, outNode.C)); //B의 물을 A에 넣음. 넘침. 넘치는 양은 B에 남김
			}
			
			//C->A
			if(outNode.A + outNode.C <= ASize) {
				bfsQueue.add(new ABC(outNode.A + outNode.C, outNode.B, 0)); //C의 물을 A에 넣음. 넘치지 X
			}
			else {
				bfsQueue.add(new ABC(ASize, outNode.B, outNode.A + outNode.C - ASize)); //C의 물을 A에 넣음. 넘침. 넘치는 양은 C에 남김
			}
			
			//A->B
			if(outNode.A + outNode.B <= BSize) {
				bfsQueue.add(new ABC(0, outNode.A + outNode.B, outNode.C)); //A의 물을 B에 넣음. 넘치지 X
			}
			else {
				bfsQueue.add(new ABC(outNode.A + outNode.B - BSize, BSize, outNode.C)); //A의 물을 B에 넣음. 넘침. 넘치는 양은 A에 남김
			}
			
			//C->B
			if(outNode.C + outNode.B <= BSize) {
				bfsQueue.add(new ABC(outNode.A, outNode.B + outNode.C, 0)); //C의 물을 B에 넣음. 넘치지 X
			}
			else {
				bfsQueue.add(new ABC(outNode.A, BSize, outNode.B + outNode.C - BSize)); //C의 물을 B에 넣음. 넘침. 넘치는 양은 B에 남김
			}
			
			//A->C
			if(outNode.A + outNode.C <= CSize) {
				bfsQueue.add(new ABC(0, outNode.B, outNode.C + outNode.A)); //A의 물을 C에 넣음. 넘치지 X
			}
			else {
				bfsQueue.add(new ABC(outNode.A + outNode.C - CSize, outNode.B, CSize)); //A의 물을 C에 넣음. 넘침. 넘치는 양은 A에 남김
			}
			
			//B->C
			if(outNode.B + outNode.C <= CSize) {
				bfsQueue.add(new ABC(outNode.A, 0, outNode.C + outNode.B)); //B의 물을 C에 넣음. 넘치지 X
			}
			else {
				bfsQueue.add(new ABC(outNode.A, outNode.B + outNode.C - CSize, CSize)); //B의 물을 C에 넣음. 넘침. 넘치는 양은 B에 남김
			}
		} 
	}
}	
