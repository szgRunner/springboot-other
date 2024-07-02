package sun.zhao.guo.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.Fastjson2TypeHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;
import sun.zhao.guo.enums.TestEnum;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.model
 * @Author: SzgStart-sunzg
 * @Date: 2023/02/02/下午3:10
 * @Description:
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

//@Accessors(chain = true)
//@TableName(autoResultMap = true)

public class TestInfo extends SuperModel<TestInfo> {

    private String testName;

    private String userId;

//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date testTime;

    @TableField(jdbcType = JdbcType.INTEGER)
    private TestEnum testEnum;

//    @JSONField(deserializeUsing = Set.class)
//    @JsonIgnore
    @TableField(value = "tests", jdbcType = JdbcType.VARCHAR, typeHandler = Fastjson2TypeHandler.class)
    private List<String> tests;


    /**
     * 注意！！ 必须开启映射注解
     *
     * @TableName(autoResultMap = true)
     *
     * 以下两种类型处理器，二选一 也可以同时存在
     *
     * 注意！！选择对应的 JSON 处理器也必须存在对应 JSON 解析依赖包
     */
    // @TableField(typeHandler = JacksonTypeHandler.class)
    // @TableField(typeHandler = FastjsonTypeHandler.class)
    // private OtherInfo otherInfo;

    @TableField(exist = false, typeHandler = Fastjson2TypeHandler.class)
    private User user;

}
