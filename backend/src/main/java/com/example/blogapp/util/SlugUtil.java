package com.example.blogapp.util;

import java.text.Normalizer;

public final class SlugUtil {
    private SlugUtil() {
    }

    public static String toSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-")
                .replaceAll("^-|-$", "");
        return normalized.isBlank() ? "post" : normalized;
    }
}
