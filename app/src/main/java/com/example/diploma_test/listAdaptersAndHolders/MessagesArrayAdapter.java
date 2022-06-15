package com.example.diploma_test.listAdaptersAndHolders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diploma_test.R;
import com.example.diploma_test.entity.Message;
import com.example.diploma_test.entity.MessageForTest;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat$InspectionCompanion;

public class MessagesArrayAdapter extends ArrayAdapter<MessageForTest> {

    public MessagesArrayAdapter(@NonNull Context context, @NonNull ArrayList<MessageForTest> objects) {
        super(context, R.layout.message_list_item, R.id.fakeTextViewForAdapter, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MessageForTest message = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_list_item,parent,false);
        }

        TextView sender = convertView.findViewById(R.id.senderTextView);
        TextView messageText = convertView.findViewById(R.id.messageTextView);
        TextView timeSent = convertView.findViewById(R.id.messageSentTime);

        sender.setText(message.getAuthor());
        messageText.setText(message.getText());
        timeSent.setText(message.getTime());
        return super.getView(position, convertView, parent);
    }
}
