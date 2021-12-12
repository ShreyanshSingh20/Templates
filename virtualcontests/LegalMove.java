package virtualcontests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.*;

public class LegalMove {

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

	public static void main(String[] args) {

		FastScanner f = new FastScanner();
		PrintWriter p = new PrintWriter(System.out);
		char b[][]=new char[8][8];
		int row=f.nextInt();
		int col=f.nextInt();
		char k[]=f.next().toCharArray();
		char den=k[0];
		for(int i=0;i<8;i++) {
			char c[]=f.next().toCharArray();
			for(int j=0;j<8;j++) {
				b[i][j]=c[j];
			}
		}
		
		boolean res=false;
		if(den=='B') res=black(b,row,col);
		else if(den=='W') res=white(b,row,col);
		
		
		
		p.close();

	}
	
	static boolean black(char c[][],int row,int col) {
		if(c[row][col]=='.') {
			c[row][col]='B';
		}else return false;
		
		boolean ans=false;
		//left
		StringBuffer l=new StringBuffer();
		for(int i=col;i>=0;i--) {
			if(c[row][i]!='.') {
				l.append(c[row][i]);
			}else {
				break;
			}
		}
		String left=l.toString();
		
		//right
		StringBuffer r=new StringBuffer();
		for(int i=col;i<8;i++) {
			if(c[row][i]!='.') {
				r.append(c[row][i]);
			}else {
				break;
			}
		}
		String right=r.toString();
		
		//up
		StringBuffer u=new StringBuffer();
		for(int i=row;i>=0;i--) {
			if(c[i][col]!='.') {
				u.append(c[i][col]);
			}else {
				break;
			}
		}
		String up=u.toString();
		
		//down
		StringBuffer d=new StringBuffer();
		for(int i=row;i<8;i++) {
			if(c[i][col]!='.') {
				d.append(c[i][col]);
			}else {
				break;
			}
		}
		String down=d.toString();
		
		//ne
		StringBuffer ne=new StringBuffer();
		int i=row;
		int j=col;
		while(i>=0&&j<8) {
			if(c[i][j]!='.') {
				ne.append(c[i][j]);
			}else {
				break;
			}
			i--;
			j++;
		}
		String northeast=ne.toString();
		
		//se
		StringBuffer se=new StringBuffer();
		i=row;
		j=col;
		while(i<8&&j<8) {
			if(c[i][j]!='.') {
				se.append(c[i][j]);
			}else {
				break;
			}
			i++;
			j++;
		}
		String southeast=se.toString();
		
		//nw
		StringBuffer nw=new StringBuffer();
		i=row;
		j=col;
		while(i>=0&&j>=0) {
			if(c[i][j]!='.') {
				nw.append(c[i][j]);
			}else {
				break;
			}
			i--;
			j--;
		}
		String northwest=nw.toString();
		
		//sw
		StringBuffer sw=new StringBuffer();
		i=row;
		j=col;
		while(i<8&&j>=0) {
			if(c[i][j]!='.') {
				sw.append(c[i][j]);
			}else {
				break;
			}
			i++;
			j--;
		}
		String southwest=sw.toString();
		
		//north
		if(bb(up)==true) {ans=true; return true;}
		//south
		if(bb(down)==true) {ans=true; return true;}
		
		//left
		if(bb(left)==true) {ans=true; return true;}
		
		//right
		if(bb(right)==true) {ans=true; return true;}
		
		//ne
		if(bb(northeast)==true) {ans=true; return true;}
		
		//se
		if(bb(southeast)==true) {ans=true; return true;}
		
		//nw
		if(bb(northwest)==true) {ans=true; return true;}
		
		//sw
		if(bb(southwest)==true) {ans=true; return true;}
		
		return ans;
	}
	
