package com.picpay.desafio.android.data.sources

import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.MainResult
import com.picpay.desafio.android.util.FileHelper
import java.io.File
import javax.inject.Inject

class MainActivityLocalDataSourceImpl @Inject constructor() : MainActivityDataSource.Local {
    override fun cacheInformation(
        cacheDir: File,
        fileName: String,
        data: String,
    ): MainResult<Unit, Int> {
        var mainResult: MainResult<Unit, Int> = MainResult.Fail(R.string.error)

        FileHelper.createAndWriteToCache(
            cacheDir = cacheDir,
            fileName = fileName,
            data = data
        ) { isSuccess ->
            if (isSuccess) mainResult = MainResult.Success(Unit)
        }

        return mainResult
    }
}