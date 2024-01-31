package sun.zhao.guo.model;


import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class User {

    @JSONField(name = "id")
    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @JSONField(name = "userName")
    @Schema(description = "用户姓名", requiredMode = Schema.RequiredMode.AUTO)
    private String userName;


    @JSONField(name = "birthday")
    @Schema(description = "用户生日", requiredMode = Schema.RequiredMode.AUTO, example = "2024-01-31 14:20:10")
    private Date birthday;
}
