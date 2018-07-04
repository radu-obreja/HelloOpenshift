package com.mood.common.security;

public class JWTConfiguration {

	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final int EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer:";
	
	public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
	
	public static final String EXPOSE_AUTH_HEADER_NAME = "Access-Control-Expose-Headers";
	public static final String EXPOSE_AUTH_HEADER_VALUE = "Authorization";

}
