package top.moma.zoffy.support.wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.function.Consumer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import top.moma.m64.common.helper.json.JsonHelper;

public class CustomMessageConverterWrapper {

  public static Consumer<HttpMessageConverter<?>> objectMapperWrapper() {
    return converter -> {
      if (converter instanceof MappingJackson2HttpMessageConverter httpMessageConverter) {
        ObjectMapper objectMapper =
            JsonHelper.getObjectMapper(httpMessageConverter.getObjectMapper());
        JsonHelper.registerLongSerializer(objectMapper);
        httpMessageConverter.setObjectMapper(objectMapper);
      }
    };
  }

  private CustomMessageConverterWrapper() {
    throw new IllegalStateException("Utility class");
  }
}
