package pojo;

import java.sql.Date;

public class FlowCreateFrame {
	/**
	 * 数据来源导入文件
	 */
	public static final int TYPE_IMPORT = 0;
	/**
	 * 数据来源上传文件
	 */
	public static final int TYPE_UPLOAD = 1;
	/**
	 * 数据来源类型
	 */
	private int type;
	/**
	 * 文件路径 导入时可以是多个 路径之间用�??;”分�?
	 */
	private String paths;
	/**
	 * 操作时间 系统默认 用于历史操作记录
	 */
	private Date opttime;
	private int itemid;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPaths() {
		return paths;
	}

	public void setPaths(String paths) {
		this.paths = paths;
	}

	public Date getOpttime() {
		return opttime;
	}

	public void setOpttime(Date opttime) {
		this.opttime = opttime;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

}
