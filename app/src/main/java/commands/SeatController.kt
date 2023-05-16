package commands

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import packages.commands.R


class SeatController : AppCompatActivity() {

	var controlVar = 100
	var downVar = 0
	var upVar = 0
	var position = 50

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		idle()
	}

	private fun idle(){
		controlVar = 0
		if(downVar == 1){
			downDebounce()
		}else if(upVar == 1){
			upDebounce()
		}
	}

	private fun downDebounce(){
		if(position == 0){
			idle()
		}
		if(upVar == 1 || downVar == 0){
			idle()
		}
		Thread.sleep(300)
		downRequest()
	}

	private fun downRequest(){
		controlVar-=1
		if(position == 0
			|| upVar == 1
			|| downVar == 0){
			idle()
		}
	}

	private fun upDebounce(){
		if(position == 100){
			idle()
		}
		if(upVar == 0 || downVar == 1){
			idle()
		}
		Thread.sleep(300)
		upRequest()
	}

	private fun upRequest(){
		controlVar+=1
		if(position == 100
			|| upVar == 0
			|| downVar == 1){
			idle()
		}
	}
}