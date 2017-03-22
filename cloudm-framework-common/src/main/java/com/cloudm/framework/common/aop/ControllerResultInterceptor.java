package com.cloudm.framework.common.aop;


import com.cloudm.framework.common.enums.BaseBizEnum;
import com.cloudm.framework.common.enums.BaseErrorEnum;
import com.cloudm.framework.common.ex.BusinessCheckFailException;
import com.cloudm.framework.common.ex.BusinessProcessFailException;
import com.cloudm.framework.common.util.StringUtil;
import com.cloudm.framework.common.web.result.base.BaseResult;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.time.StopWatch;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.Method;

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
            result = exceptionProcessor(invocation,e,BaseErrorEnum.BNS_PRS_ERROR);
        } catch (BusinessCheckFailException e) {
            watch.stop();
            result = exceptionProcessor(invocation,e,BaseErrorEnum.BNS_CHK_ERROR);
        } catch (Exception e) {
            watch.stop();
            result = exceptionProcessor(invocation,e,BaseErrorEnum.UNKNOWN_ERROR);
        }
        return result;
    }

    /**
     * 反射获取返回值对象
     * @param methodInvocation 目标方法
     * @return
     */

    private BaseResult getBaseResult(MethodInvocation methodInvocation) {
        Class<?> returnType = methodInvocation.getMethod().getReturnType();
        BaseResult result = (BaseResult) generator.newInstance(returnType);
        return result;
    }



    /**
     * 异常处理 记录日志并返回result对象
     * @param invocation
     * @param baseErrorEnum  {@link BaseErrorEnum}
     * @param e
     * @return
     */
    private BaseResult exceptionProcessor(MethodInvocation invocation, Throwable e,BaseErrorEnum baseErrorEnum) {
        Object[] args = invocation.getArguments();
        Method method = invocation.getMethod();
        String methodName = method.getDeclaringClass().getName() + "." + method.getName();
        log.error("服务[method=" + methodName + "] params={}" + new Gson().toJson(args) + "异常：", e);
        BaseResult result = getBaseResult(invocation);
        result.setCode(baseErrorEnum.getCode());
        result.setMessage(StringUtil.isNotEmpty(e.getMessage())?e.getMessage():baseErrorEnum.getMessage());
        result.setSuccess(false);
        return result;
    }
}
