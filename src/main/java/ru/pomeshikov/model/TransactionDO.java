package ru.pomeshikov.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Transaction")
@Data
public class TransactionDO {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="ukey")
	private String ukey;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="name")
	private String name;
	
	@Column(name="value")
	private BigDecimal value;
	
}
