
package changeclient;

import java.io.Serializable;
import java.text.DecimalFormat;

public final class ConvertWeight implements Serializable {

    //value of indexes in the vector and amounts.
    private int indexA, indexB;
    private double amountA, amountB;

    public ConvertWeight() {
        super();
        this.indexA = 0; // index in WeightConvertVector and the same in convertList
        this.amountA = 0;//the amount of A
        this.indexB = 0; // index in WeightConvertVector and the same in convertList
        this.amountB = 0;//the amount of B
    }

    public int getIndexA() {
        return indexA;
    }

    public int getIndexB() {
        return indexB;
    }

    public void setIndexA(int indexA) {
        this.indexA = indexA;
    }

    public void setIndexB(int indexB) {
        this.indexB = indexB;
    }

    @Override
    public String toString() {
        return "ConvertWeight{" + "indexA=" + indexA + ", indexB=" + indexB + ", amountA=" + amountA + ", amountB=" + amountB + '}';
    }

   

    public double getAmountA() {
        amountA = Double.parseDouble(new DecimalFormat("##.###").format(amountA));
        return amountA;
    }

    public void setAmountA(double amountA) {
        this.amountA = amountA;
    }

    public double getAmountB() {

        amountB = Double.parseDouble(new DecimalFormat("##.###").format(amountB));
        return amountB;
    }

    public void setAmountB(double amountB) {
        this.amountB = amountB;
    }

    

}
