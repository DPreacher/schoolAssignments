/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingrama1.Model;

/**
 *
 * @author Daniel
 */
public class Tractor {
    int engineRPM;
    final int wheelCircumference;

    private void setRPM() {
        this.engineRPM=24*this.throttleControl;
    }
    public enum Gear{NUETRAL,LOW,MID,HIGH}
    Gear gear;

    public Tractor() {
        this.throttleControl=0;
        this.gear=Gear.NUETRAL;
        this.wheelCircumference=4;
        this.engineRPM=-1;
    }
    public void startEninge(){
        if(this.throttleControl>10&&this.gear==Gear.NUETRAL){
           setRPM();
            
        }
    }
    public void stopEnigne(){
        this.engineRPM=-1;
    }
    private int throttleControl;

    /**
     * Set the value of throttleControl
     *
     * @param throttleControl new value of throttleControl
     */
    public void setThrottleControl(int throttleControl) {
        //Validate throttle Control
        if(throttleControl >=0&&throttleControl<=100){
            this.throttleControl = throttleControl;
            //check engine on and, if not on, set rpm 
            if(this.engineRPM!=-1){
                this.setRPM();
            }
        }
    }

    @Override
    public String toString() {
        String started;
        if(this.engineRPM==-1)
            started="not started";
        else
            started="is started";
        return "Tractor: "+started +", throttle ";/* 
    }engineRPM: " + engineRPM + ", wheelCircumference=" + wheelCircumference + ", gear=" + gear + ", throttleControl=" + throttleControl + '}';
    */
}
