package edu.coldrain.hotel_isu.controller.user;

import edu.coldrain.hotel_isu.controller.user.form.UserLoginForm;
import edu.coldrain.hotel_isu.controller.user.form.UserRegisterForm;
import edu.coldrain.hotel_isu.domain.user.User;
import edu.coldrain.hotel_isu.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/home/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(
            @SessionAttribute(name = "loginId", required = false) String loginId,
            Model model) {
        log.info("UserController.loginForm()");

        // th:field 에러발생 방지로 빈 객체를 담아놓는다.
        model.addAttribute("userLoginForm", new UserLoginForm());

        if (loginId == null) {
            log.info("home/user-login");
            return "home/user-login";
        }

        log.info("redirect:/hotel/admin/room");
        return "redirect:/hotel/admin/room";
    }

    @PostMapping("/login")
    public String login(
            @Validated @ModelAttribute("userLoginForm") UserLoginForm form, BindingResult bindingResult,
            @RequestParam(defaultValue = "/hotel/admin/room") String redirectURL, HttpServletRequest request) {

        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "home/user-login";
        }

        //아이디를 회원 조회
        final User loginUser = userService.login(form.getLoginId(), form.getPassword());
        if (loginUser == null) { //로그인 실패
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            log.info("아이디 또는 비밀번호가 맞지 않습니다.");
            return "home/user-login";
        }

        //로그인 성공 ( 세션에 로그인 사용자 정보를 담는다 )
        final HttpSession session = request.getSession(true);
        session.setAttribute("loginId", loginUser.getLoginId());

        log.info("요청 URL: {}", "redirect:" + redirectURL);

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        final HttpSession session = request.getSession(false);
        if (session != null) {
            log.info("로그인 인증 세션 제거");
            session.invalidate();
        }

        return "redirect:/home/login";
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        log.info("UserController.joinForm()");

        model.addAttribute("userRegisterForm", new UserRegisterForm());

        return "home/user-join";
    }

    @PostMapping("/join")
    public String join(
            @Validated @ModelAttribute("userRegisterForm") UserRegisterForm form, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        // Bean Validation 실패시
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            // 다시 사용자 회원가입 폼으로 이동한다.
            return "home/user-join";
        }

        //폼에서 넘어온 바인딩 값 확인
        log.info("UserRegisterForm = {}", form);

        final User newUser = new User(form.getName(), form.getLoginId(), form.getPassword(), form.getMobile());

        boolean success = userService.register(newUser);
        if (success) {
            log.info("회원 등록에 성공했습니다 : {}", newUser);
            redirectAttributes.addFlashAttribute("userRegistrationState", "success");
        } else {
            log.info("회원 등록에 실패했습니다 : {}", newUser);
        }

        return "redirect:/home/login";
    }
}