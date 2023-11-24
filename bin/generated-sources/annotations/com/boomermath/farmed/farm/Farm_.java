package com.boomermath.farmed.farm;

import com.boomermath.farmed.farm.contact.Contact;
import com.boomermath.farmed.farm.review.Review;
import com.boomermath.farmed.farm.schedule.DailySchedule;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(Farm.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Farm_ {

	
	/**
	 * @see com.boomermath.farmed.farm.Farm#schedule
	 **/
	public static volatile ListAttribute<Farm, DailySchedule> schedule;
	
	/**
	 * @see com.boomermath.farmed.farm.Farm#reviews
	 **/
	public static volatile ListAttribute<Farm, Review> reviews;
	
	/**
	 * @see com.boomermath.farmed.farm.Farm#reviewCount
	 **/
	public static volatile SingularAttribute<Farm, Integer> reviewCount;
	
	/**
	 * @see com.boomermath.farmed.farm.Farm#contact
	 **/
	public static volatile SingularAttribute<Farm, Contact> contact;
	
	/**
	 * @see com.boomermath.farmed.farm.Farm#name
	 **/
	public static volatile SingularAttribute<Farm, String> name;
	
	/**
	 * @see com.boomermath.farmed.farm.Farm#rating
	 **/
	public static volatile SingularAttribute<Farm, Integer> rating;
	
	/**
	 * @see com.boomermath.farmed.farm.Farm#id
	 **/
	public static volatile SingularAttribute<Farm, UUID> id;
	
	/**
	 * @see com.boomermath.farmed.farm.Farm
	 **/
	public static volatile EntityType<Farm> class_;

	public static final String SCHEDULE = "schedule";
	public static final String REVIEWS = "reviews";
	public static final String REVIEW_COUNT = "reviewCount";
	public static final String CONTACT = "contact";
	public static final String NAME = "name";
	public static final String RATING = "rating";
	public static final String ID = "id";

}

