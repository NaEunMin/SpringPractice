package com.example.study.domain.user.service;

import com.example.study.domain.user.dto.UserReq;
import com.example.study.domain.user.dto.UserRes;
import com.example.study.domain.user.entity.UserEntity;
import com.example.study.domain.user.entity.UserRoleType;
import com.example.study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void createUser(UserReq userReq) {
        String username = userReq.getUsername();
        String password = userReq.getPassword();
        String nickname = userReq.getNickname();

        if(userRepository.existsByUsername(username)){
            return;
        }
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setNickname(nickname);
        user.setRole(UserRoleType.USER);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserRes readUser(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow();

        UserRes userRes = UserRes.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .role(user.getRole().toString())
                .build();
        return userRes;
    }

    @Transactional(readOnly = true)
    public List<UserRes> readAllUsers(){
        List<UserEntity> list = userRepository.findAll();

        List<UserRes> userResList = new ArrayList<>();
        for(UserEntity user : list){
            UserRes userRes = UserRes.builder()
                    .username(user.getUsername())
                    .nickname(user.getNickname())
                    .role(user.getRole().toString())
                    .build();
            userResList.add(userRes);
        }
        return userResList;
    }


    //유저 로그인(로그인 같은 경우 읽기지만, 시큐리티 형식으로 맞추어야 한다.)
    //시큐리티 전용 로그인 메소드 Override
    //로그인용 read 메소드
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }

    @Transactional
    public void updateUser(UserReq userReq, String username) {
        //입력된 정보만 변경되게 하는 패턴

        //기존 유저 정보 읽기
        UserEntity user = userRepository.findByUsername(username).orElseThrow();


        //입력받은 비밀번호가 null이 아니고 입력받은 비밀번호가 비어있지 않다면
        //새 비밀번호를 암호화하여 저장한다.
        if(userReq.getPassword() != null && !userReq.getPassword().isEmpty()){
            user.setPassword(bCryptPasswordEncoder.encode(userReq.getPassword()));
        }
        //닉네임도 같은 원리
        if(userReq.getNickname() != null && !userReq.getNickname().isEmpty()){
            user.setNickname(userReq.getNickname());
        }

        userRepository.save(user);
    }

    //유저 한명 삭제
    //실제 서비스하면 user의 id로 삭제할 것 같다고 생각함.
    @Transactional
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    //유저 접근 권한 체크
    //매개 변수 username은 다른사람 유저네임
    public Boolean isAccess(String username){

        //현재 로그인되어 있는 유저의 username
        String sessionUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        //현재 로그인외더 있는 유저의 role
        String sessionRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority();

        //수직적으로 admin이면 무조건 접근 가능
        if("ROLE_ADMIN".equals(sessionRole)){
            return true;
        }

        //수평적으로 특정 행위를 수행할 username에 대해 sessionName과 같은지 검증
        if(username.equals(sessionUsername)){
            return true;
        }

        //나머지 다 불가
        return false;
    }
}
