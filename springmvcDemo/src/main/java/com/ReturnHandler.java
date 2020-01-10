package com;

import com.alibaba.fastjson.JSON;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ReturnHandler implements HandlerMethodReturnValueHandler {

  @Override
  public boolean supportsReturnType(MethodParameter methodParameter) {
    return methodParameter.getParameterType() == String.class;
  }

  @Override
  public void handleReturnValue(Object o, MethodParameter methodParameter,
      ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest)
      throws Exception {
    modelAndViewContainer.setRequestHandled(true);
    HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
    response.setContentType("text/plain;charset=UTF-8");
    response.getWriter().append(JSON.toJSONString("ZProcess is handler ok")).flush();
  }
}
