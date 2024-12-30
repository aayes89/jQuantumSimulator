package quantum;

import quantum.gates.SWAP;
import quantum.gates.CCX;
import quantum.gates.HadamardGate;

/**
 *
 * @author Slam
 */
public class QuantumCircuitSimulator {

    public static void main(String[] args) {
        QuantumCircuit circuit = new QuantumCircuit(2);

        // Añadimos puertas al circuito
        circuit.addGate(new HadamardGate(0)); // Hadamard en qubit 0
        circuit.addGate(new HadamardGate(1)); // Hadamard en qubit 1
        circuit.addGate(new SWAP(0, 1));      // SWAP
        circuit.addGate(new CCX(0, 1, 2));    // CCX
        circuit.addGate(new CCX(1, 2, 1));    // CCX
        // Ejecutamos el circuito
        circuit.execute();

        // Imprimimos el estado final
        circuit.printState();
        // Imprimir estado clásico
        circuit.printClassicState();
        
        // Estado de Bell
        System.out.println("Estado de Bell");        
        circuit.addGate(new HadamardGate(0)); // Hadamard al qubit 0
        circuit.addGate(new CCX(0, 1,2)); // CNOT entre qubit 0 y 1
        circuit.execute();
        circuit.printState();

    }
}
