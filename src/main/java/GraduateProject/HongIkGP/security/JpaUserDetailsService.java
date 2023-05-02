package GraduateProject.HongIkGP.security;

import GraduateProject.HongIkGP.domain.Member;
import GraduateProject.HongIkGP.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        List<Member> member = memberRepository.findByEmail(email);

        return new CustomUserDetails(member.get(0));
    }

}
