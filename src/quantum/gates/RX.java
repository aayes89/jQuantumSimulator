
package quantum.gates;

import quantum.QuantumGate;
import quantum.QuantumState;

/**
 *
 * @author Slam
 */
public class RX extends QuantumGate{
    private int targetQubit;
    private double angle;

    public RX(int targetQubit, double angle) {
        this.targetQubit = targetQubit;
        this.angle = angle;
    }    
    
    // RX gate: Rotación en X
    public void applyRX(int target, double angle, double[] real, double[] imag) {
        int size = real.length;
        double cos = Math.cos(angle / 2);
        double sin = Math.sin(angle / 2);

        for (int i = 0; i < size; i++) {
            if ((i & (1 << target)) != 0) {
                double realTemp = cos * real[i] - sin * imag[i];
                double imagTemp = sin * real[i] + cos * imag[i];
                real[i] = realTemp;
                imag[i] = imagTemp;
            }
        }
    }

    @Override
    public void apply(QuantumState state) {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();
        
        int size = real.length;
        double cos = Math.cos(angle / 2);
        double sin = Math.sin(angle / 2);

        for (int i = 0; i < size; i++) {
            if ((i & (1 << targetQubit)) != 0) {
                double realTemp = cos * real[i] - sin * imag[i];
                double imagTemp = sin * real[i] + cos * imag[i];
                real[i] = realTemp;
                imag[i] = imagTemp;
            }
        }
    }
    
}
