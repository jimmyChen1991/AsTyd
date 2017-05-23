package com.hhyg.TyClosing.info;

public class ActiveInfo {
	private String activeId;
	private String name;
	private String active_price;
	private String short_desc;
	private String desc;
	private String type_name;
	private String comments;
	private int priority;
	private ActiveType type;
	public enum ActiveType{
		Normal,
		Cut,
		FullReduce,
		EveryFull,
		FullCountReduce,
		NoStock
	}
	public ActiveInfo() {
		// TODO Auto-generated constructor stub
	}
	public ActiveInfo(String activeId, String name, ActiveType type) {
		super();
		this.activeId = activeId;
		this.name = name;
		this.type = type;
	}
	
	public String getActiveId() {
		return activeId;
	}
	public void setActiveId(String activeId) {
		this.activeId = activeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setActive_price(String active_price) {
		this.active_price = active_price;
	}
	
	public String getActive_price() {
		return active_price;
	}
	
	public ActiveType getType() {
		return type;
	}
	
	public void setType(ActiveType type) {
		this.type = type;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}
	
	public String getShort_desc() {
		return short_desc;
	}
	
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	public String getType_name() {
		return type_name;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public void setType(String typeStr){
		if(typeStr.equals("-1")){
			type = ActiveType.Normal;
		}else if(typeStr.equals("1")){
			type = ActiveType.Cut;
		}else if(typeStr.equals("2")){
			type = ActiveType.EveryFull;
		}else if(typeStr.equals("3")){
			type = ActiveType.FullReduce;
		}else if(typeStr.equals("4")){
			type = ActiveType.FullCountReduce;
		}
	}
}
