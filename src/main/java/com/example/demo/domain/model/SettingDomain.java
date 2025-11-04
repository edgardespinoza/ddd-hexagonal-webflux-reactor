package com.example.demo.domain.model;

import com.example.demo.domain.model.vo.setting.SettingCode;
import com.example.demo.domain.model.vo.setting.SettingDescription;
import com.example.demo.domain.model.vo.setting.SettingId;

public record SettingDomain(SettingId id, SettingCode code, SettingDescription description) {

}
