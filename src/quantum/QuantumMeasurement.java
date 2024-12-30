package quantum;

/**
 * @author Slam
 * 
 * Implementación para operaciones de mediciones
 */
public class QuantumMeasurement {

    private final QuantumUtils quantumUtils;

    public QuantumMeasurement() {
        this.quantumUtils = new QuantumUtils();
    }

    public int[] measure(QuantumState state) {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();
        int size = real.length;
        double[] probabilities = quantumUtils.calculateProbabilities(real, imag);

        double rand = Math.random();
        double cumulative = 0.0;

        for (int i = 0; i < size; i++) {
            cumulative += probabilities[i];
            if (rand < cumulative) {
                return quantumUtils.decodeState(i, (int) (Math.log(size) / Math.log(2)));
            }
        }

        throw new IllegalStateException("Measurement failed: invalid probabilities.");
    }

    public int measureAndCollapse(QuantumState state) {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();
        double[] probabilities = new double[real.length];
        double random = Math.random();
        double cumulative = 0.0;

        for (int i = 0; i < real.length; i++) {
            probabilities[i] = Math.pow(real[i], 2) + Math.pow(imag[i], 2);
            cumulative += probabilities[i];
            if (random < cumulative) {
                // Colapsar estado al resultado medido
                for (int j = 0; j < real.length; j++) {
                    if (j != i) {
                        real[j] = 0;
                        imag[j] = 0;
                    }
                }
                real[i] = 1.0;
                imag[i] = 0.0;
                return i;
            }
        }
        return -1; // Nunca debería ocurrir
    }
}
