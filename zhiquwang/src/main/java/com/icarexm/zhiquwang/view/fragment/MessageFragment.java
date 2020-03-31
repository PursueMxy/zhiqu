package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.chatroom.MainChatRoom;
import com.icarexm.zhiquwang.socket.AppSocket;
import com.icarexm.zhiquwang.utils.IConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Observer;

import butterknife.ButterKnife;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    private Context mContext;
    private View inflate;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_message, container, false);
        mContext = getContext();
        ButterKnife.bind(this, inflate);
        MainChatRoom.getInstance().addObserver((Observer) this);
        return inflate;
    }

}
