package com.picpay.desafio.android.util

import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object FileHelper {
    fun createAndWriteToCache(
        cacheDir: File,
        fileName: String,
        data: String,
        isSuccess: (Boolean) -> (Unit)
    ) {
        val myFile = File(cacheDir, fileName)

        try {
            BufferedOutputStream(FileOutputStream(myFile)).use { bos ->
                bos.write(data.toByteArray())
                isSuccess(true)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            isSuccess(false)
        }
    }
}