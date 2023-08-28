package com.asseco.demo.user;

import com.asseco.demo.common.Chunk;
import com.asseco.demo.user.search.UserSearchCriteria;

import java.util.List;
import java.util.Set;

public interface UserRepository {

    void saveUser(UserDbForm form);

    boolean existsById(Long id);

    List<UserData> getUsers();

    Chunk<UserData> findUsers(UserSearchCriteria searchCriteria);

    void saveUsers(Set<UserDbForm> users);
}
