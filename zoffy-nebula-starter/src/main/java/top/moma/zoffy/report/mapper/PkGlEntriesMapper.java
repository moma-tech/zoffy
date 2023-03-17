package top.moma.zoffy.report.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.moma.zoffy.report.entity.PkGlEntries;

/**
 * PkGlEntriesMapper
 *
 * <p>巴基斯坦凭证记录表;(pk_gl_entries)表数据库访问层
 *
 * @version 1.0
 * @author Created by ivan at 14:16.
 */
@Mapper
public interface PkGlEntriesMapper extends BaseMapper<PkGlEntries> {}
