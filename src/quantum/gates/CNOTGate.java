package quantum.gates;

import quantum.QuantumState;
import quantum.QuantumGate;

/**
 * @author Slam
 * 
 * Implementación de la puerta CNOT (Controlled-NOT)
 *
 * Qubit de control: Si está en |1>, aplica NOT al qubit objetivo. Qubit
 * objetivo: Cambia de estado si el control está en |1>.
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
        int numQubits = state.getNumQubits();
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();

        // Iterar sobre todos los posibles estados en la superposición
        for (int i = 0; i < real.length; i++) {
            // Verificar si el control está en |1>
            if (((i >> controlQubit) & 1) == 1) {
                // Calcular el índice objetivo
                int targetIndex = i ^ (1 << targetQubit);

                // Intercambiar amplitudes entre el índice actual y el índice objetivo
                double tempReal = real[i];
                double tempImag = imag[i];
                real[i] = real[targetIndex];
                imag[i] = imag[targetIndex];
                real[targetIndex] = tempReal;
                imag[targetIndex] = tempImag;
            }
        }
    }
}
