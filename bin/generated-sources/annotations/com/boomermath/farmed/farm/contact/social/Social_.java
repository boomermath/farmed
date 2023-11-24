package com.boomermath.farmed.farm.contact.social;

import com.boomermath.farmed.farm.contact.Contact;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(Social.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Social_ {

	
	/**
	 * @see com.boomermath.farmed.farm.contact.social.Social#contact
	 **/
	public static volatile SingularAttribute<Social, Contact> contact;
	
	/**
	 * @see com.boomermath.farmed.farm.contact.social.Social#id
	 **/
	public static volatile SingularAttribute<Social, UUID> id;
	
	/**
	 * @see com.boomermath.farmed.farm.contact.social.Social#socialType
	 **/
	public static volatile SingularAttribute<Social, SocialType> socialType;
	
	/**
	 * @see com.boomermath.farmed.farm.contact.social.Social#socialInfo
	 **/
	public static volatile SingularAttribute<Social, String> socialInfo;
	
	/**
	 * @see com.boomermath.farmed.farm.contact.social.Social
	 **/
	public static volatile EntityType<Social> class_;

	public static final String CONTACT = "contact";
	public static final String ID = "id";
	public static final String SOCIAL_TYPE = "socialType";
	public static final String SOCIAL_INFO = "socialInfo";

}

