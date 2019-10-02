package FamilyTree;
import java.util.Scanner;

public class FTUtil {
	static Scanner input;
	static Integer printPadding = 5; 
	
	public static void Init() {
		input = new Scanner(System.in);
	}
	
	public static void PrintEmptyText() {
		System.out.println("");
	}
	
	public static void Print(Integer text) {
		System.out.println(text);
	}
	
	public static void Print(String text) {
		System.out.println(text);
	}
	
	public static void PrintErro() {
		Print("Erro ao captar caracteres, tente novamente!");
	}

	public static void PrintTab(Integer qtdPadding) {
		Integer i = 0;
		
		while((i+printPadding) < qtdPadding * printPadding) {
			System.out.print("-");
			i++;
		}
	}
	
	public static int RequestInt(String text) {
		Init();		
		System.out.print(text);
		
		try {
			return input.nextInt();
		} catch (Exception e) {
			PrintErro();
			return RequestInt(text);
		}
	}
	
	public static int RequestIntWithLimit(String text, Integer minValue, Integer maxValue) {
		Init();		
		System.out.print(text);
		
		try {
			Integer v = input.nextInt();
			
			if (v >= minValue && v <= maxValue) {
				return v;
			} else {
				PrintErro();
				return RequestIntWithLimit(text, minValue, maxValue);
			}
		} catch (Exception e) {
			PrintErro();
			return RequestInt(text);
		}
	}
	
	public static String RequestString(String text) {
		Init();		
		System.out.print(text);
		
		try {
			return input.next();
		} catch (Exception e) {
			PrintErro();
			return RequestString(text);
		}
	}	
	
	public static Boolean RequestOption(String text, String op1, String op2) {
		Init();		
		System.out.print(text);
		
		try {
			String v = input.next();
			
			if (v.toLowerCase().equals(op1.toLowerCase())) {
				return true;
			} else if (v.toLowerCase().equals(op2.toLowerCase())) {
				return false;	
			}  else {
				PrintErro();
				return RequestOption(text, op1, op2);
			}
		} catch (Exception e) {
			PrintErro();
			return RequestOption(text, op1, op2);
		}
	}
}
