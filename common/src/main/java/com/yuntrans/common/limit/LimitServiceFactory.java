/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:26
 */
package com.yuntrans.common.limit;


import org.springframework.context.ApplicationContextAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class LimitServiceFactory implements ApplicationContextAware {
    private static final String LIMIT_SERVICE_BEAN_NAME_DEFAULT = "defaultLimitServiceImpl";

    private static final String LIMIT_SERVICE_BEAN_NAME_META = "metaLimitServiceImpl";

    private static final String LIMIT_SERVICE_BEAN_NAME_META_APPKEY = "metaAppkeyLimitServiceImpl";

    private static final String NAMESPACE_VIRTUAL_ASSISTANT = "VirtualAssistant";

    private ApplicationContext applicationContext;

    public LimitService getLimitService(String env, String namespace) {
        switch (env) {
            case "inner":
            case "enterprise-cluster":
                return getLimitService("metaAppkeyLimitServiceImpl");
            case "cloud":
                if ("VirtualAssistant".equals(namespace))
                    return getLimitService("metaAppkeyLimitServiceImpl");
                return getLimitService("metaLimitServiceImpl");
        }
        return getLimitService("defaultLimitServiceImpl");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private LimitService getLimitService(String beanName) {
        return (LimitService)this.applicationContext.getBean(beanName);
    }
}
