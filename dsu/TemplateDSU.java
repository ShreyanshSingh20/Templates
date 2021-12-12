package dsu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class TemplateDSU {

	static long mod = 1_000_000_007;

	public static void main(String[] args) {

		FastScanner f = new FastScanner();
		PrintWriter p = new PrintWriter(System.out);

		int n = f.nextInt();
		DSU dsu = new DSU(n);
		
		p.close();

	}

	// 0 based indexing
	static class DSU {
		int parent[];
		int size[];
//		int rank[];

		public DSU(int n) {
			parent = new int[n];
			size = new int[n];
//			rank=new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}

		// union by size and path compression
		public void union(int u, int v) {
			int pu = find(u);
			int pv = find(v);
			if (pu == pv)
				return;

			if (size[pu] < size[pv]) {
				parent[pu] = pv;
				size[pv] += size[pu];
			} else {
				parent[pv] = pu;
				size[pu] += size[pv];
			}
		}

		// find by path compression
		// time complexity->O(4*alpha)--as good as constant time
		public int find(int node) {
			if (parent[node] == node)
				return node;
			return parent[node] = find(parent[node]);
		}

		// union by rank and path compression
//		public void unionByRank(int u,int v) {
//			int pu=find(u);
//			int pv=find(v);
//			if(pv==pu) return;
//			
//			if(rank[pu]<rank[pv]) {
//				parent[pu]=pv;
//			}else if(rank[pu]>rank[pv]) {
//				parent[pv]=pu;
//			}else {
//				parent[pv]=pu;
//				rank[pu]++;
//			}
//		}

	}

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------

	static int offx[] = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int offy[] = { -1, 0, 1, 1, 1, 0, -1, -1 };

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
	static long[] radixSort(long[] f) {
		return radixSort(f, f.length);
	}

	static long[] radixSort(long[] f, int n) {
		long[] to = new long[n];
		{
			int[] b = new int[65537];
			for (int i = 0; i < n; i++)
				b[1 + (int) (f[i] & 0xffff)]++;
			for (int i = 1; i <= 65536; i++)
				b[i] += b[i - 1];
			for (int i = 0; i < n; i++)
				to[b[(int) (f[i] & 0xffff)]++] = f[i];
			long[] d = f;
			f = to;
			to = d;
		}
		{
			int[] b = new int[65537];
			for (int i = 0; i < n; i++)
				b[1 + (int) (f[i] >>> 16 & 0xffff)]++;
			for (int i = 1; i <= 65536; i++)
				b[i] += b[i - 1];
			for (int i = 0; i < n; i++)
				to[b[(int) (f[i] >>> 16 & 0xffff)]++] = f[i];
			long[] d = f;
			f = to;
			to = d;
		}
		{
			int[] b = new int[65537];
			for (int i = 0; i < n; i++)
				b[1 + (int) (f[i] >>> 32 & 0xffff)]++;
			for (int i = 1; i <= 65536; i++)
				b[i] += b[i - 1];
			for (int i = 0; i < n; i++)
				to[b[(int) (f[i] >>> 32 & 0xffff)]++] = f[i];
			long[] d = f;
			f = to;
			to = d;
		}
		{
			int[] b = new int[65537];
			for (int i = 0; i < n; i++)
				b[1 + (int) (f[i] >>> 48 & 0xffff)]++;
			for (int i = 1; i <= 65536; i++)
				b[i] += b[i - 1];
			for (int i = 0; i < n; i++)
				to[b[(int) (f[i] >>> 48 & 0xffff)]++] = f[i];
			long[] d = f;
			f = to;
			to = d;
		}
		return f;
	}

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

//-------------------------------------------------------------------------------------------------
}
