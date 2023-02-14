import java.util.*;
//BFS-2차원 배열, depth
class ProgrammersDfsGame {
	 public int[] x = {1, 0, -1, 0};
		public int[] y = {0, 1, 0, -1};
		static boolean[][] visited;
	    public int[][] arr;
		public int depth = 1;
	    public int solution(int[][] maps) {
	        arr = maps;
	        visited = new boolean[maps.length][maps[maps.length - 1].length];
		    visited[0][0] = true;
	        depth = bfs(0, 0, maps.length - 1, maps[maps.length - 1].length - 1);

		    if(depth == 1)
		     return -1;
		    else
		     return depth;
	      
	    }
	    public int bfs(int startX, int startY, int destX, int destY){
	        Queue<int[]> bfsQueue = new LinkedList<int[]>();
		    bfsQueue.offer(new int[]{startX, startY, 1});
	        
	         while(!bfsQueue.isEmpty()){
		            int[] now = bfsQueue.poll();
	                if(now[0] == destX && now[1] == destY)
	                    return now[2];
		            for(int i = 0; i < 4; i++){
		                int newX = now[0] + x[i];
		                int newY = now[1] + y[i];
	                    
	                    if(newX >= 0 && newX <= destX && newY >= 0 && newY <= destY){
		                    if(!visited[newX][newY] && arr[newX][newY] != 0){
		                        visited[newX][newY] = true;
		                        bfsQueue.offer(new int[]{newX, newY, now[2] + 1});
		                    }
		                }
		            }
		        }
	        return -1;
	    }
}