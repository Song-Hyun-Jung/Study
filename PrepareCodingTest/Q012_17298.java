import java.io.*;
import java.util.*;
//스택
public class Q012_17298 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		int[] result = new int[N];
		for(int i = 0; i < N; i++) {
			while(!stack.empty() && arr[stack.peek()] < arr[i]) {
				result[stack.pop()] = arr[i];
			}
			stack.push(i);
		}                
		
		while(!stack.empty()) {//반복문을 다 돌고 나왔는데 스택에 남아있는경우{
			result[stack.pop()] = -1;
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < N; i++) {
			bw.write(result[i] + " ");
		}
		bw.write("\n");
		bw.flush();
		
	}

}
