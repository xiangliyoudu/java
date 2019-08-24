package pojo;

public class Dept {
	private String name; // 部门名称
	private String description; // 描述
	private int pid; // 上级部门ID
	private int state;
	private String idtree;
	private String filename;
	private Boolean flag = false;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(Boolean flag) {

		if (flag == null) {
			this.flag = false;
		} else {
			this.flag = flag;
		}

	}

	// 上级部门名称
	private String pname;

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getIdtree() {
		return idtree;
	}

	public void setIdtree(String idtree) {
		this.idtree = idtree;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
