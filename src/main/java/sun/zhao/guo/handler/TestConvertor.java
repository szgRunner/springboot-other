package sun.zhao.guo.handler;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.handler
 * @Author: SzgStart-sunzg
 * @Date: 2023/07/05/下午3:10
 * @Description:
 */
public class TestConvertor extends AbstractJsonTypeHandler<List<String>> {

    public TestConvertor(Class<?> type) {
        super(type);
    }

    public TestConvertor(Class<?> type, Field field) {
        super(type, field);
    }

    @Override
    public List<String> parse(String json) {
        return JSON.parseArray(json, String.class);
    }

    @Override
    public String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }
}