	static boolean white(char c[][],int row,int col) {
		if(c[row][col]=='.') {
			c[row][col]='W';
		}else return false;
		
		boolean ans=false;
		//left
		StringBuffer l=new StringBuffer();
		for(int i=col;i>=0;i--) {
			if(c[row][i]!='.') {
				l.append(c[row][i]);
			}else {
				break;
			}
		}
		String left=l.toString();
		
		//right
		StringBuffer r=new StringBuffer();
		for(int i=col;i<8;i++) {
			if(c[row][i]!='.') {
				r.append(c[row][i]);
			}else {
				break;
			}
		}
		String right=r.toString();
		
		//up
		StringBuffer u=new StringBuffer();
		for(int i=row;i>=0;i--) {
			if(c[i][col]!='.') {
				u.append(c[i][col]);
			}else {
				break;
			}
		}
		String up=u.toString();
		
		//down
		StringBuffer d=new StringBuffer();
		for(int i=row;i<8;i++) {
			if(c[i][col]!='.') {
				d.append(c[i][col]);
			}else {
				break;
			}
		}
		String down=d.toString();
		
		//ne
		StringBuffer ne=new StringBuffer();
		int i=row;
		int j=col;
		while(i>=0&&j<8) {
			if(c[i][j]!='.') {
				ne.append(c[i][j]);
			}else {
				break;
			}
			i--;
			j++;
		}
		String northeast=ne.toString();
		
		//se
		StringBuffer se=new StringBuffer();
		i=row;
		j=col;
		while(i<8&&j<8) {
			if(c[i][j]!='.') {
				se.append(c[i][j]);
			}else {
				break;
			}
			i++;
			j++;
		}
		String southeast=se.toString();
		
		//nw
		StringBuffer nw=new StringBuffer();
		i=row;
		j=col;
		while(i>=0&&j>=0) {
			if(c[i][j]!='.') {
				nw.append(c[i][j]);
			}else {
				break;
			}
			i--;
			j--;
		}
		String northwest=nw.toString();
		
		//sw
		StringBuffer sw=new StringBuffer();
		i=row;
		j=col;
		while(i<8&&j>=0) {
			if(c[i][j]!='.') {
				sw.append(c[i][j]);
			}else {
				break;
			}
			i++;
			j--;
		}
		String southwest=sw.toString();
		
		//north
		if(ww(up)==true) {ans=true; return true;}
		//south
		if(ww(down)==true) {ans=true; return true;}
		
		//left
		if(ww(left)==true) {ans=true; return true;}
		
		//right
		if(ww(right)==true) {ans=true; return true;}
		
		//ne
		if(ww(northeast)==true) {ans=true; return true;}
		
		//se
		if(ww(southeast)==true) {ans=true; return true;}
		
		//nw
		if(ww(northwest)==true) {ans=true; return true;}
		
		//sw
		if(ww(southwest)==true) {ans=true; return true;}
		
		return ans;
		
	}
	
	static boolean bb(String str) {
		boolean ans=false;
		if(str.length()>=3) {
			char s=str.charAt(0);
			int w=0;
			for(int i=1;i<str.length();i++) {
				if(str.charAt(i)=='B') {
					if(w>0) return true;
					else return false;
				}else w++;
				
			}
		}
		return ans;
	}
	
	static boolean ww(String str) {
		boolean ans=false;
		if(str.length()>=3) {
			char s=str.charAt(0);
			int b=0;
			for(int i=1;i<str.length();i++) {
				if(str.charAt(i)=='W') {
					if(b>0) return true;
					else return false;
				}else b++;
				
			}
		}
		return ans;
	}

