package com.brianxia.sentineldemo.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/5 16:36
 */
@Component
public class ExceptionHanlder implements BlockExceptionHandler {


    /**
     * FlowException  //限流异常
     * DegradeException  //降级异常
     * ParamFlowException //参数限流异常
     * SystemBlockException //系统负载异常
     * AuthorityException //授权异常
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws Exception
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        if (e instanceof FlowException) {
            PrintWriter writer = httpServletResponse.getWriter();
            writer.write("rate limit...");
            writer.flush();
            writer.close();
        }
    }
}
