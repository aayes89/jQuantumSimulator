package quantum.gates;

import quantum.QuantumGate;
import quantum.QuantumState;

/**
 * @author Slam
 *
 * Implementación de la Transformada de Fourier Cuántica (QFT)
 * 
 * Operación: Transforma un estado cuántico en su representación de frecuencia.
 * Aplicación: Se utiliza en algoritmos cuánticos como Shor o estimación de fase.
 */
public class QFT extends QuantumGate {
    @Override
    public void apply(QuantumState state) {
        int n = state.getNumQubits();
        double[] real = state.getRealPart();
        double[] imag = state.getImagPart();

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < (1 << n); j++) {
                double angle = 2 * Math.PI * ((j & (1 << k)) >> k) / (1 << (k + 1));
                double cos = Math.cos(angle);
                double sin = Math.sin(angle);

                double newReal = cos * real[j] - sin * imag[j];
                double newImag = sin * real[j] + cos * imag[j];

                real[j] = newReal;
                imag[j] = newImag;
            }
        }
    }
}
