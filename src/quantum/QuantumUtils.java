package quantum;

/**
 *
 * @author Slam
 *
 * Implementación de utilidades
 */
public class QuantumUtils {

    public QuantumUtils() {
    }

    public double[] calculateProbabilities(double[] real, double[] imag) {
        int size = real.length;
        double[] probabilities = new double[size];
        double total = 0.0;

        for (int i = 0; i < size; i++) {
            probabilities[i] = real[i] * real[i] + imag[i] * imag[i];
            total += probabilities[i];
        }

        for (int i = 0; i < size; i++) {
            probabilities[i] /= total;
        }

        return probabilities;
    }

    public int[] decodeState(int index, int numQubits) {
        int[] state = new int[numQubits];
        for (int i = 0; i < numQubits; i++) {
            state[i] = (index >> i) & 1;
        }
        return state;
    }
}
