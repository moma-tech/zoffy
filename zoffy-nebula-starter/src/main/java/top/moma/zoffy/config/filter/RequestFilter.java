package top.moma.zoffy.config.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.util.UrlPathHelper;
import top.moma.m64.core.helper.regular.RegularHelper;
import top.moma.zoffy.common.constants.ApiConstants;
import top.moma.zoffy.framework.trace.TraceWorker;
import top.moma.zoffy.support.request.RequestWrapper;

/**
 * RequestFilter
 *
 * <p>Request拦截器，servlet级别
 *
 * <p>Request注入相关自定义属性
 *
 * @version 1.0
 * @author Created by ivan at 16:41.
 */
@Component
public class RequestFilter implements Filter {
  /** 忽略请求路径 */
  @Value("${zoffy.request.ignore}")
  List<String> ignoreList;

  /**
   * init
   *
   * <p>覆写，用于兼容部分Servlet 3容器的初始化
   *
   * @param filterConfig filterConfig
   * @author Created by ivan
   * @since 2023/3/22 16:42
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  /**
   * doFilter
   *
   * @param servletRequest servletRequest
   * @param servletResponse servletResponse
   * @param filterChain filterChain
   * @author Created by ivan
   * @since 2023/3/22 16:44
   */
  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) (servletRequest);
    String requestUri = UrlPathHelper.defaultInstance.getOriginatingRequestUri(httpRequest);
    /* 注入自定义属性值 */
    String requestId = TraceWorker.getTraceId();
    MDC.put(TraceWorker.TRACE_ID, requestId);
    servletRequest.setAttribute(ApiConstants.REQUEST_ID, requestId);
    servletRequest.setAttribute(ApiConstants.REQUEST_START_TIME, System.nanoTime());
    servletRequest.setAttribute(ApiConstants.REQUEST_URL, requestUri);
    servletRequest.setAttribute(ApiConstants.REQUEST_METHOD, httpRequest.getMethod());
    /* 忽略地址过滤 */
    if (ignoreList.stream().anyMatch(ignore -> RegularHelper.isMatch(ignore, requestUri))) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }
    /* 非MultiPart类型请求，对Request Body进行缓存 */
    StandardServletMultipartResolver commonsMultipartResolver =
        new StandardServletMultipartResolver();
    if (!commonsMultipartResolver.isMultipart(httpRequest)) {
      servletRequest = new RequestWrapper((HttpServletRequest) servletRequest);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
    TraceWorker.clear();
    MDC.remove(TraceWorker.TRACE_ID);
    Filter.super.destroy();
  }
}
