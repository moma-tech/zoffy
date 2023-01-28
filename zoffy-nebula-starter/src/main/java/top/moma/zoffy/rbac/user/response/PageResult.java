package top.moma.zoffy.rbac.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PageResult<T> implements Serializable {
  private long total;
  private List<T> records;
}
