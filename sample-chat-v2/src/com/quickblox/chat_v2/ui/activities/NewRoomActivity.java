package com.quickblox.chat_v2.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.quickblox.chat_v2.R;
import com.quickblox.chat_v2.utils.GlobalConsts;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew Dmitrenko
 * Date: 4/12/13
 * Time: 4:38 PM
 */
public class NewRoomActivity extends Activity {

    private CheckBox persistentCheckBox;
    private CheckBox onlyMembersCheckBox;
    private EditText roomNameEditText;
    private Button joinRoomButton;

    private boolean isPersistentChecked = false;
    private boolean isOnlyMembersChecked = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_room_layout);
        initViews();
    }

    private void initViews() {
        persistentCheckBox = (CheckBox) findViewById(R.id.persistent_cb);
        onlyMembersCheckBox = (CheckBox) findViewById(R.id.only_members_cb);
        persistentCheckBox.setOnCheckedChangeListener(persistentCheckBoxCheckListener);
        onlyMembersCheckBox.setOnCheckedChangeListener(onlyMembersCheckBoxCheckListener);
        roomNameEditText = (EditText) findViewById(R.id.room_name_et);
        joinRoomButton = (Button) findViewById(R.id.room_join_btn);
        joinRoomButton.setOnClickListener(joinButtonClickListener);
    }

    private View.OnClickListener joinButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String roomName = roomNameEditText.getText().toString();
            if (!TextUtils.isEmpty(roomName)) {
                loadChatActivity();

            } else {
                Toast.makeText(getBaseContext(), getString(R.string.room_name_emty_msg), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void loadChatActivity() {
        Intent intent = new Intent(NewRoomActivity.this, ChatActivity.class);
        intent.putExtra(GlobalConsts.PREVIOUS_ACTIVITY, GlobalConsts.ROOM_ACTIVITY);
        intent.putExtra(GlobalConsts.IS_ROOM_PERSISTENT, isPersistentChecked);
        intent.putExtra(GlobalConsts.IS_ONLY_MEMBERS, isOnlyMembersChecked);
        intent.putExtra(GlobalConsts.ROOM_NAME, roomNameEditText.getText().toString());
        intent.putExtra(GlobalConsts.IS_NEW_ROOM, true);
        startActivity(intent);
        finish();
    }

    private CompoundButton.OnCheckedChangeListener persistentCheckBoxCheckListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            isPersistentChecked = isChecked;
        }
    };

    private CompoundButton.OnCheckedChangeListener onlyMembersCheckBoxCheckListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            isOnlyMembersChecked = isChecked;
        }
    };
}
