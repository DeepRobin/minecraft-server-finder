package de.deeprobin.mcserverfinder;

import java.util.Iterator;

public class IPIterator implements Iterator {

    private IPLoopingAddress ip1;

    public IPIterator(String address){
        this.ip1 = new IPLoopingAddress(address);
    }

    public IPIterator(IPLoopingAddress ip1){
        this.ip1 = ip1;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public IPLoopingAddress next() {
        return ip1 = ip1.next();
    }
}
