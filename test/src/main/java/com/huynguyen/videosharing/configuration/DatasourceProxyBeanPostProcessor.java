package com.huynguyen.videosharing.configuration;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ReflectionUtils;

@Configuration
@Profile("local")
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) {
    if (this.isValidBean(bean)) {
      final ProxyFactory factory = new ProxyFactory(bean);
      factory.setProxyTargetClass(true);
      factory.addAdvice(new ProxyDataSourceInterceptor((DataSource) bean));
      return factory.getProxy();
    }
    return bean;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    return bean;
  }

  private boolean isValidBean(Object bean) {
    return bean instanceof DataSource && !(bean instanceof ProxyDataSource)
        && !(bean instanceof FactoryBean);
  }

  private static class ProxyDataSourceInterceptor implements MethodInterceptor {

    private final DataSource dataSource;

    ProxyDataSourceInterceptor(final DataSource dataSource) {
      this.dataSource = ProxyDataSourceBuilder.create(dataSource)
          .name("datasource-proxy-monitor")
          .multiline()
          .logSlowQueryBySlf4j(2, TimeUnit.MINUTES, SLF4JLogLevel.WARN)
          .logQueryBySlf4j(SLF4JLogLevel.TRACE)
          .build();
    }


    @Override
    public Object invoke(org.aopalliance.intercept.MethodInvocation invocation)
        throws Throwable {
      final Method proxyMethod = ReflectionUtils.findMethod(this.dataSource.getClass(),
          invocation.getMethod().getName());
      if (proxyMethod != null) {
        return proxyMethod.invoke(this.dataSource, invocation.getArguments());
      }
      return invocation.proceed();
    }
  }
}
