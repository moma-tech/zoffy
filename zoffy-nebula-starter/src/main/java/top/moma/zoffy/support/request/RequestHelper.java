package top.moma.zoffy.support.request;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.moma.m64.common.helper.json.JsonHelper;
import top.moma.m64.core.constants.StringConstants;
import top.moma.m64.core.exceptions.M64Exception;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.m64.core.helper.StringHelper;
import top.moma.m64.core.helper.io.IoHelper;
import top.moma.zoffy.common.constants.HttpConstants;
import top.moma.zoffy.common.enumerations.HttpMethodEnum;
import top.moma.zoffy.support.MapKeyComparator;

/**
 * RequestHelper
 *
 * <p>Request 支撑工具
 *
 * @version 1.0
 * @author Created by ivan at 18:04.
 */
@Slf4j
public class RequestHelper {

  private RequestHelper() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * getRequest
   *
   * <p>获取当前进程的Request对象
   *
   * @return jakarta.servlet.http.HttpServletRequest
   * @author Created by ivan
   * @since 2023/3/22 18:04
   */
  public static HttpServletRequest getRequest() {
    Optional<ServletRequestAttributes> servletRequestAttributes =
        Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    return servletRequestAttributes.map(ServletRequestAttributes::getRequest).orElse(null);
  }

  /**
   * getRequestBody
   *
   * <p>获取请求内容
   *
   * @param request request
   * @return java.lang.Object
   * @author Created by ivan
   * @since 2023/3/22 18:05
   */
  public static Object getRequestBody(HttpServletRequest request) {
    Object requestBody = StringConstants.NULL;
    if (isContainsBody(request)) {
      try {
        if (ObjectHelper.isNotEmpty(request.getInputStream())) {
          String data = IoHelper.toString(request.getInputStream(), StandardCharsets.UTF_8);
          if (ObjectHelper.isNotEmpty(data)) requestBody = JsonHelper.readValue(data);
        }
      } catch (IOException | M64Exception ex) {
        log.error("getRequestBody error >>>>", ex);
      }
    }
    return requestBody;
  }

  /**
   * description getParameterMap
   *
   * <p>获取PARAMETER MAP,排序
   *
   * @param request request
   * @return java.util.Map<java.lang.String,java.lang.String[]>
   * @author Created by ivan
   * @since 2023/3/22 18:07
   */
  public static Map<String, String[]> getParameterMap(HttpServletRequest request) {
    Map<String, String[]> parameterMap = new HashMap<>(15);
    try {
      if (ObjectHelper.isNotEmpty(request.getParameterMap())) {
        Map<String, String[]> pam = request.getParameterMap();
        if (ObjectHelper.isNotEmpty(pam) && pam.size() > 0) {
          pam = MapKeyComparator.sortMapByKey(pam);
          parameterMap = pam;
        }
      }
    } catch (Exception ex) {
      log.error("Request Parameter Map Locked >>>>" + ex);
    }
    return parameterMap;
  }

  /**
   * description isContainsBody
   *
   * <p>only post,put,patch should contains body
   *
   * @param request request
   * @return boolean
   * @author Created by ivan
   * @since 2023/3/22 18:08
   */
  public static boolean isContainsBody(HttpServletRequest request) {
    return isPost(request) || isPut(request) || isPatch(request);
  }

  /**
   * isPost
   *
   * @author Created by Ivan
   * @return boolean
   */
  public static boolean isPost(HttpServletRequest request) {
    return HttpMethodEnum.POST.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * isPut
   *
   * @author Created by Ivan
   * @return boolean
   */
  public static boolean isPut(HttpServletRequest request) {
    return HttpMethodEnum.PUT.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * isPatch
   *
   * @author Created by Ivan
   * @return boolean
   */
  public static boolean isPatch(HttpServletRequest request) {
    return HttpMethodEnum.PATCH.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * isGet
   *
   * @author Created by Ivan
   * @return boolean
   */
  public static boolean isGet(HttpServletRequest request) {
    return HttpMethodEnum.GET.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * isDelete
   *
   * @author Created by Ivan
   * @return boolean
   */
  public static boolean isDelete(HttpServletRequest request) {
    return HttpMethodEnum.DELETE.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * isTrace
   *
   * @author Created by Ivan
   * @return boolean
   */
  public static boolean isTrace(HttpServletRequest request) {
    return HttpMethodEnum.TRACE.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * isHead
   *
   * @author Created by Ivan
   * @return boolean
   */
  public static boolean isHead(HttpServletRequest request) {
    return HttpMethodEnum.HEAD.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * isOptions
   *
   * @author Created by Ivan
   * @return boolean
   */
  public static boolean isOptions(HttpServletRequest request) {
    return HttpMethodEnum.OPTIONS.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * description getIpAddr
   *
   * <p>get request IP address
   *
   * @param request request
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/22 18:09
   */
  public static String getIpAddress(HttpServletRequest request) {
    // nginx代理获取的真实用户ip
    String ip = request.getHeader(HttpConstants.X_REAL_IP);
    if (StringHelper.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader(HttpConstants.X_FORWARDED_FOR);
    }
    if (StringHelper.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader(HttpConstants.PROXY_CLIENT_IP);
    }
    if (StringHelper.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader(HttpConstants.WL_PROXY_CLIENT_IP);
    }
    if (StringHelper.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }

    if (null != ip
        && ip.length() > HttpConstants.IP_MIN_LENGTH
        && ip.contains(StringConstants.COMMA)) {
      ip = ip.substring(0, ip.indexOf(StringConstants.COMMA));
    }
    return ip;
  }
}
