package top.moma.zoffy.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.moma.zoffy.fund.entity.FundTransferOrder;

/**
 * @author weiqiangguo
 */
public interface FundTransferOrderService extends IService<FundTransferOrder> {

  /**
   * queryByOrderSeq
   *
   * <p>根据单据号查询调拨单
   *
   * @param orderSeq orderSeq
   * @return com.opay.plateform.fund.management.provider.entity.transfer.FundTransferOrder
   * @author Created by ivan
   * @since 2023/4/10 15:37
   */
  FundTransferOrder queryByOrderSeq(String orderSeq);
}
