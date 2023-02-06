/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package radarproject2.pkg0;

import java.awt.Polygon;

/**
 *
 * @author probs
 */
public class Sweeper extends Polygon{
    private double theta, dtheta, radius;
    private int circleWidth = 305*2;
    private double radarTheta = Math.PI/190;
    private int index=0;
    public Sweeper (){
        npoints = 20;
        xpoints = new int[npoints];
        ypoints = new int[npoints];
        xpoints[0] = 700/2;
        ypoints[0] = 700/2;
        xpoints[1] = xpoints[0];
        ypoints[1] = ypoints[0]-305;
        
        for (int i = 0; i < 18; i++) {
            xpoints[i+2] = (int)((circleWidth/2)*Math.cos(radarTheta*i-Math.PI/2))+xpoints[0];
            ypoints[i+2] = (int)((circleWidth/2)*Math.sin(radarTheta*i-Math.PI/2)+ypoints[0]);
        }
        xpoints[19] = xpoints[0];
        ypoints[19] = ypoints[0];
        theta = 0;
    }
    public void setDTheta (double theta){
        dtheta = theta;
    }
    public double getDTheta (){
        return dtheta;
    }
    public void rotate(){
        // xpoints[0] and ypoints[0] will stay the same.
        // Take the xpoints and ypoints ahead of the one at i, and replace it.
        for (int i = 1; i < 18; i++) {
            xpoints[i] = xpoints[i+1];
            ypoints[i] = ypoints[i+1];
        }
        // Take xpoints[18] and ypoints[18] and redefine that last point.
        theta += dtheta;
        xpoints[18] = (int)((circleWidth/2)*Math.cos(theta-Math.PI/2))+xpoints[0];
        ypoints[18] = (int)((circleWidth/2)*Math.sin(theta-Math.PI/2)+ypoints[0]);
        if (theta > Math.PI*2){
            theta = 0;
        }
        // xpoints[19] and ypoints[19] will stay the same
    }
    public boolean intersects (Polygon poly){
        boolean isIntersect = false;
        int xMax=xpoints[0],yMax=ypoints[0],xMin=xMax,yMin=yMax,polyXMax=poly.xpoints[0],polyYMax=poly.ypoints[0],polyXMin=polyXMax,polyYMin=polyYMax;
        for (int x : xpoints) {
            if(x>xMax){
                xMax=x;
            }
            if (x<xMin) {
                xMin=x;
            }
        }
        for (int y : ypoints) {
            if(y>yMax){
                yMax=y;
            }
            if (y<yMin) {
                yMin=y;
            }
        }
        for (int polyX : poly.xpoints) {
            if(polyX>polyXMax){
                polyXMax=polyX;
            }
            if (polyX<polyXMin) {
                polyXMin=polyX;
            }
        }
        for (int polyY : poly.ypoints) {
            if(polyY>polyYMax){
                polyYMax=polyY;
            }
            if (polyY<polyYMin) {
                polyYMin=polyY;
            }
        }
        if(xMax>polyXMax && xMin<polyXMin && yMax>polyYMax && yMin<polyYMin){
            isIntersect=true;
        }
        return isIntersect;
    }
}
