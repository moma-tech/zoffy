package top.moma.zoffy.fund.controller;

import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.m64.core.exceptions.M64Exception;
import top.moma.zoffy.fund.dto.ApprovalFundTransferOrderRequest;
import top.moma.zoffy.fund.dto.SaveFundTransferOrderRequest;
import top.moma.zoffy.fund.logic.FundTransferOrderLogic;
import top.moma.zoffy.helper.Result;

@RestController
@RequestMapping("/fund")
public class FundTransferOrderController {
  @Resource FundTransferOrderLogic fundTransferOrderLogic;

  @PostMapping("/save")
  public Result<String> saveTransferOrder(
      @RequestBody SaveFundTransferOrderRequest saveFundTransferOrderRequest) {
    if (Objects.isNull(saveFundTransferOrderRequest.getFundEntity())
        || Objects.isNull(saveFundTransferOrderRequest.getFundType())
        || Objects.isNull(saveFundTransferOrderRequest.getAmount())
        || Objects.isNull(saveFundTransferOrderRequest.getSenderAccountId())
        || Objects.isNull(saveFundTransferOrderRequest.getRecipientAccountId())) {
      throw new M64Exception("Params Missing");
    }
    String orderId = fundTransferOrderLogic.saveOrder(saveFundTransferOrderRequest);
    return new Result<String>().success(orderId);
  }

  @PostMapping("/submit")
  public Result<String> submitTransferOrder(
      @RequestBody SaveFundTransferOrderRequest saveFundTransferOrderRequest) {
    if (Objects.isNull(saveFundTransferOrderRequest.getFundEntity())
        || Objects.isNull(saveFundTransferOrderRequest.getFundType())
        || Objects.isNull(saveFundTransferOrderRequest.getAmount())
        || Objects.isNull(saveFundTransferOrderRequest.getSenderAccountId())
        || Objects.isNull(saveFundTransferOrderRequest.getRecipientAccountId())) {
      throw new M64Exception("Params Missing");
    }
    String orderId = fundTransferOrderLogic.submitOrder(saveFundTransferOrderRequest);
    return new Result<String>().success(orderId);
  }

  @PostMapping("/approval")
  public Result<String> approvalTransferOrder(
      @RequestBody ApprovalFundTransferOrderRequest approvalFundTransferOrderRequest) {
    if (Objects.isNull(approvalFundTransferOrderRequest.getFundOrderId())
        || Objects.isNull(approvalFundTransferOrderRequest.getApproval())) {
      throw new M64Exception("Params Missing");
    }
    String orderId = fundTransferOrderLogic.approvalOrder(approvalFundTransferOrderRequest);
    return new Result<String>().success(orderId);
  }
}
