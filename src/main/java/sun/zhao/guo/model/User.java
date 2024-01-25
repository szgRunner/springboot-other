package sun.zhao.guo.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "测试用户")
public class User {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @Schema(description = "用户姓名", requiredMode = Schema.RequiredMode.AUTO)
    private String userName;
}
