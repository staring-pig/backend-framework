package wechat.client;//package com.tebon.tot.wechat.client;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.Module;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
//import com.google.common.base.Charsets;
//import com.tebon.tots.wechat.client.MiniProgramClient;
//import com.tebon.tots.wechat.client.api.miniprogram.OrderListQuery;
//import feign.Feign;
//import feign.Logger;
//import feign.Response;
//import feign.codec.Decoder;
//import feign.jackson.JacksonEncoder;
//import feign.slf4j.Slf4jLogger;
//import net.dreamlu.mica.core.utils.DatePattern;
//import org.junit.Test;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.lang.reflect.Type;
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.util.Collections;
//
//public class MiniProgramClientTest {
//
//    @Test
//    public void orderListQueryTest() {
//        MiniProgramClient miniProgramClient = Feign.builder()
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
//                .target(MiniProgramClient.class, MiniProgramClient.URL);
//
//        String accessToken = "48_BhJ8CE1q8kzZfsS87uP2ShKHdR0DvYCEOwnTvZ1wtOTn8SAfw1GAh-vfdIq2R97eJWbkPl2RY3xXl41L8B_izDuiveLBf3KIflJcvp5awlkBN4UxDVIlVlMrAWK1dfDFM9IdYZ3FP9_LCNf8KAUbABAAQK";
//
//        LocalDateTime startDateTime = LocalDateTime.of(2021, 1, 1, 1, 0);
//        Duration duration = Duration.ofDays(100);
//
//        OrderListQuery.Result result = miniProgramClient.queryOrders(accessToken, OrderListQuery.builder()
//                .createStartTime(DatePattern.NORM_DATETIME_FORMAT.format(startDateTime))
//                .createEndTime(DatePattern.NORM_DATETIME_FORMAT.format(startDateTime.minus(duration)))
//                .build());
//
//        System.out.println(result);
//    }
//}
