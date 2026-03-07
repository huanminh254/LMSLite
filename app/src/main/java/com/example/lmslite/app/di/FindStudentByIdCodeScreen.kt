package com.example.lmslite.app.di

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lmslite.features.feature_student.presentation.StudentViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FindGradeyIdCodeStudent(
    viewModel: StudentViewModel = hiltViewModel()
){
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            Text(
                text = "Tìm kiếm sinh viên",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineMedium,

            )
        }
    ) {paddingValues ->
        Card() {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = state.searchCode,
                    onValueChange = {viewModel.onChanged(it)},
                    label = {Text("Nhập Mã Sinh Viên")},
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {keyboardController?.hide()
                        viewModel.checkSearch()
                              },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tìm Kiếm")
                }
                if(state.isLoading){
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
                else if(state.error != null){
                    Text(
                        text = state.error!!,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                else{
                    state.students.forEach { value->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(text = "Họ Và Tên: ${value.name}", style = MaterialTheme.typography.titleLarge)
                                Text(text = "Mã Sinh Viên: ${value.studentCode}", style = MaterialTheme.typography.titleLarge)
                                Text(text = "Email: ${value.email}", style = MaterialTheme.typography.titleLarge)
                            }
                        }
                    }
                }
            }
        }
    }
}