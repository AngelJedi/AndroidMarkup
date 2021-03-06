package com.devbrackets.android.androidmarkup.parser.markdown

import android.text.Spanned
import com.devbrackets.android.androidmarkup.parser.core.MarkupParser


/**
 * A MarkupParser for the Markdown protocol specified at
 * http://commonmark.org/.  The common mark specification is
 * a strongly defined specification of the original Markdown
 * created by John Gruber (http://daringfireball.net/projects/markdown/)
 *
 * NOTE: currently this only supports a small subset of the format
 */
class MarkdownParser : MarkupParser() {
    override fun toSpanned(text: String): Spanned {
        return MarkdownDocument(text).toSpanned();
    }

    override fun fromSpanned(spanned: Spanned): String {
        return MarkdownDocument(spanned).toMarkdown();
    }
}