package issuetracker.issuetracker.domain.user;

import issuetracker.issuetracker.domain.user.dto.AuthorDTO;
import issuetracker.issuetracker.domain.user.login.dto.UserProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {

    final private MemberRepository userRepository;

    // TODO : Optional 예외처리 할 것
    public AuthorDTO findByAuthor(AggregateReference<Member, @NotNull Long> userId) {
        return userRepository.findByAuthorDTO(userId.getId()).get();
    }

    public void checkLoginMember(UserProfileResponse userProfile) {
        Optional<Member> findMember = userRepository.findById(userProfile.getId());
        if (findMember.isEmpty()) {
            Member member = new Member(userProfile.getId(), userProfile.getName(), userProfile.getAvatarUrl());
            userRepository.save(member);
        }
    }

}
