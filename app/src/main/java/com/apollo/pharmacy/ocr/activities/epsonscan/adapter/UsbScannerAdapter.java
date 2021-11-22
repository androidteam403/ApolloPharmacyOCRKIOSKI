package com.apollo.pharmacy.ocr.activities.epsonscan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apollo.pharmacy.ocr.R;
import com.apollo.pharmacy.ocr.databinding.AdapterUsbScannerBinding;
import com.epson.epsonscansdk.usb.UsbProfile;

import java.util.List;

public class UsbScannerAdapter extends RecyclerView.Adapter<UsbScannerAdapter.ViewHolder> {
    private Context context;
    private List<UsbProfile> usbDevices;

    public UsbScannerAdapter(Context context, List<UsbProfile> usbDevices) {
        this.context = context;
        this.usbDevices = usbDevices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterUsbScannerBinding usbScannerBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_usb_scanner, parent, false);
        return new UsbScannerAdapter.ViewHolder(usbScannerBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UsbProfile usbProfile = usbDevices.get(position);
        holder.usbScannerBinding.usbScannerName.setText(usbProfile.getProductName());
    }

    @Override
    public int getItemCount() {
        return usbDevices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterUsbScannerBinding usbScannerBinding;

        public ViewHolder(@NonNull AdapterUsbScannerBinding usbScannerBinding) {
            super(usbScannerBinding.getRoot());
            this.usbScannerBinding = usbScannerBinding;
        }
    }
}
