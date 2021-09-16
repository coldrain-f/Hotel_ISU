package edu.coldrain.hotel_isu.service;

import edu.coldrain.hotel_isu.domain.user.User;
import edu.coldrain.hotel_isu.exception.DuplicateUserException;
import edu.coldrain.hotel_isu.exception.UserNotFoundException;
import edu.coldrain.hotel_isu.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    /**
     * @return List<User> 모든 사용자의 리스트
     */
    public List<User> findByAll() {
        log.info("UserService.findByAll()");

        return userMapper.findByAll();
    }

    /**
     * @param user 사용자
     * @return boolean 사용자의 데이터베이스 등록 성공여부 (true, false)
     * 회원가입 폼에서 넘어온 아이디가 데이터베이스에 이미 존재하는 회원인지 체크하고
     * 존재하지 않는 회원이라면 회원을 저장, 존재하는 회원이라면 회원을 저장하지 않고, 에외를 발생시킨다.
     */
    public boolean register(User user) {
        log.info("UserService.register()");
        final User findUser = userMapper.findByLoginId(user.getLoginId());
        if (findUser != null) {
            try {
                throw new DuplicateUserException("This user already exists in the database.");
            } catch (DuplicateUserException exception) {
                exception.printStackTrace();
                return false;
            }
        }

        return userMapper.save(user);
    }

    /**
     * @param loginId 로그인 폼에서 사용자가 입력하고 전송한 로그인 아이디
     * @param password 로그인 폼에서 사용자가 입력하고 전송한 비밀번호
     * @return User null 이면 로그인 실패
     * 사용자가 로그인 폼에서 입력한 로그인 아이디가 데이터베이스에 존재하지 않는 회원이라면
     * UserNotFoundException 을 발생시키고 null 을 반환한다.
     * 데이터베이스에 존재하는 회원이라면 존재하는 회원의 비밀번호와 사용자가 입력해서 전송한 비밀번호와
     * 일치한지 비교하고 일치한다면 일치하는 사용자를 반환, 일치하지 않는다면 null 을 반환한다.
     */
    public User login(String loginId, String password) {
        log.info("UserService.login()");
        final User findUser = userMapper.findByLoginId(loginId);
        if (findUser == null) {
            try {
                throw new UserNotFoundException("데이터베이스에 존재하지 않는 회원입니다.");
            } catch (UserNotFoundException exception) {
                log.warn(exception.getMessage());
                return null;
            }
        }

        if (findUser.getPassword().equals(password)) {
            return findUser;
        }

        return null;
    }

}