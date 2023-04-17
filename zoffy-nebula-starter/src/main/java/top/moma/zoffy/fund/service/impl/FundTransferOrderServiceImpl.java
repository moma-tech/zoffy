package top.moma.zoffy.fund.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.moma.zoffy.fund.entity.FundTransferOrder;
import top.moma.zoffy.fund.mapper.FundTransferOrderMapper;
import top.moma.zoffy.fund.service.FundTransferOrderService;

/**
 * @author weiqiangguo
 */
@Service
@Slf4j
public class FundTransferOrderServiceImpl
    extends ServiceImpl<FundTransferOrderMapper, FundTransferOrder>
    implements FundTransferOrderService {

  @Override
  public FundTransferOrder queryByOrderSeq(String orderSeq) {
    QueryWrapper<FundTransferOrder> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("order_seq", orderSeq);
    return getOne(queryWrapper);
  }
}
