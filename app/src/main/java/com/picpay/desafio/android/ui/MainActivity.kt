package com.picpay.desafio.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.ButtonInfo
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()

            MainActivityScreen(state)
        }
    }
}

@Composable
fun MainActivityScreen(state: MainActivityUiState) {
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

            when {
                state.loading != null -> MainActivityScreenLoading(state.loading)
                state.error != null -> MainActivityScreenError(state.error)
                state.success != null -> MainActivityScreenSuccess(state.success)
            }
        }
    }
}

@Composable
fun MainActivityScreenLoading(loading: MainActivityUiLoading) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = colorResource(id = R.color.colorAccent))
        Text(
            text = stringResource(id = loading.message),
            color = Color.White,
            modifier = Modifier.padding(top = 15.dp)
        )
    }
}

@Composable
@Preview
fun MainActivityScreenLoadingPreview() {
    MainActivityScreen(MainActivityUiState(loading = MainActivityUiLoading()))
}

@Composable
fun MainActivityScreenError(error: MainActivityUiError) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = error.message),
            color = Color.White
        )
        Button(
            modifier = Modifier.padding(top = 15.dp),
            colors = ButtonDefaults.buttonColors()
                .copy(containerColor = colorResource(id = R.color.colorAccent)),
            onClick = error.button.action
        ) {
            Text(text = stringResource(id = error.button.text))
        }
    }
}

@Composable
@Preview
fun MainActivityScreenErrorPreview() {
    MainActivityScreen(
        MainActivityUiState(
            error = MainActivityUiError(
                message = R.string.error,
                button = ButtonInfo(text = R.string.retry, action = {})
            )
        )
    )
}

@Composable
fun MainActivityScreenSuccess(success: MainActivityUiSuccess) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        items(success.usersList) { item ->
            SetupUserViewHolder(item)
        }
    }
}

@Composable
@Preview
fun MainActivityScreenSuccessPreview() {
    MainActivityScreen(
        MainActivityUiState(
            success = MainActivityUiSuccess(
                usersList = listOf(
                    User(id = 1, name = "First User", img = "", username = "first_user")
                )
            )
        )
    )
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
