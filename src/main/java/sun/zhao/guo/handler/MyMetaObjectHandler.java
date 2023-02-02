package sun.zhao.guo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.handler
 * @Author: SzgStart-sunzg
 * @Date: 2023/02/02/下午4:45
 * @Description:
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {

        System.out.println("MyMetaObjectHandler.insertFill");

        this.strictInsertFill(metaObject, "testTime", Date::new, Date.class);
        this.strictInsertFill(metaObject, "version", () -> 1L, Long.class);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        System.out.println("MyMetaObjectHandler.updateFill");

        this.strictUpdateFill(metaObject, "testTime", Date::new, Date.class);

    }
}
