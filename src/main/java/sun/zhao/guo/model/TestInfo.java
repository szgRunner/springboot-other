package sun.zhao.guo.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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
public class TestInfo extends SuperModel<TestInfo> {

    private String testName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date testTime;
}
