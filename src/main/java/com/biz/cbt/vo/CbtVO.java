package com.biz.cbt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * cb_num	CHAR(5)		PRIMARY KEY,
cb_que	nVARCHAR2(100)	NOT NULL,	
cb_ex1	nVARCHAR2(100)	NOT NULL,	
cb_ex2	nVARCHAR2(100)	NOT NULL,	
cb_ex3	nVARCHAR2(100)	NOT NULL,	
cb_ex4	nVARCHAR2(100)	NOT NULL,	
cb_ans	nVARCHAR2(100)	
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CbtVO {
	private String cb_num;
	private String cb_que;
	private String cb_ex1;
	private String cb_ex2;
	private String cb_ex3;
	private String cb_ex4;
	private String cb_ans;
	

}
