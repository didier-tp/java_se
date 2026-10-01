package tp.csv;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class MyGenericCsvUtil {
	
	public static void decrire(String fullClassName) {
		 try {
			Class<?> c = Class.forName(fullClassName) ;
			System.out.println("fullClassName="+fullClassName);
			Field[] tabChamps = c.getDeclaredFields();
			for (Field f : tabChamps) {
				System.out.println("\tfield :"+f.getName());
			}
			Method[] tabMethods = c.getDeclaredMethods();
			for (Method m : tabMethods) {
				System.out.println("\tmethod :"+m.getName());
			}
		 } catch (Exception e) {
			e.printStackTrace();
		 }
	}
	
	public static String writeValuesAsCsvString(Object obj) {
		String csvString = null;
		Class<?> c = obj.getClass(); // meta description de la classe de l'objet obj
		try {
			Field[] tabChamps = c.getDeclaredFields();
			for (Field f : tabChamps) {
				CsvIgnore annotCsvIgnore = f.getAnnotation(CsvIgnore.class);
				//si annotCsvIgnore==null , @CsvIgnore n'a pas été placé au dessus de f
				if(annotCsvIgnore==null) {
				 f.setAccessible(true); // pour accéder aux parties privées
				 String fieldValue = f.get(obj) == null ? null : f.get(obj).toString();
				 csvString = (csvString == null) ? fieldValue : csvString + ";" + fieldValue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return csvString;
	}

	public static String writeFieldNamesAsCsvString(Object obj) {
		String csvString = null;
		Class<?> c = obj.getClass(); // meta description de la classe de l'objet obj
		try {
			Field[] tabChamps = c.getDeclaredFields();
			for (Field f : tabChamps) {
				CsvIgnore annotCsvIgnore = f.getAnnotation(CsvIgnore.class);
				//si annotCsvIgnore==null , @CsvIgnore n'a pas été placé au dessus de f
				if(annotCsvIgnore==null) {
				   String fieldName = f.getName();
				   csvString = (csvString == null) ? fieldName : csvString + ";" + fieldName;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return csvString;
	}

	public static void writeValuesAsCsvFile(List<?> col, String fileName) {
		if (col.isEmpty())
			return;// fin fonction sans rien faire
		try (PrintStream ps = new PrintStream(new FileOutputStream(fileName))) {
			Object firstObj = col.get(0);
			String ligneEnteteAuFormatCsv = writeFieldNamesAsCsvString(firstObj);
			ps.println(ligneEnteteAuFormatCsv);
			for (Object obj : col) {
				String ligneValeursAuFormatCsv = writeValuesAsCsvString(obj);
				ps.println(ligneValeursAuFormatCsv);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} // try_with_autoCloseable resource
	}
}
