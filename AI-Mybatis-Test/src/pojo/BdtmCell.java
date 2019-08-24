package pojo;

import java.io.Serializable;
import java.util.Date;

public class BdtmCell implements Serializable {

    private Long NOTEBOOK_ID;

    private String TYPE;

    private Long PAIXU;

    private Date CTIME;

    private static final long serialVersionUID = 1L;

    public Long getNOTEBOOK_ID() {
        return NOTEBOOK_ID;
    }

    public void setNOTEBOOK_ID(Long NOTEBOOK_ID) {
        this.NOTEBOOK_ID = NOTEBOOK_ID;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public Long getPAIXU() {
        return PAIXU;
    }

    public void setPAIXU(Long PAIXU) {
        this.PAIXU = PAIXU;
    }

    public Date getCTIME() {
        return CTIME;
    }

    public void setCTIME(Date CTIME) {
        this.CTIME = CTIME;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BdtmCell other = (BdtmCell) that;
        return (this.getNOTEBOOK_ID() == null ? other.getNOTEBOOK_ID() == null : this.getNOTEBOOK_ID().equals(other.getNOTEBOOK_ID()))
            && (this.getTYPE() == null ? other.getTYPE() == null : this.getTYPE().equals(other.getTYPE()))
            && (this.getPAIXU() == null ? other.getPAIXU() == null : this.getPAIXU().equals(other.getPAIXU()))
            && (this.getCTIME() == null ? other.getCTIME() == null : this.getCTIME().equals(other.getCTIME()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNOTEBOOK_ID() == null) ? 0 : getNOTEBOOK_ID().hashCode());
        result = prime * result + ((getTYPE() == null) ? 0 : getTYPE().hashCode());
        result = prime * result + ((getPAIXU() == null) ? 0 : getPAIXU().hashCode());
        result = prime * result + ((getCTIME() == null) ? 0 : getCTIME().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", NOTEBOOK_ID=").append(NOTEBOOK_ID);
        sb.append(", TYPE=").append(TYPE);
        sb.append(", PAIXU=").append(PAIXU);
        sb.append(", CTIME=").append(CTIME);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}