package com.broadcastreceiver1401

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        //TODO("MyReceiver.onReceive() is not implemented")
        if(intent.action.equals("android.provider.Telephony.SMS_RECEIVED")){
            val bundle = intent.extras
            val objects = bundle?.get("pdus") as Array<Any>
            val sms  = objects[0] as ByteArray
            val format = bundle.getString("format")
            val message = SmsMessage.createFromPdu(sms,format)

            val newIntent = Intent(context,MainActivity::class.java)
            newIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            newIntent.putExtra("msgSender",message.originatingAddress)
            newIntent.putExtra("msgBody",message.messageBody)

            context.startActivity(newIntent)
        }
    }
}