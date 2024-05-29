package io.swagger.worker;

import io.swagger.model.Payment;
import io.swagger.model.User;
import io.swagger.serdes.CustomDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.UUID;

public class RiskEngine4Payment extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RiskEngine4Payment.class);

    public static void startEngine() {
        RiskEngine4Payment thread = new RiskEngine4Payment();
        thread.start();
        System.out.println("This code is outside of the thread");
    }
    public void run() {
        log.info("I am a Kafka Consumer");

        String bootstrapServers = "127.0.0.1:9092";
        String groupId = "my-fifth-application";
        String topic = "demo_java";

        // create consumer configs
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create consumer
        final KafkaConsumer<String, Payment> consumer = new KafkaConsumer<>(properties);

        // get a reference to the current thread
        final Thread mainThread = Thread.currentThread();

        // adding the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("Detected a shutdown, let's exit by calling consumer.wakeup()...");
                consumer.wakeup();

                // join the main thread to allow the execution of the code in the main thread
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        try {

            // subscribe consumer to our topic(s)
            consumer.subscribe(Arrays.asList(topic));

            // poll for new data
            while (true) {
                ConsumerRecords<String, Payment> records =
                        consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, Payment> record : records) {
                    log.info("Key: " + record.key() + ", Value: " + record.value());
                    log.info("Partition: " + record.partition() + ", Offset:" + record.offset());

                    // todo : user == null is used for POC
                    Payment payment = (Payment)record.value();
                    if(riskCheck(payment, null)){
                        log.info("riskCheck passed");
                        recordPayment(payment, null);
                    }else{
                        log.error("riskCheck Failed");
                    }
                }
            }

        } catch (WakeupException e) {
            log.info("Wake up exception!");
            // we ignore this as this is an expected exception when closing a consumer
        } catch (Exception e) {
            log.error("Unexpected exception", e);
        } finally {
            consumer.close(); // this will also commit the offsets if need be.
            log.info("The consumer is now gracefully closed.");
        }

    }

    // return true if riskCheck pass
    // return false if riskCheck fail
    // TODO : implement real risk engine, random pass used currently
    boolean riskCheck(Payment payment, User user){
        Random rand = new Random();
        int random = rand.nextInt(100);

        // TODO : CHANGE TO 75
        return random >= 0;
    }

    void recordPayment(Payment payment, User user){
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (Exception e) {
                    System.out.println(e);
                }
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/intuit_payment", "hyuan", "Q!w2e3r4");
                System.out.println("Connection is created successfully:");

                UUID paymentid = UUID.randomUUID();

                stmt=conn.prepareStatement("insert into Payment values(?,?,?,?,?)");
                stmt.setString(1,paymentid.toString());//1 specifies the first parameter in the query
                stmt.setString(2,payment.getPaymentMethodID().toString());
                stmt.setString(3,payment.getPayeeId().toString());
                stmt.setString(4,payment.getAmount());
                stmt.setString(5,payment.getCurrency());

                stmt.executeUpdate();

                System.out.println("Record is inserted in the table successfully..................");
            } catch (SQLException excep) {
                excep.printStackTrace();
            } catch (Exception excep) {
                excep.printStackTrace();
            } finally {
                try {
                    if (stmt != null)
                        conn.close();
                } catch (SQLException se) {}
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            System.out.println("End of recordPayment()");
    }

}