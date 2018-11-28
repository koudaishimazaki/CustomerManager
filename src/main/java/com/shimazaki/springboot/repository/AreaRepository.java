package com.shimazaki.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shimazaki.springboot.entity.Area;

public interface AreaRepository extends JpaRepository<Area, Long>, JpaSpecificationExecutor<Area> {

}
