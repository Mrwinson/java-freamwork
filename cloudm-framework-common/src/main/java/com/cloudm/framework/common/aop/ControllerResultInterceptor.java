package com.cloudm.framework.common.aop;


import com.cloudm.framework.common.ex.BusinessCheckFailException;
import com.cloudm.framework.common.ex.BusinessProcessFailException;
import com.cloudm.framework.common.util.StringUtil;
import com.cloudm.framework.common.web.result.base.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.time.StopWatch;
import org.objenesis.ObjenesisStd;

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
            result = getBaseResult(invocation);
            result.setSuccess(Boolean.FALSE);
            result.setMessage(StringUtil.isEmpty(e.getMessage())?"系统未知错误！":e.getMessage());
            result.setCode(StringUtil.isEmpty(e.getErrorCode())?"-100":e.getErrorCode());
            handleBusinessException(e);
        } catch (BusinessCheckFailException e) {
            result = getBaseResult(invocation);
            result.setSuccess(Boolean.FALSE);
            result.setMessage(e.getMessage());
            result.setCode(e.getErrorCode());
            handleBusinessException(e);
        } catch (Exception e) {
            result = getBaseResult(invocation);
            setSystemError(result);
            handleThrowable(e);
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
     * 系统错误封装，这个是未知的
     * @param result
     */
    private void setSystemError(BaseResult result) {
        log.error("系统异常:code={}, message={}", result.getCode(), result.getMessage());
        result.setSuccess(false);
        result.setCode("-1");
        result.setMessage("系统繁忙，请稍后重试");
    }

    /**
     * 系统错误日志
     * @param e
     */
    private void handleThrowable(Throwable e) {
        log.error("系统出错:", e);
    }

    /**
     * 业务错误日志
     * @param e
     */
    private void handleBusinessException(RuntimeException e) {
        log.error("业务出错:", e);
    }
}
