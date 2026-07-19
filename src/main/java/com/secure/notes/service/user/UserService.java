package com.secure.notes.service.user;

import com.secure.notes.dto.UserDTO;

import java.util.List;

public interface UserService {
    void updateUserRole(Long userId, String roleName);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);
}
