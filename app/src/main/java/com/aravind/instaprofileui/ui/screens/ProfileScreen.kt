package com.aravind.instaprofileui.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aravind.instaprofileui.R
import com.aravind.instaprofileui.ui.model.ImageWithText

@Composable
fun ProfileScreen() {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        TopBar(name = "Amber Herd(im_amber)")
        Spacer(modifier = Modifier.height(4.dp))
        ProfileSection(modifier = Modifier.padding(10.dp))
        Spacer(modifier = Modifier.height(15.dp))
        ButtonSection(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(25.dp))
        HighlightSection(highlight = listOf(
            ImageWithText("Q&A", painterResource(id = R.drawable.qa)),
            ImageWithText("Youtube", painterResource(id = R.drawable.youtube)),
            ImageWithText("Discord", painterResource(id = R.drawable.discord)),
            ImageWithText("Telegram", painterResource(id = R.drawable.telegram)),
            ),
            modifier = Modifier.padding(horizontal = 3.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        PostTabView(imageWithTextList =
            listOf(
                ImageWithText(text = "Posts", image =  painterResource(id = R.drawable.ic_grid)),
                ImageWithText(text ="IGTV", image =painterResource(id = R.drawable.ic_igtv)),
                ImageWithText(text ="Reels", image =painterResource(id = R.drawable.ic_reels)),
                ImageWithText(text ="Profile",image = painterResource(id = R.drawable.profile),
                )
            )
        ) {
            selectedTabIndex = it
        }
            when(selectedTabIndex){
                0 -> {
                    PostSection(postList =
                    listOf(
                        painterResource(id = R.drawable.ic_image_one),
                        painterResource(id = R.drawable.ic_image_two),
                        painterResource(id = R.drawable.ic_image_three),
                        painterResource(id = R.drawable.ic_image_four),
                        painterResource(id = R.drawable.ic_image_five),
                        painterResource(id = R.drawable.ic_image_six),
                        ),
                    modifier = Modifier.fillMaxWidth()
                    )
                }

        }
    }
}

@Composable
fun TopBar(
    name: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth(),

        ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            fontSize = 20.sp
        )
        Image(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "Bell",
            modifier = Modifier.size(20.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_dotmenu),
            contentDescription = "Dot Menu",
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            //  horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            RoundImage(
                image = painterResource(id = R.drawable.ic_profile),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            StateSection(
                modifier = Modifier.weight(7f)
            )
        }
        ProfileDescription(
            displayName = "Mobile Developer",
            description = "5+ years of coding experience",
            otherCount = 77,
            followedBy = listOf("Prasad T, Arunkumar M"),
            url = "https://github.com/aravinfaf",
        )

    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
fun StateSection(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ProfileStat(numberText = "500", description = "Posts")
        ProfileStat(numberText = "500K", description = "Followers")
        ProfileStat(numberText = "50", description = "Following")
    }
}

@Composable
fun ProfileStat(
    numberText: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = description)
    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    otherCount: Int,
    followedBy: List<String>,
    url: String,
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = description,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = url,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight,
            color = Color(0xff3d3d91)
        )
        if (followedBy.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    append("Followed by ")
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if (index < followedBy.size - 1) {
                            append(", ")
                        }
                    }
                    if (otherCount > 2) {
                        append(" and ")
                        pushStyle(boldStyle)
                        append("$otherCount others")
                    }
                },
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }
    }
}

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier,
) {
    val minWidth = 95.dp
    val height = 34.dp

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        ActionButton(
            text = "Following",
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            text = "Message",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            text = "Email",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .height(height)
        )
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(6.dp)
    ) {
        if (text != null) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@Composable
fun HighlightSection(
    modifier: Modifier = Modifier,
    highlight: List<ImageWithText>,
) {
    LazyRow(modifier = modifier) {
        items(highlight.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                RoundImage(
                    image = highlight[it].image,
                    modifier = modifier.size(70.dp))

                Text(text = highlight[it].text,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithTextList : List<ImageWithText>,
    onTabSelected : (selectedIndex : Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inActiveColor = Color(0XFF777777)
    
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier
        ) {

        imageWithTextList.forEachIndexed { index, imageWithText ->

            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inActiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }) {
                Icon(
                    painter = imageWithTextList[index].image,
                    contentDescription = imageWithText.text,
                    tint = if (selectedTabIndex == index) Color.Black else inActiveColor,
                    modifier = modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostSection(
    modifier: Modifier = Modifier,
    postList : List<Painter>
) {
    LazyVerticalGrid(
        cells =  GridCells.Fixed(3),
        modifier = modifier.scale(1.01f)
        ){
        items(postList.size){
            Image(
                painter = postList[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .border(
                    width = 1.dp,
                    color = Color.White
                )
            )
        }
    }
}