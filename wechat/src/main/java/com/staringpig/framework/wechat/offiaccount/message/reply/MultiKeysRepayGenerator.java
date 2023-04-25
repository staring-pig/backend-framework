package com.staringpig.framework.wechat.offiaccount.message.reply;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import com.staringpig.framework.wechat.offiaccount.message.event.KeyClickEvent;

import java.util.Optional;

public class MultiKeysRepayGenerator implements RepayGenerator {

    private final KeyRepayMessageRepository keyRepayMessageRepository;

    public MultiKeysRepayGenerator(KeyRepayMessageRepository keyRepayMessageRepository) {
        this.keyRepayMessageRepository = keyRepayMessageRepository;
    }

    @Override
    public Optional<ReplyMessage> generate(KeyClickEvent.Key clickKey, OAAccount oaAccount) {

        Optional<KeyRepayMessage> repayMessage = keyRepayMessageRepository.query(clickKey);

        if (repayMessage.isPresent()) {
            return repayMessage.map(keyRepayMessage -> keyRepayMessage.generate(oaAccount));
        }

        return Optional.empty();
    }
}
