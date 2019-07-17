package com.outplaying.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.outplaying.constants.Utils;

public class Validator {

	public static boolean ValidateIfIdIsOfAuthenticatedUser(Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!Utils.anonymousUser.equals(authentication.getName())) {
			Long idUserAuth = Long.parseLong(authentication.getName());
			if (id == idUserAuth) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!Utils.anonymousUser.equals(authentication.getName())) {
			return true;
		} else {
			return false;
		}
	}
}
