
package quantum;

/**
 *
 * @author Slam
 */

public class QuantumState {
    private double[] real;
    private double[] imag;
    private int numQubits;

    public QuantumState(int numQubits) {
        this.numQubits = numQubits;
        int size = (int) Math.pow(2, numQubits);
        this.real = new double[size];
        this.imag = new double[size];
        this.real[0] = 1.0; // Estado inicial |0>
    }

    public double[] getRealPart() {
        return real;
    }

    public double[] getImagPart() {
        return imag;
    }

    public int getNumQubits() {
        return numQubits;
    }

    public void printState() {
        System.out.println("Estado cuántico:");
        for (int i = 0; i < real.length; i++) {
            System.out.printf("|%s> -> %.2f + %.2fi\n", Integer.toBinaryString(i), real[i], imag[i]);
        }
    }
}
