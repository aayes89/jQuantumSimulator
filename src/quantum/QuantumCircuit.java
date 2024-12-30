package quantum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuantumCircuit {

    private final List<QuantumGate> gates;
    private final QuantumState state;
    private final QuantumMeasurement measurement;
    private final QuantumNoise quantumNoise;

    public QuantumCircuit(int numQubits) {
        this.gates = new ArrayList<>();
        this.state = new QuantumState(numQubits);
        this.measurement = new QuantumMeasurement();
        this.quantumNoise = new QuantumNoise();
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

    public void applyNoise(double gamma) {
        quantumNoise.applyAmplitudeDamping(state, gamma);
    }

    public int[] measure() {
        return measurement.measure(state);
    }

    public int measureAndCollapse() {
        return measurement.measureAndCollapse(state);
    }

    public void printClassicState() {
        int[] classicalState = measure();
        System.out.println("Estado colapsado: " + Arrays.toString(classicalState) + "\n");
    }
}
