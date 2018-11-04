package com.xyz.testengine.question.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ParallelUpload implements Runnable{
private int x=0;

	ArrayList<String> file=null;
	
	public ParallelUpload(ArrayList<String> file){
		this.file=file;
		
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (QuestionUploadHelper.class) {
			try {
			
				new QuestionUploadHelper().read(file.get(x));
				x++;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
