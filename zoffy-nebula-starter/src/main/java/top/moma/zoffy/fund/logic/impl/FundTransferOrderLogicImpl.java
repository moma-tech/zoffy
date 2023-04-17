package top.moma.zoffy.fund.logic.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.moma.m64.core.exceptions.M64Exception;
import top.moma.zoffy.fund.constants.FundTransferOrderApprovalStatusEnum;
import top.moma.zoffy.fund.constants.FundTransferOrderApprovalTypeEnum;
import top.moma.zoffy.fund.constants.FundTransferOrderTradeStatusEnum;
import top.moma.zoffy.fund.constants.FundTransferRuleDirectionEnum;
import top.moma.zoffy.fund.dto.ApprovalFundTransferOrderRequest;
import top.moma.zoffy.fund.dto.FundTransferOrderResponse;
import top.moma.zoffy.fund.dto.QueryFundTransferOrderRequest;
import top.moma.zoffy.fund.dto.SaveFundTransferOrderRequest;
import top.moma.zoffy.fund.entity.FundTransferAccountInfo;
import top.moma.zoffy.fund.entity.FundTransferOrder;
import top.moma.zoffy.fund.entity.FundTransferRule;
import top.moma.zoffy.fund.logic.FundTransferOrderLogic;
import top.moma.zoffy.fund.service.FundTransferAccountInfoService;
import top.moma.zoffy.fund.service.FundTransferOrderService;
import top.moma.zoffy.fund.service.FundTransferRuleService;
import top.moma.zoffy.helper.PageResult;
import top.moma.zoffy.helper.Result;
import top.moma.zoffy.helper.SerialNumberUtil;

/**
 * @author weiqiangguo
 */
@Service
@Slf4j
public class FundTransferOrderLogicImpl implements FundTransferOrderLogic {
  @Autowired FundTransferOrderService fundTransferOrderService;
  @Autowired FundTransferRuleService fundTransferRuleService;
  @Autowired FundTransferAccountInfoService fundTransferAccountInfoService;

  @Override
  public Result<PageResult<FundTransferOrderResponse>> page(QueryFundTransferOrderRequest request) {

    return null;
  }

  @Override
  public String saveOrder(SaveFundTransferOrderRequest request) {
    String resultHolder = "";
    FundTransferOrder fundTransferOrder;
    if (Objects.nonNull(request.getFundOrderId())) {
      fundTransferOrder =
          updateOrder(request, FundTransferOrderTradeStatusEnum.CREATED.tradeStatus());
      if (fundTransferOrderService.updateById(fundTransferOrder)) {
        resultHolder = request.getFundOrderId();
      }
    } else {
      fundTransferOrder =
          createOrder(request, FundTransferOrderTradeStatusEnum.CREATED.tradeStatus());
      if (fundTransferOrderService.save(fundTransferOrder)) {
        fundTransferOrder =
            fundTransferOrderService.queryByOrderSeq(fundTransferOrder.getOrderSeq());
        resultHolder = fundTransferOrder.getOrderId() + "";
      }
    }
    return resultHolder;
  }

  @Override
  public String submitOrder(SaveFundTransferOrderRequest request) {
    String resultHolder = "";
    FundTransferOrder fundTransferOrder;
    if (Objects.nonNull(request.getFundOrderId())) {
      fundTransferOrder =
          updateOrder(request, FundTransferOrderTradeStatusEnum.APPROVAL_PENDING.tradeStatus());
      if (fundTransferOrderService.updateById(fundTransferOrder)) {
        resultHolder = request.getFundOrderId();
      }
    } else {
      fundTransferOrder =
          createOrder(request, FundTransferOrderTradeStatusEnum.APPROVAL_PENDING.tradeStatus());
      if (fundTransferOrderService.save(fundTransferOrder)) {
        fundTransferOrder =
            fundTransferOrderService.queryByOrderSeq(fundTransferOrder.getOrderSeq());
        resultHolder = fundTransferOrder.getOrderId() + "";
      }
    }
    return resultHolder;
  }

  /**
   * createOrder
   *
   * <p>创建调拨单
   *
   * @param request request
   * @param tradeStatus tradeStatus
   * @return com.opay.plateform.fund.management.provider.entity.transfer.FundTransferOrder
   * @author Created by ivan
   * @since 2023/4/10 15:41
   */
  private FundTransferOrder createOrder(SaveFundTransferOrderRequest request, Integer tradeStatus) {
    FundTransferOrder fundTransferOrder =
        initFundTransferOrder(
            request.getFundEntity(),
            request.getFundType(),
            request.getAmount(),
            request.getFundDescription(),
            request.getSenderAccountId(),
            request.getRecipientAccountId());
    if (Objects.nonNull(fundTransferOrder)) {
      fundTransferOrder.setCreateTime(LocalDateTime.now());
      fundTransferOrder.setCreateUser(request.getCreateUser());
      fundTransferOrder.setCreateUserName(request.getCreateUserName());
      if (Objects.equals(
          tradeStatus, FundTransferOrderTradeStatusEnum.APPROVAL_PENDING.tradeStatus())) {
        fundTransferOrder.setTradeStatus(
            FundTransferOrderTradeStatusEnum.APPROVAL_PENDING.tradeStatus());
        fundTransferOrder.setApprovalStatus(
            FundTransferOrderApprovalStatusEnum.APPROVAL_PENDING.approvalStatus());
      }
      return fundTransferOrder;
    }
    throw new M64Exception(
        "Create Transfer Order Failed, Check Fund Type, Sender Account or Recipient Account");
  }

