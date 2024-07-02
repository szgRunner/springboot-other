package sun.zhao.guo.configuration;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.configuration
 * @Author: SzgStart-sunzg
 * @Date: 2024/01/25/17:56
 * @Description:
 */

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 如果你在Spring Boot应用中发现WebMvcConfigurer配置的转换器未生效，
     * 或者尝试添加HttpMessageConverter时发现它们没有被注册，这里有几个可能的原因及解决方案：
     *
     * 1. 确保重写方法正确
     * 在Spring Boot应用中，通过扩展WebMvcConfigurer接口来添加自定义的HttpMessageConverter时，
     * 应该重写extendMessageConverters方法而不是configureMessageConverters。
     * 这是因为Spring Boot自动配置了自己的转换器，你应该在不替换默认配置的基础上进行扩展。
     *
     *
     * //@Configuration
     * public class WebConfig implements WebMvcConfigurer {
     *
     *     //@Override
     *     public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
     *         // 添加自定义的转换器到现有列表
     *         converters.add(new ByteArrayHttpMessageConverter());
     *     }
     * }
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        WebMvcConfigurer.super.configureMessageConverters(converters);

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //自定义配置...
        FastJsonConfig config = new FastJsonConfig();
        config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
        config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.PrettyFormat, JSONWriter.Feature.BrowserCompatible);
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(0, converter);

        // 添加 ByteArrayHttpMessageConverter
        /**
         * 确保你的Spring配置中包含了ByteArrayHttpMessageConverter，
         * 它是处理字节数组并支持application/octet-streamContent-Type的标准转换器。
         * 如果你使用的是Spring Boot，这个转换器通常已经自动配置好了。
         * 但如果你遇到上述错误，可能是因为某些原因它没有正常工作或者被覆盖了。
         */
//        converters.add(new ByteArrayHttpMessageConverter());
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /**
     * 跨域
     * 1、注解 @CrossOrigin
     * 2、全局配置启用 CROS 支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api")
                .allowedOrigins("http://localhost:9080")
                .allowedMethods("GET", "POST");
    }
}
