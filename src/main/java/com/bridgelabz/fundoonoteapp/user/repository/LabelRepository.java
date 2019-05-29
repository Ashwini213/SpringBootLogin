package com.bridgelabz.fundoonoteapp.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonoteapp.user.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {

	Optional<Label> findByUserIdAndLableId(int verifiedUserId, int lableId);

	void deleteByUserIdAndLableId(int verifiedUserId, int lableId);

	List<Label> findByUserId(int verifiedUserId);

}
