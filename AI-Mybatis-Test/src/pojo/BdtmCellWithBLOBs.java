package pojo;

import java.io.Serializable;

/**
 * @author 
 */
public class BdtmCellWithBLOBs extends BdtmCell implements Serializable {
    private String INPUT;

    private String OUTPUT;

    private static final long serialVersionUID = 1L;

    public String getINPUT() {
        return INPUT;
    }

    public void setINPUT(String INPUT) {
        this.INPUT = INPUT;
    }

    public String getOUTPUT() {
        return OUTPUT;
    }

    public void setOUTPUT(String OUTPUT) {
        this.OUTPUT = OUTPUT;
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
        BdtmCellWithBLOBs other = (BdtmCellWithBLOBs) that;
        return (this.getNOTEBOOK_ID() == null ? other.getNOTEBOOK_ID() == null : this.getNOTEBOOK_ID().equals(other.getNOTEBOOK_ID()))
            && (this.getTYPE() == null ? other.getTYPE() == null : this.getTYPE().equals(other.getTYPE()))
            && (this.getPAIXU() == null ? other.getPAIXU() == null : this.getPAIXU().equals(other.getPAIXU()))
            && (this.getCTIME() == null ? other.getCTIME() == null : this.getCTIME().equals(other.getCTIME()))
            && (this.getINPUT() == null ? other.getINPUT() == null : this.getINPUT().equals(other.getINPUT()))
            && (this.getOUTPUT() == null ? other.getOUTPUT() == null : this.getOUTPUT().equals(other.getOUTPUT()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNOTEBOOK_ID() == null) ? 0 : getNOTEBOOK_ID().hashCode());
        result = prime * result + ((getTYPE() == null) ? 0 : getTYPE().hashCode());
        result = prime * result + ((getPAIXU() == null) ? 0 : getPAIXU().hashCode());
        result = prime * result + ((getCTIME() == null) ? 0 : getCTIME().hashCode());
        result = prime * result + ((getINPUT() == null) ? 0 : getINPUT().hashCode());
        result = prime * result + ((getOUTPUT() == null) ? 0 : getOUTPUT().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", INPUT=").append(INPUT);
        sb.append(", OUTPUT=").append(OUTPUT);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}