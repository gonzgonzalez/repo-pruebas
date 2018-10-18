import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

public class DescargaPdf {

    private static final String urlPdf = "http://www.conicyt.cl/fondecyt/files/2018/04/Bases-Iniciacion-2018_FINAL.pdf";

    public byte[] getPdf() {
        try {
            URL url = new URL(urlPdf);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            int contentLength = connection.getContentLength();
            ByteArrayOutputStream byteArrayOutputStream;
            if (contentLength != -1) {
                byteArrayOutputStream = new ByteArrayOutputStream(contentLength);
            } else {
                byteArrayOutputStream = new ByteArrayOutputStreamEx(16384);
            }
            byte[] buffer = new byte[512];
            while (true) {
                int len = inputStream.read(buffer);
                if (len == -1) {
                    break;
                }
                byteArrayOutputStream.write(buffer, 0, len);
            }
            inputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}