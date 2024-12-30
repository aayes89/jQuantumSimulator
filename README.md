# ¿Simulador Cuántico básico en Java?
<p>El propósito es crear un simulador para ordenadores cuánticos que permita emular los comportamientos esperados en uno real utilizando la potencia de cómputo de un ordenador convencional.
  <br>Actualmente se encuentra en desarrollo y aceptaría cualquier ayuda posible que permita aumentar las capacidades y potencialidades que ofrece.
</p>

# Análisis de sus componentes
<b>QuantumGate:</b>
* Función <b>apply</b>: Como es abstracta, permite que cada tipo de puerta (e.g., X, H, CNOT) implemente su comportamiento específico. (Esto es una buena práctica)
* Función <b>calculateTargets</b>: Calcula los índices de los estados objetivo para una operación en un qubit específico, lo que es esencial para aplicar puertas correctamente. Es eficiente para puertas de un solo qubit. Para puertas de dos o más qubits, debo extender el manejo.
  
<b> QuantumState:</b>
* Estado inicial: Inicia con estado base ∣0⟩∣0⟩, lo cual es estándar y funcional.
* Almacenamiento de amplitudes: Separar las partes real e imaginaria para manejar números complejos en Java, ya que Java no tiene un tipo nativo para números complejos.
* Visualización <b>printState</b>: Es útil para depurar y entender el estado cuántico.

<b> Compuertas Implementadas: </b>
<b>Hadamard</b>, <b>CNOT</b>, <b>Pauli-X</b>, <b>Pauli-Z</b>, <b>CCX (Toffoli)</b>, <b>Rx</b>, <b>SWAP</b>, <b>T</b> y <b>QFT</b>.

# Por implementar y/o mejorar:
1. Implementar Algoritmos Cuánticos:
   -    Algoritmo de Grover: Para búsqueda no estructurada en O(sqrt(N)).
   -    Algoritmo de Deutsch-Josza: Para verificar si una función es constante o balanceada con un solo paso.
   -    Soporte para puertas paramétricas y Transformada de Fourier Cuántica (QFT).
   -    Algoritmo de Shor: Para factorización de números enteros. (Requiere trabajar con transformadas de Fourier cuánticas).
   -    Soporte para Estados entrelazados (Bell y GHZ)
2. Agregar Decoherencia.
3. Visualización del Circuito.
4. Añadir Simulación de Medición y Colapso del Estado.
5. Optimización del Simulador.

# Sobre el proyecto
<p><b>Este proyecto no tiene ningún tipo de financiamiento o apoyo hasta el momento, todo cuanto existe en él es debido al interés y curiosidad del desarrollador por este tema emergente. 
  Si gusta apoyar con la continuidad del mismo, no dude en contactarme.</b></p>
