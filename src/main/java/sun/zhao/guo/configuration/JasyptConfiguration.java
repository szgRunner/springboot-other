package sun.zhao.guo.configuration;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * https://www.cnblogs.com/luo630/p/17154027.html
 *
 * @package: sun.zhao.guo.configuration
 * @Author: SzgStart-sunzg
 * @Date: 2023/07/05/下午1:31
 * @Description:
 */
@Configuration
public class JasyptConfiguration {

    @Bean("jasyptStringEncryptor")
    public StandardPBEStringEncryptor jasyptStringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

        EnvironmentStringPBEConfig pconf = new EnvironmentStringPBEConfig();
        pconf.setAlgorithm("PBEWithMD5AndDES");
        pconf.setPassword("tencent");
        encryptor.setConfig(pconf);

        return encryptor;
    }

}
