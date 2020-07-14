package com.phayathai.dashboard_bedv2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.phayathai.dashboard_bedv2.adapter.RoomListener
import com.phayathai.dashboard_bedv2.remote.phayathaiApi
import com.phayathai.dashboard_bedv2.adapter.*
import com.phayathai.dashboard_bedv2.model.view.response.ViewRes
import com.phayathai.dashboard_bedv2.adapter.RoomAdapter
import kotlinx.android.synthetic.main.room_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RoomFragment: Fragment(), RoomListener {

    lateinit var mainHandler: Handler
    private val updateCallTask = object : Runnable {
        override fun run() {
            mainHandler.postDelayed(this, 10000)
            CallApi()
            Log.d("CallApi Room 10 Sec : ","OK")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.room_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainHandler = Handler(Looper.getMainLooper())

    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateCallTask)
    }

    override fun onItemClick() {

    }

    fun CallApi()
    {

        val MDC_URL = "http://freewillmdc.loginto.me:56870/"
        val PHAYATHAI_URL = "http://192.168.43.254/"
        val SOSO_URL = "http://192.168.1.51/"
        val SOSOFWG_URL = "http://10.32.10.71/"
        val path_URL = "phayathai_nurse_tracking_backend/api/"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PHAYATHAI_URL+path_URL)
            .build()
        val phayathaiApi  = retrofit.create(phayathaiApi::class.java)
        var myCall: Call<ViewRes> = phayathaiApi.getRoom()
        myCall.enqueue(object: Callback<ViewRes> {

            override fun onFailure(call: Call<ViewRes>, t: Throwable) {
                Log.d("Network Fail - > ","${t.localizedMessage}")
            }

            override fun onResponse(call: Call<ViewRes>, response: Response<ViewRes>)
            {
                val responseJSON = response.body()
                val room_list = responseJSON?.body?.room

//                Log.d("|responseJSON|", responseJSON?.body.toString())
                Log.d("|Room| ",room_list.toString())

                val roomAdapter = RoomAdapter( room_list , this@RoomFragment,context)

                recyclerView_room.apply {
                    layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                    isNestedScrollingEnabled = false
                    adapter = roomAdapter
                    onFlingListener = null
                }
                recyclerView_room2.apply {
                    layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
                    isNestedScrollingEnabled = false
                    adapter = roomAdapter
                    onFlingListener = null
                }



                roomAdapter.notifyDataSetChanged()

            }
        })
    }

}

