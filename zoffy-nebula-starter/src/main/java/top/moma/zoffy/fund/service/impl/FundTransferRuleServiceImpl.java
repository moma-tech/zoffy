package top.moma.zoffy.fund.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.moma.zoffy.fund.constants.StatusEnum;
import top.moma.zoffy.fund.entity.FundTransferRule;
import top.moma.zoffy.fund.mapper.FundTransferRuleMapper;
import top.moma.zoffy.fund.service.FundTransferRuleService;

/**
 * @author weiqiangguo
 */
@Service
@Slf4j
public class FundTransferRuleServiceImpl
    extends ServiceImpl<FundTransferRuleMapper, FundTransferRule>
    implements FundTransferRuleService {

  @Override
  public List<FundTransferRule> listBy(FundTransferRule condition) {
    return list(
        Wrappers.lambdaQuery(FundTransferRule.class)
            .eq(
                Objects.nonNull(condition.getActiveStatus()),
                FundTransferRule::getActiveStatus,
                condition.getActiveStatus()));
  }

  @Override
  public List<FundTransferRule> findByType(String fundEntity, Integer fundType) {
    QueryWrapper<FundTransferRule> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .eq("fund_entity", fundEntity)
        .eq("fund_type", fundType)
        .eq("active_status", StatusEnum.VALID.getCode());
    return list(queryWrapper);
  }
}
