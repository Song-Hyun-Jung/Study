import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//이진탐색-탐색 대상을 잘 찾는게 중요
public class Q030_2343 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int lessons = Integer.parseInt(st.nextToken());
		int blueRay = Integer.parseInt(st.nextToken());
		
		
		int start = 0;
		int end = 0;
		
		int[] lessonLength = new int[lessons];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < lessons; i++) {
			lessonLength[i] = Integer.parseInt(st.nextToken());
			if(start < lessonLength[i]) start = lessonLength[i]; //최대 길이의 레슨
			end += lessonLength[i]; //모든 레슨 길이의 합
			
		}
		
		//시작인덱스는 최대 길이의 레슨. 종료 인덱스는 모든 레슨 길이의 합 
		//=> ex)1, 2, 3, 4, 5, 6, 7, 8, 9 블루레이 3개일때 9~45 사이에서 블루레이 크기의 최솟값을 찾으면 됨. 
		//9혼자들어갈때가 블루레이 하나의 크기가 9이므로 최소일때. 45는 블루레이 하나에 모든 원소가 다들어갈때
		
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int sum = 0; //블루레이 하나당 합
			int count = 0; //블루레이 개수 확인
			for(int i = 0; i < lessons; i++) {
				if(sum + lessonLength[i] > mid) { //블루레이 하나의 합이 mid보다 커지면 블루레이 새로 만들기 
					count++; //블루레이 개수 추가
					sum = 0; //블루레이 합 초기화
				}
				sum = sum + lessonLength[i]; //블루레이 하나에 속하는 값 더하기
			}
			if(sum != 0) //탐색 후에 sum이 남았다면 블루레이가 하나 더 필요한것이므로 추가
				count++;
			
			if(count > blueRay) { //현재 블루레이 개수가 나눠야 할 개수보다 많으면 최소값(start) 키우기 -> 블루레이 개수 감소
				start = mid + 1;
			
			}
			else{ //현재 블루레이 개수가 나눠야 할 개수보다 적으면 최대값(end) 줄이기 -> 블루레이 개수 증가
				end = mid - 1;	
			}
			
		}
		System.out.println(start);
		
	}
}
