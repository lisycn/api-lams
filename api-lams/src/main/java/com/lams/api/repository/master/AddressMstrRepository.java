package com.lams.api.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.master.AddressMstr;

public interface AddressMstrRepository extends JpaRepository<AddressMstr, Long> {

	public AddressMstr findByIsActive(Boolean isActive);

	@Modifying
	@Query("update AddressMstr addr set addr.isActive = false where addr.isActive = true and addr.user.id =:userId")
	public int inactive(@Param("userId") Long userId);

}
