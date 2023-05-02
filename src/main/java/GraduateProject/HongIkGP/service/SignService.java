package GraduateProject.HongIkGP.service;

import GraduateProject.HongIkGP.domain.Authority;
import GraduateProject.HongIkGP.domain.Member;
import GraduateProject.HongIkGP.domain.dto.SignRequest;
import GraduateProject.HongIkGP.domain.dto.SignResponse;
import GraduateProject.HongIkGP.repository.MemberRepository;
import GraduateProject.HongIkGP.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /** 회원 가입 **/
    public boolean register(SignRequest request) throws Exception {

        try {
            Member member = Member.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .build();

            member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

            // 아이디 중복 검사
            List<Member> findMember = memberRepository.findByEmail(member.getEmail());
            if (!findMember.isEmpty()) {
                throw new IllegalStateException("이미 존재하는 아이디입니다.");
            }
            memberRepository.save(member);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    /** 로그인 **/
    public SignResponse login(SignRequest request) throws Exception {

        List<Member> member = memberRepository.findByEmail(request.getEmail());

        if (member.isEmpty()) {
            throw new BadCredentialsException("존재하지 않는 이메일(아이디)입니다.");
        }

        if (!passwordEncoder.matches(request.getPassword(), member.get(0).getPassword())) {
            throw new BadCredentialsException("잘못된 비밀번호입니다.");
        }

        return SignResponse.builder()
                .id(member.get(0).getId())
                .email(member.get(0).getEmail())
                .name(member.get(0).getName())
                .roles(member.get(0).getRoles())
                .token(jwtProvider.createToken(member.get(0).getEmail(), member.get(0).getRoles()))
                .build();
    }

}
