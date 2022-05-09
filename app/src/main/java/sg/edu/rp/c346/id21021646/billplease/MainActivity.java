package sg.edu.rp.c346.id21021646.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView TotalBill;
    TextView SplitBill;
    Button btnSplit;
    Button Reset;
    EditText Amount;
    EditText Pax;
    EditText Discount;
    ToggleButton SVS;
    ToggleButton GST;
    RadioGroup rgPayment;
    RadioButton cash;
    RadioButton paynow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TotalBill=findViewById(R.id.totalBill);
        SplitBill=findViewById(R.id.SplitBill);
        btnSplit=findViewById(R.id.buttonsplit);
        Amount=findViewById(R.id.Amount);
        Pax=findViewById(R.id.Pax);
        Discount=findViewById(R.id.Discount);
        SVS=findViewById(R.id.SVS);
        GST=findViewById(R.id.GST);
        rgPayment=findViewById(R.id.RadioGroup);
        cash=findViewById(R.id.cash);
        paynow=findViewById(R.id.paynow);
        Reset=findViewById(R.id.Reset);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double TotalAmount = Double.parseDouble(Amount.getText().toString());
                double pax = Double.parseDouble(Pax.getText().toString());
                double discount= Double.parseDouble(Discount.getText().toString());

                double gst = 1.0;
                if (SVS.isChecked() && !GST.isChecked()) {
                    gst *= 1.1;
                } else if (GST.isChecked()&& !SVS.isChecked()) {
                    gst *= 1.07;
                } else if (SVS.isChecked() && GST.isChecked()) {
                    gst *= 1.17;
                }
                double RawTotalAmount = TotalAmount * gst;
                double GrandTotal=RawTotalAmount-(RawTotalAmount*(discount/100)) ;
                double SplitAmount = GrandTotal / pax;
                TotalBill.setText("Total Bill: $" + GrandTotal);
                int CheckedRadio = rgPayment.getCheckedRadioButtonId();
                if(CheckedRadio==R.id.paynow) {
                    SplitBill.setText("Each Pays: $"+ SplitAmount + " Via PayNow to 912345678");
                }else if
                    (CheckedRadio == R.id.cash) {
                    SplitBill.setText("Each Pays: $"+ SplitAmount + " in Cash");
                }

            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Amount.setText("");
                Pax.setText("");
                SVS.setChecked(false);
                GST.setChecked(false);
                Discount.setText("");
                TotalBill.setText("Total Bill: $");
                rgPayment.clearCheck();
                SplitBill.setText("Each Pays: $");
            }
        });
    }
}

