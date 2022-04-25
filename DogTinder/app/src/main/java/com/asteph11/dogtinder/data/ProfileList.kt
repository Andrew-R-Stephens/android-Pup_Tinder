package com.asteph11.dogtinder.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.Log
import com.asteph11.dogtinder.R

class ProfileList @SuppressLint("ResourceType") constructor(c: Context) {

    var profiles = ArrayList<Profile>()
    var currentProfileIndex: Int = 0

    init {
        val typedArray: TypedArray =
            c.resources.obtainTypedArray(R.array.userProfiles)
        for (i in 0 until typedArray.length()) {
            val profileTypeArray: TypedArray =
                c.resources.obtainTypedArray(typedArray.getResourceId(i, 0))

            val name: String? = profileTypeArray.getString(0)
            val age: Int = profileTypeArray.getInteger(1, 0)
            val distance: Int = profileTypeArray.getInteger(2, 0)
            val bio: String? = profileTypeArray.getString(3)
            val img: Int = profileTypeArray.getResourceId(4, 0)
            Log.d("Bio", "$bio")

            profileTypeArray.recycle()

            val p = Profile(name, age, distance, bio, img)
            profiles.add(p)
        }
        typedArray.recycle()
    }

    fun hasNextProfile(): Boolean {
        return currentProfileIndex < profiles.size
    }

    fun getNextProfile(): Profile {
        return profiles[currentProfileIndex++]
    }

    fun hasAwaitingNextProfile(): Boolean {
        return currentProfileIndex < profiles.size
    }

    fun getAwaitingNextProfile(): Profile {
        return profiles[currentProfileIndex - 1]
    }

    class Profile(n: String?, a: Int, d: Int, b: String?, i: Int) {
        var name: String? = n
        var age: Int = a
        var distance: Int = d
        var bio: String? = b
        var img: Int = i
    }
}
