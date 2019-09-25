package Adapter;

public class MovableAdapterImpl implements MovableAdapter {
    private Movable luxuryCars;
     
    // standard constructors
    public MovableAdapterImpl(Movable luxuryCar) {
    	this.luxuryCars = luxuryCar;
    }
 
    @Override
    public double getSpeed() {
        return convertMPHtoKMPH(luxuryCars.getSpeed());
    }
     
    private double convertMPHtoKMPH(double mph) {
        return mph * 1.60934;
    }
}
