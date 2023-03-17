package top.moma.zoffy.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.moma.zoffy.report.entity.LoanData;
import top.moma.zoffy.report.mapper.LoanDataMapper;
import top.moma.zoffy.report.service.LoanDataService;

/**
 * @author zhiwen.wang
 * @description 针对表【loan_data(loan_data)】的数据库操作Service实现
 * @createDate 2023-03-08 15:56:13
 */
@Service
public class LoanDataServiceImpl extends ServiceImpl<LoanDataMapper, LoanData>
    implements LoanDataService {}