	// will work till array size of 65537---fastest sorting time
//	static long[] radixSort(long[] f) {
//		return radixSort(f, f.length);
//	}
//
//	static long[] radixSort(long[] f, int n) {
//		long[] to = new long[n];
//		{
//			int[] b = new int[65537];
//			for (int i = 0; i < n; i++)
//				b[1 + (int) (f[i] & 0xffff)]++;
//			for (int i = 1; i <= 65536; i++)
//				b[i] += b[i - 1];
//			for (int i = 0; i < n; i++)
//				to[b[(int) (f[i] & 0xffff)]++] = f[i];
//			long[] d = f;
//			f = to;
//			to = d;
//		}
//		{
//			int[] b = new int[65537];
//			for (int i = 0; i < n; i++)
//				b[1 + (int) (f[i] >>> 16 & 0xffff)]++;
//			for (int i = 1; i <= 65536; i++)
//				b[i] += b[i - 1];
//			for (int i = 0; i < n; i++)
//				to[b[(int) (f[i] >>> 16 & 0xffff)]++] = f[i];
//			long[] d = f;
//			f = to;
//			to = d;
//		}
//		{
//			int[] b = new int[65537];
//			for (int i = 0; i < n; i++)
//				b[1 + (int) (f[i] >>> 32 & 0xffff)]++;
//			for (int i = 1; i <= 65536; i++)
//				b[i] += b[i - 1];
//			for (int i = 0; i < n; i++)
//				to[b[(int) (f[i] >>> 32 & 0xffff)]++] = f[i];
//			long[] d = f;
//			f = to;
//			to = d;
//		}
//		{
//			int[] b = new int[65537];
//			for (int i = 0; i < n; i++)
//				b[1 + (int) (f[i] >>> 48 & 0xffff)]++;
//			for (int i = 1; i <= 65536; i++)
//				b[i] += b[i - 1];
//			for (int i = 0; i < n; i++)
//				to[b[(int) (f[i] >>> 48 & 0xffff)]++] = f[i];
//			long[] d = f;
//			f = to;
//			to = d;
//		}
//		return f;
//	}

	// Function to sort an array using quick sort algorithm.
	static void quickSort(long arr[], int low, int high) {
		if (low < high) {
			int p = partition(arr, low, high);
			quickSort(arr, low, p - 1);
			quickSort(arr, p + 1, high);
		}
	}

	static int partition(long arr[], int low, int high) {
		long pivot = arr[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				i++;
				long temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		long temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}

	/// Ceils
	static long ceil(long x, long m) {
		long res = x / m;
		if (x % m != 0) {
			res++;
		}
		return res;
	}

	// ------------------------------------------------------------------------------------------------
	// makes the prefix sum array
	static long[] prefixSum(long arr[], int n) {
		long psum[] = new long[n];
		psum[0] = arr[0];
		for (int i = 1; i < n; i++) {
			psum[i] = psum[i - 1] + arr[i];
		}
		return psum;
	}
	// ------------------------------------------------------------------------------------------------

	// makes the suffix sum array
	static long[] suffixSum(long arr[], int n) {
		long ssum[] = new long[n];
		ssum[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			ssum[i] = ssum[i + 1] + arr[i];
		}
		return ssum;
	}

//------------------------------------------------------------------------------------------
	// BINARY EXPONENTIATION OF A NUMBER MODULO M FASTER METHOD WITHOUT RECURSIVE
	// OVERHEADS
	static long m = (long) (1e9 + 7);

	static long binPower(long a, long n, long m) {
		if (n == 0)
			return 1;
		long res = 1;
		while (n > 0) {
			if ((n & 1) != 0) {
				res *= a;
			}
			a *= a;
			n >>= 1;
		}
		return res;
	}

//-------------------------------------------------------------------------------------------

	// gcd
	static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}

//------------------------------------------------------------------------------------------

	// lcm
	static long lcm(long a, long b) {
		return a / gcd(a, b) * b;
	}

//------------------------------------------------------------------------------------------
	// BRIAN KERNINGHAM TO CHECK NUMBER OF SET BITS
	// O(LOGn)
	static int setBits(int n) {
		int count = 0;
		while (n > 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}
//------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------
	// 0 based indexing
	static boolean KthBitSet(int n, int k) {
		int mask = 1;
		mask = mask <<= k;
		if ((mask & n) != 0)
			return true;
		else
			return false;
	}

//------------------------------------------------------------------------------------------

	// EXTENDED EUCLIDEAN THEOREM
	// TO REPRESENT GCD IN TERMS OF A AND B
	// gcd(a,b) = a.x + b.y where x and y are integers

	static long x = -1;
	static long y = -1;

	static long gcdxy(long a, long b) {
		if (b == 0) {
			x = 1;
			y = 0;
			return a;
		} else {
			long d = gcdxy(b, a % b);
			long x1 = y;
			long y1 = x - (a / b) * y;
			x = x1;
			y = y1;

			return d;
		}
	}

//-------------------------------------------------------------------------------------------------F
}
