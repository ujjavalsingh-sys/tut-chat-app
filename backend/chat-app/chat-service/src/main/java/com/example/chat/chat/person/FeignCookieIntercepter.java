package com.example.chat.chat.person;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignCookieIntercepter implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate requestTemplate) {
    ServletRequestAttributes requestAttributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (requestAttributes != null) {
      String cookie = requestAttributes.getRequest().getHeader("Cookie");
      if (cookie != null) {
        requestTemplate.header("Cookie", cookie);
      }
    }
  }
}
