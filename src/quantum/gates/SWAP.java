package quantum.gates;

import quantum.QuantumGate;
import quantum.QuantumState;

/**
 *
 * @author Slam
 */
public class SWAP extends QuantumGate {

    private int targetQubit1;
    private int targetQubit2;

    public SWAP(int targetQubit1, int targetQubit2) {
        this.targetQubit1 = targetQubit1;
        this.targetQubit2 = targetQubit2;
    }

    // SWAP gate
    public void applySWAP(int qubit1, int qubit2, double[] real, double[] imag) {
        int size = real.length;
        for (int i = 0; i < size; i++) {
            // Cambiar los índices de acuerdo a los qubits seleccionados
            int swappedIndex1 = i ^ (1 << qubit1);
            int swappedIndex2 = i ^ (1 << qubit2);

            if (swappedIndex1 != swappedIndex2) {
                double realTemp = real[swappedIndex1];
                double imagTemp = imag[swappedIndex1];
                real[swappedIndex1] = real[swappedIndex2];
                imag[swappedIndex1] = imag[swappedIndex2];
                real[swappedIndex2] = realTemp;
                imag[swappedIndex2] = imagTemp;
            }
        }
    }

    @Override
    public void apply(QuantumState state) {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();
        int size = real.length;

        for (int i = 0; i < size; i++) {
            // Se calcula el índice de intercambio de acuerdo a los qubits seleccionados
            int swappedIndex1 = i ^ (1 << targetQubit1);
            int swappedIndex2 = i ^ (1 << targetQubit2);

            // Intercambiar solo si los índices son distintos
            if (swappedIndex1 != swappedIndex2) {
                double realTemp = real[swappedIndex1];
                double imagTemp = imag[swappedIndex1];
                real[swappedIndex1] = real[swappedIndex2];
                imag[swappedIndex1] = imag[swappedIndex2];
                real[swappedIndex2] = realTemp;
                imag[swappedIndex2] = imagTemp;
            }
        }
    }
}
