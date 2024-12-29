package quantum.gates;

import quantum.QuantumGate;
import quantum.QuantumState;

/**
 *
 * @author Slam
 */
public class HadamardGate extends QuantumGate {

    private int targetQubit;

    public HadamardGate(int targetQubit) {
        this.targetQubit = targetQubit;
    }

    @Override
    public void apply(QuantumState state) {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();
        int[] targets = calculateTargets(targetQubit, state.getNumQubits());

        for (int target : targets) {
            int pairIndex = target ^ (1 << targetQubit);
            double realTemp = (real[target] + real[pairIndex]) / Math.sqrt(2);
            double imagTemp = (imag[target] + imag[pairIndex]) / Math.sqrt(2);

            double realPair = (real[target] - real[pairIndex]) / Math.sqrt(2);
            double imagPair = (imag[target] - imag[pairIndex]) / Math.sqrt(2);

            real[target] = realTemp;
            imag[target] = imagTemp;
            real[pairIndex] = realPair;
            imag[pairIndex] = imagPair;
        }
    }

    // Hadamard gate
    private void applyHadamard(int target, double[] real, double[] imag) {
        int size = real.length;
        for (int i = 0; i < size; i += (1 << (target + 1))) {
            for (int j = 0; j < (1 << target); j++) {
                int idx1 = i + j;
                int idx2 = idx1 + (1 << target);

                double realTemp1 = (real[idx1] + real[idx2]) / Math.sqrt(2);
                double realTemp2 = (real[idx1] - real[idx2]) / Math.sqrt(2);
                double imagTemp1 = (imag[idx1] + imag[idx2]) / Math.sqrt(2);
                double imagTemp2 = (imag[idx1] - imag[idx2]) / Math.sqrt(2);

                real[idx1] = realTemp1;
                real[idx2] = realTemp2;
                imag[idx1] = imagTemp1;
                imag[idx2] = imagTemp2;
            }
        }
    }
}
