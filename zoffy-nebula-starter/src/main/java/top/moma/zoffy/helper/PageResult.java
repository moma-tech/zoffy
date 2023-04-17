package top.moma.zoffy.helper;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author weiqiangguo
 */
@Data
public class PageResult<T> implements Serializable {

  private static final long serialVersionUID = -3001472956801424409L;

  private long total;
  private List<T> records;

  public PageResult(long total, List<T> records) {
    this.total = total;
    this.records = records;
  }

  public static <D> Result<PageResult<D>> ok(long total, List<D> records) {
    return (new Result()).success(new PageResult(total, records));
  }
}
