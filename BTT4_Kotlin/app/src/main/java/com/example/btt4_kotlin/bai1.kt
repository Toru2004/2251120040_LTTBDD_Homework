package com.example.btt4_kotlin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.btt4_kotlin.listBook
@Composable
fun baitap4(navHostController: NavHostController) {
    var i by remember { mutableStateOf(0) }
    val user = listUser[i]
    val listBook = listBookAndItsOwner[user] ?: emptyList()
    var showDialog by remember { mutableStateOf(false) }
    var showStudentDialog by remember { mutableStateOf(false) }
    val addedBook = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp, horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Hệ thống\nQuản lý Thư viện",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        SinhVienSection(user = user, onNext = { showStudentDialog = true })
        Spacer(modifier = Modifier.height(20.dp))

        BookSection(user, listBook)
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.width(130.dp).height(55.dp),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                addedBook.clear()
                addedBook.addAll(listBook)
                showDialog = true
            }
        ) {
            Text("Chỉnh sửa", fontSize = 15.sp)
        }
    }

    if (showDialog) {
        BookDialog(
            user = user,
            addedBook = addedBook,
            onDismiss = { showDialog = false },
            onConfirm = {
                listBookAndItsOwner[user] = addedBook.toList()
            }
        )
    }

    if (showStudentDialog) {
        SelectStudentDialog(
            currentUser = user,
            onDismiss = { showStudentDialog = false },
            onStudentSelected = {
                i = listUser.indexOf(it)
                showStudentDialog = false
            }
        )
    }
}

@Composable
fun SelectStudentDialog(currentUser: String, onDismiss: () -> Unit, onStudentSelected: (String) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = { Text("Chọn sinh viên", fontSize = 18.sp) },
        text = {
            Column {
                listUser.forEach { student ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onStudentSelected(student) }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = student == currentUser,
                            onClick = { onStudentSelected(student) }
                        )
                        Text(student, fontSize = 14.sp)
                    }
                }
            }
        }
    )
}

@Composable
fun BookDialog(user: String, addedBook: SnapshotStateList<String>, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Row {
                Button(onClick = {
                    onConfirm()
                    onDismiss()
                }) {
                    Text("Cập nhật", fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDismiss) {
                    Text("Đóng")
                }
            }
        },
        title = { Text("Thêm sách", fontSize = 20.sp) },
        text = {
            LazyColumn {
                val ownerbooks = listBookAndItsOwner[user] ?: emptyList()
                val bookinlist = listBook.filter { it !in ownerbooks }

                item {
                    if (ownerbooks.isEmpty()) Text("Chưa thêm sách.", fontSize = 15.sp)
                    else {
                        Text("Sách đã thêm", fontSize = 15.sp)
                        ownerbooks.forEach { BookItemRow(it, addedBook) }
                    }
                    Divider()
                    if (bookinlist.isEmpty()) Text("Hết sách.", fontSize = 15.sp)
                    else {
                        Text("Sách còn dư", fontSize = 15.sp)
                        bookinlist.forEach { BookItemRow(it, addedBook) }
                    }
                }
            }
        }
    )
}

@Composable
fun BookItemRow(book: String, addedBook: SnapshotStateList<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (addedBook.contains(book)) addedBook.remove(book)
                else addedBook.add(book)
            }
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = addedBook.contains(book),
            onCheckedChange = {
                if (addedBook.contains(book)) addedBook.remove(book)
                else addedBook.add(book)
            },
            colors = CheckboxDefaults.colors(Color.Red)
        )
        Text(book)
    }
}

@Composable
fun SinhVienSection(user: String, onNext: () -> Unit) {
    Column {
        Text("Sinh viên")
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .width(200.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(15.dp))
            ) {
                Text(user, fontSize = 15.sp, modifier = Modifier.padding(10.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                modifier = Modifier.width(110.dp).height(55.dp),
                shape = RoundedCornerShape(15.dp),
                onClick = onNext
            ) {
                Text("Thay đổi", fontSize = 15.sp)
            }
        }
    }
}

@Composable
fun BookSection(user: String, listBook: List<String>) {
    Column {
        Text("Danh sách sách của $user", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))

        if (listBook.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Bạn chưa mượn quyển sách nào.", fontSize = 14.sp)
                    Text("Nhấn \"Thêm\" để bắt đầu hành trình đọc sách!", fontSize = 14.sp)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.LightGray)
                    .padding(8.dp)
            ) {
                items(listBook.size) { index ->
                    val book = listBook[index]
                    BookItem(book)
                }
            }
        }
    }
}

@Composable
fun BookItem(book: String) {
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = true,
                onCheckedChange = null,
                colors = CheckboxDefaults.colors(Color.Red)
            )
            Text(book, fontSize = 14.sp, modifier = Modifier.padding(start = 8.dp))
        }
    }
}



