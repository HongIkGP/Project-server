package GraduateProject.HongIkGP.service;

import GraduateProject.HongIkGP.domain.Member;
import GraduateProject.HongIkGP.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /** 회원 가입 **/
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member);// 중복 회원 검증
        memberRepository.save(member);

        return member.getId();
    }

    /** 중복 회원 검증 **/
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByUserId(member.getUserId());

        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }
}
