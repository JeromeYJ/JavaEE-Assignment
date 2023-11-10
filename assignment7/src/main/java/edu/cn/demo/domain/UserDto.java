package edu.cn.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto extends User{

    // 一个user可以对应多个角色
    private List<Role> roles = new java.util.ArrayList<>();
}