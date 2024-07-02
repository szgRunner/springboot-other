package sun.zhao.guo.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import sun.zhao.guo.component.MyFilter;
import sun.zhao.guo.component.MyListener;
import sun.zhao.guo.component.MyServlet;

import java.util.Arrays;

//@Configuration
public class ServletConfig {

//    @Bean
    public ServletRegistrationBean getServlet(MyServlet myServlet){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(myServlet, "/myServlet");
        return servletRegistrationBean;
    }

//    @Bean
    public FilterRegistrationBean getFilter(MyFilter myFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/toLoginPage", "/myFilter"));
        return filterRegistrationBean;
    }

//    @Bean
    public ServletListenerRegistrationBean getListener(MyListener myListener) {
        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean(myListener);
        return listenerRegistrationBean;

    }
}
