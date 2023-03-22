package top.moma.zoffy.config.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import top.moma.m64.core.exceptions.M64Exception;
import top.moma.zoffy.annotation.ZoffyResponse;
import top.moma.zoffy.common.enumerations.HttpResponseEnum;
import top.moma.zoffy.support.reaponse.FailedResponse;

/**
 * GeneralExceptionHandler
 *
 * <p>统一异常处理
 *
 * <p>使用FailedResponse响应
 *
 * @version 1.0
 * @author Created by ivan at 17:20.
 */
@Slf4j
@RestControllerAdvice
@ZoffyResponse
public class GeneralExceptionHandler {
  /**
   * handleM64Exception
   *
   * <p>系统异常500响应
   *
   * @param ex ex
   * @param request request
   * @param httpServletRequest httpServletRequest
   * @return top.moma.zoffy.support.reaponse.FailedResponse
   * @author Created by ivan
   * @since 2023/3/22 17:30
   */
  @ExceptionHandler(M64Exception.class)
  public FailedResponse handleM64Exception(
      M64Exception ex, WebRequest request, HttpServletRequest httpServletRequest) {
    log.error("M64Exception >>>> {}", (Object) ex);
    return FailedResponse.failed(HttpResponseEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
  }

  /**
   * handleMethodArgumentNotValidException
   *
   * <p>参数规则校验失败
   *
   * @param ex ex
   * @param request request
   * @param httpServletRequest httpServletRequest
   * @return top.moma.zoffy.support.reaponse.FailedResponse
   * @author Created by ivan
   * @since 2023/3/22 17:21
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public FailedResponse handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex,
      WebRequest request,
      HttpServletRequest httpServletRequest) {
    log.debug("MethodArgumentNotValidException >>>> {}", (Object) ex);
    StringBuilder builder = new StringBuilder("Validation Failed: ");
    List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
    allErrors.forEach(
        error ->
            builder
                .append(((FieldError) error).getField())
                .append(" ")
                .append(error.getDefaultMessage())
                .append("; "));
    return FailedResponse.failed(HttpResponseEnum.BAD_REQUEST, builder.toString());
  }

  /**
   * handleMissingServletRequestParameterException
   *
   * <p>参数缺失异常
   *
   * @param ex ex
   * @param request request
   * @param httpServletRequest httpServletRequest
   * @return top.moma.zoffy.support.reaponse.FailedResponse
   * @author Created by ivan
   * @since 2023/3/22 17:21
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public FailedResponse handleMissingServletRequestParameterException(
      MissingServletRequestParameterException ex,
      WebRequest request,
      HttpServletRequest httpServletRequest) {
    log.debug("MissingServletRequestParameterException >>>> {}", (Object) ex);
    String builder = "Parameter: " + ex.getParameterName() + " Missing";
    return FailedResponse.failed(HttpResponseEnum.BAD_REQUEST, builder);
  }

  /**
   * handleMissingPathVariableException
   *
   * <p>路径参数缺失异常
   *
   * @param ex ex
   * @param request request
   * @param httpServletRequest httpServletRequest
   * @return top.moma.zoffy.support.reaponse.FailedResponse
   * @author Created by ivan
   * @since 2023/3/22 17:23
   */
  @ExceptionHandler(MissingPathVariableException.class)
  public FailedResponse handleMissingPathVariableException(
      MissingPathVariableException ex, WebRequest request, HttpServletRequest httpServletRequest) {
    log.debug("MissingPathVariableException >>>> {}", (Object) ex);
    String builder = "Path Variable： " + ex.getVariableName() + " Missing";
    return FailedResponse.failed(HttpResponseEnum.BAD_REQUEST, builder);
  }

  /**
   * handleConstraintViolationException
   *
   * <p>其他约束校验异常
   *
   * @param ex ex
   * @param request request
   * @param httpServletRequest httpServletRequest
   * @return top.moma.zoffy.support.reaponse.FailedResponse
   * @author Created by ivan
   * @since 2023/3/22 17:24
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public FailedResponse handleConstraintViolationException(
      ConstraintViolationException ex, WebRequest request, HttpServletRequest httpServletRequest) {
    log.debug("ConstraintViolationException >>>> {}", (Object) ex);
    StringBuilder builder = new StringBuilder("Method/Parameters: ");
    Optional<ConstraintViolation<?>> first = ex.getConstraintViolations().stream().findFirst();
    if (first.isPresent()) {
      ConstraintViolation<?> constraintViolation = first.get();
      builder.append(constraintViolation.getPropertyPath().toString());
      builder.append(" Invalid.");
    }
    return FailedResponse.failed(HttpResponseEnum.BAD_REQUEST, builder.toString());
  }

  /**
   * handleMismatchedInputException
   *
   * <p>类型不匹配异常
   *
   * @param ex ex
   * @param request request
   * @param httpServletRequest httpServletRequest
   * @return top.moma.zoffy.support.reaponse.FailedResponse
   * @author Created by ivan
   * @since 2023/3/22 17:26
   */
  @ExceptionHandler(MismatchedInputException.class)
  public FailedResponse handleMismatchedInputException(
      MismatchedInputException ex, WebRequest request, HttpServletRequest httpServletRequest) {
    log.debug("MismatchedInputException >>>> {}", (Object) ex);
    StringBuilder builder = new StringBuilder("Input:  ");
    List<JsonMappingException.Reference> referenceList = ex.getPath();
    referenceList.stream()
        .findFirst()
        .ifPresent(reference -> builder.append(reference.getFieldName()));
    builder.append(" Expected as: ").append(ex.getTargetType());
    return FailedResponse.failed(HttpResponseEnum.BAD_REQUEST, builder.toString());
  }
}
