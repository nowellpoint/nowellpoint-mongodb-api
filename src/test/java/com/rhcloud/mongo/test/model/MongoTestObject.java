package com.rhcloud.mongo.test.model;

import java.util.Date;

import org.bson.types.ObjectId;

import com.google.gson.annotations.SerializedName;
import com.rhcloud.mongo.annotation.Document;
import com.rhcloud.mongo.annotation.Id;

@Document(collectionName="TestObjects")
public class MongoTestObject {
	
	@Id
	@SerializedName("_id")
	private ObjectId id;
	private String name;
	private Date creationDate;
	
	public MongoTestObject() {
		creationDate = new Date();
	}
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}