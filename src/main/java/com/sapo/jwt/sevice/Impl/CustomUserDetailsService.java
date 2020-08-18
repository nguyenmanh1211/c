package com.sapo.jwt.sevice.Impl;

import com.sapo.jwt.constant.SystemConstant;
import com.sapo.jwt.model.dto.MyUser;
import com.sapo.jwt.model.entity.RoleEntity;
import com.sapo.jwt.model.entity.UserEntity;
import com.sapo.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userCustomService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        //Lấy role từ bảng role
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : userEntity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode()));
        }

        MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(),
                true, true, true, true, authorities);
        //Custom lại User để lất dc FullName
        myUser.setId(userEntity.getId());
        return myUser;
    }
}
