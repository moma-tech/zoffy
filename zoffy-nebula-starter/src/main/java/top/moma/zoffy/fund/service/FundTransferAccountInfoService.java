package top.moma.zoffy.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Collection;
import java.util.List;
import top.moma.zoffy.fund.entity.FundTransferAccountInfo;

/**
 * @author weiqiangguo
 */
public interface FundTransferAccountInfoService extends IService<FundTransferAccountInfo> {

  /**
   * 根据fundType查询
   *
   * @param fundTypeSet
   * @return
   */
  List<FundTransferAccountInfo> listByAccountIds(Collection<Long> fundTypeSet);
}
