package issuetracker.issuetracker.domain.user.login;


import issuetracker.issuetracker.domain.user.login.dto.GithubToken;
import issuetracker.issuetracker.domain.user.login.dto.UserEmailResponse;
import issuetracker.issuetracker.domain.user.login.dto.UserProfileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO : userProfiel과 userEmail 가져오는 코드의 중복이 대부분인데, profile과 email은 요청을 두 번 보내야 하는 부분을 어떻게 해결해야 할 지 고민
@Slf4j
@Service
public class LoginService {

    @Value("${GITHUB_URL}")
    private String url;

    @Value("${GITHUB_CLIENT_ID}")
    private String clientId;

    @Value("${GITHUB_CLIENT_SECRET}")
    private String clientSecret;

    public GithubToken getAccessToken(String code) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json"); //json 형식으로 응답 받음
        headers.setAll(header);

        MultiValueMap<String, String> requestPayloads = new LinkedMultiValueMap<>();
        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("client_id", clientId);
        requestPayload.put("client_secret", clientSecret);
        requestPayload.put("code", code);
        requestPayloads.setAll(requestPayload);

        HttpEntity<?> request = new HttpEntity<>(requestPayloads, headers);
        ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, GithubToken.class); //미리 정의해둔 GithubToken 클래스 형태로 Response Body를 파싱해서 담아서 리턴
        return (GithubToken) response.getBody();
    }


    public UserProfileResponse getUserProfile(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserProfileResponse> response = new RestTemplate().exchange(
                OAuthConst.PROFILE_URL,
                HttpMethod.GET,
                requestEntity,
                UserProfileResponse.class
        );

        return response.getBody();
    }

    // TODO : email 여러개 중 primary만 주는 것이 괜찮을지, orElse로 null을 주는 것이 괜찮을지
    public String getUserEmail(String accessToken) {
        return getUserEmails(accessToken).stream()
                .filter(email -> email.isPrimary())
                .findAny()
                .orElse(null)
                .getEmail();
    }

    private List<UserEmailResponse> getUserEmails(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserEmailResponse[]> response = new RestTemplate().exchange(
                OAuthConst.EMAIL_URL,
                HttpMethod.GET,
                requestEntity,
                UserEmailResponse[].class
        );

        return Arrays.asList(response.getBody());
    }



}
