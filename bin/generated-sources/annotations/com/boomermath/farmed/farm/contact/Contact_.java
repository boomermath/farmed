package com.boomermath.farmed.farm.contact;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.contact.social.Social;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(Contact.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Contact_ {

	
	/**
	 * @see com.boomermath.farmed.farm.contact.Contact#website
	 **/
	public static volatile SingularAttribute<Contact, String> website;
	
	/**
	 * @see com.boomermath.farmed.farm.contact.Contact#phoneNumber
	 **/
	public static volatile SingularAttribute<Contact, String> phoneNumber;
	
	/**
	 * @see com.boomermath.farmed.farm.contact.Contact#social
	 **/
	public static volatile ListAttribute<Contact, Social> social;
	
	/**
	 * @see com.boomermath.farmed.farm.contact.Contact#farm
	 **/
	public static volatile SingularAttribute<Contact, Farm> farm;
	
	/**
	 * @see com.boomermath.farmed.farm.contact.Contact#id
	 **/
	public static volatile SingularAttribute<Contact, UUID> id;
	
	/**
	 * @see com.boomermath.farmed.farm.contact.Contact
	 **/
	public static volatile EntityType<Contact> class_;
	
	/**
	 * @see com.boomermath.farmed.farm.contact.Contact#email
	 **/
	public static volatile SingularAttribute<Contact, String> email;

	public static final String WEBSITE = "website";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String SOCIAL = "social";
	public static final String FARM = "farm";
	public static final String ID = "id";
	public static final String EMAIL = "email";

}

