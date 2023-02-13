package com.staringpig.framework.wechat.message;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatterDataType extends MessageDataType<String> {

    public TimeFormatterDataType(DateTimeFormatter formatter, LocalTime localTime) {
        super(formatter.format(localTime));
    }
}
