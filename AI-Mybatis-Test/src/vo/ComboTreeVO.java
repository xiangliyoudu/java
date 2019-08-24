package vo;

import java.util.List;

public class ComboTreeVO {
	
	private int id;
	private String text;
	private List<ComboTreeVO> children;
	private boolean flag = false;

	private  boolean checked=false;//树形菜单显示是否被选中


	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		if(flag == null){
			this.flag = false;
		}else{
			this.flag = flag;
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<ComboTreeVO> getChildren() {
		return children;
	}
	public void setChildren(List<ComboTreeVO> children) {
		this.children = children;
	}

}
