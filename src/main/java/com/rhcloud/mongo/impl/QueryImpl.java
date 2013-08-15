package com.rhcloud.mongo.impl;

import java.util.List;

import com.google.common.collect.Lists;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.QueryBuilder;
import com.rhcloud.mongo.AnnotationScanner;
import com.rhcloud.mongo.Query;

/**
 * @author jherson
 * @param <T>
 * @param <T>
 *
 */
public class QueryImpl<T> implements Query<T> {
	
	/**
	 * 
	 */
	
	protected DB db;	
	
	/**
	 * 
	 */
	
	protected Class<T> clazz;
	
	/**
	 * 
	 */
	
	protected DBCollection collection;
	
	/**
	 * 
	 */
	
	protected QueryBuilder queryBuilder;
	
	/**
	 * 
	 */
	
	protected DocumentManagerImpl documentManager;
	
	/**
	 * constructor
	 * 
	 * @param mongoDBDao
	 * @param clazz
	 */
	
	protected QueryImpl(DocumentManagerImpl documentManager, Class<T> clazz) {
		this.documentManager = documentManager;	
		this.clazz = clazz;
		this.db = documentManager.getDB();
		this.collection = db.getCollection(AnnotationScanner.getCollectionName(clazz));
		this.queryBuilder = QueryBuilder.start();
	}
	
	/**
	 * put
	 * 
	 * @param key 
	 * @return Query object
	 */
	
	@Override
	public Query<T> put(String key) {
		queryBuilder.put(key);
		return this;
	}
	
	/**
	 * is
	 * 
	 * @param value
	 * @return query object 
	 */
	
	@Override
	public Query<T> isEqual(Object value) {
		queryBuilder.is(value);
		return this;
	}
	
	/**
	 * getSingleResult
	 * 
	 * @param clazz
	 * @return single document that matched the search criteria
	 */
		
	@Override
	public T getSingleResult() {
		return documentManager.getAsObject(clazz, collection.findOne(queryBuilder.get()));
	}
	
	/**
	 * getResultList
	 * 
	 * @param clazz
	 * @return list of documents that matched the search criteria
	 */
	
	@Override
	public List<T> getResultList() {			
		DBCursor cursor = collection.find(queryBuilder.get());
		
		List<T> queryResult = Lists.newArrayList();
		try {
			while (cursor.hasNext()) {
				queryResult.add(documentManager.getAsObject(clazz, cursor.next()));
			}
		} finally {
			cursor.close();
		}
		return queryResult;
	}
}