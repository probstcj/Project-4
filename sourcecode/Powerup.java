/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package radarproject2.pkg0;

import java.awt.Color;
import java.awt.Polygon;

/**
 *
 * @author probs
 */
public class Powerup extends Polygon{
    
    private int health, powerup;
    private int[] dx,dy;
    private Color color;
    private double dr, theta, drad;
    public Powerup(double theta, int health, int powerup){
        // List of powerups
        /*
        1. Nuke
        2. Extend Sweeper
        3. Faster shooting
        4. Added Sweeper
        5. Freeze all enemies
        6. Show all enemies
        7. Double Damage
        8. Heal to Full Health
        */
        if(powerup<9){
            this.powerup = powerup;
        }
        else{
            throw new IllegalArgumentException("int Powerup must be an integer between 1-7");
        }
        color = Color.GREEN;
        this.health=health;
        this.theta = theta;
        // Center is (350,350)
        // Create a triangle using the nGon class, centered at the center
        int sides =3;
        double thetaRad = ((double)2/sides)*Math.PI;
        double radius = (health/8)/(2*Math.sin(thetaRad/2));
        npoints = sides;
        xpoints = new int[sides];
        ypoints = new int[sides];
        for (int i = 0; i < sides; i++) {
            xpoints[i] = (int)(radius*(double)Math.cos(thetaRad*i-(Math.PI/(double)2))+350);
            ypoints[i] = (int)(radius*(double)Math.sin(thetaRad*i-(Math.PI/(double)2))+350);
        }
        // Take the points, and put them on the outer radius, at a rotation theta from x-axis
        
        for (int i = 0; i < xpoints.length; i++) {
            xpoints[i] = xpoints[i]-(int)(300*Math.cos(theta-Math.PI));
            ypoints[i] = ypoints[i]-(int)(300*Math.sin(theta-Math.PI));
        }
        dr = (double)20/health;
        drad = dr;
        
        dx = new int[3];
        dy = new int[3];
        for (int i = 0; i < 3; i++) {
            if (i==2){
                dx[i] = xpoints[0] - xpoints[i];
                dy[i] = ypoints[0] - ypoints[i];
            }
            else{
                dx[i] = xpoints[i+1] - xpoints[i];
                dy[i] = ypoints[i+1] - ypoints[i];
            }
        }
    }
    public Color getColor(){
        return color;
    }
    public void move(){
        for (int i = 0; i < xpoints.length; i++) {
            xpoints[i] = (int)(350-300*Math.cos(theta-Math.PI)+dr*Math.cos(theta-Math.PI))-dx[i];
            ypoints[i] = (int)(350-300*Math.sin(theta-Math.PI)+dr*Math.sin(theta-Math.PI))-dy[i];
        }
        dr = dr+drad;
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public int getCenterX(){
        int sum =0;
        int i = 0;
        for (int x : xpoints) {
            sum += x;
            i++;
        }
        return sum/i;
    }
    public int getCenterY(){
        int sum =0;
        int i = 0;
        for (int y : ypoints) {
            sum += y;
            i++;
        }
        return sum/i;
    }
    public int getMaxX(){
        int max=xpoints[0];
        for (int x : xpoints) {
            if(x>max){
                max =x;
            }
        }
        return max;
    }
    public int getMaxY(){
        int max=ypoints[0];
        for (int y : ypoints) {
            if(y>max){
                max =y;
            }
        }
        return max;
    }
    public int getMinX(){
        int min=xpoints[0];
        for (int x : xpoints) {
            if(x<min){
                min =x;
            }
        }
        return min;
    }
    public int getMinY(){
        int min=ypoints[0];
        for (int y : ypoints) {
            if(y<min){
                min =y;
            }
        }
        return min;
    }
    public int getPowerup(){
        return this.powerup;
    }
}
