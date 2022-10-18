import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
//그리디 + 정렬 기준 커스텀
public class Q035_1931 {
	public static class Meeting implements Comparable<Meeting>{
		private int start;
		private int end;
		
		public Meeting() {}
		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting m1) { //정렬 기준 커스텀
			if(this.end < m1.end) {
				return -1;
			}
			else if(this.end > m1.end) {
				return 1;
			}
			else { //종료시간이 같을경우-시작시간으로 정렬
				if(this.start < m1.start)
					return -1;
				else
					return 1;
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<Meeting> meetingList = new ArrayList<>();
		
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			meetingList.add(new Meeting(s, e));
		}
		
		Collections.sort(meetingList); //정렬 기준 커스텀->종료시간순 정렬. 만약 종료시간이 같다면 시작시간순 정렬
		
		int startTime = -1;
		int endTime = -1;
		int cnt = 0;
		for(int i = 0; i < meetingList.size(); i++) { //그리디
			if(meetingList.get(i).start >= endTime) {
				startTime = meetingList.get(i).start;
				endTime = meetingList.get(i).end;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
}
