package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.user.User;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Review.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Review_ {

	
	/**
	 * @see com.boomermath.farmed.farm.review.Review#createdAt
	 **/
	public static volatile SingularAttribute<Review, LocalDateTime> createdAt;
	
	/**
	 * @see com.boomermath.farmed.farm.review.Review#farm
	 **/
	public static volatile SingularAttribute<Review, Farm> farm;
	
	/**
	 * @see com.boomermath.farmed.farm.review.Review#id
	 **/
	public static volatile SingularAttribute<Review, ReviewId> id;
	
	/**
	 * @see com.boomermath.farmed.farm.review.Review#stars
	 **/
	public static volatile SingularAttribute<Review, Integer> stars;
	
	/**
	 * @see com.boomermath.farmed.farm.review.Review#text
	 **/
	public static volatile SingularAttribute<Review, String> text;
	
	/**
	 * @see com.boomermath.farmed.farm.review.Review
	 **/
	public static volatile EntityType<Review> class_;
	
	/**
	 * @see com.boomermath.farmed.farm.review.Review#user
	 **/
	public static volatile SingularAttribute<Review, User> user;
	
	/**
	 * @see com.boomermath.farmed.farm.review.Review#updatedAt
	 **/
	public static volatile SingularAttribute<Review, LocalDateTime> updatedAt;

	public static final String CREATED_AT = "createdAt";
	public static final String FARM = "farm";
	public static final String ID = "id";
	public static final String STARS = "stars";
	public static final String TEXT = "text";
	public static final String USER = "user";
	public static final String UPDATED_AT = "updatedAt";

}

