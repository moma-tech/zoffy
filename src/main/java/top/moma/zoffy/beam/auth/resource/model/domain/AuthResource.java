package top.moma.zoffy.beam.auth.resource.model.domain;

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
@TableName("auth_resource")
public class AuthResource extends SuperModel {

    private static final long serialVersionUID = 1L;

    private String id;

    private String resourceName;

    private String resourceDisplayName;

    private Boolean resourceType;

    private String resourcePath;

    private String resourceCode;

    private Boolean status;

    private String resourceOprations;

    private String resourceParentId;

    private String resourceTag;

    private LocalDate createTime;

    private LocalDate updateTime;

    private String modifier;


    public static final String ID = "id";

    public static final String RESOURCE_NAME = "resource_name";

    public static final String RESOURCE_DISPLAY_NAME = "resource_display_name";

    public static final String RESOURCE_TYPE = "resource_type";

    public static final String RESOURCE_PATH = "resource_path";

    public static final String RESOURCE_CODE = "resource_code";

    public static final String STATUS = "status";

    public static final String RESOURCE_OPRATIONS = "resource_oprations";

    public static final String RESOURCE_PARENT_ID = "resource_parent_id";

    public static final String RESOURCE_TAG = "resource_tag";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String MODIFIER = "modifier";

}
