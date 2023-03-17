package top.moma.zoffy.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.moma.zoffy.report.entity.PkGlRetApArMonth;
import top.moma.zoffy.report.mapper.PkGlRetApArMonthMapper;
import top.moma.zoffy.report.service.PkGlRetApArMonthService;

/**
 * PkGlRetApArMonthServiceImpl
 *
 * <p>结果表-应收应付ApAr-按月;(pk_gl_ret_ap_ar_month)表服务实现类
 *
 * @version 1.0
 * @author Created by ivan at 18:27.
 */
@Service
public class PkGlRetApArMonthServiceImpl
    extends ServiceImpl<PkGlRetApArMonthMapper, PkGlRetApArMonth>
    implements PkGlRetApArMonthService {}
