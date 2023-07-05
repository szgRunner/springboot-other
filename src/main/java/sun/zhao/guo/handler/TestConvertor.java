package sun.zhao.guo.handler;

import cn.hutool.Hutool;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.handler
 * @Author: SzgStart-sunzg
 * @Date: 2023/07/05/下午3:10
 * @Description:
 */
public class TestConvertor extends AbstractJsonTypeHandler<Set<String>> {
    @Override
    protected Set<String> parse(String json) {
        return Sets.newHashSet(JSONUtil.toList(json, String.class));
    }

    @Override
    protected String toJson(Set<String> obj) {
        return JSONUtil.toJsonStr(obj);
    }
}
