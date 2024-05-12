package com.example.wazitoecommerce.ui.theme.screens.property

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.wazitoecommerce.data.PropertyViewModel
import com.example.wazitoecommerce.models.Property
import com.example.wazitoecommerce.navigation.ADD_PROPERTY_URL
import com.example.wazitoecommerce.navigation.LOGIN_URL
import com.example.wazitoecommerce.ui.theme.NewBlue
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme
import com.example.wazitoecommerce.ui.theme.lBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewPropertyScreen(navController:NavHostController) {



    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var propertyRepository = PropertyViewModel(navController, context)


        val emptyPropertyState = remember { mutableStateOf(Property("","","","","","","")) }
        var emptyPropertyListState = remember { mutableStateListOf<Property>() }

        var properties = propertyRepository.allProperties(emptyPropertyState, emptyPropertyListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column {
                //TOP APP BAR CODE
                TopAppBar(title = { Text(text = "PropertyPulse", color = Color.White, fontWeight = FontWeight.ExtraBold) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Blue),
                    navigationIcon = { }, actions = {
                        IconButton(onClick = { navController.navigate(LOGIN_URL) }) {
                            Icon(imageVector = Icons.Default.ExitToApp,
                                contentDescription ="add",
                                tint = Color.White ) }

                    })
                //END OF TOP APP BAR
            }

            Box {
                Text(
                    text = "Explore",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 7.dp)
                )
            }

            //lazy column here

            LazyColumn(){
                items(properties){
                    PropertyItem(
                        propertyType = it.propertyType,
                        price = it.price,
                        buyORrent = it.buyORrent,
                        propertyLocation = it.propertyLocation,
                        phoneNumber = it.phoneNumber,
                        id = it.id,

                        navController = navController,
                        propertyRepository = propertyRepository,
                        propertyImage = it.imageUrl,
                    )
                }
            }
            //end lazy column

        }
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun PropertyItem(
    propertyType: String, price: String, buyORrent: String, propertyLocation: String, phoneNumber:String,
    navController: NavHostController,
    propertyRepository: PropertyViewModel, propertyImage: String, id: String
) {
    var mContext = LocalContext.current

        //1 item
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)) {
            Card (modifier = Modifier
                .height(250.dp)
                .width(370.dp)
            ) {
                Box (modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Image(
                        painter = rememberAsyncImagePainter(propertyImage),
                        contentDescription = "null",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )


                    Row (modifier = Modifier.align(Alignment.BottomStart)) {
                        Column (modifier = Modifier
                            .background(brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                )
                            ))
                            .fillMaxWidth()
                            .padding(7.dp)) {
                            //details

                            Text(text = propertyType,
                                fontSize = 27.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.Default,
                                color = Color.White
                            )


                            Text(text = price,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily.Default,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(text = buyORrent,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(text = propertyLocation,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(text = phoneNumber,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                color = Color.White,
                                modifier = Modifier.clickable { val callIntent= Intent(Intent.ACTION_DIAL)
                                    callIntent.data=phoneNumber.toUri()
                                    mContext.startActivity(callIntent)  }
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            //end details

                        }
                    }

                }
            }
            //end 1 item

    }




}



@Composable
@Preview(showBackground = true)
fun ViewPropertyScreenPreview(){
    WazitoECommerceTheme {
        ViewPropertyScreen(navController = rememberNavController())
    }
}