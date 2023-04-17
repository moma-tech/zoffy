package top.moma.zoffy.fund.logic;

import top.moma.zoffy.fund.dto.ApprovalFundTransferOrderRequest;
import top.moma.zoffy.fund.dto.FundTransferOrderResponse;
import top.moma.zoffy.fund.dto.QueryFundTransferOrderRequest;
import top.moma.zoffy.fund.dto.SaveFundTransferOrderRequest;
import top.moma.zoffy.helper.PageResult;
import top.moma.zoffy.helper.Result;

/**
 * @author weiqiangguo
 */
public interface FundTransferOrderLogic {

  Result<PageResult<FundTransferOrderResponse>> page(QueryFundTransferOrderRequest request);

  /**
   * 订单保存 saveOrder
   *
   * @param request request
   * @return com.opay.plateform.fund.management.provider.entity.transfer.FundTransferOrder
   * @author Created by ivan
   * @since 2023/4/10 11:52
   */
  String saveOrder(SaveFundTransferOrderRequest request);

  /**
   * 订单提交 submitOrder
   *
   * @param request request
   * @return com.opay.plateform.fund.management.provider.entity.transfer.FundTransferOrder
   * @author Created by ivan
   * @since 2023/4/10 11:52
   */
  String submitOrder(SaveFundTransferOrderRequest request);

  /**
   * 订单审核 approvalOrder
   *
   * @param request request
   * @return com.opay.plateform.fund.management.provider.entity.transfer.FundTransferOrder
   * @author Created by ivan
   * @since 2023/4/10 11:52
   */
  String approvalOrder(ApprovalFundTransferOrderRequest request);
}
