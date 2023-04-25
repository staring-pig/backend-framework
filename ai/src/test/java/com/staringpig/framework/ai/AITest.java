//package com.staringpig.framework.ai;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import com.staringpig.framework.ai.capability.ChatCompleting;
//import com.staringpig.framework.ai.capability.Completing;
//import com.staringpig.framework.ai.capability.Instruction;
//import com.staringpig.framework.ai.endpoint.openai.AuthenticationInterceptor;
//import com.staringpig.framework.ai.endpoint.openai.Gpt_3_5_Turbo;
//import com.staringpig.framework.ai.endpoint.openai.OpenAIAPI;
//import com.staringpig.framework.ai.endpoint.openai.OpenAIEndpoint;
//import com.staringpig.framework.ai.endpoint.openai.utils.TokenizerAPI;
//import com.staringpig.framework.ai.endpoint.openai.utils.UtilsEndpoint;
//import com.staringpig.framework.ai.model.ChatContextStore;
//import okhttp3.ConnectionPool;
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.converter.jackson.JacksonConverterFactory;
//
//import java.net.InetSocketAddress;
//import java.net.Proxy;
//import java.util.Optional;
//import java.util.concurrent.TimeUnit;
//
//public class AITest {
//
//    public static void main(String[] args) {
//        Gpt_3_5_Turbo model = new Gpt_3_5_Turbo(
//                (user, model1, usage) ->
//                        System.out.println("cost:" + user + model1.getName() + usage.toString()),
//                new ChatContextStore() {
//                    ChatCompleting.ChatContext context = new ChatCompleting.ChatContext("");
//
//                    @Override
//                    public Optional<ChatCompleting.ChatContext> query(String chat) {
//                        return Optional.of(context);
//                    }
//
//                    @Override
//                    public void save(ChatCompleting.ChatContext context) {
//                        this.context = context;
//                    }
//                },
//                new OpenAIEndpoint(new Retrofit.Builder()
//                        .baseUrl("https://api.openai.com/")
//                        .client(new OkHttpClient.Builder()
//                                .addInterceptor(new AuthenticationInterceptor("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"))
//                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 57011)))
//                                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
//                                .readTimeout(10, TimeUnit.MINUTES)
//                                .connectTimeout(1, TimeUnit.SECONDS)
//                                .build())
//                        .addConverterFactory(JacksonConverterFactory.create(mapper))
//                        .build().create(OpenAIAPI.class)),
//                new UtilsEndpoint(new Retrofit.Builder()
//                        .baseUrl("http://127.0.0.1:5000")
//                        .addConverterFactory(JacksonConverterFactory.create(mapper))
//                        .build().create(TokenizerAPI.class)));
//
//        String chat = model.openChat();
//        model.instruct(chat, new Instruction("你是一个聊天工具") {
//            @Override
//            public String getContent() {
//                return "你是一个聊天工具";
//            }
//        });
//        model.chat(chat, new Completing.CompletingPrompt("user", "你好呀"),
//                chatCompletion -> System.out.println(chatCompletion.getContent()));
//    }
//
//
//}
