package aed;

public class InternetToolkit {

    public InternetToolkit() {

    }

    public Fragment[] tcpReorder(Fragment[] fragments) {
        // Insertion Sort
        for (int i=1; i<fragments.length; i++){
            int j = i-1;     
            while (j >= 0 && fragments[j].compareTo(fragments[j+1]) >= 0){
                // Intercambiar posiciones j e i
                Fragment tmp = new Fragment(fragments[j]._id);
                fragments[j] = fragments[j+1];
                fragments[j+1] = tmp; 
                j--;
            }
        }
        return fragments;
    }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {     
        // Heap Sort   
        HeapSort orderedRouters = new HeapSort(routers, k, umbral);
        Router[] res = new Router[k]; 
        for (int i=0; i<k; i++){
            Router proximo = orderedRouters.getNext();
            res[i] = proximo;
        }
        return res;
    }

    public IPv4Address[] sortIPv4(String[] ipv4) {
        // Radix Sort        
        IPv4Address[] res = new IPv4Address[ipv4.length];
        int i = 0;
        for (String strval : ipv4) {
            IPv4Address t = new IPv4Address(strval);
            res[i] = t;
            i++;
        }

        for (int p=3; p>=0; p--){
            // Ordenamos a la lista de res, con los digitos de la posicion p
            res = orderByPAddress(res, p);
        }

        return res;
    }

    public IPv4Address[] orderByPAddress(IPv4Address[] list, int p){
        // Insertion Sort
        for (int i=1; i<list.length; i++){
            int j = i-1;     
            while (j >= 0 && list[j].getOctet(p) > list[j+1].getOctet(p)){
                IPv4Address tmp = new IPv4Address(list[j].toString());
                list[j] = list[j+1];
                list[j+1] = tmp; 
                j--;
            }
        }
        return list;
    }

}
