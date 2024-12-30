
package quantum.gates;

import quantum.QuantumGate;
import quantum.QuantumState;

/**
 *
 * @author Slam
 */
public class CCX extends QuantumGate {

    private int targetQubit;
    private int control1;
    private int control2;

    public CCX(int targetQubit, int control1, int control2) {
        this.targetQubit = targetQubit;
        this.control1 = control1;
        this.control2 = control2;
    }

    // CCX gate (Toffoli)
    public void applyCCX(int control1, int control2, int target, double[] real, double[] imag) {
        int size = real.length;
        for (int i = 0; i < size; i++) {
            if ((i & (1 << control1)) != 0 && (i & (1 << control2)) != 0) {
                int targetBit = 1 << target;
                int idx1 = i;
                int idx2 = i ^ targetBit;

                double realTemp = real[idx1];
                double imagTemp = imag[idx1];
                real[idx1] = real[idx2];
                imag[idx1] = imag[idx2];
                real[idx2] = realTemp;
                imag[idx2] = imagTemp;
            }
        }
    }

    @Override
    @Override
    public void apply(QuantumState state) {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();
        int size = real.length;
        for (int i = 0; i < size; i++) {
            if ((i & (1 << control1)) != 0 && (i & (1 << control2)) != 0) {
                int targetBit = 1 << targetQubit;
                int idx1 = i;
                int idx2 = i ^ targetBit;

                double realTemp = real[idx1];
                double imagTemp = imag[idx1];
                real[idx1] = real[idx2];
                imag[idx1] = imag[idx2];
                real[idx2] = realTemp;
                imag[idx2] = imagTemp;
            }
        }
    }

}
