package def;

import java.util.Comparator;

public class NodeComparator implements Comparator<Nodes>{
    
	 @Override
    public int compare(Nodes n1, Nodes n2) {
        if (n1.getValue() > n2.getValue())
            return 1;
        if (n1.getValue() < n2.getValue())
            return -1;
        return 0;
        }
}