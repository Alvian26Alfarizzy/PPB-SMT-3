package com.example.quiz1studentmanager.viewmodel

import androidx.lifecycle.ViewModel
import com.example.quiz1studentmanager.model.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StudentViewModel : ViewModel() {
    // Initial dummy data as shown in the wireframe
    private val _students = MutableStateFlow(
        listOf(
            Student(nim = "5053251044", name = "Fauzan", studyProgram = "Informatika"),
            Student(nim = "5053251004", name = "Khauzaky", studyProgram = "Sistem Informasi"),
            Student(nim = "5053251009", name = "Andi Wijaya", studyProgram = "Teknik Komputer"),
            Student(nim = "5053251005", name = "Dheva Alvian Alfarizzy Excellent", studyProgram = "Rekayasa Perangkat Lunak")
        )
    )
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun addStudent(student: Student) {
        _students.update { current -> current + student }
    }

    fun updateStudent(updatedStudent: Student) {
        _students.update { current ->
            current.map { if (it.id == updatedStudent.id) updatedStudent else it }
        }
    }

    fun deleteStudent(studentId: String) {
        _students.update { current ->
            current.filter { it.id != studentId }
        }
    }

    fun getStudentById(id: String): Student? {
        return _students.value.find { it.id == id }
    }
}
