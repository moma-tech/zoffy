package top.moma.zoffy.support.request;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;
import top.moma.m64.core.helper.ObjectHelper;

/**
 * RequestWrapper
 *
 * <p>扩展Servlet Request支持RequestBody多次读取
 *
 * @version 1.0
 * @author Created by ivan at 18:11.
 */
@Slf4j
public class RequestWrapper extends HttpServletRequestWrapper {
  /** Body Buffer */
  private final byte[] requestBodyBuffer;

  public RequestWrapper(HttpServletRequest request) {
    super(request);
    requestBodyBuffer = getByteBody(request);
  }

  @Override
  public BufferedReader getReader() {
    ServletInputStream inputStream = getInputStream();
    return ObjectHelper.isEmpty(inputStream)
        ? null
        : new BufferedReader(new InputStreamReader(inputStream));
  }

  @Override
  public ServletInputStream getInputStream() {
    if (ObjectUtils.isEmpty(requestBodyBuffer)) {
      return null;
    }
    final ByteArrayInputStream bais = new ByteArrayInputStream(requestBodyBuffer);
    return new ServletInputStream() {
      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {
        throw new UnsupportedOperationException();
      }

      @Override
      public int read() {
        return bais.read();
      }
    };
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
}
