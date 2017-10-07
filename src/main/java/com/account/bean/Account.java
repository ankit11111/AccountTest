package com.account.bean;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1288221802150760693L;

	private String accountid;

	@JsonProperty("customerName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String customerName;

	@JsonProperty("currency")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String currency;

	@JsonProperty("amount")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private float amount;

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
