package leetcodeHard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;



public class DetectSquares {
	
	 static int freq[][]=new int[1001][1001];
	 static List<int[]> pts=new ArrayList<>();
	    
	    public void add(int[] point) {
	        freq[point[0]][point[1]]++;
	        pts.add(point);
	    }
	    
	    public int count(int[] point) {
	        int res=0;
	        int px=point[0];
	        int py=point[1];
	        for(int[] pt:pts){
	            int x=pt[0];
	            int y=pt[1];
	            //check for diagonal and positive area
	            if(Math.abs(px-x)!=Math.abs(py-y)||x==px||y==py) continue;
	            res+=freq[px][py]*freq[x][y]*freq[x][py]*freq[px][y];
	        }
	        
	        return res;
	    }
	
	public static void main(String[] args) {

        FastScanner f = new FastScanner();
        PrintWriter p = new PrintWriter(System.out);
        int t = f.nextInt();
        while (t-- > 0) {
        	
        }
        p.close();

    }
	
	static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

}
