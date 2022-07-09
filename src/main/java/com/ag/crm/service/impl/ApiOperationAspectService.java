package com.ag.crm.service.impl;

import com.ag.crm.dao.LogMapper;
import com.ag.crm.domain.Log;
import com.ag.crm.domain.User;
import com.ag.crm.utils.UserThreadLocal;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description: 日志切面、重复请求验证 服务类
 * @Date 2021/9/10
 */
@Aspect
@Component
public class ApiOperationAspectService {

    private static Logger LOGGER = LoggerFactory.getLogger(ApiOperationAspectService.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogMapper logMapper;
    private User user;
    private long start;
    private Log sysLog;

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 配置切入点，在注解的位置切入代码
     */
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void apiOperationPointcut(){}

    /* 切面 配置通知，各注解作用
     * @Before:前置通知，在方法执行之前执行
     * @After:后置通知，在方法执行之后执行
     * @AfterRunning:返回通知，在方法返回结果之后执行，用这个注解参数会随方法改变，例新增一个实体，参数是一个id为null的，用这个注解后就会赋上真实的id
     * @AfterThrowing:异常通知，在方法抛出异常之后执行
     * @Around：环绕通知，围绕着方法执行
     *  */
    @Before("apiOperationPointcut()")
    public void beforeAdvice(JoinPoint joinPoint){
        start = System.currentTimeMillis();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //操作简述
        String describe="";
        //操作类型
        String operationType = "";
        //是否涉及敏感信息
        user = UserThreadLocal.get();
        ApiOperation log = method.getAnnotation(ApiOperation.class);
        if (log != null) {
            describe = log.value()!=null? log.value():"未知";
            operationType = log.notes()!= null?log.notes():"未知";
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        //参数
        String params = "";
        //请求的参数
        Object[] args = joinPoint.getArgs();
        params = getParamsToJSONStr(args,true);

        String currentDateTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //创建sysLog对象
        sysLog = new Log();
        sysLog.setUrl(request.getRequestURI() ==null ? "" :request.getRequestURI());
        sysLog.setOperationMethod(className + "." + methodName);
        sysLog.setOperationDesc(describe);
        sysLog.setOperationType(operationType);
        sysLog.setParameter(params);
        sysLog.setIp(getIpAddress(request));
        sysLog.setOperationTime(currentDateTime);
    }

    /**
     * 获取请求入参
     * @param args 参数数组
     * @param assignConvertJsonException 是否在参数转换json失败时将失败异常赋值给返回变量
     * @return
     */
    private String getParamsToJSONStr(Object[] args,boolean assignConvertJsonException){
        String params = "";
        int argsLength = args.length;
        Object argumentObj=null;
        if(argsLength==1){
            argumentObj = args[0];
            if (argumentObj instanceof ServletRequest || argumentObj instanceof ServletResponse || argumentObj instanceof MultipartFile || argumentObj instanceof MultipartFile[]) {
                argumentObj = null;
            }
        }
        if(argsLength > 1){
            Object[] arguments  = new Object[argsLength];
            for (int i = 0; i < argsLength; i++) {
                if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile || args[i] instanceof MultipartFile[]) {
                    //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                    //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                    continue;
                }
                arguments[i] = args[i];
            }
            argumentObj = arguments;
        }
        //将参数转换成json
        if (argumentObj != null) {
            try {
                params = JSONObject.toJSONString(argumentObj);
            } catch (Exception e) {
                if(assignConvertJsonException){
                    params = "入参转换至JSON异常:"+e.toString();
                }
            }
        }
        return params;
    }

    /**
     * AfterRunning: 返回通知
     * 如果这里是@After在目标切入点的方法抛出异常时执行到@AfterThrowing方法后，也会执行到这里
     */
    @AfterReturning("apiOperationPointcut()")
    public void afterAdvice(){
        Long timeConsuming = System.currentTimeMillis() - start;
        if(sysLog!=null){
            sysLog.setTimeConsuming(timeConsuming.intValue());
            sysLog.setLogType((byte)1);
            sysLog.setErrorLogMsg("");
            //保存日志
            saveSysLog(sysLog);
        }
    }

    /**
     * AfterThrowing:异常通知
     * @param e
     */
    @AfterThrowing(value = "apiOperationPointcut()",throwing = "e")
    public void errorAdvice(Throwable e){
        Long timeConsuming = System.currentTimeMillis() - start;
        if(sysLog!=null){
            sysLog.setTimeConsuming(timeConsuming.intValue());
            sysLog.setLogType((byte)2);
            //这里用的e.toString()获取到异常的异常类型和异常详细消息，而e.getMessage()只会获取到异常的消息字符串，如:
            //java.lang.NullPointerException 或 java.lang.ArithmeticException: / by zero   这是e.toString()获取到的
            //null  或 / by zero  这是e.getMessage()获取到的
            String exToString = e.toString();
            int limitOfLength = 1000;
            if(exToString.length()>limitOfLength){
                exToString = exToString.substring(0,limitOfLength);
            }
            sysLog.setErrorLogMsg(exToString);
            //保存日志
            saveSysLog(sysLog);
        }
    }

    private void saveSysLog(Log sysLog){
//        sysLog.setCreateMan(user.getUserName());
//        LOGGER.info("【操作记录】用户:{},在:{},操作了方法:{},耗时:{}ms",user.getUserName()+"("+user.getId()+")", sysLog.getOperationTime(),sysLog.getOperationMethod(),sysLog.getTimeConsuming());
        logMapper.insert(sysLog);
    }
}
