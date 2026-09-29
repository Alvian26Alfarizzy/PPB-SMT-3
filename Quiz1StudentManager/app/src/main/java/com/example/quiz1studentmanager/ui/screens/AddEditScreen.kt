package com.example.quiz1studentmanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quiz1studentmanager.model.Student
import com.example.quiz1studentmanager.viewmodel.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("DEPRECATION")
@Composable
fun AddEditScreen(
    navController: NavController,
    viewModel: StudentViewModel,
    studentId: String? = null
) {
    val isEditing = studentId != null
    var nim by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var studyProgram by remember { mutableStateOf("") }

    val studyPrograms = listOf(
        "Informatika",
        "Sistem Informasi",
        "Teknik Komputer",
        "Desain Komunikasi Visual",
        "Manajemen",
        "Akuntansi",
        "Rekayasa Perangkat Lunak"
    )

    LaunchedEffect(studentId) {
        if (studentId != null) {
            viewModel.getStudentById(studentId)?.let { student ->
                nim = student.nim
                name = student.name
                studyProgram = student.studyProgram
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditing) "Edit Mahasiswa" else "Tambah Mahasiswa", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = nim,
                onValueChange = { nim = it },
                label = { Text("NRP") },
                placeholder = { Text("Masukkan NRP") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nama") },
                placeholder = { Text("Masukkan nama mahasiswa") },
                modifier = Modifier.fillMaxWidth()
            )

            var expanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = studyProgram,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Program Studi") },
                    placeholder = { Text("Pilih program studi") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    studyPrograms.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                studyProgram = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Batal")
                }
                Button(
                    onClick = {
                        val student = Student(
                            id = studentId ?: java.util.UUID.randomUUID().toString(),
                            nim = nim,
                            name = name,
                            studyProgram = studyProgram
                        )
                        if (studentId != null) {
                            viewModel.updateStudent(student)
                        } else {
                            viewModel.addStudent(student)
                        }
                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f),
                    enabled = nim.isNotBlank() && name.isNotBlank() && studyProgram.isNotBlank()
                ) {
                    Text("Simpan")
                }
            }
        }
    }
}
