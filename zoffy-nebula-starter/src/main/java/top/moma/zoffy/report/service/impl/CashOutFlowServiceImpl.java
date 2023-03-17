package top.moma.zoffy.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.moma.zoffy.report.entity.CashOutFlow;
import top.moma.zoffy.report.mapper.CashOutFlowMapper;
import top.moma.zoffy.report.service.CashOutFlowService;

/**
 * @author zhiwen.wang
 * @description 针对表【cash_out_flow(cashoutflow)】的数据库操作Service实现
 * @createDate 2023-03-08 15:25:27
 */
@Service
public class CashOutFlowServiceImpl extends ServiceImpl<CashOutFlowMapper, CashOutFlow>
    implements CashOutFlowService {}
