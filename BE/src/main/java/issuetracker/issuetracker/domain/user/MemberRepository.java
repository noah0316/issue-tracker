package issuetracker.issuetracker.domain.user;

import issuetracker.issuetracker.domain.issue.Assignee;
import issuetracker.issuetracker.domain.user.dto.AuthorDTO;
import issuetracker.issuetracker.domain.user.login.dto.UserProfileResponse;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    @Query("SELECT " +
            "member.member_id AS id, " +
            "member.member_name AS name, " +
            "member.profile_url " +
            "FROM member")
    List<AuthorDTO> getMemberFilter();


    @Query("SELECT member.member_id AS id, member.member_name AS name," +
            " member.profile_url AS profile_url FROM member WHERE member.member_id = :userId")
    Optional<AuthorDTO> findByAuthorDTO(@Param("userId") Long userId);

}
