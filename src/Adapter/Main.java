package Adapter;
import static org.junit.Assert.*;

public class Main {

	public static void main(String[] args) {
		whenConvertingMPHToKMPH_thenSuccessfullyConverted();
	}
	
	public static void whenConvertingMPHToKMPH_thenSuccessfullyConverted() {
	    Movable bugattiVeyron = new BugattiVeyron();
	    MovableAdapter bugattiVeyronAdapter = new MovableAdapterImpl(bugattiVeyron);
	  
	    Double KMPH = new Double(431.30312);
	    
	    if (KMPH == bugattiVeyronAdapter.getSpeed()) {
	    	System.out.println("VELOCIDADE CONVERTIDA COM SUCESSO! " + KMPH);
	    } else {
	    	assertEquals(bugattiVeyronAdapter.getSpeed(), KMPH, 0.00001);
	    	System.out.println("ERRO AO CONVERTER A VELOCIDADE!");
	    }
	}
}