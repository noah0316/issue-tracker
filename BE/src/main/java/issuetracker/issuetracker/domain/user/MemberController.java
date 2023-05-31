package issuetracker.issuetracker.domain.user;

import issuetracker.issuetracker.domain.user.dto.AssigneeDTO;
import issuetracker.issuetracker.domain.user.dto.AuthorDTO;
import issuetracker.issuetracker.domain.user.dto.AuthorFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberRepository repository;

    @GetMapping("/users")
    public List<AuthorDTO> getAssignees() {
        return repository.getMemberFilter();
    }

}
