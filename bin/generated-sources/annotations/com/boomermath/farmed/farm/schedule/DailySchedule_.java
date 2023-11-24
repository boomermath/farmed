package com.boomermath.farmed.farm.schedule;

import com.boomermath.farmed.farm.Farm;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalTime;
import java.util.UUID;

@StaticMetamodel(DailySchedule.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class DailySchedule_ {

	
	/**
	 * @see com.boomermath.farmed.farm.schedule.DailySchedule#farm
	 **/
	public static volatile SingularAttribute<DailySchedule, Farm> farm;
	
	/**
	 * @see com.boomermath.farmed.farm.schedule.DailySchedule#startTime
	 **/
	public static volatile SingularAttribute<DailySchedule, LocalTime> startTime;
	
	/**
	 * @see com.boomermath.farmed.farm.schedule.DailySchedule#id
	 **/
	public static volatile SingularAttribute<DailySchedule, UUID> id;
	
	/**
	 * @see com.boomermath.farmed.farm.schedule.DailySchedule#endTime
	 **/
	public static volatile SingularAttribute<DailySchedule, LocalTime> endTime;
	
	/**
	 * @see com.boomermath.farmed.farm.schedule.DailySchedule
	 **/
	public static volatile EntityType<DailySchedule> class_;
	
	/**
	 * @see com.boomermath.farmed.farm.schedule.DailySchedule#day
	 **/
	public static volatile SingularAttribute<DailySchedule, DayOfWeek> day;

	public static final String FARM = "farm";
	public static final String START_TIME = "startTime";
	public static final String ID = "id";
	public static final String END_TIME = "endTime";
	public static final String DAY = "day";

}

