package com.account.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.account.bean.Account;
import com.account.config.ApplicationProperties;
import com.account.constants.AccountConstants;
import com.account.utils.AccountUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonpatch.JsonPatch;

public class AccountService {
	
	@Autowired
	ApplicationProperties applicationProperties;

	private ObjectMapper mapper = new ObjectMapper();

	public List<Account> getAll() {
		List<Account> accounts;
		try {
			accounts = mapper.readValue(new File(AccountConstants.jsonFileName), new TypeReference<List<Account>>() {
			});
			if (accounts == null || accounts.isEmpty()) {
				throw new RuntimeException("No accounts found");
			}
		} catch (IOException e) {
			throw new RuntimeException("Unable to get the accounts. Please try after sometime.");
		}
		return accounts;
	}

	public Account getAccount(String accountId) {
		List<Account> accounts;
		try {
			accounts = mapper.readValue(new File(AccountConstants.jsonFileName), new TypeReference<List<Account>>() {
			});
			if (accounts == null || accounts.isEmpty()) {
				throw new RuntimeException("No accounts found");
			}
			return accounts.stream().filter(account -> accountId.equals(account.getAccountid())).findFirst().get();
		} catch (IOException e) {
			throw new RuntimeException("Unable to get the accounts. Please try after sometime.");
		}
	}

	public void createAccount(Account account) {
		account.setAccountid(AccountUtils.generateUniqueId());
		try {
			List<Account> accounts = new ArrayList<Account>();
			File file = new File(AccountConstants.jsonFileName);
			if (!file.exists()) {
				try {
					file.createNewFile();
					accounts.add(account);
				} catch (IOException ioe) {
					System.out.println(ioe.getMessage());
				}
			} else {
				accounts = mapper.readValue(file, new TypeReference<List<Account>>() {
				});
				accounts.add(account);
			}
			mapper.writeValue(file, accounts);
		} catch (Exception e) {
			throw new RuntimeException("Error while processing request. Please check the input provided.");
		}
	}

	public void updateAccount(String accountId, JsonNode jsonNode) {
		List<Account> accounts = getAll();
		File file = new File(AccountConstants.jsonFileName);
		List<Account> accountsUpdated = accounts.stream().map(account -> {
			if (accountId.equals(account.getAccountid())) {
				try {
					String accountJson = mapper.writeValueAsString(account);
					JsonPatch patch = JsonPatch.fromJson(jsonNode);
					JsonNode existingObjectNode = JsonLoader.fromString(accountJson);
					JsonNode patchedJSONObj = patch.apply(existingObjectNode);
					return mapper.readValue(patchedJSONObj.toString(), Account.class);
				} catch (Exception e) {
					throw new RuntimeException("Error while processing json patch request.");
				}
			} 
			return account;
		}).collect(Collectors.toList());
		try {
			mapper.writeValue(file, accountsUpdated);
		} catch (IOException e) {
			throw new RuntimeException("Error while processing json patch request.");
		}

	}

}
