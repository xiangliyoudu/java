package pojo;

import java.sql.Timestamp;

public class User {

	private String userName; // 用户昵称
	private String passWord; // 密码
	private String nikename;
	private String email; // 邮箱
	private String mobile; // 手机
	private int sex; // 性别
	private int state; // 状态
	private Timestamp ctime;
	private int login_state;
	private Timestamp last_login_time;
	private String login_session;
	private int ISNOTEBOOK; // 是否有notebook权限
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// 添加字段
	private int dId;

	public int getISNOTEBOOK() {
		return ISNOTEBOOK;
	}

	public void setISNOTEBOOK(int ISNOTEBOOK) {
		this.ISNOTEBOOK = ISNOTEBOOK;
	}

	private String dname;
	private String rname;
	private boolean firstLogin = false;// 是否第一次登陆

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	private boolean flag = false;
	private int flagnew;
	private boolean isAdmin = false;
	// 添加 是否部门管理员权限
	private boolean isDeptAdmin = false;

	public boolean isDeptAdmin() {
		return isDeptAdmin;
	}

	public void setDeptAdmin(boolean isDeptAdmin) {
		this.isDeptAdmin = isDeptAdmin;
	}

	public int getFlagnew() {
		return flagnew;
	}

	public void setFlagnew(int flagnew) {

		if (flagnew == 1) {
			this.flag = true;
		} else {
			this.flag = false;
		}

		this.flagnew = flagnew;
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
	public void setFlag(boolean flag) {

		if (flag == false) {
			this.flag = false;
		} else if (flag == true) {
			this.flag = flag;
			this.flagnew = 1;

		} else {
			this.flag = flag;
			this.flagnew = 0;

		}

	}

	/**
	 * @return the username
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return the status
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setState(Integer state) {
		if (state == null) {
			this.state = 1;
		} else {
			this.state = state;
		}

	}

	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * @return the name
	 */
	public String getNikename() {
		return nikename;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setNikename(String nikename) {
		this.nikename = nikename;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public int getLogin_state() {
		return login_state;
	}

	public void setLogin_state(int login_state) {
		this.login_state = login_state;
	}

	public Timestamp getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Timestamp last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLogin_session() {
		return login_session;
	}

	public void setLogin_session(String login_session) {
		this.login_session = login_session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userName=").append(userName)
				.append(", passWord=").append(passWord).append(", state=")
				.append(state).append(", sex=").append(sex)
				.append(", nikename=").append(nikename).append(", email=")
				.append(email).append(", mobile=").append(mobile)
				.append(", ctime=").append(ctime).append(", login_state=")
				.append(login_state).append(", last_login_time=")
				.append(last_login_time).append(", login_session=")
				.append(login_session).append("]");
		return builder.toString();
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setState(int state) {
		this.state = state;
	}

}