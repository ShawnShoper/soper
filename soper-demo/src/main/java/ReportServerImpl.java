import org.apache.thrift.TException;

/**
 * Created by ShawnShoper on 16/6/18.
 */
public class ReportServerImpl implements  ReportServer.Iface {
    public void reportJobDone(String report) throws TException {
        System.out.println(report);
    }
}
