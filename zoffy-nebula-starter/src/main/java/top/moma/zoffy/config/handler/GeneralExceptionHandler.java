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
import top.moma.zoffy.common.enumerations.HttpResponseEnum;
import top.moma.zoffy.support.reaponse.FailedResponse;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {

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

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public FailedResponse handleMissingServletRequestParameterException(
      MissingServletRequestParameterException ex,
      WebRequest request,
      HttpServletRequest httpServletRequest) {
    log.debug("MissingServletRequestParameterException >>>> {}", (Object) ex);
    String builder = "参数字段 " + ex.getParameterName() + " 校验不通过";
    return FailedResponse.failed(HttpResponseEnum.BAD_REQUEST, builder);
  }

  @ExceptionHandler(MissingPathVariableException.class)
  public FailedResponse handleMissingPathVariableException(
      MissingPathVariableException ex, WebRequest request, HttpServletRequest httpServletRequest) {
    log.debug("MissingPathVariableException >>>> {}", (Object) ex);
    String builder = "路径字段 " + ex.getVariableName() + " 校验不通过";
    return FailedResponse.failed(HttpResponseEnum.BAD_REQUEST, builder);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public FailedResponse handleConstraintViolationException(
      ConstraintViolationException ex, WebRequest request, HttpServletRequest httpServletRequest) {
    log.debug("ConstraintViolationException >>>> {}", (Object) ex);
    StringBuilder builder = new StringBuilder("方法.参数字段 ");
    Optional<ConstraintViolation<?>> first = ex.getConstraintViolations().stream().findFirst();
    if (first.isPresent()) {
      ConstraintViolation<?> constraintViolation = first.get();
      builder.append(constraintViolation.getPropertyPath().toString());
      builder.append(" 校验不通过 ");
    }
    return FailedResponse.failed(HttpResponseEnum.BAD_REQUEST, builder.toString());
  }

  @ExceptionHandler(MismatchedInputException.class)
  public FailedResponse handleMismatchedInputException(
      MismatchedInputException ex, WebRequest request, HttpServletRequest httpServletRequest) {
    log.debug("MismatchedInputException >>>> {}", (Object) ex);
    StringBuilder builder = new StringBuilder("参数字段 ");
    List<JsonMappingException.Reference> referenceList = ex.getPath();
    referenceList.stream()
        .findFirst()
        .ifPresent(reference -> builder.append(reference.getFieldName()));
    builder.append(" 期望类型: ").append(ex.getTargetType());
    return FailedResponse.failed(HttpResponseEnum.BAD_REQUEST, builder.toString());
  }
}
