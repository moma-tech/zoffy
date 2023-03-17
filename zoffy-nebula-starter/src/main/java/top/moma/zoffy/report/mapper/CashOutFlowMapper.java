package top.moma.zoffy.report.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.moma.zoffy.report.entity.CashOutFlow;

/**
 * @author zhiwen.wang
 * @description 针对表【cash_out_flow(cashoutflow)】的数据库操作Mapper
 * @createDate 2023-03-08 15:25:27 @Entity generator.domain.CashOutFlow
 */
@Mapper
public interface CashOutFlowMapper extends BaseMapper<CashOutFlow> {}
