package quantum.gates;

import quantum.QuantumGate;
import quantum.QuantumState;

/**
 *
 * @author Slam
 */
public class PauliZ extends QuantumGate{
    private int targetQubit;

    public PauliZ(int targetQubit) {
        this.targetQubit = targetQubit;
    }    
    
    //Pauli-Z gate
    public void applyPauliZ(int target, double[] real, double[] imag) {
        int size = real.length;
        for (int i = 0; i < size; i++) {
            if ((i & (1 << target)) != 0) {
                real[i] = -real[i];
                imag[i] = -imag[i];
            }
        }
    }

    @Override
    public void apply(QuantumState state) {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();
        int size = real.length;
        for (int i = 0; i < size; i++) {
            if ((i & (1 << targetQubit)) != 0) {
                real[i] = -real[i];
                imag[i] = -imag[i];
            }
        }
    }
    
}
