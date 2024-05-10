package com.ecjtu.osbs.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局事物配置
 *
 * @author CaoLongHui
 * @since 2024/4/25 23:14
 */
@Aspect
@Configuration
public class TransactionAdviceConfig {

    /**
     * 配置方法过期时间，默认-1,永不超时
     */
    private static final int TX_METHOD_TIMEOUT = 10;

    /**
     * 配置切入点表达式 : 指定哪些包中的类使用事务
     */
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.ecjtu.osbs..service.*.*(..))";

    /**
     * 事务管理器
     */
    @Autowired
    private TransactionManager transactionManager;

    /**
     * 声明业务方法的事务属性
     */
    @Bean
    public TransactionInterceptor txAdvice() {
        // 这里配置只读事务
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        // 设置当前事务是否为只读事务，true为只读
        readOnlyTx.setReadOnly(true);
        // 事物传播行为: 当前存在事务就使用当前事务，当前不存在事务,就开启一个新的事务
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        // 抛出异常后执行切点回滚
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        // 若当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // 设置事务失效时间，超过10秒，则回滚事务
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);

        // 设置方法对应的事务
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        //只读事务
        txMap.put("get*", readOnlyTx);
        txMap.put("find*", readOnlyTx);
        txMap.put("list*", readOnlyTx);
        txMap.put("count*", readOnlyTx);
        txMap.put("exist*", readOnlyTx);
        txMap.put("search*", readOnlyTx);
        txMap.put("fetch*", readOnlyTx);

        //写事务
        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("modify*", requiredTx);
        txMap.put("delete*", requiredTx);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.setNameMap(txMap);

        //返回事务拦截器
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        //配置事务切入点表达式
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        //增强事务，关联切入点和事务属性
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

}
