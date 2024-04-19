package sun.zhao.guo.model;


import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "测试用户")
@TableName(value = "user_info", autoResultMap = true)
public class User {

    @JSONField(name = "id")
    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @TableId
    private String id;

    @JSONField(name = "userName")
    @Schema(description = "用户姓名", requiredMode = Schema.RequiredMode.AUTO)
    @TableField
    private String userName;


    @JSONField(name = "birthday", format = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "用户生日", requiredMode = Schema.RequiredMode.AUTO, example = "2024-01-31 14:20:10")
    @TableField(fill = FieldFill.INSERT)
    private Date birthday;
}
