//package com.tebon;
//
//import com.tebon.tots.wechat.client.MiniProgramClient;
//import feign.codec.Decoder;
//import feign.optionals.OptionalDecoder;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
//import org.springframework.cloud.openfeign.support.SpringDecoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@FeignClient(name = "mini-program", url = MiniProgramClient.URL,
//        configuration = MiniProgramFeignClient.SupportAutoConfiguration.class)
//public interface MiniProgramFeignClient extends MiniProgramClient {
//
//    @Configuration
//    class SupportAutoConfiguration {
//
//        @Bean
//        public Decoder feignDecoder() {
//            WxMessageConverter wxConverter = new WxMessageConverter();
//            ObjectFactory<HttpMessageConverters> messageConverters = () -> new HttpMessageConverters(wxConverter);
//            return new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(messageConverters)));
//        }
//
//        /**
//         * // 创建一个新的转换器 解析微信的 [text/plain]
//         */
//        static class WxMessageConverter extends MappingJackson2HttpMessageConverter {
//            public WxMessageConverter() {
//                List<MediaType> mediaTypes = new ArrayList<>();
//                mediaTypes.add(MediaType.TEXT_PLAIN);
//                setSupportedMediaTypes(mediaTypes);
//            }
//        }
//    }
//}
