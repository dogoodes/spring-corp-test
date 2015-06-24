package br.com.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	private String stringOnlyLetters;
	private String stringOnlyNumbers;
	private String stringOnlyLettersNumbers;
	private String string100and1000;
	
	private Integer integer;
	private Integer integer100and1000;
	
	private Long lonG;
	private Long long100and1000;
	
	private BigDecimal bigDecimalFormato0000;
	private BigDecimal bigDecimal100and1000;
	
	private Calendar calendar;
	private Calendar calendar23061990and29052015;
	
	private String email100;
	private String placa;
	private String ncm;
	
	private List<String> listaStrings = new ArrayList<String>();
	private List<Integer> listaIntegers = new ArrayList<Integer>();
	
	public String getStringOnlyLetters() {
		return stringOnlyLetters;
	}
	
	public void setStringOnlyLetters(String stringOnlyLetters) {
		this.stringOnlyLetters = stringOnlyLetters;
	}
	
	public String getStringOnlyNumbers() {
		return stringOnlyNumbers;
	}
	
	public void setStringOnlyNumbers(String stringOnlyNumbers) {
		this.stringOnlyNumbers = stringOnlyNumbers;
	}
	
	public String getStringOnlyLettersNumbers() {
		return stringOnlyLettersNumbers;
	}
	
	public void setStringOnlyLettersNumbers(String stringOnlyLettersNumbers) {
		this.stringOnlyLettersNumbers = stringOnlyLettersNumbers;
	}
	
	public String getString100and1000() {
		return string100and1000;
	}
	
	public void setString100and1000(String string100and1000) {
		this.string100and1000 = string100and1000;
	}
	
	public Integer getInteger() {
		return integer;
	}
	
	public void setInteger(Integer integer) {
		this.integer = integer;
	}
	
	public Integer getInteger100and1000() {
		return integer100and1000;
	}
	
	public void setInteger100and1000(Integer integer100and1000) {
		this.integer100and1000 = integer100and1000;
	}
	
	public Long getLonG() {
		return lonG;
	}
	
	public void setLonG(Long lonG) {
		this.lonG = lonG;
	}
	
	public Long getLong100and1000() {
		return long100and1000;
	}
	
	public void setLong100and1000(Long long100and1000) {
		this.long100and1000 = long100and1000;
	}
	
	public BigDecimal getBigDecimalFormato0000() {
		return bigDecimalFormato0000;
	}
	
	public void setBigDecimalFormato0000(BigDecimal bigDecimalFormato0000) {
		this.bigDecimalFormato0000 = bigDecimalFormato0000;
	}
	
	public BigDecimal getBigDecimal100and1000() {
		return bigDecimal100and1000;
	}
	
	public void setBigDecimal100and1000(BigDecimal bigDecimal100and1000) {
		this.bigDecimal100and1000 = bigDecimal100and1000;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public Calendar getCalendar23061990and29052015() {
		return calendar23061990and29052015;
	}

	public void setCalendar23061990and29052015(Calendar calendar23061990and29052015) {
		this.calendar23061990and29052015 = calendar23061990and29052015;
	}

	public String getEmail100() {
		return email100;
	}
	
	public void setEmail100(String email100) {
		this.email100 = email100;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public List<String> getListaStrings() {
		return listaStrings;
	}

	public void setListaStrings(List<String> listaStrings) {
		this.listaStrings = listaStrings;
	}
	
	public void addListaString(String string) {
		if (listaStrings != null) {
			listaStrings = new ArrayList<String>();
		}
		this.listaStrings.add(string);
	}

	public List<Integer> getListaIntegers() {
		return listaIntegers;
	}

	public void setListaIntegers(List<Integer> listaIntegers) {
		this.listaIntegers = listaIntegers;
	}
	
	public void addListaInteger(Integer integer) {
		if (listaIntegers != null) {
			listaIntegers = new ArrayList<Integer>();
		}
		this.listaIntegers.add(integer);
	}

	@Override
	public String toString() {
		return "Entidade [stringOnlyLetters=" + stringOnlyLetters
				+ ", stringOnlyNumbers=" + stringOnlyNumbers
				+ ", stringOnlyLettersNumbers=" + stringOnlyLettersNumbers
				+ ", string100and1000=" + string100and1000 + ", integer="
				+ integer + ", integer100and1000=" + integer100and1000
				+ ", lonG=" + lonG + ", long100and1000=" + long100and1000
				+ ", bigDecimalFormato0000=" + bigDecimalFormato0000
				+ ", bigDecimal100and1000=" + bigDecimal100and1000
				+ ", calendar=" + calendar + ", calendar23061990and29052015="
				+ calendar23061990and29052015 + ", email100=" + email100
				+ ", placa=" + placa + ", ncm=" + ncm + ", listaStrings="
				+ listaStrings + ", listaIntegers=" + listaIntegers + "]";
	}
}