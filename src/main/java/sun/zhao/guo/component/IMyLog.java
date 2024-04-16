package sun.zhao.guo.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

public interface IMyLog {


    default String GetClassPathName() {

        Annotation[] annotations = this.getClass().getAnnotations();
        if (null == annotations || annotations.length == 0) {
            return "";
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof Service){
                Service item = (Service) annotation;
                return item.value();
            }
            if (annotation instanceof Component){
                Component item = (Component) annotation;
                return item.value();
            }
        }
        return "";
    }

    default Logger getLogger() {
        return LoggerFactory.getLogger(GetClassPathName());
    }
}