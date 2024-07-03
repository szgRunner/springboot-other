package sun.zhao.guo.guo.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.enums
 * @Author: SzgStart-sunzg
 * @Date: 2023/07/05/下午3:03
 * @Description:
 */
public enum TestEnum implements IEnum<Integer> {

    TEST(1, "test");

    private int type;

    private String descp;

    TestEnum(int type, String descp) {
        this.type = type;
        this.descp = descp;
    }

    @Override
    public Integer getValue() {
        return this.type;
    }
}
