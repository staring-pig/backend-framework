package wechat.ministore;//package com.tebon.tot.wechat.ministore;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.Module;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
//import com.google.common.base.Charsets;
//import com.tebon.tots.wechat.OPAppType;
//import com.tebon.tots.wechat.OPApplication;
//import com.tebon.tots.wechat.client.MiniProgramClient;
//import com.tebon.tots.wechat.ministore.MiniStoreService;
//import com.tebon.tots.wechat.ministore.RemoteMiniStoreService;
//import com.tebon.tots.wechat.ministore.order.Order;
//import feign.Feign;
//import feign.Logger;
//import feign.Response;
//import feign.codec.Decoder;
//import feign.jackson.JacksonEncoder;
//import feign.slf4j.Slf4jLogger;
//import org.junit.Test;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.lang.reflect.Type;
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.util.Collections;
//
//public class MiniStoreServiceTest {
//
//    @Test
//    public void testOrders() {
//
//        MiniStoreService miniStoreService = new RemoteMiniStoreService(new OPApplication("", OPAppType.MINI_PROGRAM) {
//        }, (appId, appSecret) -> null, Feign.builder()
//                .encoder(new JacksonEncoder())
//                .decoder(new Decoder() {
//                    private final ObjectMapper mapper = new ObjectMapper()
//                            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//                            .registerModules(Collections.<Module>emptyList());
//
//                    @Override
//                    public Object decode(Response response, Type type) throws IOException {
//                        if (response.body() == null)
//                            return null;
//                        Reader reader = response.body().asReader(Charsets.UTF_8);
//                        if (!reader.markSupported()) {
//                            reader = new BufferedReader(reader, 1);
//                        }
//                        try {
//                            // Read the first byte to see if we have any data
//                            reader.mark(1);
//                            if (reader.read() == -1) {
//                                return null; // Eagerly returning null avoids "No content to map due to end-of-input"
//                            }
//                            reader.reset();
//                            return mapper.readValue(reader, mapper.constructType(type));
//                        } catch (RuntimeJsonMappingException e) {
//                            if (e.getCause() != null && e.getCause() instanceof IOException) {
//                                throw IOException.class.cast(e.getCause());
//                            }
//                            throw e;
//                        }
//                    }
//                })
//                .logLevel(Logger.Level.FULL)
//                .logger(new Slf4jLogger(MiniProgramClient.class))
//                .target(MiniProgramClient.class, MiniProgramClient.URL)) {
//            @Override
//            protected synchronized String fetchAccessToken() {
//                return "48_wDDZrUAc8S65EFL3K82RDQZKdWvExYESMiNKKW6hI0zpCgjDwrMSaBYl-MmKoY2Ke0TBBXXZPu2kizjtbcoHJ4hyuL1eYnhPt-OVhLtbr4vU8ADEw_BpB-hG6X85qeuohrscIEsOOIpy-ANWOSJhAIAFJA";
//            }
//        };
//
//        LocalDateTime startDateTime = LocalDateTime.of(2021, 1, 1, 1, 0);
//        Duration duration = Duration.ofDays(300);
//        Integer count = miniStoreService.countOrdersByCreateTimeDuration(startDateTime, duration);
//        System.out.println(count);
//
//        Page<Order> orders = miniStoreService.queryOrdersByCreateTimeDuration(startDateTime, duration, PageRequest.of(1, 10));
//
//        for (Order order : orders) {
//            System.out.println(order);
//        }
//    }
//}
