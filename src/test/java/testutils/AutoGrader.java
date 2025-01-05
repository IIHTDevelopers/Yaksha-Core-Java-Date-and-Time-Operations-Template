package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;

public class AutoGrader {

	// Test for Date and Time Operations
	public boolean testDateTimeOperations(String filePath) throws IOException {
		System.out.println("Starting testDateTimeOperations with file: " + filePath);

		// Load participant's file
		File participantFile = new File(filePath); // Path to participant's file
		if (!participantFile.exists()) {
			System.out.println("File does not exist at path: " + filePath);
			return false;
		}

		// Parse the file using JavaParser
		FileInputStream fileInputStream = new FileInputStream(participantFile);
		JavaParser javaParser = new JavaParser();
		CompilationUnit cu;
		try {
			cu = javaParser.parse(fileInputStream).getResult()
					.orElseThrow(() -> new IOException("Failed to parse the Java file"));
		} catch (IOException e) {
			System.out.println("Error parsing the file: " + e.getMessage());
			throw e;
		}

		System.out.println("Parsed the Java file successfully.");

		// Flags to check for each date-time related operation
		boolean hasNow = false;
		boolean hasPlusDays = false;
		boolean hasMinusHours = false;

		// Checking if any required date-time related methods are used
		System.out.println("------ Checking Date and Time Operations ------");
		for (MethodCallExpr method : cu.findAll(MethodCallExpr.class)) {
			String methodName = method.getNameAsString();
			if (methodName.equals("now")) {
				hasNow = true;
				System.out.println("✓ Found date-time operation: now");
			} else if (methodName.equals("plusDays")) {
				hasPlusDays = true;
				System.out.println("✓ Found date-time operation: plusDays");
			} else if (methodName.equals("minusHours")) {
				hasMinusHours = true;
				System.out.println("✓ Found date-time operation: minusHours");
			}
		}

		// Check if all required methods were used
		boolean allMethodsUsed = hasNow && hasPlusDays && hasMinusHours;

		// Output the result for date-time operations
		if (allMethodsUsed) {
			System.out.println("✓ All required date-time operations are present.");
		} else {
			System.out.println("✘ Missing some date-time operations.");
		}

		// Test result
		System.out.println("Test result: " + allMethodsUsed);
		return allMethodsUsed;
	}
}
