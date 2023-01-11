import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DFS
public class BaekJoon1987 {
	static boolean[][] visited; //방문 여부 저장
	static boolean[] gift; //기념품 구매 여부 저장
	static String[][] city; //도시별 기념품 정보 저장
	public static void main(String[] args) throws Exception{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			city = new String[R + 1][C + 1];
			visited = new boolean[R + 1][C + 1];
			gift = new boolean[26]; //A, B, C -> 0, 1, 2
			
			//도시 정보 저장
			for(int r = 1; r <= R; r++) {
				String[] splited = br.readLine().split("");
				for(int c = 1; c <= C; c++) {
					city[r][c] = splited[c - 1];
				}
			}
			
			solve(1, 1, R, C, 1);
			System.out.println(max_depth);
			
		
	}

	static int max_depth = Integer.MIN_VALUE;

	//DFS
	public static void solve(int startX, int startY, int R, int C, int depth) {
		int[] dx = {0, 1, 0, -1}; //방향 이동 위한 배열
		int[] dy = {1, 0, -1, 0}; 
		
		visited[startX][startY] = true;
		gift[city[startX][startY].charAt(0) - 65] = true;

		//더이상 갈수 없는경우->종료지점
		int flag = 0;
		for(int i = 0; i < 4; i++) {
			int x = startX + dx[i];
			int y = startY + dy[i];
			if(x < 1 || y < 1 || x >= R + 1 || y >= C + 1) //범위 이탈
				flag++;
			else if((x >= 1 && y >= 1) && (x <= R && y <= C)) {
				if(gift[city[x][y].charAt(0) - 65] == true) { //이미 구매한 기념품을 사야하는 여행지는 가지 않음
					flag++;
				}
			}

		}
	
		if(flag == 4) { //4방향 모두 갈 수 없음
			if(depth >= max_depth)
				max_depth = depth;
			return;
		}
		else { //한방향이라도 갈 수 있는 경우
			for(int i = 0; i < 4; i++) {
				int x = startX + dx[i];
				int y = startY + dy[i];
			
				if(x < 1 || y < 1 || x >= R + 1 || y >= C + 1) //범위 이탈
					continue;
				if((x >= 1 && y >= 1) && (x <= R && y <= C)) {
					if(visited[x][y] == false && gift[city[x][y].charAt(0) - 65] == false) {
						visited[x][y] = true;
						gift[city[x][y].charAt(0) - 65] = true;
						//System.out.print("(" + x + " " + y + ")"); //경로 출력
						solve(x, y, R, C, depth + 1);
						visited[x][y] = false;
						gift[city[x][y].charAt(0) - 65] = false;
					}
					
				}
			}
			//System.out.println();
		}
		
	}

	/*
	 *테스트케이스 여러개일 경우 main
	 * 	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCase = Integer.parseInt(br.readLine());
		for(int i = 0; i < testCase; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			city = new String[R + 1][C + 1];
			visited = new boolean[R + 1][C + 1];
			gift = new boolean[26]; //A, B, C -> 0, 1, 2
			
			//도시 정보 저장
			for(int r = 1; r <= R; r++) {
				String[] splited = br.readLine().split("");
				for(int c = 1; c <= C; c++) {
					city[r][c] = splited[c - 1];
				}
			}
			max_depth = Integer.MIN_VALUE;
			solve(1, 1, R, C, 1);
			//System.out.println("#" + (i + 1) + " " + max_depth);
			bw.write("#" + (i + 1) + " " + max_depth + "\n");
		}
        bw.flush();
		bw.close();
	}
	 */
	

}
