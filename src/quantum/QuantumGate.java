package quantum;

/**
 *
 * @author Slam
 */
public abstract class QuantumGate {
    public abstract void apply(QuantumState state);

    protected int[] calculateTargets(int qubitIndex, int numQubits) {
        int size = (int) Math.pow(2, numQubits);
        int[] targets = new int[size / 2];
        int mask = 1 << qubitIndex;
        for (int i = 0, idx = 0; i < size; i++) {
            if ((i & mask) == mask) {
                targets[idx++] = i;
            }
        }
        return targets;
    }
}
