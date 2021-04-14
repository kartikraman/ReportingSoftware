package pdfformating;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import application.Patient;
import application.TestFormat;
import application.TestResult;
import dbconnection.DBConnectionMethods;

public class FormatRoot {

	public void createReport(Patient a,ArrayList<TestResult> ar) {
		// TODO Auto-generated method stub
		try {
			Document d=new Document();

			String dest="F:/"+a.getId()+".pdf";
			PdfWriter.getInstance(d, new FileOutputStream(dest));
			d.open();
			setUpPage(d);
			addCreds(d,a);
			addTestFunctions(d,ar);
			d.close();
			
			//opening file
			
			openFile(dest);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	private void openFile(String dest) {
		// TODO Auto-generated method stub
		try{  
		//constructor of file class having file as argument  
			File file = new File(dest);   
			if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not  
			{  
				System.out.println("not supported");  
				return; 
			}  
			Desktop desktop = Desktop.getDesktop();  
			if(file.exists())         //checks file exists or not  
				desktop.open(file);              //opens the specified file  
		}  
		catch(Exception e){  
			e.printStackTrace();  
		} 
	}
	
	
	
	
	
	

	private void addCreds(Document d, Patient a) throws DocumentException {
		// TODO Auto-generated method stub
		float[] columnWidth= {2,1};
		PdfPTable cred=new PdfPTable(columnWidth);
//		cred.setWidthPercentage(100);
		Phrase id=new Phrase("Ref No:"+a.getId());
		Phrase Date=new Phrase("Date:"+a.getDate());
		Phrase name=new Phrase("Patient Name:"+a.getName());
		Phrase age=new Phrase("Age/Sex:"+a.getAge()+"/"+a.getGender());
		Phrase doc=new Phrase("Rfd By:"+a.getDoc());
		PdfPCell x=new PdfPCell(id);
		x.setBorder(Rectangle.NO_BORDER);
		PdfPCell b=new PdfPCell(Date);
		b.setBorder(Rectangle.NO_BORDER);
		PdfPCell c=new PdfPCell(name);
		c.setBorder(Rectangle.NO_BORDER);
		PdfPCell e=new PdfPCell(age);
		e.setBorder(Rectangle.NO_BORDER);
		PdfPCell f=new PdfPCell(doc);
		f.setBorder(Rectangle.NO_BORDER);
		cred.addCell(x);
		cred.addCell(b);
		cred.addCell(c);
		cred.addCell(e);
		cred.addCell(f);
		PdfPCell g=new PdfPCell();
		g.setBorder(Rectangle.NO_BORDER);
		cred.addCell(g);
		d.add(cred);
	}

	private void setUpPage(Document d) throws DocumentException {
		d.add(new Paragraph(" "));
		d.add(new Paragraph(" "));
		d.add(new Paragraph(" "));
		d.add(new Paragraph(" "));
		d.add(new Paragraph(" "));
		d.add(new Paragraph(" "));
		d.add(new Paragraph(" "));
		d.add(new Paragraph(" "));
	}
	
	
	private void addTestFunctions(Document d,ArrayList<TestResult> ar) throws Exception{
		
		ArrayList<Test2Subject> t2s=new ArrayList<Test2Subject>();
		ArrayList<String> subjectAL=new ArrayList<String>();
		for(TestResult i:ar) {
			String subject=DBConnectionMethods.getTestSubject(i.getTestName());
			if(!subjectAL.contains(subject)) {
				subjectAL.add(subject);
			}
			t2s.add(new Test2Subject(i,subject));
		}
		for(String i:subjectAL) {
			ArrayList<TestResult> temp=new ArrayList<TestResult>();
			for(Test2Subject j:t2s) {
				if(j.getSubject().equals(i)) {
					temp.add(j.getT());
				}
			}
			createResultSection(d,i,temp);
			temp.clear();
		}
		
		
	}
	
	
	private void createResultSection(Document d,String heading,ArrayList<TestResult> tr) throws DocumentException {
		Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		Font subHead=new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD);
		Paragraph head=new Paragraph(heading,boldFont);
		head.setAlignment(Element.ALIGN_CENTER);
		d.add(new Paragraph(" "));
		d.add(new Paragraph(" "));
		d.add(head);
		d.add(new Paragraph(" "));
		float[] columnWidth= {2,2,1};
		PdfPTable tests=new PdfPTable(columnWidth);
		PdfPCell test=new PdfPCell(new Paragraph("Test",subHead));
		test.setBorder(Rectangle.NO_BORDER);
		PdfPCell result=new PdfPCell(new Paragraph("Result",subHead));
		result.setBorder(Rectangle.NO_BORDER);
		PdfPCell range=new PdfPCell(new Paragraph("Range",subHead));
		range.setBorder(Rectangle.NO_BORDER);
		tests.addCell(test);
		tests.addCell(result);
		tests.addCell(range);
		for(int i=0;i<tr.size();i++) {
			TestFormat temp=DBConnectionMethods.getTestFormat(tr.get(i).getTestName());
			PdfPCell testName=new PdfPCell(new Phrase(temp.getTestName()));
			testName.setBorder(Rectangle.NO_BORDER);
			PdfPCell value=new PdfPCell(new Phrase(tr.get(i).getTestValue()+temp.getUnit()));
			value.setBorder(Rectangle.NO_BORDER);
			PdfPCell ran=new PdfPCell(new Phrase(temp.getLb()+"-"+temp.getUb()));
			ran.setBorder(Rectangle.NO_BORDER);
			tests.addCell(testName);
			tests.addCell(value);
			tests.addCell(ran);
		}
		d.add(tests);
	}
	
}
