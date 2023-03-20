package top.moma.zoffy.support.reaponse;

public class ResponseHelper extends Response<String> {
  public static Response COMMON_SUCCESS = Response.<String>builder().build().success();
  public static Response COMMON_SYSTEM_FAILED = Response.<String>builder().build().failed();
}
