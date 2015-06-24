package br.com.csv;

import spring.corp.framework.metadatabean.IMetaData;
import spring.corp.framework.metadatabean.IMetaDataDefinition;
import spring.corp.framework.metadatabean.MetaDataBean;
import spring.corp.framework.metadatabean.MetaDataDefinition;
import spring.corp.framework.metadatabean.types.mainframe.BigDecimalType;
import spring.corp.framework.metadatabean.types.mainframe.BooleanType;
import spring.corp.framework.metadatabean.types.mainframe.CarryReturn;
import spring.corp.framework.metadatabean.types.mainframe.DateType;
import spring.corp.framework.metadatabean.types.mainframe.IntegerType;
import spring.corp.framework.metadatabean.types.mainframe.StringType;

public class CSVMetaDataBean implements IMetaData {
	
	private final IMetaDataDefinition definition = new MetaDataDefinition();
	private final char delimiter;
	private CSV csv;
	
	private CSVMetaDataBean(CSV csv, char delimiter){
		this.csv = csv;
		this.delimiter = delimiter;
	}
	
	public static CSVMetaDataBean newInstance(CSV csv, char delimiter){
		CSVMetaDataBean smdb = new CSVMetaDataBean(csv, delimiter);
		smdb.loadDefinition(csv);
		return smdb;
	}
	
	private void loadDefinition(CSV csv) {
		definition.addMetaDataBean(new MetaDataBean("csv.ignore", new StringType(0)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorString1", new StringType(0)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorString2", new StringType(0)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorInteger", new IntegerType(0)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorDate", new DateType(10, "dd/MM/dddd")));
		definition.addMetaDataBean(new MetaDataBean("csv.valorBigDecimal", new BigDecimalType(4, 4)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorBoolean", new BooleanType()));
		definition.addMetaDataBean(new MetaDataBean("csv.ignore", new CarryReturn(0)));
		
		definition.addMetaDataBean(new MetaDataBean("csv.valorString1", new StringType(0)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorString2", new StringType(0)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorInteger", new IntegerType(0)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorDate", new DateType(10, "dd/MM/dddd")));
		definition.addMetaDataBean(new MetaDataBean("csv.valorBigDecimal", new BigDecimalType(4, 4)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorBoolean", new BooleanType()));
		definition.addMetaDataBean(new MetaDataBean("csv.ignore", new CarryReturn(0)));
		
		definition.addMetaDataBean(new MetaDataBean("csv.valorString1", new StringType(0)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorString2", new StringType(0)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorInteger", new IntegerType(0)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorDate", new DateType(10, "dd/MM/dddd")));
		definition.addMetaDataBean(new MetaDataBean("csv.valorBigDecimal", new BigDecimalType(4, 4)));
		definition.addMetaDataBean(new MetaDataBean("csv.valorBoolean", new BooleanType()));
	}
	
	@Override
	public IMetaDataDefinition getMetaDataDefinition() {
		return definition;
	}

	public char getDelimiter() {
		return delimiter;
	}
	
	public CSV getCsv() {
		return csv;
	}

	public void setCsv(CSV csv) {
		this.csv = csv;
	}

	public void clear(){
		csv = new CSV();
	}
}