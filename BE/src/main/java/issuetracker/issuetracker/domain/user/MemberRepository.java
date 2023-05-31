package issuetracker.issuetracker.domain.user;

import issuetracker.issuetracker.domain.user.dto.AuthorDTO;
import issuetracker.issuetracker.domain.user.login.dto.UserProfileResponse;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    @Query("SELECT " +
            "member.member_id AS id, " +
            "member.member_name AS name, " +
            "member.profile_url " +
            "FROM member " +
            "WHERE " +
            "member.member_id IN (SELECT issue.author FROM issue)")
    List<AuthorDTO> getMemberFilter();


    @Query("SELECT member.member_id AS id, member.member_name AS name," +
            " member.profile_url AS profile_url FROM member WHERE member.member_id = :userId")
    Optional<AuthorDTO> findByAuthorDTO(@Param("userId") Long userId);

//    @Modifying
    @Query("INSERT INTO member (member_id, member_name, profile_url) VALUES (:#{#loginDTO.memberId}, :#{#loginDTO.memberName}, :#{#loginDTO.profileUrl})")
    void joinMember(@Param("loginDTO") UserProfileResponse loginDTO);
}
