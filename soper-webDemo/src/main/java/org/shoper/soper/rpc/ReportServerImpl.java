package org.shoper.soper.rpc;

import org.apache.thrift.TException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ShawnShoper on 16/6/18.
 */
@Component
@RestController
public class ReportServerImpl implements  ReportServer.Iface {
    @RequestMapping("/report")
    @Override
    public String reportJobDone(String report) throws TException {
        System.out.println(report);
        return report;
    }
}
