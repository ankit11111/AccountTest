package com.account.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import com.account.annotation.PATCH;
import com.account.bean.Account;
import com.account.constants.AccountConstants;
import com.account.service.AccountService;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * REST controller for managing account.
 */
@Path("/accounts")
public class AccountResource {
	
	private AccountService accountService;
	
	public AccountResource(AccountService accountService){
		this.accountService = accountService;
	}

	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/account")
	public Response getAll() {
		List<Account> accountList = accountService.getAll(); 
		return Response.ok().entity(accountList).build();
    }
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/account/{accountId}")
	public Response getAccountById(@PathParam("accountId") String accountId) {
		Account account = accountService.getAccount(accountId);
		return Response.ok().entity(account).build();
    }
	
	@POST 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/account")
	public Response createAccount(@RequestBody Account account) {
		accountService.createAccount(account);
		return Response.created(null).build();
    }
	
	@PATCH
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/account/{accountId}")
	@Consumes(AccountConstants.MEDIA_TYPE_APPLICATION_JSON_PATCH)
	public Response updateAccount(@PathParam("accountId") String accountId, final JsonNode jsonNode) throws Exception {
		accountService.updateAccount(accountId, jsonNode);
		return Response.ok().build();
		
    }

}
