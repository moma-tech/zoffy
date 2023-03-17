package top.moma.zoffy.report.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.moma.zoffy.report.entity.PkGlOdsApArDaily;

/**
 * PkGlOdsApArDailyService
 *
 * <p>基础表-应收应付ApAr-按天;(pk_gl_ods_ap_ar_daily)表数据库访问层
 *
 * @version 1.0
 * @author Created by ivan at 18:24.
 */
@Mapper
public interface PkGlOdsApArDailyMapper extends BaseMapper<PkGlOdsApArDaily> {}
