package io.icons.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import org.apache.commons.text.WordUtils;

public class StringUtils {
    public static String asFixedLengthLines(String hintText) {
        return Stream.of(hintText.split("\\n+"))
            .map(StringUtils::toFixedLengthLines)
            .collect(Collectors.joining("\n"));
    }

    private static String toFixedLengthLines(final String hintText) {
        return String.join("\n", Lists.newArrayList(WordUtils.wrap(hintText, 35)));
    }
}
