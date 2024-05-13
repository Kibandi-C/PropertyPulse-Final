package com.example.wazitoecommerce.ui.theme.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.R
import com.example.wazitoecommerce.navigation.ADD_PROPERTY_URL
import com.example.wazitoecommerce.navigation.LOGIN_URL
import com.example.wazitoecommerce.navigation.VIEW_PROPERTY_URL
import com.example.wazitoecommerce.ui.theme.NewBlue
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController:NavHostController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var selected by remember { mutableIntStateOf(0) }
        Scaffold(
            topBar = {
                //TOP APP BAR CODE
                TopAppBar(title = { Text(text = "PropertyPulse", color = Color.White, fontWeight = FontWeight.ExtraBold) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Blue),
                    navigationIcon = { }, actions = {
                        IconButton(onClick = { navController.navigate(ADD_PROPERTY_URL) }) {
                            Icon(imageVector = Icons.Default.Add,
                                contentDescription ="settings",
                                tint = Color.White ) }

                        IconButton(onClick = { navController.navigate(LOGIN_URL) }) {
                            Icon(imageVector = Icons.Default.ExitToApp,
                                contentDescription ="settings",
                                tint = Color.White ) }
                    })
                //END OF TOP APP BAR
            }
            ,bottomBar = {
                NavigationBar {
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = index == selected,
                            onClick = {
                                selected = index
                                navController.navigate(bottomNavItem.route)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (bottomNavItem.badges != 0) {
                                            Badge {
                                                Text(text = bottomNavItem.badges.toString())
                                            }
                                        } else if (bottomNavItem.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(imageVector =
                                    if (index == selected)
                                        bottomNavItem.selectedIcon
                                    else
                                        bottomNavItem.unselectedIcon,
                                        contentDescription = bottomNavItem.title)
                                }

                            },
                            label = {
                                Text(text = bottomNavItem.title)
                            })
                    }
                }
            },


            //Content Section
            content = @Composable{
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(60.dp))
                    Box (){
                        Text(
                            text = "HOME",
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

                    Column (modifier = Modifier
                        .background(color = NewBlue, RoundedCornerShape(15.dp))
                    )
                    {
                        Text(
                            text = "Choose an option",
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp,
                            color = Color.White,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 10.dp)
                        )




                        //start of option 1

                            Card(modifier = Modifier
                                .padding(15.dp)
                                .fillMaxWidth()
                                .height(150.dp)
                                .width(370.dp)
                                .clickable { navController.navigate(VIEW_PROPERTY_URL) }
                            ) {

                                Box (modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center){

                                    Image(painter = painterResource(id = R.drawable.nicehome),
                                        contentDescription = "usa",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop)



                                    Row (modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.BottomStart)
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = listOf(
                                                    Color.Transparent,
                                                    Color.Black
                                                )
                                            )
                                        )){

                                            Text(text = "SEE RECENT UPLOADS",
                                                fontSize = 30.sp,
                                                fontWeight = FontWeight.Bold,
                                                fontFamily = FontFamily.Default,
                                                color = Color.White,
                                                textAlign = TextAlign.Center

                                            )

                                            Spacer(modifier = Modifier.height(5.dp))


                                    }

                                }


                            } //end of option 1








                    }

                }//end content section

            }

        )

    }
}



val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route="home",
        selectedIcon=Icons.Filled.Home,
        unselectedIcon=Icons.Outlined.Home,
        hasNews = false,
        badges=0
    ),



    BottomNavItem(
        title = "Explore",
        route="view_property",
        selectedIcon=Icons.Filled.Search,
        unselectedIcon=Icons.Outlined.Search,
        hasNews = false,
        badges=0
    ),

    BottomNavItem(
        title = "Post",
        route="add_property",
        selectedIcon=Icons.Filled.Add,
        unselectedIcon=Icons.Outlined.Add,
        hasNews = false,
        badges=0
    ),


    )



data class BottomNavItem(
    val title :String,
    val route :String,
    val selectedIcon: ImageVector,
    val unselectedIcon :ImageVector,
    val hasNews :Boolean,
    val badges :Int
)



@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    WazitoECommerceTheme {
        HomeScreen(navController = rememberNavController())
    }
}