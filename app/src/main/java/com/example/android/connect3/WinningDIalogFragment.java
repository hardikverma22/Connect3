package com.example.android.connect3;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;

/**
 * Created by hp on 07-10-2017.
 */

public class WinningDIalogFragment extends DialogFragment {

    String winner;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
    **/
    public static WinningDIalogFragment newInstance(String winnerName) {
        WinningDIalogFragment f = new WinningDIalogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("winner", winnerName);
        f.setArguments(args);

        return f;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        winner =getArguments().getString("winner");
        if(winner.contains("-2"))
        {
            winner="Draw!";
        }else
        {
            winner = winner+" Wins!";
        }
        AlertDialog.Builder builder = new  AlertDialog.Builder(getActivity());
        builder.setTitle("Game Over")
            //    .setMessage("Winner is winner");
                .setMessage(winner)
                .setCancelable(false)

                .setPositiveButton("Play Again",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onDialogPositiveClick(WinningDIalogFragment.this);
                    }

                });
        return builder.create();
    }


    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);

    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
