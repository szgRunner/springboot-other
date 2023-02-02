package sun.zhao.guo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
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
    private Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
