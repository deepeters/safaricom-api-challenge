package com.external.files.readdata.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.external.files.readdata.Model.Customer;
import com.external.files.readdata.Repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service
@Transactional
public class CustomerServiceImplementation implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public boolean saveDataFromUploadfile(MultipartFile file) {
		// TODO Auto-generated method stub
		boolean isFlag = false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		
		if (extension.equalsIgnoreCase("json")) {
			isFlag = readDataFromJson(file);
		}
		else if (extension.equalsIgnoreCase("csv")) {
			isFlag = readDataFromCsv(file);
		}
		else if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			isFlag = readDataFromExcel(file);
		}
		else if (extension.equalsIgnoreCase("xml")) {
			isFlag = readDataFromXml(file);
		}
		return isFlag;
	}

	//XLS & XLSX External Data File
	private boolean readDataFromExcel(MultipartFile file) {
		// TODO Auto-generated method stub
		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator <Row> rows = sheet.iterator();
		rows.next();
		
		while(rows.hasNext()) {
			Row row = rows.next();
			Customer customer = new Customer();
			if (row.getCell(0).getCellType() == CellType.STRING) {
				customer.setFilmName(row.getCell(0).getStringCellValue());
			}
			
			if (row.getCell(1).getCellType() == CellType.NUMERIC) {
				String filmYear = NumberToTextConverter.toText(row.getCell(1).getNumericCellValue());
				customer.setFilmYear(filmYear);
			}
			else if (row.getCell(1).getCellType() == CellType.STRING) {
				customer.setFilmYear(row.getCell(1).getStringCellValue());				
			}
			
			if (row.getCell(2).getCellType() == CellType.STRING) {
				customer.setMainCharacter(row.getCell(2).getStringCellValue());
			}
			if (row.getCell(3).getCellType() == CellType.STRING) {
				customer.setMainPlanet(row.getCell(3).getStringCellValue());
			}
			if (row.getCell(4).getCellType() == CellType.STRING) {
				customer.setFilmSummary(row.getCell(4).getStringCellValue());
			}
			
			if (row.getCell(5).getCellType() == CellType.NUMERIC) {
				String filmRating = NumberToTextConverter.toText(row.getCell(5).getNumericCellValue());
				customer.setFilmRating(filmRating);
			}
			else if (row.getCell(5).getCellType() == CellType.STRING) {
				customer.setFilmRating(row.getCell(5).getStringCellValue());				
			}
			
			customer.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
			customerRepository.save(customer);
		}
		
		return true;
	}

	private Workbook getWorkBook(MultipartFile file) {
		// TODO Auto-generated method stub
		Workbook workbook = null;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		try {
			if (extension.equalsIgnoreCase("xlsx")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			}
			else if (extension.equalsIgnoreCase("xls")) {
				workbook = new HSSFWorkbook(file.getInputStream());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return workbook;
	}

	
	//CSV External Data File
	private boolean readDataFromCsv(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			InputStreamReader reader = new InputStreamReader(file.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> rows = csvReader.readAll();
			
			for (String[] row : rows) {
				customerRepository.save(new Customer(row[0], row[1], row[2], row[3], row[4], row[5], FilenameUtils.getExtension(file.getOriginalFilename())));
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	
	//JSON External Data File
	private boolean readDataFromJson(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			InputStream inputStream = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			List<Customer> customers = Arrays.asList(mapper.readValue(inputStream, Customer[].class));
			if(customers!= null && customers.size()>0) {
				for (Customer customer : customers) {
					customer.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
					customerRepository.save(customer);
				}
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	//XML External Data File
	private boolean readDataFromXml(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			InputStream inputStream = file.getInputStream();
			XmlMapper mapper = new XmlMapper();
			List<Customer> customers = Arrays.asList(mapper.readValue(inputStream, Customer[].class));
			
			if(customers!= null && customers.size()>0) {
				for (Customer customer : customers) {
					customer.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
					customerRepository.save(customer);
				}
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	

}
