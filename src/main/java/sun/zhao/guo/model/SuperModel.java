package sun.zhao.guo.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.model
 * @Author: SzgStart-sunzg
 * @Date: 2023/02/02/下午3:11
 * @Description:
 */
public class SuperModel<T extends Model<T>> extends Model<T> {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Long version;
}
