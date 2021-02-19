package de.userservice.service;

import de.userservice.VO.Department;
import de.userservice.VO.ResponseTemplateVO;
import de.userservice.entity.User;
import de.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.saveUser(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department = restTemplate.getForObject("http://localhost:9001/departments/" + user.getDepartmentId(),
                Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
