package com.devbrackets.android.androidmarkup.parser.markdown

import android.text.Spanned
import com.devbrackets.android.androidmarkup.parser.core.MarkupDocument
import com.devbrackets.android.androidmarkup.parser.core.MarkupElement
import com.devbrackets.android.androidmarkup.parser.core.SpanType

/**
 * This markdown document and associated parsing follows the
 * spec defined by [spec.commonmark.org/0.24/](http://spec.commonmark.org/0.24/)
 */
class MarkdownDocument : MarkupDocument {

    constructor(spanned: Spanned) : super(spanned)

    constructor(markdown: String) : super() {
        //TODO: convert to the intermediate map
    }

    fun toMarkdown(): String {
        val builder: StringBuilder = StringBuilder()
        toMarkdown(rootElement, builder)

        return builder.toString()
    }

    protected fun toMarkdown(element: MarkupElement, builder: StringBuilder) {
        //Appends the opening tag
        builder.append(getSpanTag(element))
        builder.append(element.text.orEmpty())

        for (child in element.children) {
            toMarkdown(child, builder)
        }

        //Appends the closing tag
        builder.append(getSpanTag(element))
    }

    protected fun getSpanTag(element: MarkupElement): String {
        when (element.spanType) {
            SpanType.BOLD -> return BOLD_TAG
            SpanType.ITALIC -> return ITALICS_TAG
        }

        //TODO: lists

        return ""
    }

    protected fun escapeString(unescapedString: String): String {
        //TODO: don't forget to escape non-MD characters (e.g. * in text should be \*)
        return unescapedString
    }

    companion object {
        const val BOLD_TAG: String = "**"
        const val ITALICS_TAG: String = "_"
    }
}