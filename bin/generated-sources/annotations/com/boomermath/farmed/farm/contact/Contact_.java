package com.boomermath.farmed.farm.contact;

import com.boomermath.farmed.farm.contact.social.Social;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

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
	 * @see com.boomermath.farmed.farm.contact.Contact
	 **/
	public static volatile EmbeddableType<Contact> class_;
	
	/**
	 * @see com.boomermath.farmed.farm.contact.Contact#email
	 **/
	public static volatile SingularAttribute<Contact, String> email;

	public static final String WEBSITE = "website";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String SOCIAL = "social";
	public static final String EMAIL = "email";

}

