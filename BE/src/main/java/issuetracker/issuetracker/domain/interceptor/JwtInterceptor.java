package issuetracker.issuetracker.domain.interceptor;

import issuetracker.issuetracker.domain.user.login.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // JWT 토큰을 가져오는 로직 구현
        String token = request.getHeader("Authorization");

        // 토큰 유효성 검사 로직 구현
        if (isValidToken(token)) {
            return true;
        } else {
            // 토큰이 유효하지 않으면 요청 중단
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    private boolean isValidToken(String token) {
        return jwtUtil.validateToken(token);
    }

}
