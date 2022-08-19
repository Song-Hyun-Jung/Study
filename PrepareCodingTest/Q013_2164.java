import java.io.*;
import java.util.*;
//큐
public class Q013_2164 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Queue<Integer> q = new LinkedList<Integer>(); //new Queue는 없음, LinkedList<>
		
		for(int i = 1; i <= N; i++) { //1부터 N까지 큐에 넣기
			q.add(i);
		}
		
		while(!q.isEmpty() && q.size() > 1) { //한장 남을때까지 반복
			q.poll(); //가장 위에 있는 카드 버리기
			int moveRear = q.poll(); //두번째 있는 카드를 빼서 
			q.add(moveRear); //맨 밑에 넣기
		}
		System.out.println(q.poll()); //마지막 한장 남은 카드 빼면서 출력
		
	}

}
