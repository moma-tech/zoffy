package top.moma.zoffy.beam.auth.role.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import top.moma.m78.framework.common.model.entity.SuperModel;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ivan
 * @since 2019-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_role")
public class AuthRole extends SuperModel {

    private static final long serialVersionUID = 1L;

    private String id;

    private String roleName;

    private Boolean roleType;

    private Boolean status;

    private LocalDate createTime;

    private LocalDate updateTime;

    private String modifier;


    public static final String ID = "id";

    public static final String ROLE_NAME = "role_name";

    public static final String ROLE_TYPE = "role_type";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String MODIFIER = "modifier";

}
