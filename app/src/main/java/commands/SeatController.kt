package commands

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import packages.commands.R
import java.util.*


class SeatController : AppCompatActivity() {

	private lateinit var speechRecognizer : SpeechRecognizer

	private fun handleTimeout(){

		Toast.makeText(this, getString(R.string.speak_again), Toast.LENGTH_SHORT).show()
	}

	fun handleListening() {
		getString(R.string.listening)
	}

	fun handleTranslatedText(translatedText : String){
		translatedText[0].uppercaseChar() + translatedText.substring(1)
	}

	private fun checkPermission() {
		ActivityCompat.requestPermissions(
			this,
			arrayOf(Manifest.permission.RECORD_AUDIO),
			1
		)
	}

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
			checkPermission()
		}else{
			handleTimeout()
		}

		speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
		val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
		speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
		speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US.toString())
		speechRecognizer.setRecognitionListener(object : RecognitionListener {
			override fun onReadyForSpeech(p0: Bundle?) {

			}

			override fun onBeginningOfSpeech() {
				handleListening()
			}

			override fun onRmsChanged(p0: Float) {
			}

			override fun onBufferReceived(p0: ByteArray?) {
			}

			override fun onEndOfSpeech() {
			}

			override fun onError(p0: Int) {
			}

			override fun onResults(p0: Bundle?) {
				if(p0 == null){
					return
				}
				val data = p0.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) ?: return
				handleTranslatedText(data[0])
			}

			override fun onPartialResults(p0: Bundle?) {
			}

			override fun onEvent(p0: Int, p1: Bundle?) {
			}
		})
	}
}