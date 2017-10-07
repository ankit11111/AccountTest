package com.account.utils;

import java.util.Locale;
import java.util.UUID;

public class AccountUtils {

	public static String generateUniqueId() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase(Locale.US);
	}
	
	
}
