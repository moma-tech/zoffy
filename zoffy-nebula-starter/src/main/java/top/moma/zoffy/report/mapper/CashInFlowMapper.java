package top.moma.zoffy.report.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.moma.zoffy.report.entity.CashInFlow;

/**
 * @author zhiwen.wang
 * @description 针对表【cash_in_flow(cashinflow)】的数据库操作Mapper
 * @createDate 2023-03-08 11:25:32 @Entity generator.domain.CashInFlow
 */
@Mapper
public interface CashInFlowMapper extends BaseMapper<CashInFlow> {}
