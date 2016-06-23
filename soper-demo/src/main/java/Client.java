import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TSocket;

/**
 * Created by ShawnShoper on 16/6/19.
 */
public class Client {

    public static void main(String[] args) throws TException {
        ReportServer.Client transServerClient;
        TSocket socket = null;
        socket = new TSocket("127.0.0.1",
                             8099);
        TCompactProtocol protocol = new TCompactProtocol(socket);
        System.out.println(ReportServer.class.getName());
        TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol,
                                                            ReportServer.class.getName());
        transServerClient = new ReportServer.Client(mp1);
        socket.open();
        transServerClient.reportJobDone("sss");
    }
}
