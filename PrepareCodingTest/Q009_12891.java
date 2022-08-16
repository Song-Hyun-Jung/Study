import java.io.*;
import java.util.*;
//슬라이딩 윈도우
public class Q009_12891 {
	static int[] checkCounts; //비밀번호 체크 배열
	static int[] currentCounts; //현재 상태 배열
	static int forCheck; //몇개의 문자와 관련된 개수를 충족했는지 판단하는 변수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int allLength = Integer.parseInt(st.nextToken());
		int partLength = Integer.parseInt(st.nextToken());
		int result = 0;
		
		String s = String.valueOf(br.readLine());
		
		checkCounts = new int[4]; //비밀번호 체크배열
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) { 
			checkCounts[i] = Integer.parseInt(st.nextToken());
			if(checkCounts[i] == 0) //1 0 0 1 에서 0인 부분은 충족했음으로 확인. 
				forCheck++;
		}
		
		currentCounts = new int[4]; //현재 상태 배열
		
		for(int i = 0; i < partLength; i++) { //초기 P부분 문자열 처리 부분 0~P-1
			Add(s.charAt(i));
		}
		
		if(forCheck == 4) // A, C, G, T개수가 모두 만족하는 경우
			result++;
	
		for(int i = partLength; i < allLength; i++){ //endIdx
			int j = i - partLength; //startIdx
			Add(s.charAt(i));
			Remove(s.charAt(j));
			if(forCheck == 4)
				result++;
		}
		
		System.out.println(result);
	}
	

	private static void Add(char c) { //새로 들어온 문자 처리하는 함수
		switch(c) {
		case 'A':
			currentCounts[0]++; //현재 문자 개수 +
			if(currentCounts[0] == checkCounts[0]) //현재 문자에 해당하는 개수와 조건 개수가 같으면 
				forCheck++;
			break;
		case 'C':
			currentCounts[1]++;
			if(currentCounts[1] == checkCounts[1])
				forCheck++;
			break;
		case 'G':
			currentCounts[2]++;
			if(currentCounts[2] == checkCounts[2])
				forCheck++;
			break;
		case 'T':
			currentCounts[3]++;
			if(currentCounts[3] == checkCounts[3])
				forCheck++;
			break;
		}
	}
	
	private static void Remove(char c) { //제거되는 문자를 처리하는 함수- startIdx 증가로 발생하는 제거되는 문자
		switch(c) {
		case 'A':
			if(currentCounts[0] == checkCounts[0])
				forCheck--;
			currentCounts[0]--;
			break;
		case 'C':
			if(currentCounts[1] == checkCounts[1])
				forCheck--;
			currentCounts[1]--;
			break;
		case 'G':
			if(currentCounts[2] == checkCounts[2])
				forCheck--;
			currentCounts[2]--;
			break;
		case 'T':
			if(currentCounts[3] == checkCounts[3])
				forCheck--;
			currentCounts[3]--;
			break;
		}
	}
	
}
