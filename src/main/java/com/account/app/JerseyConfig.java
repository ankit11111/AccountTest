package com.account.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.account.exception.GenericExceptionMapper;
import com.account.resource.AccountResource;
import com.account.service.AccountService;

@Component
@ApplicationPath("/v1")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		AccountService accountService = new AccountService();
		register(new AccountResource(accountService));
		register(GenericExceptionMapper.class);
	}

}