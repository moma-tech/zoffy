package top.moma.zoffy.support.request;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

/**
 * CachedBodyHttpServletRequest
 *
 * <p>扩展Servlet Request支持RequestBody多次读取
 *
 * @version 1.0
 * @author Created by ivan at 18:11.
 */
@Slf4j
public class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {
  /** Body Buffer */
  private final byte[] requestBodyBuffer;

  public CachedBodyHttpServletRequest(HttpServletRequest request) {
    super(request);
    requestBodyBuffer = getByteBody(request);
  }

  @Override
  public ServletInputStream getInputStream() {
    return new CachedBodyServletInputStream(requestBodyBuffer);
  }

  private byte[] getByteBody(HttpServletRequest request) {
    byte[] requestBody = new byte[0];
    try {
      requestBody = StreamUtils.copyToByteArray(request.getInputStream());
    } catch (IOException e) {
      log.error("Error: Get Request Body fail", e);
    }
    return requestBody;
  }

  static class CachedBodyServletInputStream extends ServletInputStream {

    private final InputStream cachedBodyInputStream;

    public CachedBodyServletInputStream(byte[] cachedBody) {
      cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
    }

    @Override
    public boolean isFinished() {
      try {
        return cachedBodyInputStream.available() == 0;
      } catch (IOException e) {
        e.printStackTrace();
      }
      return true;
    }

    @Override
    public boolean isReady() {
      return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {
      throw new UnsupportedOperationException();
    }

    @Override
    public int read() throws IOException {
      return cachedBodyInputStream.read();
    }
  }
}
