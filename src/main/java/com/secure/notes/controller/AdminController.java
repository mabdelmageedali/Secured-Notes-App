package com.secure.notes.controller;

import com.secure.notes.dto.ApiResponse;
import com.secure.notes.dto.UserDTO;
import com.secure.notes.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getusers")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(userService.getAllUsers()));
    }

    @PutMapping("/update-role")
    public ResponseEntity<ApiResponse<String>> updateRole(@RequestParam Long userId,
                                                          @RequestParam String roleName){
        userService.updateUserRole(userId, roleName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Role updated successfully", null));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUser(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("User Founded" , userService.getUserById(id)));
    }
}
