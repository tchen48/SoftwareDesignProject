package com.ProjMgmtSys.model.Field;

public class Field {
	private int fieldId;
	private String fieldName;
	private int dataType;
	private int objId;
	private int depId;
	private int groId;
	
	public int getFieldId() {
		return fieldId;
	}
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	public int getObjId() {
		return objId;
	}
	public void setObjId(int objId) {
		this.objId = objId;
	}
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	public int getGroId() {
		return groId;
	}
	public void setGroId(int groId) {
		this.groId = groId;
	}
	
}
