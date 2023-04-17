package top.moma.zoffy.fund.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.moma.zoffy.fund.entity.FundTransferAccountInfo;
import top.moma.zoffy.fund.mapper.FundTransferAccountInfoMapper;
import top.moma.zoffy.fund.service.FundTransferAccountInfoService;

/**
 * @author weiqiangguo
 */
@Service
@Slf4j
public class FundTransferAccountInfoServiceImpl
    extends ServiceImpl<FundTransferAccountInfoMapper, FundTransferAccountInfo>
    implements FundTransferAccountInfoService {

  @Override
  public List<FundTransferAccountInfo> listByAccountIds(Collection<Long> accountIds) {
    return list(
        Wrappers.lambdaQuery(FundTransferAccountInfo.class)
            .in(FundTransferAccountInfo::getAccountId, accountIds));
  }
}
