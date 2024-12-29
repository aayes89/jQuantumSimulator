
package quantum.gates;

import quantum.QuantumGate;
import quantum.QuantumState;

/**
 *
 * @author Slam
 */
public class CNOTGate extends QuantumGate {

    private int controlQubit;
    private int targetQubit;

    public CNOTGate(int controlQubit, int targetQubit) {
        this.controlQubit = controlQubit;
        this.targetQubit = targetQubit;
    }

    @Override
    public void apply(QuantumState state) {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();
        int numQubits = state.getNumQubits();
        int size = (int) Math.pow(2, numQubits);

        // Iteramos por todos los estados posibles
        for (int i = 0; i < size; i++) {
            // Verificamos si el bit de control está activado (estado |1⟩)
            if (((i >> controlQubit) & 1) == 1) {
                // Calculamos el índice del estado objetivo
                int targetIndex = i ^ (1 << targetQubit);

                // Intercambiamos las amplitudes de los estados
                double realTemp = real[i];
                double imagTemp = imag[i];

                real[i] = real[targetIndex];
                imag[i] = imag[targetIndex];

                real[targetIndex] = realTemp;
                imag[targetIndex] = imagTemp;
            }
        }
    }

    // CNOT gate
    public void applyCNOT(int control, int target, double[] real, double[] imag) {
        int size = real.length;
        for (int i = 0; i < size; i++) {
            if ((i & (1 << control)) != 0) {
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
}
