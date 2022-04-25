package com.asteph11.dogtinder

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.get
import com.asteph11.dogtinder.data.ProfileList

/**
 * @see <a href = https://www.youtube.com/watch?v=lwAvI3WDXBY><h1>Youtube Video</h1></a>
 */
class MainActivity : Activity() {

    private lateinit var profileList: ProfileList

    private lateinit var mDetector: GestureDetectorCompat
    private lateinit var profileContainer: ConstraintLayout
    private lateinit var profileWaitingContainer: ConstraintLayout
    private lateinit var statusIcon: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileList = ProfileList(this)

        mDetector = GestureDetectorCompat(this, MyGestureListener())
        profileContainer = findViewById(R.id.profileContainer)
        profileWaitingContainer = findViewById(R.id.profileContainer_waiting)
        statusIcon = findViewById(R.id.status)

        loadNextProfile()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    fun loadNextProfile(vararg vel: Float) {
        if (!profileList.hasNextProfile()) {
            (this@MainActivity).loadDefaultProfile(vel[0], vel[1])
            return
        }

        //Immediate Next Profile
        val p: ProfileList.Profile = profileList.getNextProfile()
        val inflator: LayoutInflater = LayoutInflater.from(this)
        val profileLayout =
            (inflator.inflate(
                R.layout.layout_profile, null
            )
                    ) as RelativeLayout
        val userName = profileLayout.findViewById<AppCompatTextView>(R.id.userName)
        val userAge = profileLayout.findViewById<AppCompatTextView>(R.id.userAge)
        val userDistance = profileLayout.findViewById<AppCompatTextView>(R.id.userDistance)
        val userBio = profileLayout.findViewById<AppCompatTextView>(R.id.userBio)
        val userImg = profileLayout.findViewById<AppCompatImageView>(R.id.userImg)
        userName.text = p.name
        userAge.text = "${p.age}"
        userDistance.text = "${p.distance} miles away"
        userBio.text = p.bio
        userImg.setImageDrawable(ResourcesCompat.getDrawable(resources, p.img, theme))

        //Awaiting Next Profile
        var profile2Layout = RelativeLayout(this)
        if (profileList.hasAwaitingNextProfile()) {
            val p2 = profileList.getAwaitingNextProfile()
            profile2Layout =
                (inflator.inflate(
                    R.layout.layout_profile, null
                )
                        ) as RelativeLayout
            val userName2 = profile2Layout.findViewById<AppCompatTextView>(R.id.userName)
            val userAge2 = profile2Layout.findViewById<AppCompatTextView>(R.id.userAge)
            val userDistance2 = profile2Layout.findViewById<AppCompatTextView>(R.id.userDistance)
            val userBio2 = profile2Layout.findViewById<AppCompatTextView>(R.id.userBio)
            val userImg2 = profile2Layout.findViewById<AppCompatImageView>(R.id.userImg)
            userName2.text = p2.name
            userAge2.text = "${p2.age}"
            userDistance2.text = "${p2.distance} miles away"
            userBio2.text = p2.bio
            userImg2.setImageDrawable(ResourcesCompat.getDrawable(resources, p2.img, theme))
        }

        if (vel.isNotEmpty()) {
            profileWaitingContainer.removeAllViews()
            profileWaitingContainer.addView(profile2Layout)
            profileWaitingContainer.alpha = 1f
            /*profileWaitingContainer.animate()
                .alpha(1f)
                .setDuration(500)
                .start()*/
            profileWaitingContainer.alpha = .25f
            if (vel[0] < 0) {
                statusIcon.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.sad,
                        theme
                    )
                )
            } else {
                statusIcon.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.love,
                        theme
                    )
                )
            }

            ObjectAnimator.ofFloat(profileContainer[0], "translationY", vel[1]).apply {
                duration = 500
                start()
            }
            ObjectAnimator.ofFloat(profileContainer[0], "translationX", vel[0]).apply {
                duration = 500
                start()
                statusIcon.animate()
                    .alpha(.8f)
                    .setDuration(500)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            statusIcon.animate()
                                .alpha(0f)
                                .duration = 500
                        }
                    })
                    .start()
                doOnEnd {
                    Log.d("Animate", "OnEnd")
                    profileContainer.removeAllViews()
                    profileContainer.addView(profileLayout)
                }
            }
        } else {
            profileContainer.removeAllViews()
            profileContainer.addView(profileLayout)
        }
    }

    private fun loadDefaultProfile(vararg vel: Float) {
        val inflator: LayoutInflater = LayoutInflater.from(this)
        val defaultProfile: RelativeLayout =
            (inflator.inflate(
                R.layout.layout_defaultprofile, null
            ) as RelativeLayout)

        profileContainer.removeAllViews()
        profileContainer.addView(defaultProfile)

    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(event: MotionEvent): Boolean {
            //Log.d("Swipe", "onDown: $event")
            return true
        }

        override fun onFling(
            event1: MotionEvent,
            event2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {

            loadNextProfile(velocityX, velocityY)

            return true
        }
    }
}