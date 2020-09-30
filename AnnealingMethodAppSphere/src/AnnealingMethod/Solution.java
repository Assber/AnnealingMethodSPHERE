package AnnealingMethod;

/**
 *
 * @author Almaz
 */
public class Solution {

    private Double r;
    private Double p;
    private Double h;
    private Double x;
    private Double m;
    private Double wСalculated;
    private Double wTheoretical;
    private Double wDiff;

    public Solution() {
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public Double getM() {
        return m;
    }

    public void setM(Double m) {
        this.m = m;
    }

    public Double getwСalculated() {
        return wСalculated;
    }

    public void setwСalculated(Double wСalculated) {
        this.wСalculated = wСalculated;
    }

    public Double getwTheoretical() {
        return wTheoretical;
    }

    public void setwTheoretical(Double wTheoretical) {
        this.wTheoretical = wTheoretical;
    }

    public Double getwDiff() {
        return wDiff;
    }

    public void setwDiff(Double wDiff) {
        this.wDiff = wDiff;
    }

}
