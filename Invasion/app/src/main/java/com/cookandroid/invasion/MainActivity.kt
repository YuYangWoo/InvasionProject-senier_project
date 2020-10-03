package com.cookandroid.invasion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.invasion.OptionActivity
import com.cookandroid.invasion.log.LogActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


class MainActivity : AppCompatActivity() {
    private var mqttAndroidClient: MqttAndroidClient? = null
    // BackpressCloseHandler 객체화
    private val backPressCloseHandler = BackPressCloseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mqttAndroidClient = MqttAndroidClient(
            this, "tcp://192.168.25.43:1883", MqttClient.generateClientId(),
            MemoryPersistence(), MqttAndroidClient.Ack.AUTO_ACK
        )

        // btnLog 클릭 이벤트
        btnLog.setOnClickListener {
            // LogActivity 호출
            startActivity(Intent(this, LogActivity::class.java))
        }

        // btnOption 클릭 이벤트
        btnOption.setOnClickListener {
            // OptionActivity 호출
            startActivity(Intent(this, OptionActivity::class.java))
        }

        try {
            val token = mqttAndroidClient!!.connect(getMqttConnectionOption()) // mqtttoken 이라는것을 만들어 connect option을 달아줌
            token.actionCallback = object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    mqttAndroidClient!!.setBufferOpts(getDisconnectedBufferOptions()) // 연결에 성공한경우
                    Log.e("Connect_success", "Success")
                    try {
                        mqttAndroidClient!!.subscribe("alarm", 0)
                        mqttAndroidClient!!.subscribe("danger", 0)
                        mqttAndroidClient!!.subscribe("door", 0)
                        mqttAndroidClient!!.subscribe("door_open", 0)
                        mqttAndroidClient!!.subscribe("weight", 0)
                    } catch (e: MqttException) {
                        e.printStackTrace()
                    }
                }
                override fun onFailure(
                    asyncActionToken: IMqttToken,
                    exception: Throwable
                ) {   // 연결에 실패한경우
                    Log.e("connect_fail", "Failure $exception")
                }
            }
        } catch (e: MqttException) {
            e.printStackTrace()
        }
        mqttAndroidClient!!.setCallback(object : MqttCallback {
            // 클라이언트의 콜백을 처리하는부분
            override fun connectionLost(cause: Throwable) {}

            @Throws(Exception::class)
            override fun messageArrived(
                topic: String,
                message: MqttMessage
            ) {    // 모든 메시지가 올때 Callback method
                if (topic == "door") {     // topic 별로 분기처리하여 작업을 수행할수도있음
                    val msg = String(message.payload)
                    Log.e("arrive message : ", msg)
                    // 문이 열린경우에 해당되는 알고리즘 필요
                }
                else if(topic == "danger"){
                    val msg = String(message.payload)
                    Log.e("arrive message : ", msg)
                    // 위험한 경우 해당되는 알고리즘 필요
                }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken) {}
        })


    }


    // Back 버튼을 눌렀을 때
    override fun onBackPressed() {
        backPressCloseHandler.onBackPressed()
    }

    private fun getDisconnectedBufferOptions(): DisconnectedBufferOptions? {
        val disconnectedBufferOptions = DisconnectedBufferOptions()
        disconnectedBufferOptions.isBufferEnabled = true
        disconnectedBufferOptions.bufferSize = 100
        disconnectedBufferOptions.isPersistBuffer = true
        disconnectedBufferOptions.isDeleteOldestMessages = false
        return disconnectedBufferOptions
    }

    private fun getMqttConnectionOption(): MqttConnectOptions? {
        val mqttConnectOptions = MqttConnectOptions()
        mqttConnectOptions.isCleanSession = false
        mqttConnectOptions.isAutomaticReconnect = true
        mqttConnectOptions.setWill("aaa", "I am going offline".toByteArray(), 1, true)
        return mqttConnectOptions
    }
}