  /**
   * updateOrder
   *
   * <p>更新调拨单
   *
   * @param request request
   * @param tradeStatus tradeStatus
   * @return com.opay.plateform.fund.management.provider.entity.transfer.FundTransferOrder
   * @author Created by ivan
   * @since 2023/4/10 15:42
   */
  private FundTransferOrder updateOrder(SaveFundTransferOrderRequest request, Integer tradeStatus) {
    FundTransferOrder fundTransferOrder =
        fundTransferOrderService.getById(request.getFundOrderId());
    if (Objects.isNull(fundTransferOrder)) {
      log.error("Order record not found. Order id: {}", request.getFundOrderId());
      throw new M64Exception("Transfer Order not found");
    } else if (!Objects.equals(
        fundTransferOrder.getTradeStatus(),
        FundTransferOrderTradeStatusEnum.CREATED.tradeStatus())) {
      log.error("Order already submitted. Order id: {}", request.getFundOrderId());
      throw new M64Exception("Transfer Order already submitted");
    } else {
      FundTransferOrder update =
          initFundTransferOrder(
              request.getFundEntity(),
              request.getFundType(),
              request.getAmount(),
              request.getFundDescription(),
              request.getSenderAccountId(),
              request.getRecipientAccountId());
      if (Objects.nonNull(update)) {
        update.setOrderId(Long.valueOf(request.getFundOrderId()));
        update.setOrderSeq(fundTransferOrder.getOrderSeq());
        if (Objects.equals(
            tradeStatus, FundTransferOrderTradeStatusEnum.APPROVAL_PENDING.tradeStatus())) {
          update.setTradeStatus(FundTransferOrderTradeStatusEnum.APPROVAL_PENDING.tradeStatus());
          update.setApprovalStatus(
              FundTransferOrderApprovalStatusEnum.APPROVAL_PENDING.approvalStatus());
        }
        return update;
      }
      throw new M64Exception(
          "Update Transfer Order Failed, Check Fund Type, Sender Account or Recipient Account");
    }
  }

  /**
   * initFundTransferOrder
   *
   * <p>初始化调拨单
   *
   * <p>匹配场景类型/出金账户/入金账户失败时，返回null
   *
   * @param fundEntity fundEntity
   * @param fundType fundType
   * @param amount amount
   * @param fundDescription fundDescription
   * @param senderId senderId
   * @param recipientId recipientId
   * @return com.opay.plateform.fund.management.provider.entity.transfer.FundTransferOrder
   * @author Created by ivan
   * @since 2023/4/10 15:42
   */
  public FundTransferOrder initFundTransferOrder(
      String fundEntity,
      Integer fundType,
      String amount,
      String fundDescription,
      String senderId,
      String recipientId) {
    FundTransferAccountInfo sender;
    FundTransferAccountInfo recipient;
    String transType;
    List<FundTransferRule> rules = fundTransferRuleService.findByType(fundEntity, fundType);
    if (!CollectionUtils.isEmpty(rules)) {
      transType = rules.get(0).getTransactionType();
      if (validFundAccountWithType(
              rules, Long.valueOf(senderId), FundTransferRuleDirectionEnum.SENDER)
          && validFundAccountWithType(
              rules, Long.valueOf(recipientId), FundTransferRuleDirectionEnum.RECIPIENT)) {
        sender = fundTransferAccountInfoService.getById(senderId);
        recipient = fundTransferAccountInfoService.getById(recipientId);
        if (Objects.nonNull(sender)
            && Objects.nonNull(recipient)
            && org.springframework.util.StringUtils.hasText(transType)) {
          return FundTransferOrder.builder()
              .orderSeq(SerialNumberUtil.getSerialNumber())
              .fundEntity(fundEntity)
              .fundType(fundType)
              .fundDescription(fundDescription)
              .tradeStatus(FundTransferOrderTradeStatusEnum.CREATED.tradeStatus())
              .transactionType(transType)
              .transactionAmount(new BigDecimal(amount).longValue())
              .transactionCurrency("NGN")
              .transactionCountry("NG")
              .senderAccountName(sender.getAccountName())
              .senderAccountCode(sender.getAccountCode())
              .senderAccountBankName(sender.getAccountBankName())
              .senderAccountBankCode(sender.getAccountBankCode())
              .recipientAccountName(recipient.getAccountName())
              .recipientAccountCode(recipient.getAccountCode())
              .recipientAccountBankName(recipient.getAccountBankName())
              .recipientAccountBankCode(recipient.getAccountBankCode())
              .approvalType(FundTransferOrderApprovalTypeEnum.MANUAL.approvalType())
              .build();
        }
      }
    }
    log.error(
        "Build Transfer Order Failed, fundEntity:{} type:{}, senderId:{}, recipientId:{}",
        fundEntity,
        fundType,
        senderId,
        recipientId);
    return null;
  }

