package top.moma.zoffy.common.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import top.moma.zoffy.common.enumerations.HttpResponseEnum;
import top.moma.zoffy.support.reaponse.Response;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<String> handleBusinessException(
            MethodArgumentNotValidException ex,
            WebRequest request,
            HttpServletRequest httpServletRequest) {
        log.info("MethodArgumentNotValidException >>>> {}", (Object) ex);
        StringBuilder builder = new StringBuilder("Validation Failed: ");
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        allErrors.stream()
                .forEach(
                        error -> {
                            builder
                                    .append(((FieldError) error).getField())
                                    // .append(",校验规则为")
                                    .append(" " + error.getDefaultMessage())
                                    .append("; ");
                        });
        return new Response<String>()
                .failed(
                        HttpResponseEnum.BAD_REQUEST.code(),
                        HttpResponseEnum.BAD_REQUEST.msg(),
                        builder.toString());
    }

    public static String formatException(Exception exception) {
        if (null == exception) {
            return null;
        } else if (exception instanceof MethodArgumentNotValidException) {
            StringBuilder builder = new StringBuilder("校验失败:");
            List<ObjectError> allErrors =
                    ((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors();
            allErrors.stream()
                    .findFirst()
                    .ifPresent(
                            error -> {
                                builder
                                        .append(((FieldError) error).getField())
                                        .append(" 字段规则为 ")
                                        .append(error.getDefaultMessage());
                            });
            return builder.toString();
        } else if (exception instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException ex =
                    (MissingServletRequestParameterException) exception;
            String builder = "参数字段" + ex.getParameterName() + "校验不通过";
            return builder;
        } else if (exception instanceof MissingPathVariableException) {
            MissingPathVariableException ex = (MissingPathVariableException) exception;
            String builder = "路径字段" + ex.getVariableName() + "校验不通过";
            return builder;
        } else if (exception instanceof ConstraintViolationException) {
            StringBuilder builder = new StringBuilder("方法.参数字段");
            ConstraintViolationException ex = (ConstraintViolationException) exception;
            Optional<ConstraintViolation<?>> first = ex.getConstraintViolations().stream().findFirst();
            if (first.isPresent()) {
                ConstraintViolation<?> constraintViolation = first.get();
                builder.append(constraintViolation.getPropertyPath().toString());
                builder.append("校验不通过");
            }
            return builder.toString();
        } else if (exception instanceof HttpMessageNotReadableException) {
            if (exception.getCause() instanceof MismatchedInputException) {
                StringBuilder builder = new StringBuilder("参数字段");
                MismatchedInputException ex = ((MismatchedInputException) exception.getCause());
                List<JsonMappingException.Reference> referenceList = ex.getPath();
                referenceList.stream()
                        .findFirst()
                        .ifPresent(
                                reference -> {
                                    builder.append(reference.getFieldName());
                                });
                builder.append(" 期望类型: ").append(ex.getTargetType());
                return builder.toString();
            }
        }
        return exception.toString();
    }
}
