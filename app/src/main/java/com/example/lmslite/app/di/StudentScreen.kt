package com.example.lmslite.app.di

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lmslite.features.feature_student.domain.model.Student
import com.example.lmslite.features.feature_student.presentation.StudentViewModel

@Composable
fun StudentScreen(  //Yêu cầu cấp phát Repository thông qua ViewModel
    viewModel: StudentViewModel = hiltViewModel()
) {
    //bằng collectAsState sẽ nhận dữ liệu từ ViewModel thành thứ Compose hiểu được
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            Text(
                "Quản lý Sinh Viên",
                //Modifer dùng để chỉnh khoảng cách
                modifier = Modifier.padding(16.dp),
                //font chữ Material và kích thước tiêu chuẩn
                style = MaterialTheme.typography.headlineMedium
            )
        }
    ) {paddingValues ->
        //paddingValues: Khoảng trống mà Scaffold để lại mà không bị đè bởi topbar
        //Column: Xếp thành phần theo chiều dọc
        Column(
            modifier = Modifier
                .padding(paddingValues) // Luôn áp dụng padding từ Scaffold
                .fillMaxSize()  //Tràn hết màn hình
        ) {
            if(state.isLoading){
                //CircularProgressIndicator: Vòng loading mặc định của Android
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            // ?.let{} nếu state.error không null thì mới thực hiện vẽ dòng tiếp dưới
            state.error?.let {
                Text(text = it, color = Color.Red, modifier = Modifier.padding(16.dp))
            }
            LazyColumn(
                contentPadding = PaddingValues(16.dp), //Khoảng cách lề cho toàn bộ danh sách
                verticalArrangement = Arrangement.spacedBy(8.dp) //Khoảng cách tự động giữa các hàng
            ) {
                items(state.students){student->
                    // Mỗi sinh viên thì vẽ hàm StudentItem bên dưới
                    StudentItem(student = student)
                }
            }
        }
    }
}
@Composable
fun StudentItem(student: Student) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp), // Khoảng cách bên ngoài Card
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp) // Khoảng cách bên trong Card
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically // Căn giữa Avatar và Chữ theo chiều dọc
        ) {
            // 1. Hình tròn đại diện (Avatar)
            Box(
                modifier = Modifier
                    .size(50.dp) // Tăng kích thước lên một chút cho dễ nhìn
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary), // Dùng màu chủ đạo của App
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = student.name.take(1).uppercase(),
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 2. Cột chứa thông tin sinh viên
            Column(
                modifier = Modifier.weight(1f) // Chiếm toàn bộ không gian còn lại để đẩy nội dung
            ) {
                Text(
                    text = student.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "MSSV: ${student.studentCode}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Email: ${student.email}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}
