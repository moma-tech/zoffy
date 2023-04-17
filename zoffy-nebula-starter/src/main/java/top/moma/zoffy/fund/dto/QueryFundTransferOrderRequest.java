package top.moma.zoffy.fund.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import top.moma.zoffy.helper.PageRequest;

/**
 * @author weiqiangguo
 */
@Data
public class QueryFundTransferOrderRequest extends PageRequest implements Serializable {

  private static final long serialVersionUID = -5437803166401920820L;

  private LocalDateTime createTimeStart;
  private LocalDateTime createTimeEnd;

  private LocalDateTime completeTimeStart;
  private LocalDateTime completeTimeEnd;

  private Integer orderStatus;

  private String fundType;
}
