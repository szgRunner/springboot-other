package sun.zhao.guo.component;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@Component
@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized ...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println("ContextDestroyed ...");
    }
}
