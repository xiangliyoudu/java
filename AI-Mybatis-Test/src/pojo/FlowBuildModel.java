package pojo;

import java.sql.Date;

public class FlowBuildModel {
	/**
	 * 模型名称
	 */
	private String modelid;
	/**
	 * 模型算法
	 */
	private String algorithm;
	/**
	 * 目标变量
	 */
	private String targetcol;
	/**
	 * 模型训练参数
	 */
	private String paramters;

	private int itemid;
	/**
	 * 操作时间 系统默认 用于历史操作记录
	 */
	private Date opttime;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getTargetcol() {
		return targetcol;
	}

	public void setTargetcol(String targetcol) {
		this.targetcol = targetcol;
	}

	public String getParamters() {
		return paramters;
	}

	public void setParamters(String paramters) {
		this.paramters = paramters;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public Date getOpttime() {
		return opttime;
	}

	public void setOpttime(Date opttime) {
		this.opttime = opttime;
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

}
