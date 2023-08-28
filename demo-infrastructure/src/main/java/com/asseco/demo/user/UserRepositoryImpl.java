package com.asseco.demo.user;

import com.asseco.demo.common.Chunk;
import com.asseco.demo.user.search.UserSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    private final UserMapper userMapper;

    @Transactional
    @Override
    public void saveUser(UserDbForm form) {
        var entity = userMapper.toUser(form);
        userJpaRepository.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public List<UserData> getUsers() {
        return new ArrayList<>(userJpaRepository.findAllWithContacts());
    }

    @Override
    public Chunk<UserData> findUsers(UserSearchCriteria searchCriteria) {
        var pageRequest = PageRequest.of(searchCriteria.getPageNumber(), searchCriteria.getPageSize());
        var specification = new UserSpecification(searchCriteria);
        var page = userJpaRepository.findAll(specification, pageRequest);
        return new Chunk<>(new ArrayList<>(page.getContent()), !page.hasNext());
    }

    @Override
    @Transactional
    public void saveUsers(Set<UserDbForm> users) {
        var entities = userMapper.toUserSet(users);
        userJpaRepository.saveAll(entities);
    }

}