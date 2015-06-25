package br.com.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import spring.corp.framework.exceptions.UserLinkException;
import spring.corp.framework.i18n.ManagerMessage;
import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.utils.DateUtils;
import spring.corp.framework.utils.StringUtils;
import spring.corp.framework.view.ComplexValidation;
import spring.corp.framework.view.Input;
import spring.corp.framework.view.Input.HoldBuilder;
import spring.corp.framework.view.InputArray;
import spring.corp.framework.view.RegexValidation;

public class ManterEntidade {

	public JSONReturn manterEntidade(ServletRequest request, ServletResponse response) {
		Input.HoldBuilder holdBuilder = popularHold(request);
		Entidade entidade = new Entidade();
		popularBloco1(request, holdBuilder, entidade, false);
		popularBloco2(request, holdBuilder, entidade, false);
		popularBloco3(request, holdBuilder, entidade, false);
		popularBlocoLista(request, entidade, false);
		
		System.out.println(entidade.toString());
		
		return JSONReturn.newInstance(Consequence.SUCESSO, entidade.toString()).message("Sucesso");
	}
	
	private Input.HoldBuilder popularHold(ServletRequest request) {
		Input.HoldBuilder holdBuilder = Input.builderHoldInstance();
		holdBuilder.add(Input.builderInstance(request, String.class).name("stringOnlyLetters").label("String Only Letters").min(10).max(100).validation(RegexValidation.OnlyLetters).required().build())
				   .add(Input.builderInstance(request, Integer.class).name("integer").label("Integer").required().build())
				   .add(Input.builderInstance(request, Long.class).name("long").label("Long").build())
				   .add(Input.builderInstance(request, BigDecimal.class).name("bigDecimalFormato0000").label("BigDecimal (Formato 0,0000)").defaultValue("0,0000").required().build())
				   .add(Input.builderInstance(request, Calendar.class).name("calendar").label("Calendar").required().build())
				   .add(Input.builderInstance(request, String.class).name("stringOnlyNumbers").label("String Only Numbers").min(10).max(100).validation(RegexValidation.OnlyNumbers).build())
				   .add(Input.builderInstance(request, Integer.class).name("integer100and1000").label("Integer (+100 1000-)").biggerThan(100).lessThan(1000).build())
				   .add(Input.builderInstance(request, Long.class).name("long100and1000").label("Long (+100 1000-)").biggerThan(100).lessThan(1000).required().build())
				   .add(Input.builderInstance(request, BigDecimal.class).name("bigDecimal100and1000").label("BigDecimal (+100,00 1000,00-)").biggerThan(100).lessThan(1000).defaultValue("0,00").build())
				   .add(Input.builderInstance(request, Calendar.class).name("calendar23061990and29052015").label("Calendar (+23/06/1990 29/05/2015-)").required().validation(new ComplexValidation() {
						public void validate(String name, String value) throws UserLinkException {
							if (value != null) {
								try {
									Integer data = DateUtils.stringToInteger(value, "dd/MM/yyyy");
									if (data < 19900623 || 20150529 < data) {
										String message = ManagerMessage.getMessage("framework.utils.min.between.max", "Calendar", "23/06/1990", "29/05/2015");
										throw new UserLinkException(name, message);
									}
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}	
						}
						}).build())
				   .add(Input.builderInstance(request, String.class).name("stringOnlyLettersNumbers").label("String Only Letters Numbers").validation(RegexValidation.OnlyLettersNumbers).required().build())
				   .add(Input.builderInstance(request, String.class).name("string100and1000").label("String (+100 1000-)").biggerThan(100).lessThan(1000).validation(RegexValidation.OnlyNumbers).build())
				   .add(Input.builderInstance(request, String.class).name("email100").label("Email (100-)").max(100).validation(RegexValidation.EMAIL).build())
				   .add(Input.builderInstance(request, String.class).name("placa").label("Placa").validation(RegexValidation.PLACA).required().build())
				   .add(Input.builderInstance(request, String.class).name("ncm").label("NCM").required().validation(new ComplexValidation() {
						public void validate(String name, String value) throws UserLinkException {
							if (value != null) {
								if (value.length() != 2 && value.length() != 8) {
									String message = ManagerMessage.getMessage("Valor do NCM inv\u00e1lido o campo deve conter 2 ou 8 d\u00edgitos.");
									throw new UserLinkException(name, message);
								}
							}	
						}
						}).validation(RegexValidation.NCM).build());
		return holdBuilder;
	}
	
	public void popularBloco1(ServletRequest request, HoldBuilder holdBuilder, Entidade entidade, boolean screenSaver) {
		Input<String> stringOnlyLetters = holdBuilder.get("stringOnlyLetters");
		Input<Integer> integer = holdBuilder.get("integer");
		Input<Long> lonG = holdBuilder.get("long");
		Input<BigDecimal> bigDecimalFormato0000 = holdBuilder.get("bigDecimalFormato0000");
		Input<Calendar> calendar = holdBuilder.get("calendar");
		
		entidade.setStringOnlyLetters(stringOnlyLetters.getValue());
		entidade.setInteger(integer.getValue());
		entidade.setLonG(lonG.getValue());
		entidade.setBigDecimalFormato0000(bigDecimalFormato0000.getValue());
		entidade.setCalendar(calendar.getValue());
	}
	
	public void popularBloco2(ServletRequest request, HoldBuilder holdBuilder, Entidade entidade, boolean screenSaver) {
		Input<String> stringOnlyNumbers = holdBuilder.get("stringOnlyNumbers");
		Input<Integer> integer100and1000 = holdBuilder.get("integer100and1000");
		Input<Long> long100and1000 = holdBuilder.get("long100and1000");
		Input<BigDecimal> bigDecimal100and1000 = holdBuilder.get("bigDecimal100and1000");
		Input<Calendar> calendar23061990and29052015 = holdBuilder.get("calendar23061990and29052015");
		
		entidade.setStringOnlyNumbers(stringOnlyNumbers.getValue());
		entidade.setInteger100and1000(integer100and1000.getValue());
		entidade.setLong100and1000(long100and1000.getValue());
		entidade.setBigDecimal100and1000(bigDecimal100and1000.getValue());
		entidade.setCalendar23061990and29052015(calendar23061990and29052015.getValue());
	}
	
	public void popularBloco3(ServletRequest request, HoldBuilder holdBuilder, Entidade entidade, boolean screenSaver) {
		Input<String> stringOnlyLettersNumbers = holdBuilder.get("stringOnlyLettersNumbers");
		Input<String> string100and1000 = holdBuilder.get("string100and1000");
		Input<String> email100 = holdBuilder.get("email100");
		Input<String> placa = holdBuilder.get("placa");
		Input<String> ncm = holdBuilder.get("ncm");
		
		entidade.setStringOnlyLettersNumbers(stringOnlyLettersNumbers.getValue());
		entidade.setString100and1000(string100and1000.getValue());
		entidade.setEmail100(email100.getValue());
		entidade.setPlaca(placa.getValue());
		entidade.setNcm(ncm.getValue());
	}
	
	public void popularBlocoLista(ServletRequest request, Entidade entidade, boolean screenSaver) {
		String listaStrings = request.getParameter("listaStrings");
		if (!StringUtils.isBlank(listaStrings)) {
			String[] strings = listaStrings.split(",");
			for (int i = 0, l = strings.length; i < l; i++) {
				InputArray<String> string = InputArray.builderInstance(strings[i], String.class, i, screenSaver).name("string").label("String").build();
				entidade.addListaString(string.getValue());
			}
		}
		
		String listaIntegers = request.getParameter("listaIntegers");
		if (!StringUtils.isBlank(listaIntegers)) {
			String[] integers = listaIntegers.split(",");
			for (int i = 0, l = integers.length; i < l; i++) {
				InputArray<Integer> integer = InputArray.builderInstance(integers[i], Integer.class, i, screenSaver).name("integer").label("Integer").build();
				entidade.addListaInteger(integer.getValue());
			}
		}
	}
}