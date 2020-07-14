package com.phayathai.dashboard_bedv2.model.view.response

data class Room(
    val device_id: String,
    val nurse_list: ArrayList<Nurse>,
    val ordinal: Int,
    val room_title: String
)