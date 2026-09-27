package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter ) {
        return parameter.hasParameterAnnotation(AuthorizeUser.class)
        && parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        User user = new User();
        user.setUser(webRequest.getParameter("user"));
        user.setPassword(webRequest.getParameter("password"));

        if (parameter.hasParameterAnnotation(Valid.class)) {
            WebDataBinder binder = binderFactory.createBinder(webRequest, user, "user");
            binder.validate();
            BindingResult bindingResult = binder.getBindingResult();

            if (bindingResult.hasErrors()) {
                throw new MethodArgumentNotValidException(parameter, bindingResult);
            }
        }
        return user;
    }
}
