package com.rhcloud.mongo.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Stereotype;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ApplicationScoped
@Stereotype
@Target({ FIELD })
@Retention(RUNTIME)
@Documented
public @interface MongoDBDatastore {
	Provider provider() default Provider.OPENSHIFT;
}