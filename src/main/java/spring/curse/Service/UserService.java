package spring.curse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.curse.Models.Roles;
import spring.curse.Models.User;
import spring.curse.Repos.UserRepos;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepos userRepos;

    public UserService(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepos.findByUsername(username);
    }


    public boolean addUser(User user) {
        User userFromDB = userRepos.findByUsername(user.getUsername());

        System.out.println("Enter add");
        if (userFromDB != null) {
            return false;
        }

        user.setActive(true);
        user.setCountElectricity((long)0);
        user.setCountHeating((long)0);
        user.setCountTrash((long)0);
        user.setCountWaterCold((long)0);
        user.setCountWaterHot((long)0);
        user.setCountWaterTrash((long)0);
        user.setRoles(Collections.singleton(Roles.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepos.save(user);
        return true;
    }



    public List<User> findAll() {
        return userRepos.findAll();
    }

    public User getById(Long id) {
        return userRepos.getById(id);
    }

    public void saveUser(String username, Map<String, String> form, User user) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Roles.values()).map(Roles::name).collect(Collectors.toSet());

        user.getRoles().clear();
        for (String key : form.keySet()) {
            if(roles.contains(key)){
                user.getRoles().add(Roles.valueOf(key));
            }

        }
        userRepos.save(user);
    }

}
