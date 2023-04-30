package edu.uksw.fti.pam.pamactivityintent.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.uksw.fti.pam.pamactivityintent.R
import android.util.DisplayMetrics
import androidx.compose.foundation.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import edu.uksw.fti.pam.pamactivityintent.models.*
import kotlinx.coroutines.launch




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeField(
    navigateToProfile: (GroupsModel) -> Unit
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
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                "Sentigram",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color(0xff36a8eb),

                fontSize = 22.sp
            )

        }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen(navigateToProfile: (GroupsModel) -> Unit){
    val pagerState = rememberPagerState(pageCount = 2)

    Column (modifier = Modifier.background(Color.White)) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, navigateToProfile = navigateToProfile)
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState){


    val list = listOf(stringResource(R.string.grup),stringResource(R.string.grupFav))
    
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
fun TabsContent(pagerState: PagerState, navigateToProfile: (GroupsModel) -> Unit){

    HorizontalPager(state = pagerState) {page ->
        when (page){
            0-> Group(vm = GroupsViewModel(), navigateToProfile = navigateToProfile)
            1-> FavGroup(vm = FavGroupViewModel(), navigateToProfile = navigateToProfile)

        }
    }
}



@Composable
fun Group(
    vm: GroupsViewModel,
    navigateToProfile: (GroupsModel) -> Unit)
{
    val displayMetrics = DisplayMetrics()
    val height = (displayMetrics.heightPixels / displayMetrics.density) - 150;
    val width = displayMetrics.widthPixels / displayMetrics.density;

    LaunchedEffect(
        Unit,
        block = {
            vm.getContactList()

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

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val chat = remember { vm.groupsModelList }

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
fun FavGroup(
    vm: FavGroupViewModel,
    navigateToProfile: (GroupsModel) -> Unit)
{
    val displayMetrics = DisplayMetrics()
    val height = (displayMetrics.heightPixels / displayMetrics.density) - 150;
    val width = displayMetrics.widthPixels / displayMetrics.density;

    LaunchedEffect(
        Unit,
        block = {
            vm.getContactList()

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

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val chat = remember { vm.groupsModelList }

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