package top.moma.zoffy.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import top.moma.zoffy.fund.entity.FundTransferRule;

/**
 * @author weiqiangguo
 */
public interface FundTransferRuleService extends IService<FundTransferRule> {

  List<FundTransferRule> listBy(FundTransferRule activeStatus);

  /**
   * findByType
   *
   * <p>根据调拨场景查询调拨规则
   *
   * @param fundType fundType
   * @return com.opay.plateform.fund.management.provider.entity.transfer.FundTransferRule
   * @author Created by ivan
   * @since 2023/4/10 14:31
   */
  List<FundTransferRule> findByType(String fundEntity, Integer fundType);
}
