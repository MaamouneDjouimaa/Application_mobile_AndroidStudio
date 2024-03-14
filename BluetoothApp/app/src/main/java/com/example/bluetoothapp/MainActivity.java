package com.example.bluetoothapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter bluetoothAdapter;

    private Button activateButton;
    private Button disableButton;
    private Button researchButton;

    private List<BluetoothDevice> discoveredDevicesList;

    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                discoveredDevicesList.add(device);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                showDiscoveredDevices();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        activateButton = findViewById(R.id.activate_button);
        disableButton = findViewById(R.id.disable_button);
        researchButton = findViewById(R.id.research_button);

        activateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableBluetooth();
            }
        });

        disableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableBluetooth();
            }
        });

        researchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBluetoothDiscovery();
            }
        });
    }

    private void enableBluetooth() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Le Bluetooth n'est pas pris en charge sur cet appareil", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            Toast.makeText(this, "Le Bluetooth est déjà activé", Toast.LENGTH_SHORT).show();
        }
    }

    private void disableBluetooth() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Le Bluetooth n'est pas pris en charge sur cet appareil", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
            Toast.makeText(this, "Bluetooth désactivé", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Le Bluetooth est déjà désactivé", Toast.LENGTH_SHORT).show();
        }
    }

    private void startBluetoothDiscovery() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Le Bluetooth n'est pas pris en charge sur cet appareil", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }

        discoveredDevicesList = new ArrayList<>();

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(bluetoothReceiver, filter);

        bluetoothAdapter.startDiscovery();
    }

    private void showDiscoveredDevices() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Appareils Bluetooth disponibles");

        List<String> deviceNames = new ArrayList<>();
        for (BluetoothDevice device : discoveredDevicesList) {
            deviceNames.add(device.getName());
        }

        if (deviceNames.isEmpty()) {
            builder.setMessage("Aucun appareil Bluetooth trouvé.");
        } else {
            builder.setItems(deviceNames.toArray(new String[0]), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    BluetoothDevice selectedDevice = discoveredDevicesList.get(which);
                    // Effectuez l'action souhaitée avec l'appareil sélectionné
                }
            });
        }

        builder.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(bluetoothReceiver);
    }
}
