package br.com.csv;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CSV implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String ignore = "";
	private String valorString1;
	private String valorString2;
	private Integer valorInteger;
	private Date valorDate;
	private BigDecimal valorBigDecimal;
	private Long valorLong;
	private Boolean valorBoolean;
	
	public String getIgnore() {
		return ignore;
	}

	public String getValorString1() {
		return valorString1;
	}
	
	public void setValorString1(String valorString1) {
		this.valorString1 = valorString1;
	}
	
	public String getValorString2() {
		return valorString2;
	}
	
	public void setValorString2(String valorString2) {
		this.valorString2 = valorString2;
	}
	
	public Integer getValorInteger() {
		return valorInteger;
	}
	
	public void setValorInteger(Integer valorInteger) {
		this.valorInteger = valorInteger;
	}
	
	public Date getValorDate() {
		return valorDate;
	}
	
	public void setValorDate(Date valorDate) {
		this.valorDate = valorDate;
	}
	
	public BigDecimal getValorBigDecimal() {
		return valorBigDecimal;
	}
	
	public void setValorBigDecimal(BigDecimal valorBigDecimal) {
		this.valorBigDecimal = valorBigDecimal;
	}
	
	public Long getValorLong() {
		return valorLong;
	}
	
	public void setValorLong(Long valorLong) {
		this.valorLong = valorLong;
	}
	
	public Boolean getValorBoolean() {
		return valorBoolean;
	}
	
	public void setValorBoolean(Boolean valorBoolean) {
		this.valorBoolean = valorBoolean;
	}
	
	@Override
	public String toString() {
		return "CSV [valorString1=" + valorString1 + ", valorString2=" + valorString2 + ", valorInteger=" + valorInteger + ", valorDate=" + valorDate + ", valorBigDecimal=" + valorBigDecimal + ", valorLong=" + valorLong + ", valorBoolean=" + valorBoolean + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((valorBigDecimal == null) ? 0 : valorBigDecimal.hashCode());
		result = prime * result
				+ ((valorBoolean == null) ? 0 : valorBoolean.hashCode());
		result = prime * result
				+ ((valorDate == null) ? 0 : valorDate.hashCode());
		result = prime * result
				+ ((valorInteger == null) ? 0 : valorInteger.hashCode());
		result = prime * result
				+ ((valorLong == null) ? 0 : valorLong.hashCode());
		result = prime * result
				+ ((valorString1 == null) ? 0 : valorString1.hashCode());
		result = prime * result
				+ ((valorString2 == null) ? 0 : valorString2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CSV other = (CSV) obj;
		if (valorBigDecimal == null) {
			if (other.valorBigDecimal != null)
				return false;
		} else if (!valorBigDecimal.equals(other.valorBigDecimal))
			return false;
		if (valorBoolean == null) {
			if (other.valorBoolean != null)
				return false;
		} else if (!valorBoolean.equals(other.valorBoolean))
			return false;
		if (valorDate == null) {
			if (other.valorDate != null)
				return false;
		} else if (!valorDate.equals(other.valorDate))
			return false;
		if (valorInteger == null) {
			if (other.valorInteger != null)
				return false;
		} else if (!valorInteger.equals(other.valorInteger))
			return false;
		if (valorLong == null) {
			if (other.valorLong != null)
				return false;
		} else if (!valorLong.equals(other.valorLong))
			return false;
		if (valorString1 == null) {
			if (other.valorString1 != null)
				return false;
		} else if (!valorString1.equals(other.valorString1))
			return false;
		if (valorString2 == null) {
			if (other.valorString2 != null)
				return false;
		} else if (!valorString2.equals(other.valorString2))
			return false;
		return true;
	}
}