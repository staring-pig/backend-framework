package com.staringpig.framework.wechat.message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterDataType extends MessageDataType<String> {

    public DateTimeFormatterDataType(DateTimeFormatter formatter, LocalDateTime localDateTime) {
        super(formatter.format(localDateTime));
    }
}
