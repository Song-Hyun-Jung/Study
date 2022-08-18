import java.util.*;
import java.io.*;
//스택
public class Q011_1874 {
//1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓음으로써, 하나의 수열을 만들 수 있다. 이때, 스택에 push하는 순서는 반드시 오름차순을 지키도록 한다고 하자. 임의의 수열이 주어졌을 때 스택을 이용해 그 수열을 만들 수 있는지 없는지, 있다면 어떤 순서로 push와 pop 연산을 수행해야 하는지를 알아낼 수 있다. 이를 계산하는 프로그램을 작성하라.
public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); //메모리 초과 
		StringBuffer bf = new StringBuffer(); //StringBuffer보다는  StringBuilder 성능이 더 좋다-이 문제에서는 별차이 없다.. BufferedWriter보다 빠르다
		//StringBuilder bf = new StringBuilder();
		
		String pushPop = "";
		boolean result = true;
		
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) { //오름차순 수들로 만들어야 할 배열
			arr[i] = sc.nextInt();
		}
		
		Stack<Integer> st = new Stack<Integer>();
		
		int num = 1;//오름차순으로 증가하는 수 1,2,3,4,...
		for(int i = 0; i < N; i++) { //수열의 개수만큼 반복
			if(num <= arr[i]) { //현재 수열의 수
				while(num <= arr[i]) { //증가하고 있는 수가 현재 수열의 수보다 작을때 현재 수열의 수까지 값 push
					st.push(num);
					//System.out.println(num + " + ");
					//pushPop += "+\n";
					bf.append("+\n");
					num++;
				}
				int val = st.pop(); //증가하고 있는 수가 현재 수열의 수와 같아지면 pop
				//System.out.println(val + " - ");
				//pushPop += "-\n";
				bf.append("-\n");
			}
			else { //증가하고 있는 수가 현재 수열의 수보다 클때
				int val = st.pop();
				if(val > arr[i]) { //스택의 가장 위의 수가 만들어야하는 수열의 수보다 크면 제시된 수열을 출력할수 없다. 
					result = false;
					System.out.println("NO");
					break;
				}
				else { //pop을 했기 때문에 -를 문자열에 추가해줌
					//System.out.println(val + " - ");
					//pushPop += "-\n";
					bf.append("-\n");
				}
				
			}	
		}
		
		if(result == true) {
			//System.out.println(pushPop);
			System.out.println(bf.toString());
		}
		
	}

}
