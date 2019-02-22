package com.sinosoft.config.jdbc;


import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;
import java.util.Properties;

@Configuration
//开启事务
@ComponentScan
@EnableTransactionManagement
public class TransactionAdviceConfig {
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.sinosoft.*.service..*.*(..))";
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public UserTransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
    public JtaTransactionManager transactionManager() throws Throwable {
        JtaTransactionManager manager = new JtaTransactionManager( userTransaction(), atomikosTransactionManager());
        return manager;
    }

    @Bean(name = "transactionInterceptor")
    public TransactionInterceptor transactionInterceptor(@Qualifier("transactionManager") PlatformTransactionManager platformTransactionManager) {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        // 事物管理器
        transactionInterceptor.setTransactionManager(platformTransactionManager);
        Properties transactionAttributes = new Properties();

        // 新增
        transactionAttributes.setProperty("insert*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("save*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("exec*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("add*","PROPAGATION_REQUIRED,-Throwable");
        // 修改
        transactionAttributes.setProperty("update*", "PROPAGATION_REQUIRED,-Throwable");
        // 删除
        transactionAttributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Throwable");
        // 查询
        transactionAttributes.setProperty("select*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
        transactionAttributes.setProperty("query*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
        transactionAttributes.setProperty("find*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
        transactionAttributes.setProperty("list*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
        transactionAttributes.setProperty("count*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
        logger.debug("txAdvice");
        transactionInterceptor.setTransactionAttributes(transactionAttributes);
        return transactionInterceptor;

    }
    @Bean
    public Advisor txAdviceAdvisor(TransactionInterceptor transactionInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        logger.debug("txAdviceAdvisor");
        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor);
    }
    // 代理到ServiceImpl的Bean
//    @Bean
//    public BeanNameAutoProxyCreator transactionAutoProxy() {
//        BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
//        transactionAutoProxy.setProxyTargetClass(true);
//        transactionAutoProxy.setBeanNames("*ServiceImpl");
//        transactionAutoProxy.setInterceptorNames("transactionInterceptor");
//        return transactionAutoProxy;
//    }





}
