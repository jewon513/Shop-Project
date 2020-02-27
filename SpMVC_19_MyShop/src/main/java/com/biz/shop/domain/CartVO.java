package com.biz.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Target;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_cart", schema = "emsDB")
public class CartVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;
	
	@Column(name="username")
	private String username;	// 사용자 아이디
	
	@Column(name="p_code")
	private String p_code;		// 상품코드
	
	@Transient
	private String p_name;
	
	@Column(name="p_oprice")
	private int p_oprice;		// 판매가격
	
	@Column(name="p_qty")
	private int p_qty;			// 수량
	 
	@Column(name="p_status", length = 10)
	private String p_status;
}
