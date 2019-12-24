package io.icons.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.ArrayList;

public class StringUtils {
    public static String asFixedLengthLines(String hintText) {
        return String.join("\n", Lists.newArrayList(Splitter.fixedLength(30).split(hintText)));
    }
}
