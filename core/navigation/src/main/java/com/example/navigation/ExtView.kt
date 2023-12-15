package com.example.navigation

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(image: String) {
    Glide.with(this).load(image).into(this)
}

fun Context.convertToText(number: Int): String {
    return when (number) {
        1 -> "1"
        2 -> "2"
        3 -> "3"
        4 -> "4"
        5 -> "6"
        else -> {
            "error"
        }
    }
}

fun editTextIsEmpty(
    editText: EditText,
    textInputLayout: TextInputLayout,
    errorMessage: String
) {
    if (editText.text.toString().isEmpty()) {
        textInputLayout.error =
            errorMessage
        textInputLayout.isErrorEnabled = true
    }
}


fun setupTextWatcherDate(
    editText: EditText,
    textInputLayout: TextInputLayout,
    formatPattern: String?,
    errorMessage: String
) {
    editText.addTextChangedListener(object : TextWatcher {
        var lastChar = " "

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if (formatPattern != null) {
                val digits: Int = editText.text.toString().length
                if (digits > 1) lastChar = editText.text.toString().substring(digits - 1)
            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let {
                if (formatPattern != null && lastChar != formatPattern) {
                    val digits: Int = editText.text.toString().length
                    if (digits == 2 || digits == 5) {
                        editText.append(formatPattern)
                    }
                    if (digits != 10) {
                        textInputLayout.error =
                            errorMessage
                        textInputLayout.isErrorEnabled = true
                    } else {
                        textInputLayout.error = null
                        textInputLayout.isErrorEnabled = false
                    }
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun setupTextWatcherPhoneNumber(
    editText: EditText,
    textInputLayout: TextInputLayout,
    formatPattern: String?,
    formatPattern2: String?,
    formatPattern3: String?,
    errorMessage: String
) {
    editText.addTextChangedListener(object : TextWatcher {
        var lastChar = " "

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if (formatPattern != null) {
                val digits: Int = editText.text.toString().length
                if (digits > 1) lastChar = editText.text.toString().substring(digits - 1)
            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let {
                if (formatPattern != null && lastChar != formatPattern && lastChar != formatPattern2 && lastChar != formatPattern3) {
                    val digits: Int = editText.text.toString().length

                    if (digits == 12 || digits == 15) {
                        editText.append(formatPattern)
                    }
                    if (digits == 7) {
                        editText.append(formatPattern3)
                    }
                    if (digits != 18 || editText.text.toString()[4] != '9') {
                        textInputLayout.error =
                            errorMessage
                        textInputLayout.isErrorEnabled = true
                    } else {
                        textInputLayout.error = null
                        textInputLayout.isErrorEnabled = false
                    }

                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun setupTextWatcherPassportNumber(
    editText: EditText,
    textInputLayout: TextInputLayout,
    formatPattern: String?,
    errorMessage: String
) {
    editText.addTextChangedListener(object : TextWatcher {
        var lastChar = " "

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if (formatPattern != null) {
                val digits: Int = editText.text.toString().length
                if (digits > 1) lastChar = editText.text.toString().substring(digits - 1)
            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let {
                if (formatPattern != null && lastChar != formatPattern) {
                    val digits: Int = editText.text.toString().length
                    if (digits == 2) {
                        editText.append(formatPattern)
                    }
                    if (digits != 10) {
                        textInputLayout.error =
                            errorMessage
                        textInputLayout.isErrorEnabled = true
                    } else {
                        textInputLayout.error = null
                        textInputLayout.isErrorEnabled = false
                    }

                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun setupTextWatcherNames(
    editText: EditText,
    textInputLayout: TextInputLayout
) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let {
                if (editText.text.toString().isNotEmpty()) {
                    textInputLayout.error = null
                    textInputLayout.isErrorEnabled = false
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

private val emailPattern = Pattern.compile(
    "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"
)

fun setupTextWatcherEmail(
    editText: EditText,
    textInputLayout: TextInputLayout,
    errorMessage: String
) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let {
                if (!emailPattern.matcher(it.toString()).matches()) {
                    textInputLayout.error = errorMessage
                    textInputLayout.isErrorEnabled = true
                } else {
                    textInputLayout.error = null
                    textInputLayout.isErrorEnabled = false
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}
