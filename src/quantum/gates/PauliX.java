package quantum.gates;

import quantum.QuantumGate;
import quantum.QuantumState;

/**
 * @author Slam
 * 
 * Implementación de la puerta Pauli-X (NOT Gate)
 * 
 * Operación: Invierte el estado del qubit (|0> ↔ |1>).
 * Qubit objetivo: Cambia su estado sin depender de un control externo.
 */
public class PauliX extends QuantumGate {

    private int targetQubit;

    public PauliX(int targetQubit) {
        this.targetQubit = targetQubit;
    }

    // NOT Gate
    public void applyPauliX(int target, double[] real, double[] imag) {
        int size = real.length;
        for (int i = 0; i < size; i++) {
            if ((i & (1 << target)) == 0) {
                int swapIdx = i | (1 << target);
                double tempReal = real[i];
                double tempImag = imag[i];
                real[i] = real[swapIdx];
                imag[i] = imag[swapIdx];
                real[swapIdx] = tempReal;
                imag[swapIdx] = tempImag;
            }
        }
    }

    @Override
    public void apply(QuantumState state) {
        int[] targets = calculateTargets(targetQubit, state.getNumQubits());
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();

        for (int target : targets) {
            int partner = target ^ (1 << targetQubit);
            double tempReal = real[target];
            double tempImag = imag[target];
            real[target] = real[partner];
            imag[target] = imag[partner];
            real[partner] = tempReal;
            imag[partner] = tempImag;
        }
    }

    public void apply1(QuantumState state) {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();
        int size = real.length;
        for (int i = 0; i < size; i++) {
            if ((i & (1 << targetQubit)) == 0) {
                int swapIdx = i | (1 << targetQubit);
                double tempReal = real[i];
                double tempImag = imag[i];
                real[i] = real[swapIdx];
                imag[i] = imag[swapIdx];
                real[swapIdx] = tempReal;
                imag[swapIdx] = tempImag;
            }
        }
    }
}
