package com.flight.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class base {

	@Id
	@SequenceGenerator(
			name = "squences",
			sequenceName = "squences",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "squences")
	private Long id;
	@Column
	@CreatedBy
	private String name_create;
	@Column
	@CreatedDate
	private Date date_create;
	@Column
	@LastModifiedBy
	private String name_modify;
	@Column
	@LastModifiedDate
	private Date date_modify;
	
}
