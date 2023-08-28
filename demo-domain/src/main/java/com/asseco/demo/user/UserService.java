package com.asseco.demo.user;

import com.asseco.demo.common.Chunk;
import com.asseco.demo.common.FileGenerator;
import com.asseco.demo.user.dto.UserDto;
import com.asseco.demo.user.form.UserCreateForm;
import com.asseco.demo.user.generator.UserProcessor;
import com.asseco.demo.user.search.UserSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asseco.demo.user.generator.UserGenerator.CHUNK_SIZE;
import static com.asseco.demo.user.generator.UserGenerator.USERS_NUMBER;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserValidator userValidator;

    private final UserRepository userRepository;

    private final UserAssembler userAssembler;

    private final FileGenerator fileGenerator;

    private final UserProcessor userProcessor;

    public void addUser(UserCreateForm form) {
        userValidator.validateSave(form);
        var dbForm = userAssembler.toUserDbForm(form);
        userRepository.saveUser(dbForm);
    }

    public List<UserDto> getUsers() {
        var users = userRepository.getUsers();
        return userAssembler.toUserDtoList(users);
    }

    public Chunk<UserDto> findUsers(UserSearchCriteria searchCriteria) {
        var chunk = userRepository.findUsers(searchCriteria);
        var dtoList = userAssembler.toUserDtoList(chunk.getList());
        return new Chunk<>(dtoList, chunk.isLast());
    }

    public ResponseEntity<ByteArrayResource> getUsersFile() {
        var users = getUsers();
        return fileGenerator.generateFile(users);
    }

    public void generateUsers() {
        for (int i = 0; i < USERS_NUMBER / CHUNK_SIZE; i++) {
            userProcessor.processChunk();
        }
    }
}
