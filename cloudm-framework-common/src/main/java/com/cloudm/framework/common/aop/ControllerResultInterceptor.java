package com.cloudm.framework.common.aop;


import com.cloudm.framework.common.enums.BaseErrorEnum;
import com.cloudm.framework.common.ex.BusinessCheckFailException;
import com.cloudm.framework.common.ex.BusinessProcessFailException;
import com.cloudm.framework.common.web.result.base.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.time.StopWatch;
import org.objenesis.ObjenesisStd;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: Controller 拦截处理
 * 只要在配置文件中注入AOP配置即可生效
 * 实例如下:
 * <ul>
        <bean id="controllerResultInterceptor" class="com.cloudm.framework.common.aop.ControllerResultInterceptor"/>
        <aop:config proxy-target-class="true">
            <aop:pointcut id="resultControlWrapper" expression="(@annotation(org.springframework.web.bind.annotation.ResponseBody) and execution(com.cloudm.framework.common.web.result.Result *.*(..))) or (@annotation(org.springframework.web.bind.annotation.ResponseBody))"></aop:pointcut>
            <aop:advisor advice-ref="controllerResultInterceptor" pointcut-ref="resultControlWrapper"/>
        </aop:config>
</ul>

 * @author: Courser
 * @date: 2017/3/17
 * @version: V1.0
 */
@Slf4j
public class ControllerResultInterceptor implements MethodInterceptor {
    private ObjenesisStd generator = new ObjenesisStd();

    @Autowired
    private ExceptionInterceptor exceptionInterceptor;

    /**
     * 环绕通知
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public BaseResult invoke(final MethodInvocation invocation) throws Throwable {
        BaseResult result = null;
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            result = (BaseResult) invocation.proceed();
            watch.stop();
            if (log.isInfoEnabled()){
                String info = invocation.getMethod().getDeclaringClass()+ "." +
                        invocation.getMethod().getName() + "()";
                log.info("The Method==>{}, execute Time==>{}ms",info,watch.getTime());
            }

            return result;
        } catch (BusinessProcessFailException e) {
            watch.stop();
            result = exceptionInterceptor.exceptionProcessor(invocation,e,e.getErrorCode(),e.getMessage(),BaseErrorEnum.BNS_PRS_ERROR);
        } catch (BusinessCheckFailException e) {
            watch.stop();
            result = exceptionInterceptor.exceptionProcessor(invocation,e,e.getErrorCode(),e.getMessage(),BaseErrorEnum.BNS_CHK_ERROR);
        } catch (Exception e) {
            watch.stop();
            result = exceptionInterceptor.exceptionProcessor(invocation,e,BaseErrorEnum.UNKNOWN_ERROR);
        }
        return result;
    }

}
