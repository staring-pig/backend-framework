//package com.tebon;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
//import com.tebon.tots.wechat.client.api.miniprogram.Code2SessionQuery;
//import feign.Feign;
//import feign.Response;
//import feign.jackson.JacksonDecoder;
//import feign.jackson.JacksonEncoder;
//import net.dreamlu.mica.core.utils.Charsets;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.lang.reflect.Type;
//import java.util.Collections;
//
//@SpringBootTest(classes = WechatApplication.class)
//@RunWith(SpringRunner.class)
//public class MiniProgramClientTest {
//
//    @Resource
//    private MiniProgramFeignClient miniProgramFeignClient;
//    @Resource
//    private ObjectProvider<HttpMessageConverters> httpMessageConverters;
//
//    @Test
//    public void test() {
//
////        AccessTokenQuery.Result accessToken =
////                miniProgramFeignClient.getAccessToken("wxd7458664c105080b", "9c6fcfce2f9124bc60fb84077c3984dc");
////        System.out.println(accessToken.toString());
//
//
//        Code2SessionQuery.Result result =
//                miniProgramFeignClient.code2Session("wxd7458664c105080b",
//                        "9c6fcfce2f9124bc60fb84077c3984dc", "021lTp000xdTaM1dyD300lyFW02lTp0c");
//        System.out.println(result.toString());
//    }
//
//    public static void main(String[] args) {
//        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//                .registerModules(Collections.emptyList());
//        MiniProgramFeignClient miniProgramFeignClient = Feign.builder()
//                .decoder(new JacksonDecoder(objectMapper) {
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
//                            return objectMapper.readValue(reader, objectMapper.constructType(type));
//                        } catch (RuntimeJsonMappingException e) {
//                            if (e.getCause() != null && e.getCause() instanceof IOException) {
//                                throw IOException.class.cast(e.getCause());
//                            }
//                            throw e;
//                        }
//                    }
//                })
//                .encoder(new JacksonEncoder())
//                .target(MiniProgramFeignClient.class, MiniProgramFeignClient.URL);
//
//        Code2SessionQuery.Result code2Session = miniProgramFeignClient.code2Session("wxd7458664c105080b",
//                "9c6fcfce2f9124bc60fb84077c3984dc", "021lTp000xdTaM1dyD300lyFW02lTp0c");
//        System.out.println(code2Session.toString());
//    }
//}
