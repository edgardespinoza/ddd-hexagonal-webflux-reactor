package com.example.demo.domain.model;

import com.example.demo.domain.model.vo.user.UserEmail;
import com.example.demo.domain.model.vo.user.UserId;
import com.example.demo.domain.model.vo.user.UserName;

public record UserDomain(
                UserId id,
                UserName name,
                UserEmail email) {
}