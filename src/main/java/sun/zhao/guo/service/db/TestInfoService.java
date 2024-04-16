package sun.zhao.guo.service.db;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.zhao.guo.mapper.TestInfoMapper;
import sun.zhao.guo.model.TestInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.service.db
 * @Author: SzgStart-sunzg
 * @Date: 2023/02/02/下午3:21
 * @Description:
 */

@Service
public class TestInfoService extends ServiceImpl<TestInfoMapper, TestInfo>{

    private final TestInfoMapper testInfoMapper;

    public TestInfoService(TestInfoMapper testInfoMapper) {
        this.testInfoMapper = testInfoMapper;
    }

    @Override
    public List<TestInfo> list() {
        return testInfoMapper.queryList(test -> test.isNotNull("id").orderByAsc("version"));
    }
}
