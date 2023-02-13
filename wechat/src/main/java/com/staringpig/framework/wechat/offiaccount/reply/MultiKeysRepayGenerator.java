package com.staringpig.framework.wechat.offiaccount.reply;

import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.offiaccount.event.KeyClickEvent;

import java.util.Optional;

public class MultiKeysRepayGenerator implements RepayGenerator {

    private final KeyRepayMessageRepository keyRepayMessageRepository;

    public MultiKeysRepayGenerator(KeyRepayMessageRepository keyRepayMessageRepository) {
        this.keyRepayMessageRepository = keyRepayMessageRepository;
    }

    @Override
    public Optional<ReplyMessage> generate(KeyClickEvent.Key clickKey, OPAppAccount opaAccount) {

        Optional<KeyRepayMessage> repayMessage = keyRepayMessageRepository.query(clickKey);

        if (repayMessage.isPresent()) {
            return repayMessage.map(keyRepayMessage -> keyRepayMessage.generate(opaAccount));
        }

        return Optional.empty();
    }
}
