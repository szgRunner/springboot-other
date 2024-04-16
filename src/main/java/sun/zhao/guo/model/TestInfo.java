package sun.zhao.guo.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;
import sun.zhao.guo.enums.TestEnum;
import sun.zhao.guo.handler.TestConvertor;

import java.util.Date;
import java.util.Set;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestInfo extends SuperModel<TestInfo> {

    private String testName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date testTime;

    @TableField(jdbcType = JdbcType.INTEGER)
    private TestEnum testEnum;

//    @JSONField(deserializeUsing = Set.class)
    @JsonIgnore
    @TableField(jdbcType = JdbcType.VARCHAR, typeHandler = TestConvertor.class)
    private Set<String> tests;
}
