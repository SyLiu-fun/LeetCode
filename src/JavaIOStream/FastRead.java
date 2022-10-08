package JavaIOStream;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Read {
    BufferedReader bf;
    StringTokenizer st;
    BufferedWriter bw;

    public Read() {
        bf = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer("");
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public String nextLine() throws IOException {
        return bf.readLine();
    }

    public String next() throws IOException {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(bf.readLine());
        }
        return st.nextToken();
    }

    public char nextChar() throws IOException {
        return next().charAt(0);
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public float nextFloat() throws IOException {
        return Float.parseFloat(next());
    }

    public byte nextByte() throws IOException {
        return Byte.parseByte(next());
    }

    public short nextShort() throws IOException {
        return Short.parseShort(next());
    }

    public BigInteger nextBigInteger() throws IOException {
        return new BigInteger(next());
    }

    public void println(int a) throws IOException {
        bw.write(String.valueOf(a));
        bw.newLine();
    }

    public void print(int a) throws IOException {
        bw.write(String.valueOf(a));
    }

    public void println(String a) throws IOException {
        bw.write(a);
        bw.newLine();
    }

    public void print(String a) throws IOException {
        bw.write(a);
    }

    public void println(long a) throws IOException {
        bw.write(String.valueOf(a));
        bw.newLine();
    }

    public void print(long a) throws IOException {
        bw.write(String.valueOf(a));
    }

    public void println(double a) throws IOException {
        bw.write(String.valueOf(a));
        bw.newLine();
    }

    public void print(double a) throws IOException {
        bw.write(String.valueOf(a));
    }
}
