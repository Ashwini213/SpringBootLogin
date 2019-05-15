package com.bridgelabz.fundoonoteapp.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonoteapp.user.model.Lable;

@Repository
public interface LableRepository extends JpaRepository<Lable, Integer> {

	Optional<Lable> findByUserIdAndLableId(int varifiedUserId, int lableId);

	void deleteByUserIdAndLableId(int varifiedUserId, int lableId);

	List<Lable> findByUserId(int varifiedUserId);

}
