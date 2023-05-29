package issuetracker.issuetracker.domain.user.login;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${JWT_SECRET_KEY}")
    private String secret; // 시크릿 키를 설정

    public String createToken(String id, String name, String email, String profileUrl) {
        return Jwts.builder()
                .claim("loginId", id) // 아이디를 설정-> db에 저장한 후 db의 pk를 가져오는 식으로 , 혹은 claim 없이
                .claim("name", name) // 이름을 설정
                .claim("email", email) // 이메일을 설정
                .claim("profileUrl", profileUrl) // 프로필 URL을 설정
                .setIssuedAt(new Date()) // 토큰의 발행일을 설정
                .setHeaderParam("typ", "JWT")
                .setExpiration(new Date((new Date()).getTime() + 3600000)) // 토큰의 만료일을 설정 : 현재 1시간
                .signWith(SignatureAlgorithm.HS256, secret) // HS256 알고리즘과 시크릿 키를 사용하여 서명
                .compact(); // 토큰을 생성하세요.
    }
}
