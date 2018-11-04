package com.xyz.testengine.question.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.xyz.testengine.question.dao.QuestionDAO;
import com.xyz.testengine.question.dto.QuestionDTO;
import com.xyz.testengine.util.CommonUtils;
import com.xyz.testengine.util.constants.PathConstants;

public class QuestionUploadHelper {
	
	public boolean writeToDB(String path) throws IOException, ClassNotFoundException, SQLException{
		ArrayList<QuestionDTO> questionList = new ArrayList<>();
			boolean isFirstRowPass = false;
			boolean isUploaded = false;
			int cellCounter;
//			System.out.println("Path   ***"+path);
			FileInputStream file = new FileInputStream(path);
			HSSFWorkbook workBook = new HSSFWorkbook(file);
		
			HSSFSheet sheet = workBook.getSheetAt(0);
			
			Iterator<Row> rows = sheet.rowIterator();
			while(rows.hasNext()){
				Row currentRow = rows.next();
				if(!isFirstRowPass){
					isFirstRowPass = true;
					continue;
				}
				cellCounter=0;
				QuestionDTO questionDTO = new QuestionDTO();
				Iterator<Cell> cells = currentRow.cellIterator();
				while(cells.hasNext()){
					Cell currentCell = cells.next();
					cellCounter++;
					setIntoQuestionDTO(questionDTO, currentCell, cellCounter);
	
				} // Cell Loop Ends
				questionList.add(questionDTO);
			} // Row Loop Ends
			QuestionDAO questionDAO = new QuestionDAO();
			
			return questionDAO.bulkUpload(questionList);
		
			
		}
		
		
	
	
	
	public void setIntoQuestionDTO(QuestionDTO questionDTO,Cell currentCell,int cellCount) {
		if(cellCount==1) {
			questionDTO.setId((int)currentCell.getNumericCellValue());
			}
		if(cellCount==2) {
			questionDTO.setName(currentCell.getStringCellValue());
		}
		if(cellCount==3) {
			questionDTO.setAns1(currentCell.getStringCellValue());
		}
		if(cellCount==4) {
			questionDTO.setAns2(currentCell.getStringCellValue());
		}
		if(cellCount==5) {
			questionDTO.setAns3(currentCell.getStringCellValue());
		}
		if(cellCount==6) {
			questionDTO.setAns4(currentCell.getStringCellValue());
		}
		if(cellCount==7) {
			questionDTO.setRans(currentCell.getStringCellValue());
		}
		if(cellCount==8) {
			questionDTO.setScore((int)currentCell.getNumericCellValue());
		}
	}
	
	
	
	public boolean read(String path) throws IOException, ClassNotFoundException, SQLException {
		File file = new File(path);
		String fileName = CommonUtils.getFileName(path);
		String fullName = PathConstants.UPLOAD_PATH+fileName;
		final int EOF =-1;
		FileInputStream fs=null;
		BufferedInputStream bs =null;
		FileOutputStream fo = null;
		BufferedOutputStream bo = null;
		if(file.exists()) {
			try {
			fs = new FileInputStream(file);
			bs = new BufferedInputStream(fs);
			fo = new FileOutputStream(fullName);
			bo = new BufferedOutputStream(fo);
			int singleByte = bs.read();
			while(singleByte!=EOF) {
				bo.write(singleByte);
				singleByte = bs.read();
				}
			}
			
			finally {
			if(bo!=null) {
				bo.close();
				}
			if(fo!=null) {
				fo.close();
				}
			if(bs!=null) {
				bs.close();
				}
			if(fs!=null) {
				fs.close();
				}

			}
			
		}
		return writeToDB(fullName);
	}
	
	
	
	
	
	
	
	

}
