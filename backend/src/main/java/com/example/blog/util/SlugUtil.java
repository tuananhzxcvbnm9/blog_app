package com.example.blog.util;

import java.text.Normalizer;
import java.util.Locale;

public final class SlugUtil {
    private SlugUtil() {}

    public static String toSlug(String input) {
        String noAccent = Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return noAccent.toLowerCase(Locale.ROOT).trim().replaceAll("[^a-z0-9\\s-]", "").replaceAll("\\s+", "-");
    }
}
