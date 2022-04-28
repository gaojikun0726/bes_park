package com.efounder.JEnterprise.model;

public class MongoUser {

	private Long id;
	private String name;
	private String age;
	private String sex;
	
//	public ISSPMongoUser(Long id,String name,String age,String sex){
//		this.id = id;
//		this.name = name ;
//		this.age  = age  ;
//		this.sex  = sex  ;
//	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
