package com.test.app.pin.home

import android.util.Log
import com.test.app.pin.BuildConfig
import java.util.*
import kotlin.experimental.xor


class HomeRepo {

    private val hexArray = "0123456789ABCDEF".toCharArray()

        // generate pin block
      fun generateFormat3(PIN: String) : String{
        var pinField =  "3" + Integer.toHexString(PIN.length) + PIN
        val r =  Random()

        for (i in 0 until 14 - PIN.length) {
            pinField += Integer.toHexString(r.nextInt(6) + 10)
        }


        val panWithoutCheckDigit = BuildConfig.PAN_NUMBER.substring(0, HomeViewModel.pan.length-1)


        val panField = "0000"+ if (panWithoutCheckDigit.length > 12) panWithoutCheckDigit
            .substring(
                panWithoutCheckDigit.length - 12,
                panWithoutCheckDigit.length
            ) else String.format(
            "%12s", panWithoutCheckDigit
        ).replace(' ', '0')


        val pinFieldByteArray: ByteArray = hexStringToByteArray(pinField)
        val panFieldByteArray: ByteArray = hexStringToByteArray(panField)

        val pinBlockByteArray = ByteArray(8)

        for (i in 0..7) {
            pinBlockByteArray[i] = (pinFieldByteArray[i] xor panFieldByteArray[i])
        }

        return bytesToHex(pinBlockByteArray).uppercase()

    }
    // hexadecimal to byte array
    fun hexStringToByteArray(input : String): ByteArray {
        val len = input.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(input[i], 16) shl 4) +
                    Character.digit(input[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }
    //   byte array to hex
    private fun bytesToHex(bytes: ByteArray): String {
        val hexChars = CharArray(bytes.size * 2)
        for (j in bytes.indices) {
            val v = bytes[j].toInt() and 0xFF

            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }

}