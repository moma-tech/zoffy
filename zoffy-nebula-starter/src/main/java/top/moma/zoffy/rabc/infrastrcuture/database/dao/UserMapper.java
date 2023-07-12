package top.moma.zoffy.rabc.infrastrcuture.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.moma.zoffy.rabc.domain.repository.po.UserPo;

@Mapper
public interface UserMapper extends BaseMapper<UserPo> {}
