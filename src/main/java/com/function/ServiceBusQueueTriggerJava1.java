package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.function.model.Person;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.MessageBody;

/**
 * Azure Functions with Service Bus Trigger.
 * 
 */
public class ServiceBusQueueTriggerJava1 {
    /**
     * This function will be invoked when a new message is received at the Service Bus Queue.
     */
    
    @FunctionName("ServiceBusQueueTriggerJava1")
    public void run(
            @ServiceBusQueueTrigger(name = "message", queueName = "inputqueue", connection = "azeemsb_SERVICEBUS") String message,
            final ExecutionContext context, 
            @BindingName("CorrelationId") String correlationId,
            @ServiceBusQueueOutput(name = "response", queueName = "outputqueue", connection = "azeemsb_SERVICEBUS")OutputBinding<Person> result) 
    {
        context.getLogger().info("Java Service Bus Queue trigger function executed.");
        context.getLogger().info("Message is: " + message + ", CorrelationId is: " + correlationId);
        
        // Build message
        Message msg = new Message();
        msg.setCorrelationId("output-" + correlationId);
        msg.setContentType("application/json");
        
        // build MessageBody from input message
        MessageBody outputBody = MessageBody.fromValueData(message);
        msg.setMessageBody(outputBody);

        // create a person object and set the message
        Person person = new Person();
        person.setMessage(msg);

        // set value for OutputBinding
        result.setValue(person);

    }
}
