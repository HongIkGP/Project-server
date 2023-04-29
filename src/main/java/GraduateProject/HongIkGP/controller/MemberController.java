package GraduateProject.HongIkGP.controller;

import GraduateProject.HongIkGP.domain.Member;
import GraduateProject.HongIkGP.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        /** 패스워드 인코딩**/
        String rawPassword = form.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        /** 회원 생성 **/
        Member member = new Member();
        member.setUserId(form.getUserId());
        member.setName(form.getName());
        member.setRole("USER");
        member.setPassword(encPassword);
        memberService.join(member);

        return "redirect:/";
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
