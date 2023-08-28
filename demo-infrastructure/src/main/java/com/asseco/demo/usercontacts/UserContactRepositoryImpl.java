package com.asseco.demo.usercontacts;

import com.asseco.demo.user.UserJpaRepository;
import com.asseco.demo.usercontact.UserContactDbForm;
import com.asseco.demo.usercontact.UserContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserContactRepositoryImpl implements UserContactRepository {

    private final UserContactJpaRepository userContactJpaRepository;
    private final UserJpaRepository userJpaRepository;

    private final UserContactMapper userContactMapper;

    @Transactional
    @Override
    public void saveUserContacts(Long userId, Set<UserContactDbForm> forms) {
        userJpaRepository.findById(userId)
                .map(user -> userContactMapper.toUserContacts(user, forms))
                .ifPresent(userContactJpaRepository::saveAll);
    }
}