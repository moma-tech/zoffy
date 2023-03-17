package top.moma.zoffy.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.moma.zoffy.report.entity.CashInFlow;
import top.moma.zoffy.report.mapper.CashInFlowMapper;
import top.moma.zoffy.report.service.CashInFlowService;

/**
 * @author zhiwen.wang
 * @description 针对表【cash_in_flow(cashinflow)】的数据库操作Service实现
 * @createDate 2023-03-08 11:25:32
 */
@Service
public class CashInFlowServiceImpl extends ServiceImpl<CashInFlowMapper, CashInFlow>
    implements CashInFlowService {}
