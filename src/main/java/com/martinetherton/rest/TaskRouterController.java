package com.martinetherton.rest;

import com.twilio.sdk.verbs.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by martin on 28/11/15.
 */
@RestController
public class TaskRouterController {

    public static final String ACCOUNT_SID = "ACcfe5e87f30326045cdde74d99bfcccf2";
    public static final String AUTH_TOKEN = "0ce5c20a765ced1f3b2f5b84fbb0f809";
    public static final String WORKFLOW_SID = "WS2b332a2b43dc7e9e6570b7ac423afcf6";

    @RequestMapping(value="/order", method=RequestMethod.GET, produces="application/xml")
    public Order hello() {
        System.out.println("Rest says hello");
        Order order = new Order("123");
        return order;
    }

    @RequestMapping(value="incoming_call", method=RequestMethod.GET)
    public void incomingCall(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TwiMLResponse twiml = new TwiMLResponse();
        final Gather gather = new Gather();
        gather.setNumDigits(1);
        gather.setTimeout(5);
        gather.setAction("enqueue_call");

        final Say sayEspanol = new Say("Para Espanol oprime el uno.");
        sayEspanol.setLanguage("es");

        final Say sayEnglish = new Say("For English, please hold or press two.");
        sayEnglish.setLanguage("en");

        try {
            gather.append(sayEspanol);
            gather.append(sayEnglish);
            twiml.append(gather);
        } catch (final TwiMLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        resp.setContentType("application/xml");
        resp.getWriter().print(twiml.toXML());
    }

    @RequestMapping(value="enqueue_call", method=RequestMethod.POST)
    public void enqueueCall(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TwiMLResponse twiml = new TwiMLResponse();
        final Enqueue enqueue = new Enqueue();
        enqueue.setWorkflowSid(WORKFLOW_SID);

        final com.twilio.sdk.verbs.Task task = new com.twilio.sdk.verbs.Task("{\"selected_language\":\"es\"}");

        try {
            enqueue.append(task);
            twiml.append(enqueue);
        } catch (final TwiMLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        resp.setContentType("application/xml");
        resp.getWriter().print(twiml.toXML());
    }

}
