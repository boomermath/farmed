package com.boomermath.farmed.user.auth.identity;

import com.boomermath.farmed.user.User;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import java.util.UUID;

@StaticMetamodel(Identity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Identity_ {

	
	/**
	 * @see com.boomermath.farmed.user.auth.identity.Identity#createdAt
	 **/
	public static volatile SingularAttribute<Identity, LocalDateTime> createdAt;
	
	/**
	 * @see com.boomermath.farmed.user.auth.identity.Identity#identityType
	 **/
	public static volatile SingularAttribute<Identity, IdentityType> identityType;
	
	/**
	 * @see com.boomermath.farmed.user.auth.identity.Identity#id
	 **/
	public static volatile SingularAttribute<Identity, UUID> id;
	
	/**
	 * @see com.boomermath.farmed.user.auth.identity.Identity
	 **/
	public static volatile EntityType<Identity> class_;
	
	/**
	 * @see com.boomermath.farmed.user.auth.identity.Identity#user
	 **/
	public static volatile SingularAttribute<Identity, User> user;
	
	/**
	 * @see com.boomermath.farmed.user.auth.identity.Identity#hash
	 **/
	public static volatile SingularAttribute<Identity, String> hash;
	
	/**
	 * @see com.boomermath.farmed.user.auth.identity.Identity#updatedAt
	 **/
	public static volatile SingularAttribute<Identity, LocalDateTime> updatedAt;

	public static final String CREATED_AT = "createdAt";
	public static final String IDENTITY_TYPE = "identityType";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String HASH = "hash";
	public static final String UPDATED_AT = "updatedAt";

}

