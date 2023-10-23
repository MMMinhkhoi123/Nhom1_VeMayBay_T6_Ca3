package com.flight.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.entity.customer;
import com.flight.entity.parent_einvoice;

import jakarta.transaction.Transactional;

public interface parent_repo extends JpaRepository<parent_einvoice, Long> {
	
	@Modifying
	@Transactional
	@Query(value = "update parent_bill set status = 'yes' WHERE id = :parentid", nativeQuery = true)
	public void update(@Param(value = "parentid") Long idbill);
	
	
	@Modifying
	@Transactional
	@Query(value = "update parent_bill set iduser = :iduser, status = 'yes' WHERE id = :parentid", nativeQuery = true)
	public void update_user(@Param(value = "parentid") Long idbill,@Param(value = "iduser") Long iduser);
	
	@Query(value = "select * from parent_bill where iduser = :id ", nativeQuery = true)
	public List<parent_einvoice> gethistory(@Param(value = "id") Long iduser);
	
	@Modifying
	@Transactional
	@Query(value = "update parent_bill set codepay = :codepay WHERE id = :parentid", nativeQuery = true)
	public void update_codepay(@Param(value = "parentid") Long idbill,@Param(value = "codepay") String codepay);
	
	@Modifying
	@Transactional
	@Query(value = "update parent_bill set sumprice = :price WHERE id = :parentid", nativeQuery = true)
	public void update_price(@Param(value = "parentid") Long idbill,@Param(value = "price") int price);
	
	@Modifying
	@Transactional
	@Query(value = "delete from parent_bill where codepay = :codepay", nativeQuery = true)
	public void deletebill(@Param(value = "codepay") String codepay);
	
	
	//aply
	@Query(value = "select * from parent_bill where codepay = :codepay ", nativeQuery = true)
	public parent_einvoice getbill(@Param(value = "codepay") String codepay);
	
}
