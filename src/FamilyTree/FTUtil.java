package FamilyTree;
import java.util.Scanner;

public class FTUtil {
	static Scanner input;
	
	public static void Init() {
		input = new Scanner(System.in);
	}
	
	public static void Print(Integer text) {
		System.out.println(text);
	}
	
	public static void Print(String text) {
		System.out.println(text);
	}
	
	public static void PrintErro() {
		Print("Erro ao captar caractere, tente novamente!");
	}
	
	public static int RequestInt(String text) {
		Init();		
		Print(text);
		
		try {
			return input.nextInt();
		} catch (Exception e) {
			PrintErro();
			return RequestInt(text);
		}
	}
	
	public static String RequestString(String text) {
		Init();		
		Print(text);
		
		try {
			return input.next();
		} catch (Exception e) {
			PrintErro();
			return RequestString(text);
		}
	}	
}
