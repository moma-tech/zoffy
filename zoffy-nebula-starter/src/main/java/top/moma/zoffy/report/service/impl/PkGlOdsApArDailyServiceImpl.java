package top.moma.zoffy.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.moma.zoffy.report.entity.PkGlOdsApArDaily;
import top.moma.zoffy.report.mapper.PkGlOdsApArDailyMapper;
import top.moma.zoffy.report.service.PkGlOdsApArDailyService;

/**
 * PkGlOdsApArDailyServiceImpl
 *
 * <p>基础表-应收应付ApAr-按天;(pk_gl_ods_ap_ar_daily)表服务实现类
 *
 * @version 1.0
 * @author Created by ivan at 18:29.
 */
@Service
public class PkGlOdsApArDailyServiceImpl
    extends ServiceImpl<PkGlOdsApArDailyMapper, PkGlOdsApArDaily>
    implements PkGlOdsApArDailyService {}
