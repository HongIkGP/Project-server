package GraduateProject.HongIkGP.controller;

import GraduateProject.HongIkGP.domain.dto.SignResponse;
import GraduateProject.HongIkGP.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/user/get")
    public ResponseEntity<SignResponse> getUser(@RequestBody Map<String, String> data) throws Exception {
        return new ResponseEntity<>(memberService.getMember(data.get("email")), HttpStatus.OK);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<SignResponse> getUserForAdmin(@RequestParam Map<String, String> data) throws Exception {
        return new ResponseEntity<>(memberService.getMember(data.get("email")), HttpStatus.OK);
    }
}