  /**
   * validFundAccountWithType
   *
   * <p>校验出入金账户与场景规则的关联有效性
   *
   * @param rules rules
   * @param accountId accountId
   * @param ruleDirectionEnum ruleDirectionEnum
   * @return boolean
   * @author Created by ivan
   * @since 2023/4/12 14:47
   */
  private boolean validFundAccountWithType(
      List<FundTransferRule> rules,
      Long accountId,
      FundTransferRuleDirectionEnum ruleDirectionEnum) {
    return rules.parallelStream()
        .anyMatch(
            fundTransferRule ->
                fundTransferRule.getAccountId().equals(accountId)
                    && fundTransferRule
                        .getFundDirection()
                        .equals(ruleDirectionEnum.fundDirection()));
  }

  @Override
  public String approvalOrder(ApprovalFundTransferOrderRequest request) {
    String resultHolder;
    List<String> fundIds = spiltIds(request.getFundOrderId());
    List<String> updated = new ArrayList<>(fundIds.size());
    for (String fundId : fundIds) {
      FundTransferOrder fundTransferOrder =
          fundTransferOrderService.getById(request.getFundOrderId());
      if (Objects.isNull(fundTransferOrder)) {
        log.error("Transfer Order Not Found, Order Id: {}", fundId);
      } else {
        fundTransferOrder = approvalOrder(fundTransferOrder, request);
        if (Objects.nonNull(fundTransferOrder)
            && fundTransferOrderService.updateById(fundTransferOrder)) {
          updated.add(fundId);
          if (Objects.equals(
              request.getApproval(),
              FundTransferOrderApprovalStatusEnum.APPROVAL_PASS.approvalStatus())) {
            callTransfer(fundTransferOrder);
          }
        }
      }
    }
    resultHolder = (String.join(",", updated));
    return resultHolder;
  }

  /**
   * approvalOrder
   *
   * <p>构造审批对象
   *
   * @param fundTransferOrder fundTransferOrder
   * @param request request
   * @return com.opay.plateform.fund.management.provider.entity.transfer.FundTransferOrder
   * @author Created by ivan
   * @since 2023/4/10 16:42
   */
  private FundTransferOrder approvalOrder(
      FundTransferOrder fundTransferOrder, ApprovalFundTransferOrderRequest request) {
    if (Objects.equals(
        fundTransferOrder.getApprovalStatus(),
        FundTransferOrderApprovalStatusEnum.APPROVAL_PENDING.approvalStatus())) {
      fundTransferOrder.setApprovalStatus(request.getApproval());
      fundTransferOrder.setApprovalCompleteTime(LocalDateTime.now());
      fundTransferOrder.setUpdateTime(LocalDateTime.now());
      fundTransferOrder.setTradeStatus(
          Objects.equals(
                  request.getApproval(),
                  FundTransferOrderApprovalStatusEnum.APPROVAL_PASS.approvalStatus())
              ? FundTransferOrderTradeStatusEnum.APPROVAL_PASS.tradeStatus()
              : FundTransferOrderTradeStatusEnum.APPROVAL_REJECTED.tradeStatus());
      if (Objects.nonNull(request.getDenialReason())) {
        fundTransferOrder.setApprovalDenialReason(request.getDenialReason());
      }
      if (Objects.nonNull(request.getApprovalUser())) {
        fundTransferOrder.setApprovalUser(request.getApprovalUser());
      }
      if (Objects.nonNull(request.getApprovalUserName())) {
        fundTransferOrder.setApprovalUserName(request.getApprovalUserName());
      }
      return fundTransferOrder;
    } else {
      log.error("Order already approved. Order id: {}", fundTransferOrder.getOrderId());
    }
    return null;
  }

  /**
   * spiltIds
   *
   * @param ids ids
   * @return java.util.List<java.lang.String>
   * @author Created by ivan
   * @since 2023/4/10 15:51
   */
  private List<String> spiltIds(String ids) {
    return Stream.of(ids.split(",")).collect(Collectors.toList());
  }

  /**
   * description callTransfer
   *
   * @param fundTransferOrder fundTransferOrder
   * @author Created by ivan
   * @since 2023/4/10 16:23
   */
  @Async
  public void callTransfer(FundTransferOrder fundTransferOrder) {}
}
