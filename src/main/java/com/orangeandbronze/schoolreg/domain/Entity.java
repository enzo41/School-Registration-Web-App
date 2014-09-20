package com.orangeandbronze.schoolreg.domain;

/**
 * Layer Supertype for all entities in this domain model. Contains the private
 * key that maps each entity to a database row.
 **/
public abstract class Entity {
	
	/** Should be set by reflection. **/
	private final Long privateKey = null;

}
