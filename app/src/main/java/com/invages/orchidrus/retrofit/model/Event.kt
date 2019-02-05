package com.invages.orchidrus.retrofit.model

import android.util.Log

class Event(id: String, name: String, description: String, createdAt: String, updatedAt: String) {

    internal var id = ""
    var name = ""
    var description = ""
    var createdAt = ""
    var updatedAt = ""

    private var TAG = javaClass.simpleName

    init {
        this.id = id
        this.name = name
        this.description = description
        this.createdAt = createdAt
        this.updatedAt = updatedAt
    }

    fun getId(): String {
        Log.i(TAG, "getId: ")
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

}
