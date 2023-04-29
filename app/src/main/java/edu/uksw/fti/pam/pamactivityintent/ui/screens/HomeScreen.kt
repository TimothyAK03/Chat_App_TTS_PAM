package edu.uksw.fti.pam.pamactivityintent.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.uksw.fti.pam.pamactivityintent.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.google.accompanist.pager.*
import edu.uksw.fti.pam.pamactivityintent.models.*
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.roundToInt
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
import kotlinx.coroutines.launch




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeField(
    navigateToProfile: (ContactModel) -> Unit
) {


    val displayMetrics = DisplayMetrics()
    val height = (displayMetrics.heightPixels / displayMetrics.density) - 150;
    val width = displayMetrics.widthPixels / displayMetrics.density;




    Scaffold(
        topBar = { TopBar() }
    ) {
        TabScreen(navigateToProfile)
    }
}


@Composable
fun TopBar(){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.07f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_notification),
                contentDescription = stringResource(id = R.string.notif),
                tint = Color.Gray,
                modifier = Modifier.padding(start = 15.dp, end = 30.dp)
            )
            Text(
                "Sentigram",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color(0xff36a8eb),
                fontSize = 22.sp
            )
            Row(
                modifier = Modifier.padding(end = 15.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.filter_list),
                    contentDescription = stringResource(id = R.string.filter_list),
                    tint = Color.Gray,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = stringResource(id = R.string.search),
                    tint = Color.Gray,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen(navigateToProfile: (ContactModel) -> Unit){
    val pagerState = rememberPagerState(pageCount = 3)

    Column (modifier = Modifier.background(Color.White)) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, navigateToProfile = navigateToProfile)
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState){

    val list = listOf("All", "Messages", "Group")
    
    val scope = rememberCoroutineScope()
    TabRow(selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = Color.Black,
        indicator = {tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState,tabPositions),
                color = Color(0xff2d8bc2)
            )
        }
     ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(text = list[index],
                        color = if (pagerState.currentPage == index) Color.Black else Color.LightGray
                        )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
        
    }
    

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(pagerState: PagerState, navigateToProfile: (ContactModel) -> Unit){

    HorizontalPager(state = pagerState) {page ->
        when (page){
            0-> All(vm = ContactViewModel(), vm2 = GroupViewModel(), navigateToProfile = navigateToProfile)
            1-> Messages(vm = ContactViewModel() , vm2 = GroupViewModel(), navigateToProfile = navigateToProfile)
            2-> Group(vm = TodosViewModel(), vm2 = GroupViewModel())
        }
    }
}



@Composable
fun All(
    vm: ContactViewModel,
    vm2: GroupViewModel, navigateToProfile: (ContactModel) -> Unit)
{
    val displayMetrics = DisplayMetrics()
    val height = (displayMetrics.heightPixels / displayMetrics.density) - 150;
    val width = displayMetrics.widthPixels / displayMetrics.density;

    LaunchedEffect(
        Unit,
        block = {
            vm.getContactList()
            vm2.getGroupList()
        }
    )


    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .size(width = width.dp, height = height.dp)

    ) {
        Column(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.message),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif
                )
                Icon(
                    painter = painterResource(R.drawable.icon_more),
                    contentDescription = stringResource(id = R.string.icon_more),
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val chat = remember { vm.contactModelList }

                    LazyColumn() {
                        items(
                            items = chat,
                            itemContent = {
                                ChatListItem(chatt = it!!, navigateToProfile)
                            }
                        )
                    }


            }
        }

        Column(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.grup),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif
                )
                Icon(
                    painter = painterResource(R.drawable.icon_more),
                    contentDescription = stringResource(id = R.string.icon_more),
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val gchat = remember { vm2.GroupList }

                LazyColumn() {
                    items(
                        items = gchat,
                        itemContent = {
                            GChatListItem(gchatt = it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Messages(
    vm: ContactViewModel,
    vm2: GroupViewModel,navigateToProfile: (ContactModel) -> Unit)
{
    val displayMetrics = DisplayMetrics()
    val height = (displayMetrics.heightPixels / displayMetrics.density) - 150;
    val width = displayMetrics.widthPixels / displayMetrics.density;

    LaunchedEffect(
        Unit,
        block = {
            vm.getContactList()
            vm2.getGroupList()
        }
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .size(width = width.dp, height = height.dp)


    ) {
        Column(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.message),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif
                )
                Icon(
                    painter = painterResource(R.drawable.icon_more),
                    contentDescription = stringResource(id = R.string.icon_more),
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val chat = remember { vm.contactModelList }

                LazyColumn() {
                    items(
                        items = chat,
                        itemContent = {
                            ChatListItem(chatt = it!!, navigateToProfile)
                        }
                    )
                }


            }
        }


    }

}

@Composable
fun Group( vm: TodosViewModel,
           vm2: GroupViewModel){
    val displayMetrics = DisplayMetrics()
    val height = (displayMetrics.heightPixels / displayMetrics.density) - 150;
    val width = displayMetrics.widthPixels / displayMetrics.density;

    LaunchedEffect(
        Unit,
        block = {
            vm.getToDoList()
            vm2.getGroupList()
        }
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .size(width = width.dp, height = height.dp)


    ) {

        Column(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.grup),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif
                )
                Icon(
                    painter = painterResource(R.drawable.icon_more),
                    contentDescription = stringResource(id = R.string.icon_more),
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val gchat = remember { vm2.GroupList }

                LazyColumn() {
                    items(
                        items = gchat,
                        itemContent = {
                            GChatListItem(gchatt = it)
                        }
                    )
                }
            }
        }
    }
}





//@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
//@Preview(showBackground = true)
//@Composable
//
//fun DefaultPreview20(){
//
//    PAMActivityIntentTheme {
//        HomeField()
//    }
//
//
//}