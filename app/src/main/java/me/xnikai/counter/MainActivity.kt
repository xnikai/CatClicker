package me.xnikai.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.xnikai.counter.ui.theme.CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViemModel by viewModels()
        setContent {
            val font = FontFamily(Font(R.font.lato_regular, FontWeight.Normal))
            Column() {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .background(Color(0xFFBDE4EF))
                ){
                    Image(modifier = Modifier.padding(top = 100.dp, start = 20.dp), painter = painterResource(id = R.drawable.cloud), contentDescription = "Cloud Image")
                    Image(modifier = Modifier.align(Alignment.TopEnd).padding(10.dp), painter = painterResource(id = R.drawable.sun), contentDescription = "Sun Image")
                    Image(modifier = Modifier.align(Alignment.BottomStart).padding(start = 0.dp), painter = painterResource(id = R.drawable.tree), contentDescription = "Tree Image")
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color(0xFF87E248)))
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color(0xFFA25100)))
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ){
                Text("Click on cat", modifier = Modifier.padding(bottom = 5.dp, start = 5.dp))
                Image(modifier = Modifier
                    .size(256.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                viewModel.addCount()
                            }
                        )
                    }, painter = painterResource(id = R.drawable.cat), contentDescription = "Cat Image")
//                Text("Click on cat", modifier = Modifier.padding(start = 5.dp))

                Spacer(modifier = Modifier.height(40.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Text(text = "Coins:")
                    Text(
                        text = viewModel.count.value.toString(),
                        style = TextStyle(color = Color.White, fontFamily = font)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(modifier = Modifier.size(60.dp), painter = painterResource(id = R.drawable.coins), contentDescription = "Coins image")

                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(){
                    Column() {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(modifier = Modifier.size(60.dp), painter = painterResource(id = R.drawable.time), contentDescription = "Time image")
                            Text(text = "${viewModel.modifier.value}/s",
                                style = TextStyle(color = Color.White, fontFamily = font)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        viewModel.addModifier()
                                    }
                                )
                            }) {
                            Image(modifier = Modifier
                                .size(45.dp),
                                painter = painterResource(id = R.drawable.cart), contentDescription = "Time Upper Image")
                            Text(text = " - ${viewModel.upPrice.value}",
                                style = TextStyle(color = Color.White, fontFamily = font)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Image(modifier = Modifier.size(15.dp), painter = painterResource(id = R.drawable.coins), contentDescription = "Coins image")
                        }
                    }

                    Spacer(modifier = Modifier.width(40.dp))
                    Column() {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(modifier = Modifier.size(60.dp), painter = painterResource(id = R.drawable.click), contentDescription = "Click image")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "${viewModel.click.value}/click",
                                style = TextStyle(color = Color.White, fontFamily = font)
                            )

                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        viewModel.addClick()
                                    }
                                )
                            }) {
                            Image(modifier = Modifier
                                .size(45.dp),
                                painter = painterResource(id = R.drawable.cart), contentDescription = "Click Upper Image")
                            Text(" - ${viewModel.clickPrice.value}",
                                style = TextStyle(color = Color.White, fontFamily = font)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Image(modifier = Modifier.size(15.dp), painter = painterResource(id = R.drawable.coins), contentDescription = "Coins image")
                        }
                    }

                }
            }
        }
    }
}