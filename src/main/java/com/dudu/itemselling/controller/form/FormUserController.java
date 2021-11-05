package com.dudu.itemselling.controller.form;

import com.dudu.itemselling.domain.User;
import com.dudu.itemselling.dto.SignInDTO;
import com.dudu.itemselling.dto.SignUpDTO;
import com.dudu.itemselling.dto.UserDTO;
import com.dudu.itemselling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/selling")
@RequiredArgsConstructor
public class FormUserController {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @GetMapping("/signin")
    public String signinForm(Model model){
        model.addAttribute("user", new SignInDTO());
        return "user/signin";
    }

    @PostMapping("/signin")
    public String signin(@Validated @ModelAttribute("user") SignInDTO userDTO, BindingResult bindingResult
            , @RequestParam(defaultValue = "/form/items") String redirectURL, RedirectAttributes redirectAttributes, HttpServletRequest request){

        if (bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "user/signin";
        }

        User findUser = userRepository.findByUserId(userDTO.getUserId());

        if(findUser == null){ // 존재하지 않는 아이디일 경우

            bindingResult.reject("notUserId", "존재하지 않는 아이디입니다.");
            return "user/signin";
        } else if(!passwordEncoder.matches(userDTO.getPassword(),findUser.getPassword())){

            bindingResult.reject("notUserPassword","비밀번호가 불일치 합니다.");
            return "user/signin";
        }

        UserDTO signInUser = new UserDTO(findUser.getId(), findUser.getName());

        HttpSession session = request.getSession();
        session.setAttribute("signInUser", signInUser);

        return "redirect:"+redirectURL;
    }

    @GetMapping("/signup")
    public String signupForm(Model model){
        model.addAttribute("user", new SignUpDTO());
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute("user") SignUpDTO userDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "user/signup";
        }
        User findUser = userRepository.findByUserId(userDTO.getUserId());

        if(findUser != null){ // 존재하는 아이디

            bindingResult.reject("overlapUserId", "이미 존재하는 아이디입니다.");
            return "user/signup";
        }
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = new User(null, userDTO.getUserId(), userDTO.getName(),encodedPassword, LocalDate.now(), null);
        Long saveId = userRepository.save(user);
        return "redirect:/selling/signin";

    }



}
