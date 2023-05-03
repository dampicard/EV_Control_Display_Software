package commands

import android.content.Context

class SeatControl_UserInterface {
    fun setupImages(context : Context){
        context.resources.assets.open("image/seat.png")
        context.resources.assets.open("image/background.png")
        context.resources.assets.open("image/buttonUp.png")
        context.resources.assets.open("image/buttonDown.png")
    }

    fun setupText( context: Context){
        context.resources.assets.locales[0] = "Driver Seat"
        context.resources.assets.locales[1] = "Button Up"
        context.resources.assets.locales[2] = "Button Down"
    }
}