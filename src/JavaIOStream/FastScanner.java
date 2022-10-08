package JavaIOStream;

import java.io.DataInputStream;
import java.io.IOException;

public class FastScanner {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    private FastScanner() throws IOException {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    private short nextShort() throws IOException {
        short ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do ret = (short) (ret * 10 + c - '0');
        while ((c = read()) >= '0' && c <= '9');
        if (neg) return (short) -ret;
        return ret;
    }

    private int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do ret = ret * 10 + c - '0';
        while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    public long nextLong() throws IOException {
        long ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do ret = ret * 10 + c - '0';
        while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    private char nextChar() throws IOException {
        byte c = read();
        while (c <= ' ') c = read();
        return (char) c;
    }

    private String nextString() throws IOException {
        StringBuilder ret = new StringBuilder();
        byte c = read();
        while (c <= ' ') c = read();
        do {
            ret.append((char) c);
        } while ((c = read()) > ' ');
        return ret.toString();
    }

    private void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) buffer[0] = -1;
    }

    private byte read() throws IOException {
        if (bufferPointer == bytesRead) fillBuffer();
        return buffer[bufferPointer++];
    }
}
