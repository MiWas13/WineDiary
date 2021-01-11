package ru.miwas.winediary.utils.file

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.miwas.winediary.utils.Constants.EMPTY_STRING

fun Intent?.getFilePath(context: Context): String {
    return this?.data?.let { data -> RealPathUtil.getRealPath(context, data) ?: EMPTY_STRING } ?: EMPTY_STRING
}

fun Uri?.getFilePath(context: Context): String {
    return this?.let { uri -> RealPathUtil.getRealPath(context, uri) ?: EMPTY_STRING }
        ?: EMPTY_STRING
}

fun ClipData.Item?.getFilePath(context: Context): String {
    return this?.uri?.getFilePath(context) ?: EMPTY_STRING
}