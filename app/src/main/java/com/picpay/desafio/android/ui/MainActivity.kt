package com.picpay.desafio.android.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.ui.theme.Typography
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(url).client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    private val service: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var hasProgressBar by rememberSaveable { mutableStateOf(true) }
            var users: List<User>? by rememberSaveable { mutableStateOf(null) }

            service.getUsers().enqueue(object : Callback<List<User>> {
                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        val message = getString(R.string.error)

                        hasProgressBar = false
                        users = null

                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<List<User>>, response: Response<List<User>>
                    ) {
                        hasProgressBar = false
                        users = response.body()
                    }
                })

            MainActivityScreen(hasProgressBar, users)
        }
    }
}

@Composable
fun MainActivityScreen(hasProgressBar: Boolean, users: List<User>?) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = colorResource(R.color.colorPrimaryDark)
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.title),
                style = Typography.headlineLarge,
                modifier = Modifier.padding(
                    start = 24.dp, top = 48.dp
                )
            )

            if (hasProgressBar) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = colorResource(id = R.color.colorAccent))
                }
            }

            users?.let {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp)
                ) {
                    items(it) { item ->
                        SetupUserViewHolder(item)
                    }
                }
            }
        }
    }
}

@Composable
fun SetupUserViewHolder(user: User) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(user.img)
                .size(Size.ORIGINAL)
                .build()
        )

        val painterModifier = Modifier
                .padding(vertical = 12.dp)
                .padding(start = 24.dp, end = 16.dp)
                .size(52.dp)

        when (painter.state) {
            is AsyncImagePainter.State.Empty, is AsyncImagePainter.State.Error -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_round_account_circle),
                    contentDescription = null,
                    modifier = painterModifier.clip(RoundedCornerShape(50))
                )
            }

            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.colorAccent),
                    modifier = painterModifier
                )
            }
            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = painterModifier.clip(RoundedCornerShape(50))
                )
            }
        }

        Column {
            Text(text = user.username, color = Color.White)
            Text(text = user.name, color = colorResource(id = R.color.colorDetail))
        }
    }
}

@Composable
@Preview
fun MainActivityScreenLoadingPreview() {
    MainActivityScreen(true, null)
}

@Composable
@Preview
fun MainActivityScreenUserPreview() {
    MainActivityScreen(
        false, listOf(
            User(id = 1, name = "First User", img = "", username = "first_user")
        )
    )
}
