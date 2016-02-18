package com.devbrackets.android.androidmarkup.widget

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import com.devbrackets.android.androidmarkup.R
import com.devbrackets.android.androidmarkup.parser.HtmlParser
import com.devbrackets.android.androidmarkup.parser.MarkdownParser
import com.devbrackets.android.androidmarkup.parser.MarkupParser
import com.devbrackets.android.androidmarkup.parser.SpanType

/**
 * A WYSIWYG EditText for Markup languages such as HTML or
 * Markdown.  This leaves the UI up to the implementing application.
 */
class MarkupEditText : AppCompatEditText {
    lateinit var markupParser: MarkupParser

    var markup: String
        get() = markupParser.fromSpanned(text)
        set(markup) = setText(markupParser.toSpanned(markup))

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    fun toggleBold() {
        markupParser.updateSpan(text, SpanType.BOLD, selectionStart, selectionEnd)
    }

    fun toggleItalics() {
        markupParser.updateSpan(text, SpanType.ITALIC, selectionStart, selectionEnd)
    }

    fun toggleOrderedList() {
        markupParser.updateSpan(text, SpanType.ORDERED_LIST, selectionStart, selectionEnd)
    }

    fun toggleUnOrderedList() {
        markupParser.updateSpan(text, SpanType.UNORDERED_LIST, selectionStart, selectionEnd)
    }

    protected fun init(context: Context, attrs: AttributeSet?) {
        if (attrs == null || !readAttributes(context, attrs)) {
            markupParser = HtmlParser()
        }
    }

    /**
     * Reads the attributes associated with this view, setting any values found

     * @param context The context to retrieve the styled attributes with
     * *
     * @param attrs The [AttributeSet] to retrieve the values from
     * *
     * @return True if the attributes were read
     */
    protected fun readAttributes(context: Context, attrs: AttributeSet): Boolean {
        if (isInEditMode) {
            return false
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MarkupTextView) ?: return false

        //Updates the Parser
        val parserAttr = ParserAttr.get(typedArray.getInteger(R.styleable.MarkupTextView_parser, 0))
        setParser(parserAttr)

        typedArray.recycle()
        return true
    }

    protected fun setParser(parserAttr: ParserAttr) {
        when (parserAttr) {
            ParserAttr.HTML -> markupParser = HtmlParser()
            ParserAttr.MARKDOWN -> markupParser = MarkdownParser()
        }
    }
}