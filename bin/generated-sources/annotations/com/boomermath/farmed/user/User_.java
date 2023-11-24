package com.boomermath.farmed.user;

import com.boomermath.farmed.user.auth.identity.Identity;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import java.util.UUID;

@StaticMetamodel(User.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class User_ {

	
	/**
	 * @see com.boomermath.farmed.user.User#createdAt
	 **/
	public static volatile SingularAttribute<User, LocalDateTime> createdAt;
	
	/**
	 * @see com.boomermath.farmed.user.User#identity
	 **/
	public static volatile SingularAttribute<User, Identity> identity;
	
	/**
	 * @see com.boomermath.farmed.user.User#id
	 **/
	public static volatile SingularAttribute<User, UUID> id;
	
	/**
	 * @see com.boomermath.farmed.user.User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see com.boomermath.farmed.user.User#email
	 **/
	public static volatile SingularAttribute<User, String> email;
	
	/**
	 * @see com.boomermath.farmed.user.User#username
	 **/
	public static volatile SingularAttribute<User, String> username;

	public static final String CREATED_AT = "createdAt";
	public static final String IDENTITY = "identity";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

