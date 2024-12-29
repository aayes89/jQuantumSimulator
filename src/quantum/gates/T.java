package quantum.gates;

import quantum.QuantumGate;
import quantum.QuantumState;

/**
 *
 * @author Slam
 */
public class T extends QuantumGate {

    private int targetQubit;

    public T(int targetQubit) {
        this.targetQubit = targetQubit;
    }

    @Override
    public void apply(QuantumState state) {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();

        double phaseReal = Math.cos(Math.PI / 4);
        double phaseImag = Math.sin(Math.PI / 4);
        int size = real.length;

        for (int i = 0; i < size; i++) {
            if ((i & (1 << targetQubit)) != 0) {
                double realTemp = real[i] * phaseReal - imag[i] * phaseImag;
                double imagTemp = real[i] * phaseImag + imag[i] * phaseReal;
                real[i] = realTemp;
                imag[i] = imagTemp;
            }
        }
    }

    // T gate
    public void applyT(int target, double[] real, double[] imag) {
        double phaseReal = Math.cos(Math.PI / 4);
        double phaseImag = Math.sin(Math.PI / 4);
        int size = real.length;

        for (int i = 0; i < size; i++) {
            if ((i & (1 << target)) != 0) {
                double realTemp = real[i] * phaseReal - imag[i] * phaseImag;
                double imagTemp = real[i] * phaseImag + imag[i] * phaseReal;
                real[i] = realTemp;
                imag[i] = imagTemp;
            }
        }
    }

}
