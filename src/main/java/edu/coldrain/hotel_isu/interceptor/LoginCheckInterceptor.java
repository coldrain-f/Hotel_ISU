package edu.coldrain.hotel_isu.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    private static final String LOGIN_ID = "loginId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("로그인 인증 인터셉터 호출 {}", requestURI);

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(LOGIN_ID) == null) {
            log.info("미인증 사용자 요청");
            response.sendRedirect("/home/login?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }
}
