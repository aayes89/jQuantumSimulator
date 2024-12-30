package quantum;

/**
 *
 * @author Slam
 *
 * Implementación para decoherencia
 */
public class QuantumNoise {

    public QuantumNoise() {
    }

    // Decoherencia por amplitud (idea básica)
    public void applyAmplitudeDamping(QuantumState state, double gamma) {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();
        int size = real.length;

        for (int i = 0; i < size; i++) {
            double prob = Math.pow(real[i], 2) + Math.pow(imag[i], 2);
            if (Math.random() < gamma * prob) {
                real[i] *= Math.sqrt(1 - gamma);
                imag[i] *= Math.sqrt(1 - gamma);
            }
        }
    }
}
