package quantum;

/**
 *
 * @author Slam
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuantumCircuit {

    private List<QuantumGate> gates;
    private QuantumState state;

    public QuantumCircuit(int numQubits) {
        this.gates = new ArrayList<>();
        this.state = new QuantumState(numQubits);
    }

    public void addGate(QuantumGate gate) {
        gates.add(gate);
    }

    public void execute() {
        for (QuantumGate gate : gates) {
            gate.apply(state);
        }
    }

    public void printState() {
        state.printState();
    }

    // Decoherencia por amplitud (idea básica)
    private void applyAmplitudeDamping(QuantumState state, double gamma) {
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

    public int[] measure() {
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();
        int size = real.length;
        double[] probabilities = calculateProbabilities(real, imag);

        double rand = Math.random();
        double cumulative = 0.0;

        for (int i = 0; i < size; i++) {
            cumulative += probabilities[i];
            if (rand < cumulative) {
                return decodeState(i, (int) (Math.log(size) / Math.log(2)));
            }
        }

        throw new IllegalStateException("Measurement failed: invalid probabilities.");
    }

    public int measure1() {
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

    private double[] calculateProbabilities(double[] real, double[] imag) {
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

    private static int[] decodeState(int index, int numQubits) {
        int[] state = new int[numQubits];
        for (int i = 0; i < numQubits; i++) {
            state[i] = (index >> i) & 1;
        }
        return state;
    }

    public void printClassicState() {
        int[] classicalState = measure();
        System.out.println("Estado colapsado: " + Arrays.toString(classicalState) + "\n");
    }
}
