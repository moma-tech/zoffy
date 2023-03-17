package top.moma.zoffy.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.moma.zoffy.report.entity.PkGlEntriesAccountCode;
import top.moma.zoffy.report.mapper.PkGlEntriesAccountCodeMapper;
import top.moma.zoffy.report.service.PkGlEntriesAccountCodeService;

/**
 * PkGlEntriesAccountCodeServiceImpl
 *
 * <p>巴基斯坦凭证科目计算模版表;(pk_gl_entries_account_code)表服务实现类
 *
 * @version 1.0
 * @author Created by ivan at 14:20.
 */
@Service
public class PkGlEntriesAccountCodeServiceImpl
    extends ServiceImpl<PkGlEntriesAccountCodeMapper, PkGlEntriesAccountCode>
    implements PkGlEntriesAccountCodeService {}
