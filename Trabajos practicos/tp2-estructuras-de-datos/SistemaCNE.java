package aed;
public class SistemaCNE {
    /*

    INVREP SISTEMA_CNE:
    * La longitud de nombrePartido, votosPresidenciales, es P.    
    * La longitud de nombreDistrito, diputadosEnDisputa es D. 
    * nombrePartido tiene en cada índice el nombre de su partido correspondiente.    
    * nombreDistrito tiene en cada índice el nombre de su distrito correspondiente.
    * diputadosEnDisputa tiene en cada índice (correspondiente al índice del distrito) 
      cuántos diputados le corresponden a ese distrito, un número >= 0.
    * votosDiputados es una matriz de D x P. En cada fila hay un array de longitud P que tiene los votos de cada partido en cada indice. 
      Cada array es un distrito. Para cualquier d y p valido se cumple votosDiputatos[d][p] >= 0.
    * escañosAsignados es una matriz de D x P-1, porque no incluyo los votos en blanco para asignar escaño.
      En cada fila d hay un array (correspondiente al distrito d) de longitud P-1 que tiene los escaños asignados previamente de cada partido.
    * fueCalculado es un array de booleanos de longitud D, que indica si a cada distrito se le calcularon ya sus escaños,
      es decir si en todos los nodos del heap correspondiente a ese distrito el valorOriginal es igual al cociente.    
    * hayBallotage indica el estado actual del array de votosPresidenciales. Es calculado cada vez que se registra una nueva mesa.
      Este atributo es True <=> para los votos presidenciales registrados hasta el momento hay ballotage.
    * mesasPorDistritos tiene, en cada índice correspondiente a cada distrito, el último número de mesa de ese distrito.
      Cumple que es una lista ordenada.    
    * resultadosPorDistrito es un array que tiene en cada indice D un heap asociado a los votos de diputados del distrito D.
      Es decir que a cada distrito le corresponde un Heap.
    * escañosAsignados es una matriz de enteros D x P-1 que indica la cantidad de escaños que le fueron asignados a un partido P en el distrito D.

    Observacion: El invariante de representacion del Heap se encuentra en el archivo MaxHeap.java
    */

 
    private int P; // cantidad de partidos + 1 (incluye voto en blanco)
    private int D; // cantidad de distritos
    private String[] nombrePartido; // array de nombres de partidos (indice como id)
    private String[] nombreDistrito; // array de nombres de distritos (indice como id)
    private int[] diputadosEnDisputa; // cantidad de bancas a repartir por distrito
    private int[][] votosDiputados; // matriz de votos de diputados P x D
    private int[] votosPresidenciales; // array de votos presidenciales por partido
    private boolean hayBallotage; // variable de control para el ballotage
    private int[] mesasPorDistritos; // ultima mesa de cada distrito (array ordenado)
    private MaxHeap[] resultadosPorDistritos; // array de Heaps con los resultados de diputados por distrito
    private boolean[] fueCalculado; // array de D booleanos que indica si ya tenemos calculadas las bancas para el distrito 
    private int[][] escañosAsignados; // array de D x P-1 con las bancas asignadas por partido en los distritos ya calculados

    public class VotosPartido{
        // ambos >= 0
        private int presidente;
        private int diputados;
        VotosPartido(int presidente, int diputados){this.presidente = presidente; this.diputados = diputados;}
        public int votosPresidente(){return presidente;}
        public int votosDiputados(){return diputados;}
    }

    public SistemaCNE(String[] nombresDistritos,
                      int[] diputadosPorDistrito,
                      String[] nombresPartidos,
                      int[] ultimasMesasDistritos) {

        // Método constructor de la clase SistemaCNE.
        // Complejidad: O(D)

        // Inicializamos variables privadas
        this.nombrePartido = nombresPartidos;
        this.nombreDistrito = nombresDistritos;
        this.diputadosEnDisputa = diputadosPorDistrito;
        this.P = nombresPartidos.length; 
        this.D = nombresDistritos.length;
        this.votosDiputados = new int[D][P];
        this.votosPresidenciales = new int[P];
        this.hayBallotage = false;
        this.mesasPorDistritos = ultimasMesasDistritos;
        
        // Construimos un array de MaxHeaps (de long P) inicializados vacíos
        this.resultadosPorDistritos = new MaxHeap[D];

        // Construimos el array de fueCalculado (todos en false) y el array de escañosAsignados (todos en 0)
        this.fueCalculado = new boolean[D];
        this.escañosAsignados = new int[D][P-1];

        // Inicializamos los MaxHeaps en 0, con longitud P-1 así sacamos los votos en blanco
        for (int i=0; i<this.D; i++)
            this.resultadosPorDistritos[i] = new MaxHeap(P-1);
    }

