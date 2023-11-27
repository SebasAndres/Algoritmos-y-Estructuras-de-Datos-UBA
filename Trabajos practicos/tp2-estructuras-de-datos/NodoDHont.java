package aed;

public class NodoDHont{

    /*
    Sobre NodoDHont:
    En cada nodo del heap, utilizamos una estructura aparte que se llama NodoDHont. 
    Esta estructura tiene como variables el idPartido, votosOriginales (los votos de este partido obtenidos en diputados),
    el cociente actual, que depende de la cantidad de veces que fue dividido para obtener los escaños. 
    Este valor es el que utilizamos para determinar si un nodo es mayor que otro. 
    De este modo, podemos obtener el valor máximo del heap en O(1), ya que siempre estará en la raíz. 
    */

    public int idPartido;
    public int votosOriginales;
    public int cociente;
    public int escañosAsignados;

    public NodoDHont(int idPartido, int votos){
        this.idPartido = idPartido;
        this.votosOriginales = votos;
        this.cociente = votos;
        this.escañosAsignados = 0;
    }

}

