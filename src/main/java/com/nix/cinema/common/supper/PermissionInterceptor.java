package com.nix.cinema.common.supper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nix.cinema.common.PermissionHandler;
import com.nix.cinema.common.annotation.Clear;
import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.model.RoleInterfaceModel;
import com.nix.cinema.model.RoleModel;
import com.nix.cinema.model.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Kiss
 * @date 2018/05/01 20:07
 * 权限管理
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor,PermissionHandler<RoleInterfaceModel,Method> {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserModel user = (UserModel) request.getSession().getAttribute(UserCache.USER_SESSION_KEY);
        UserCache.putUser(user);
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Clear clear = method.getAnnotation(Clear.class);
            //如果method没有标识为清除权限校验
            if (clear == null) {
                if (user != null) {
                    RoleModel role = user.getRole();
                    List<RoleInterfaceModel> roleInterfaces = role.getRoleInterfaces();
                    for (RoleInterfaceModel roleInterface:roleInterfaces) {
                        if (isHavePermission(roleInterface,method)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isHavePermission(RoleInterfaceModel roleInterface, Method method) {
        if (!roleInterface.getEnabled()) {
            return false;
        }
        RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
        RequestMapping controllerRequestMapping = method.getDeclaringClass().getAnnotation(RequestMapping.class);
        String[] controllerUrls = controllerRequestMapping != null ? controllerRequestMapping.value() : null;
        String[] methodUrls = methodRequestMapping != null ? methodRequestMapping.value() : null;
        for (int i = 0;methodUrls != null && i < methodUrls.length;i ++) {
            String url = "";
            if (controllerUrls != null) {
                for (int j = 0;j < controllerUrls.length; j ++) {
                    url = controllerUrls[j] + methodUrls[i];
                    if (url.matches(roleInterface.getUrl().replaceAll("\\*\\*","\\.\\*"))) {
                        return true;
                    }
                }
            } else {
                url = methodUrls[i];
                if (url.matches(roleInterface.getUrl().replaceAll("\\*\\*","\\.\\*"))) {
                    return true;
                }
            }
            System.out.println(url);
        }
        return false;
    }
}
