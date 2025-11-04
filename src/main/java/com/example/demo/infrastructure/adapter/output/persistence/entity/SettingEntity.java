package com.example.demo.infrastructure.adapter.output.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.example.demo.domain.model.vo.setting.SettingCode;
import com.example.demo.domain.model.vo.setting.SettingDescription;
import com.example.demo.domain.model.vo.setting.SettingId;

@Table("settings")
public record SettingEntity  (
                @Id SettingId id,
                @Column("code") SettingCode code,
                @Column("description") SettingDescription description) {

}
