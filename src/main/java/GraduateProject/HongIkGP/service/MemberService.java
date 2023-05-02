package GraduateProject.HongIkGP.service;

import GraduateProject.HongIkGP.domain.Member;
import GraduateProject.HongIkGP.domain.dto.SignResponse;
import GraduateProject.HongIkGP.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    /** 회원 조회 **/
    public SignResponse getMember(String email) throws Exception {

        List<Member> member = memberRepository.findByEmail(email);

        if (member.isEmpty()) {
            throw new BadCredentialsException("계정을 찾을 수 없습니다.");
        }

        return new SignResponse(member.get(0));
    }
}
