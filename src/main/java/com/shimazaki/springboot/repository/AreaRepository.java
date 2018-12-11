package com.shimazaki.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shimazaki.springboot.entity.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

	// 郵便番号から地域を検索
	public Area findByPostalCode(String postalCode);

	// 都道府県を重複無しで取得
	@Query(value = "SELECT DISTINCT state FROM area", nativeQuery = true)
	public List<String> getStates();

}
