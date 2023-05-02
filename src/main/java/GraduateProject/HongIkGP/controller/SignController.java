package GraduateProject.HongIkGP.controller;

import GraduateProject.HongIkGP.domain.dto.SignRequest;
import GraduateProject.HongIkGP.domain.dto.SignResponse;
import GraduateProject.HongIkGP.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;


    @PostMapping(value = "/login")
    public ResponseEntity<SignResponse> signin(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(signService.login(request), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Boolean> signup(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(signService.register(request), HttpStatus.OK);
    }


}
