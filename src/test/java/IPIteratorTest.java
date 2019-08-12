import de.deeprobin.mcserverfinder.IPIterator;
import de.deeprobin.mcserverfinder.IPLoopingAddress;
import de.deeprobin.mcserverfinder.MinecraftServerChecker;
import org.junit.Test;

public class IPIteratorTest {


    @Test
    public void iterateIp(){
        IPIterator iterator = new IPIterator(new IPLoopingAddress("5.62.67.193"));
        while(iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }

    @Test
    public void iterateMinecraftServer(){
        IPIterator iterator = new IPIterator(new IPLoopingAddress("5.62.67.193"));
        while(iterator.hasNext()) {
            IPLoopingAddress address = iterator.next();
            MinecraftServerChecker checker = new MinecraftServerChecker(address);

            System.out.println(address + ":" + checker.isServer());
        }
    }

}
