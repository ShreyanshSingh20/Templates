package trie;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import trie.MaximumXorOfTwoNumbers.Node;

import java.util.*;

public class XORQueries {

	static long mod = 1_000_000_007;

	public static void main(String[] args) {

		FastScanner f = new FastScanner();
		PrintWriter p = new PrintWriter(System.out);
		int n=f.nextInt();
		int arr[]=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=f.nextInt();
		}
		int q=f.nextInt();
		int queries[][]=new int[q][3];
		for(int i=0;i<q;i++) {
			for(int j=0;j<2;j++) {
				queries[i][j]=f.nextInt();
			}
			queries[i][2]=i;
		}
		
		Arrays.sort(queries,(a,b)->(a[2]-b[2]));
		Arrays.sort(arr);
		int res[]=new int[q];
		Arrays.fill(res, -1);
		int prevInd=0;
		Trie trie=new Trie();
		for(int i=0;i<q;i++) {
			int num=queries[i][0];
			int x=queries[i][1];
			int idx=queries[i][2];
			int ind=UpperBound(arr, x);
			if(ind==0) {
				if(arr[0]>x) continue;
			}
			
			if(arr[ind]>x) ind--;
			int maxXor=trie.findMaxXor(arr, prevInd, ind, num);
			res[idx]=maxXor;
			prevInd=ind+1;
			
		}
		
		for(int x:res) {
			p.print(x+" ");
		}
		
		p.close();

	}
	
	static int UpperBound(int a[], int x) {// x is the key or target value
		int low = 0, high = a.length-1;
		while(low<high) {
    		int m=(low+high+1)/2;
    		if(a[m]<=x) {
    			low=m;
    		}else {
    			high=m-1;
    		}
    	}
		return low;
	}
	
	
	static class Node{
		Node arr[];
		int val;
		
		public Node() {
			arr=new Node[2];
			val=0;
		}
		
		public Node getChild(int i) {
			return arr[i];
		}
		
		public void setChild(int i,Node node) {
			arr[i]=node;
		}
		
		public void setVal(int num) {
			val=num;
		}
		
		public int getVal() {
			return val;
		}
	}
	
	static class Trie{
		Node rootNode;
		
		public Trie() {
			rootNode=new Node();
		}
		
		public void insert(int num) {
			Node tempNode=rootNode;
			for(int bit=31;bit>=0;bit--) {
				int bitVal=getBitVal(num, bit);
				if(tempNode.getChild(bitVal)==null) {
					Node newNode=new Node();
					tempNode.setChild(bitVal, newNode);
				}
				tempNode=tempNode.getChild(bitVal);
			}
			
			tempNode.setVal(num);
		}
		
		public int getMaxXor(int num) {
			Node tempNode=rootNode;
			for(int bit=31;bit>=0;bit--) {
				int bitVal=getBitVal(num, bit);
				int rev=getReverse(bitVal);
				if(tempNode.getChild(rev)!=null) {
					tempNode=tempNode.getChild(rev);
				}else {
					if(tempNode.getChild(bitVal)!=null) {
						tempNode=tempNode.getChild(bitVal);
					}else {
						return Integer.MIN_VALUE;
					}
				}
			}
			
			return num^tempNode.getVal();
		}
		
		public int findMaxXor(int arr[],int i,int j,int num) {
			for(int idx=i;idx<=j;idx++) {
				insert(arr[idx]);
			}
			return getMaxXor(num);
		}
		
		public int getBitVal(int num,int bit) {
			int bitVal=num&(1<<bit);
			return bitVal==0?0:1;
		}
		
		public int getReverse(int i) {
			return i==0?1:0;
		}
		
	}

	

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------

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

	static long add(long x, long y) {
		x += y;
		if (x >= mod)
			return (x % mod);
		return x;
	}

	static long sub(long x, long y) {
		x -= y;
		if (x < 0)
			return (x + mod);
		return x;
	}

	static long mul(long x, long y) {
		return (x * y) % mod;
	}

	static long bin_pow(long x, long p) {
		if (p == 0)
			return 1;
		if ((p & 1) != 0)
			return mul(x, bin_pow(x, p - 1));
		return bin_pow(mul(x, x), p / 2);
	}

	static long rev(long x) {
		return bin_pow(x, mod - 2);
	}

	static long div(long x, long y) {
		return mul(x, rev(y));
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
//	static long m = (long) (1e9 + 7);

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

	// ------------------------------------------------------------------------------------------
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

	// ------------------------------------------------------------------------------------------
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
