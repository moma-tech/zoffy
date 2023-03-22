package top.moma.zoffy.support.wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.function.Consumer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import top.moma.m64.common.helper.json.JsonHelper;

/**
 * CustomMessageConverterWrapper
 * <p>自定义Message解析
 *
 * @author Created by ivan at 11:28.
 * @version 1.0
 **/
public class CustomMessageConverterWrapper {

  /**
   * 替换Jackson的ObjectMapper objectMapperWrapper
   *
   * @return Function Method
   * @author Created by ivan
   * @since 2023/3/22 11:28
   **/
  public static Consumer<HttpMessageConverter<?>> objectMapperWrapper() {
    return converter -> {
      if (converter instanceof MappingJackson2HttpMessageConverter
          httpMessageConverter) {
        ObjectMapper objectMapper =
            JsonHelper.getObjectMapper(
                httpMessageConverter.getObjectMapper());
        JsonHelper.registerLongSerializer(objectMapper);
        httpMessageConverter.setObjectMapper(objectMapper);
      }
    };
  }

  private CustomMessageConverterWrapper() {
    throw new IllegalStateException("Utility class");
  }
}
