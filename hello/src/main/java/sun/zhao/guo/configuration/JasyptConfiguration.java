package sun.zhao.guo.configuration;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
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

    /*
    @Bean("jasyptStringEncryptor")
    public StandardPBEStringEncryptor jasyptStringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

        EnvironmentStringPBEConfig pconf = new EnvironmentStringPBEConfig();
        pconf.setAlgorithm("PBEWithMD5AndDES");
        pconf.setPassword("tencent");
        encryptor.setConfig(pconf);

        return encryptor;
    }
    */

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("tencent");
        config.setAlgorithm("PBEWithMD5AndDES");
//        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
//        config.setProviderName("SunJCE");
//        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
//        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
//        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

}
