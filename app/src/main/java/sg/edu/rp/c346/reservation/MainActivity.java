package sg.edu.rp.c346.reservation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etTelephone;
    EditText etSize;
    CheckBox checkBox;
    EditText etDate;
    EditText etTime;
    Button btReserve;
    Button btReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etTelephone = findViewById(R.id.editTextTelephone);
        etSize = findViewById(R.id.editTextSize);
        checkBox = findViewById(R.id.checkBox);
        etDate = findViewById(R.id.editDate);
        etTime = findViewById(R.id.editTime);
        btReserve = findViewById(R.id.buttonReserve);
        btReset = findViewById(R.id.buttonReset);


        Calendar now = Calendar.getInstance();
        String time = now.get(Calendar.HOUR_OF_DAY) + ":" +
                now.get(Calendar.MINUTE);

        String date = now.get(Calendar.DAY_OF_MONTH) + "/" +
                (now.get(Calendar.MONTH)+1) + "/" +
                now.get(Calendar.YEAR);

        etTime.setHint(time);
        etDate.setHint(date);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText( dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                };

                Calendar c = Calendar.getInstance();
                int cYear = c.get(Calendar.YEAR);
                int cMonth = c.get(Calendar.MONTH);
                int cDate = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,
                        myDateListener, cYear, cMonth, cDate);
                myDateDialog.show();
            }
        });



        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etTime.setHint(hourOfDay + ":" + minute);
                    }
                };

                Calendar c = Calendar.getInstance();

                int cHour = c.get(Calendar.HOUR_OF_DAY);
                int cMinute = c.get(Calendar.MINUTE);

                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener, cHour, cMinute, true);
                myTimeDialog.show();
            }
        });






        btReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isSmoke = "";
                if (checkBox.isChecked()) {
                    isSmoke = "smoking";
                }
                else {
                    isSmoke = "non-smoking";
                }
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setTitle("Confirm Your Order");
                myBuilder.setMessage("New Reservation" + "\n" + "Name: " + etName.getText().toString() +
                "\n" + "Smoking: " + isSmoke+ "\n" +"Size: " + etSize.getText().toString()+ "\n" +"Date: " + etDate.getText().toString()+ "\n" +
               "Time: " + etTime.getText().toString());

                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("confirm", null);
                myBuilder.setNeutralButton("cancel", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

                /*Toast.makeText(MainActivity.this,
                        "Hi, " + etName.getText().toString() + ", you have booked a "
                                + etSize.getText().toString() + " person(s) "
                                + isSmoke + " table on "
                                + etDate.getText().toString() + " at "
                                + etTime.getText().toString()  + ". Your phone number is "
                                + etTelephone.getText().toString() + ".",
                        Toast.LENGTH_LONG).show(); */
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
                etTelephone.setText("");
                etSize.setText("");
                etDate.setText("");
                etTime.setText("");
                checkBox.setChecked(false);

            }
        });
    }
}