    public String nombrePartido(int idPartido) {
        // Complejidad: O(1)
        return this.nombrePartido[idPartido];
    }

    public String nombreDistrito(int idDistrito) {
        // Complejidad: O(1)
        return this.nombreDistrito[idDistrito];
    }

    public int diputadosEnDisputa(int idDistrito) {
        // Complejidad: O(1)
        return this.diputadosEnDisputa[idDistrito];
    }

    public int idDistritoMesa(int idMesa){        
        // Recibe un idMesa y devuelve el idDistrito al que pertenece,
        // Como la lista de mesasPorDistrito está ordenada, realizamos una búsqueda binaria, 
        // Complejidad: O(log D)

        int low = 0;
        int high = this.mesasPorDistritos.length - 1;      

        // Requiere que el idMesa sea válido (idMesa < mesasPorDistritos[mesasPorDistritos.length - 1])
        // CREO QUE ESTA LINEA PODRIAMOS COMENTARLA
        if (idMesa >= this.mesasPorDistritos[high]) return high;        
        
        while (low <= high){
            int mid = (low + high) / 2;
            if (idMesa < this.mesasPorDistritos[mid])
                high = mid - 1;
            else 
                low = mid + 1;
        }
 
        return high+1;
    }

    public String distritoDeMesa(int idMesa) {
        // Complejidad: O(1)
        return this.nombreDistrito[idDistritoMesa(idMesa)];
    }

    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) {
        // Registra los votos presidenciales y de diputados dada una mesa.
        // Complejidad: O(P + log D)

        // 1. Buscamos el distrito de la mesa con la busqueda binaria: O(log D)
        int idDistrito = idDistritoMesa(idMesa);

        // 2. Sumamos los votos presidenciales y de diputados: O(P)
        for (int i=0; i<this.P; i++){
            this.votosPresidenciales[i] += actaMesa[i].votosPresidente();
            this.votosDiputados[idDistrito][i] += actaMesa[i].votosDiputados();
        }

        // 3. Actualizamos la variable de control hayBallotage: O(P)
        this.hayBallotage = auxBallotage();

        // 4. Actualizamos el heap con los votos de diputado asociado al distrito (se crea uno nuevo).
        int[] votosDistrito = this.votosDiputados[idDistrito].clone(); 
        this.resultadosPorDistritos[idDistrito] = new MaxHeap(votosDistrito); // O(P) ~ Algoritmo de Floyd       
    }

    private boolean auxBallotage() {
        // Valida si "hay ballotage" hasta el momento, es decir, 
        // si el total de votos fuera Sum(votosPresidenciales).
        // Complejidad: O(P)
        
        int primero = 0;
        int segundo = 0;
        int totalVotos = 0;

        for (int i=0; i<P; i++){
            if (this.votosPresidenciales[i] > primero){
                segundo = primero;
                primero = this.votosPresidenciales[i];
            }
            else if (this.votosPresidenciales[i] > segundo){
                segundo = this.votosPresidenciales[i];
            }
            totalVotos += this.votosPresidenciales[i];
        }
        
        if (primero > totalVotos*0.45 
            || ((primero - segundo) > 0.1*totalVotos && primero > 0.4*totalVotos))
            return false;

        return true;
    }

    public int votosPresidenciales(int idPartido) {
        // Complejidad: O(1)
        return this.votosPresidenciales[idPartido];
    }

    public int votosDiputados(int idPartido, int idDistrito) {
        // Complejidad: O(P)
        return this.votosDiputados[idDistrito][idPartido];
    }

    public int[] resultadosDiputados(int idDistrito){
        // Dado un distrito, pide devolver la cantidad de bancas asignadas por partido
        // en las elecciones de diputados para ese distrito.
        // Complejidad: pide O(Dd * log P)

        // Inicializamos un array de P-1 porque el voto en blanco no puede tener bancas
        int[] res = new int[this.P-1]; 
        int Dd = diputadosEnDisputa(idDistrito); // O(1)

        // Validamos si ya sabemos las bancas de este distrito,
        // esto es para evitar que se vuelvan a realizar las asignaciones
        // con los cocientes DHont ya modificados
        if (this.fueCalculado[idDistrito]){
            return this.escañosAsignados[idDistrito];
        }
        else{
            // Por cada banca a asignar le asignamos un partido a través de la raíz del heap
            // del distrito
            for (int i=0; i<Dd; i++){ // O(Dd)
                int idPartido = this.resultadosPorDistritos[idDistrito].proximaBanca(); // O(log P)
                res[idPartido] += 1;
            }

            // Actualizamos los escaños asignados
            this.fueCalculado[idDistrito] = true;
            this.escañosAsignados[idDistrito] = res; 
            return res;
        }
    }

    public boolean hayBallotage(){
        // Complejidad: O(1)
        return this.hayBallotage;
    }
}

