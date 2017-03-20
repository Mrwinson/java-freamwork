package com.cloudm.framework.common.aop;

import com.cloudm.framework.common.enums.BaseErrorEnum;
import com.cloudm.framework.common.web.result.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @description: 主要针对于controller 方法验证。
 * 使用方法： 参数中添加@Vaild注解与BindingResult。然后自动验证。
 * 如果验证失败：然后json字符串 {result:false,message:'java bean中验证message'}
 * @author: Courser
 * @date: 2017/3/20
 * @version: V1.0
 */
public class BindingResultAop {
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        BindingResult bindingResult = null;
        for(Object arg:joinPoint.getArgs()){
            if(arg instanceof BindingResult){
                bindingResult = (BindingResult) arg;
            }
        }

        if(bindingResult != null){
            List<ObjectError> errors = bindingResult.getAllErrors();
            if(errors.size()>0){
                StringBuilder msg = new StringBuilder();
                for(ObjectError error :errors){
                    msg.append(error.getDefaultMessage());
                    msg.append("\n");
                }
               return Result.wrapErrorResult(BaseErrorEnum.VALIDATE_ERROR.getCode(),msg.toString());
            }
        }
        return joinPoint.proceed();
    }
}
