package top.moma.zoffy.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.moma.zoffy.report.entity.PkGlEntries;
import top.moma.zoffy.report.mapper.PkGlEntriesMapper;
import top.moma.zoffy.report.service.PkGlEntriesService;

@Service
public class PkGlEntriesServiceImpl extends ServiceImpl<PkGlEntriesMapper, PkGlEntries>
    implements PkGlEntriesService {}
