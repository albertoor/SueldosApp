package com.albertoornelas.sueldosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    var finalSalary = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get references
        val employeesTypes = resources.getStringArray(R.array.employeesType)
        val txtEmployee = findViewById<EditText>(R.id.txtEmployee)
        val txtSalary = findViewById<EditText>(R.id.txtSalary)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val result = findViewById<TextView>(R.id.txtResult)

        val spinner = findViewById<Spinner>(R.id.spinnerTypeEmployee)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, employeesTypes
            )
            spinner.adapter = adapter
        }

        // btn listener
        btnCalculate.setOnClickListener(){
            finalSalary = 0.0
            if (txtEmployee.text.toString().isNullOrEmpty() || txtSalary.text.toString().isNullOrEmpty()){
                Toast.makeText(this, "Campos vacios", Toast.LENGTH_LONG).show()
            }else{
                val employeeObj = Employee(txtEmployee.text.toString(), spinner.selectedItem.toString(),txtSalary.text.toString().toDouble())
                finalSalary = caculate(employeeObj.salary, employeeObj.type)
                val text = "El sueldo final con el bono es $finalSalary para el empleado '${employeeObj.employee}' "
                result.text = text
                txtEmployee.setText("")
                txtSalary.setText("")
            }

        }
    }

    private fun caculate(salary: Double, type: String) : Double{
        var finalSalary = 0.0
        when (type) {
            "Tipo 1" -> finalSalary = salary + (salary * 0.05)
            "Tipo 2" -> finalSalary = salary + (salary * 0.07)
            "Tipo 3" -> finalSalary = salary + (salary * 0.09)
            "Tipo 4" -> finalSalary =  salary + (salary * 0.12)
            "Tipo 5" -> finalSalary =  salary + (salary * 0.15)
        }
        return finalSalary
    }

    // data class employee
    data class Employee(
        val employee: String,
        val type: String,
        val salary: Double
    )
}