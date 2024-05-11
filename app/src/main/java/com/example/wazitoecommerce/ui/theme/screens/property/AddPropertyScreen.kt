package com.example.wazitoecommerce.ui.theme.screens.property



import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.data.PropertyViewModel
import com.example.wazitoecommerce.navigation.ADD_PROPERTY_URL
import com.example.wazitoecommerce.navigation.SEARCH_URL
import com.example.wazitoecommerce.navigation.VIEW_PROPERTY_URL
import com.example.wazitoecommerce.ui.theme.NewBlue
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme
import com.example.wazitoecommerce.ui.theme.screens.home.BottomNavItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//the upload screen
fun AddPropertyScreen(navController:NavHostController){
    var mContext = LocalContext.current

//start bottom bar
    var selected by remember { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                com.example.wazitoecommerce.ui.theme.screens.property.bottomNavItems.forEachIndexed { index, bottomNavItem ->
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


        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = "menu")
                }
            }
        },
        //Content Section
        content = @Composable
        {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
            ){


                Column (modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        )
                    ))
                {
                    //TOP APP BAR CODE
                    TopAppBar(title = { Text(text = "PropertyPulse", color = Color.White, fontWeight = FontWeight.ExtraBold) },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Blue),
                        navigationIcon = { }, actions = {
                            IconButton(onClick = { navController.navigate(VIEW_PROPERTY_URL) }) {
                                Icon(imageVector = Icons.Default.Search,
                                    contentDescription ="add",
                                    tint = Color.White ) }
                            IconButton(onClick = { navController.navigate(ADD_PROPERTY_URL) }) {
                                Icon(imageVector = Icons.Default.AddCircle,
                                    contentDescription ="settings",
                                    tint = Color.White ) }
                        })
                    //END OF TOP APP BAR

                    Box {
                        Text(
                            text = "Make an Ad",
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            fontSize = 25.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 7.dp)
                        )
                    }



                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .background(
                                shape = RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 10.dp,
                                    bottomStart = 10.dp,
                                    bottomEnd = 10.dp
                                ),
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        NewBlue,
                                        Color.Black
                                    )
                                )
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Fill in the Fields below",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            color = Color.White
                        )


                        //other
                        var propertyType by remember { mutableStateOf("") }
                        var price by remember { mutableStateOf("") }
                        var buyORrent by remember { mutableStateOf("") }
                        var propertyLocation by remember { mutableStateOf("") }
                        var phoneNumber by remember { mutableStateOf("") }
                        var id by remember { mutableStateOf("") }
                        var filepath by remember { mutableStateOf("") }
                        //end other
                        val context = LocalContext.current

                        Spacer(modifier = Modifier.height(30.dp))

                        TextField(
                            value = propertyType,
                            onValueChange = { propertyType = it },
                            label = { Text(text = "Type of Property *") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            value = price,
                            onValueChange = { price = it },
                            label = { Text(text = "Price of property *") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            value = buyORrent,
                            onValueChange = { buyORrent = it },
                            label = { Text(text = "To buy or to rent? *") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            value = propertyLocation,
                            onValueChange = { propertyLocation = it },
                            label = { Text(text = "What is the Location ? *") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            label = { Text(text = "Enter your Phone Number *") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )

                        Spacer(modifier = Modifier.height(20.dp))



                        //---------------------IMAGE PICKER START-----------------------------------//

                        var modifier = Modifier
                        ImagePicker(
                            modifier,
                            context,
                                navController,
                                propertyType.trim(),
                                price.trim(),
                                buyORrent.trim(),
                                propertyLocation.trim(),
                                phoneNumber.trim(),
                                id.trim()

                        )

                        //---------------------IMAGE PICKER END-----------------------------------//



                    }

                }

            }//end content section

        }

    )//end bottom bar



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
        route="add_products",
        selectedIcon=Icons.Filled.AddCircle,
        unselectedIcon=Icons.Outlined.Add,
        hasNews = false,
        badges=0
    ),


    )


@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    context: Context, navController: NavHostController,
    propertyType:String,
    price:String,
    buyORrent:String,
    propertyLocation:String,
    phoneNumber:String,
    id:String
) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier,) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                },
            ) {
                Text(
                    text = "Select Image"
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                var propertyRepository = PropertyViewModel(navController,context)
                propertyRepository.uploadProperty(propertyType,price,buyORrent,propertyLocation,phoneNumber,id,imageUri!!)
            }) {
                Text(text = "Upload Details")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        Spacer(modifier = Modifier.height(30.dp))

    }
}

@Composable
@Preview(showBackground = true)
fun AddProductsScreenPreview(){
    WazitoECommerceTheme {
        AddPropertyScreen(navController = rememberNavController())
    }
}