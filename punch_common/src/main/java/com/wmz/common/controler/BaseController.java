package com.wmz.common.controler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public abstract class BaseController<T> {
	
	protected T entity;
	
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseController() {
		super();
		try {
			entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	protected Gson gson = new GsonBuilder()
			.registerTypeAdapter(BigDecimal.class, new JsonDeserializer<BigDecimal>() {
				@Override
				public BigDecimal deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
						throws JsonParseException {
					try {
						String dateString=json.getAsString();
						if(StringUtils.isNotBlank(dateString)){
							return new BigDecimal(dateString);
						}
						return null;
					} catch (Exception e) {
						return null;
					}
				}
			})
			.registerTypeAdapter(Long.class, new JsonDeserializer<Long>() {
				@Override
				public Long deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
						throws JsonParseException {
					try {
						String dateString=json.getAsString();
						if(StringUtils.isNotBlank(dateString)){
							return new Long(dateString);
						}
						return null;
					} catch (Exception e) {
						return null;
					}
				}
			})
			.registerTypeAdapter(Integer.class, new JsonDeserializer<Integer>() {
				@Override
				public Integer deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
						throws JsonParseException {
					try {
						String dateString=json.getAsString();
						if(StringUtils.isNotBlank(dateString)){
							return new Integer(dateString);
						}
						return null;
					} catch (Exception e) {
						return null;
					}
				}
			})
			.registerTypeAdapter(Double.class, new JsonDeserializer<Double>() {
				@Override
				public Double deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
						throws JsonParseException {
					try {
						String dateString=json.getAsString();
						if(StringUtils.isNotBlank(dateString)){
							return new Double(dateString);
						}
						return null;
					} catch (Exception e) {
						return null;
					}
				}
			})
			.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
						dfDate=new SimpleDateFormat("yyyy-MM-dd");

				@Override
				public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
						throws JsonParseException {
					try {
						String dateString=json.getAsString();
						if(StringUtils.isNotEmpty(dateString)){
							if(dateString.length()==10){
								return dfDate.parse(dateString);
							}else{
								return df.parse(json.getAsString());
							}
						}
						return null;
					} catch (ParseException e) {	
						return null;
					}
				}
			}).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	
}
