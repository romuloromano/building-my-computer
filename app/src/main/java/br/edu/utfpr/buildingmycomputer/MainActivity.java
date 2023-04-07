package br.edu.utfpr.buildingmycomputer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextProjectName, editTextInitialDate;
    private RadioGroup radioGroupProjectType;
    private Spinner spinnerOperationalSystem, spinnerOperationalSystemVersion;
    private CheckBox checkBoxMotherboard, checkBoxRamMemory, checkBoxHDD, checkBoxSSD, checkBoxGraphicsCard,
            checkBoxCabinet, checkBoxPowerSupply, checkBoxWaterCooler, checkBoxAirCooler, checkBoxFanCooler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextProjectName = findViewById(R.id.editTextProjectName);
        editTextInitialDate = findViewById(R.id.editTextInitialDate);

        radioGroupProjectType = findViewById(R.id.radioGroupProjectType);

        spinnerOperationalSystem = findViewById(R.id.spinnerOperationalSystem);
        spinnerOperationalSystemVersion = findViewById(R.id.spinnerOperationalSystemVersion);

        checkBoxMotherboard = findViewById(R.id.checkBoxMotherboard);
        checkBoxRamMemory = findViewById(R.id.checkBoxRamMemory);
        checkBoxHDD = findViewById(R.id.checkBoxHDD);
        checkBoxSSD = findViewById(R.id.checkBoxSSD);
        checkBoxGraphicsCard = findViewById(R.id.checkBoxGraphicsCard);
        checkBoxCabinet = findViewById(R.id.checkBoxCabinet);
        checkBoxPowerSupply = findViewById(R.id.checkBoxPowerSupply);
        checkBoxWaterCooler = findViewById(R.id.checkBoxWaterCooler);
        checkBoxAirCooler = findViewById(R.id.checkBoxAirCooler);
        checkBoxFanCooler = findViewById(R.id.checkBoxFanCooler);

        spinnerOperationalSystem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String operationalSystem = parent.getItemAtPosition(position).toString();

                if (operationalSystem.equals(getString(R.string.linux))) {
                    populateOperationalSystemVersion(R.array.operational_system_version_linux);
                } else {
                    populateOperationalSystemVersion(R.array.operational_system_version_windows);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void saveFields(View view) {
        String projectName = editTextProjectName.getText().toString();
        String initialDate = editTextInitialDate.getText().toString();
        int projectType = radioGroupProjectType.getCheckedRadioButtonId();

        if (projectName == null || projectName.trim().isEmpty()) {
            Toast.makeText(this, R.string.project_name_empty, Toast.LENGTH_LONG).show();
            editTextProjectName.requestFocus();
        } else if (initialDate == null || initialDate.trim().isEmpty()) {
            Toast.makeText(this, R.string.initial_date_empty, Toast.LENGTH_LONG).show();
            editTextInitialDate.requestFocus();
        } else if (projectType == -1) {
            Toast.makeText(this, R.string.project_type_empty, Toast.LENGTH_LONG).show();
            radioGroupProjectType.requestFocus();
        }
    }

    public void clearFields(View view) {
        editTextProjectName.setText(null);
        editTextInitialDate.setText(null);

        radioGroupProjectType.clearCheck();

        checkBoxMotherboard.setChecked(false);
        checkBoxRamMemory.setChecked(false);
        checkBoxHDD.setChecked(false);
        checkBoxSSD.setChecked(false);
        checkBoxGraphicsCard.setChecked(false);
        checkBoxCabinet.setChecked(false);
        checkBoxPowerSupply.setChecked(false);
        checkBoxWaterCooler.setChecked(false);
        checkBoxAirCooler.setChecked(false);
        checkBoxFanCooler.setChecked(false);

        editTextProjectName.requestFocus();

        Toast.makeText(this, R.string.clear_fields, Toast.LENGTH_LONG).show();
    }

    private void populateOperationalSystemVersion(int arrayId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arrayId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperationalSystemVersion.setAdapter(adapter);
    }
}