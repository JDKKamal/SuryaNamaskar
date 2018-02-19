package com.jdkgroup.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.util.Base64
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast

import com.jdkgroup.suryanamaskar.R

import org.json.JSONObject

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.DateFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.HashMap
import java.util.HashSet
import java.util.LinkedHashSet
import java.util.Locale
import java.util.TimeZone

object AppUtils {

    /*public static FileUri createImageFile(String prefix) {
        FileUri fileUri = new FileUri();

        File image = null;
        try {
            image = File.createTempFile(prefix + String.valueOf(System.currentTimeMillis()), ".jpg", getWorkingDirectory());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            fileUri.setFile(image);
            fileUri.setImageUrl(Uri.parse("file:" + image.getAbsolutePath()));
        }
        return fileUri;
    }*/

    private val workingDirectory: File
        get() {
            val directory = File(Environment.getExternalStorageDirectory(), "Mowadcom")
            return createDirectory(directory)
        }

    fun showToast(context: Context, message: String) {
        val toast = Toast.makeText(context, message + "", Toast.LENGTH_SHORT)
        val textView = toast.view.findViewById<TextView>(android.R.id.message)
        if (textView != null) textView.gravity = Gravity.CENTER
        toast.show()
    }

    fun showToastById(context: Context, id: Int) {
        val toast = Toast.makeText(context, getStringFromId(context, id), Toast.LENGTH_SHORT)
        val textView = toast.view.findViewById<TextView>(android.R.id.message)
        if (textView != null) textView.gravity = Gravity.CENTER
        toast.show()
    }

    private fun getStringFromId(context: Context, id: Int): String? {
        var str: String? = null
        try {
            str = context.getString(id)
        } catch (e: Exception) {
        }

        return str
    }


    fun isInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        @SuppressLint("MissingPermission") val networkInfo = connectivityManager.activeNetworkInfo
        if (!(networkInfo != null && networkInfo.isConnectedOrConnecting)) {
            showToast(context, context.getString(R.string.no_internet_message))
            return false
        }
        return true
    }

    private fun createDirectory(file: File): File {
        if (!file.exists()) {
            file.mkdir()
        }
        return file
    }

    fun setImage(imageBaseUrl: String): String? {
        // return PocketAccountConstant.IMAGE_BASE_URL.concat(imageBaseUrl);
        return null
    }

    /* public static RequestBody getRequestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    } */

    fun startActivity(context: Context, className: Class<*>) {
        val intent = Intent(context, className)
        context.startActivity(intent)
    }

    fun getDateFromTime(mTimestamp: Long, mDateFormat: String): String {
        val dateFormatter = SimpleDateFormat(mDateFormat)
        dateFormatter.timeZone = TimeZone.getDefault()

        val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        cal.timeInMillis = mTimestamp

        return dateFormatter.format(cal.time)
    }

    fun getDateString(miliis: Long, mDateFormate: String): String {
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getDefault()
        calendar.timeInMillis = miliis
        val dateFormatter = SimpleDateFormat(mDateFormate, Locale.ENGLISH)
        return dateFormatter.format(calendar.time)
    }

    fun getLongTimestampToDate(timestamp: Long, timeFormat: String): String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeZone = TimeZone.getTimeZone("GMT+5:30")
        calendar.timeInMillis = timestamp * 1000L
        return android.text.format.DateFormat.format(timeFormat, calendar).toString()
    }

    fun getUtC(millis: Long): Long {
        return millis + TimeZone.getDefault().rawOffset.toLong() + TimeZone.getDefault().dstSavings.toLong()
    }

    fun getGMT(millis: Long): Long {
        return millis - TimeZone.getDefault().rawOffset.toLong() - TimeZone.getDefault().dstSavings.toLong()
    }

    fun getDate(timeStamp: Long): String {
        try {
            val sdf = SimpleDateFormat("DD MMMM yyyy", Locale.getDefault())
            val netDate = Date(timeStamp * 1000)
            return sdf.format(netDate)
        } catch (ex: Exception) {
            return ""
        }

    }

    @Throws(ParseException::class)
    fun getDateTimeInMilliseconds(date: String): Long {
        val sdf = SimpleDateFormat("EEE MMM dd yyyy")
        val mDate = sdf.parse(date)
        return mDate.time
    }

    fun getFormatNumber(`val`: String): String {
        var d = 0.0
        try {
            d = java.lang.Double.parseDouble(`val`)
        } catch (e: Exception) {
            d = 0.00
        }

        // return String.format("%.2f",NumberFormat.getNumberInstance(Locale.UK).format(d));
        return NumberFormat.getNumberInstance(Locale.UK).format(d) + ""
    }

    fun getPathFromMediaUri(context: Context, uri: Uri): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(uri, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        } finally {
            if (cursor != null) {
                cursor.close()
            }
        }
    }

    fun decreaseImageSize(file: File): File? {
        try {
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            o.inSampleSize = 6

            var inputStream = FileInputStream(file)
            BitmapFactory.decodeStream(inputStream, null, o)
            inputStream.close()

            val REQUIRED_SIZE = 75

            var scale = 1
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2
            }

            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            inputStream = FileInputStream(file)

            val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
            inputStream.close()

            file.createNewFile()
            val outputStream = FileOutputStream(file)

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            return file
        } catch (e: Exception) {
            return null
        }

    }

    fun getFileSize(file: File): Long {
        val sizeInBytes = file.length()
        return sizeInBytes / (1024 * 1024)
    }

    fun dpToPx(dp: Float, resources: Resources): Int {
        val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
        return px.toInt()
    }

    fun NavigationBarBackground(activity: Activity, id: Int) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.window.navigationBarColor = ContextCompat.getColor(activity, id)
        }
    }

    /* public static String bytesToHex(byte[] bytes) {
         if (bytes == null) {
             return "";
         }
         char[] hexChars = new char[bytes.length * 2];
         int v;
         for (int j = 0; j < bytes.length; j++) {
             v = bytes[j] & 0xFF;
             hexChars[j * 2] = hexArray[v >>> 4];
             hexChars[j * 2 + 1] = hexArray[v & 0x0F];
         }
         return new String(hexChars);
     }
 */

    fun stringToBytes(str: String?): ByteArray {
        return if (str == null || str.length == 0) {
            ByteArray(0)
        } else Base64.decode(str, Base64.DEFAULT)
    }

    fun hexToBytes(hex: String?): ByteArray? {
        if (hex == null) {
            return null
        }
        val len = hex.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(hex[i], 16) shl 4) + Character.digit(hex[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

    fun computeSHA256(convertme: ByteArray, offset: Int, len: Int): ByteArray? {
        try {
            val md = MessageDigest.getInstance("SHA-256")
            md.update(convertme, offset, len)
            return md.digest()
        } catch (e: Exception) {

        }

        return null
    }

    fun bytesToLong(bytes: ByteArray): Long {
        return ((bytes[7].toLong() shl 56) + (bytes[6].toLong() and 0xFF shl 48) + (bytes[5].toLong() and 0xFF shl 40) + (bytes[4].toLong() and 0xFF shl 32)
                + (bytes[3].toLong() and 0xFF shl 24) + (bytes[2].toLong() and 0xFF shl 16) + (bytes[1].toLong() and 0xFF shl 8) + (bytes[0].toLong() and 0xFF))
    }

    //TODO DUPLICATE REMOVE ITEMS
    fun <E> listRemoveDuplicates(list: List<E>): List<E> {
        val uniques = HashSet<E>()
        uniques.addAll(list)
        return ArrayList(uniques)
    }

    fun <E> linkedHashSetRemoveDuplicates(list: List<E>): List<E> {
        return ArrayList(LinkedHashSet(list))
    }

    fun isNotEmpty(list: List<*>?): Boolean {
        return list != null && !list.isEmpty()
    }

    fun <T> union(first: MutableList<T>, last: List<T>): List<*> {
        if (isNotEmpty(first) && isNotEmpty(last)) {
            first.addAll(last)
            return first
        } else if (isNotEmpty(first) && !isNotEmpty(last)) {
            return first
        }
        return last
    }

    fun hasLollipop(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    fun hasM(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    fun floatFormat2Digit(str: String): String {
        return String.format("%.02f", str)
    }

    fun discount(rs: Float, tax: Float): Float {
        return rs * tax / 100
    }

    fun roundValue(value: Float): Float {
        return Math.round(value).toFloat()
    }

    fun isPackageExist(context: Context, pckName: String): Boolean {
        try {
            val pckInfo = context.packageManager.getPackageInfo(pckName, 0)
            if (pckInfo != null) {
                return true
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("", e.message)
        }

        return false
    }

    fun uninstallApk(context: Context, packageName: String) {
        if (isPackageExist(context, packageName)) {
            val packageURI = Uri.parse("package:" + packageName)
            val uninstallIntent = Intent(Intent.ACTION_DELETE, packageURI)
            context.startActivity(uninstallIntent)
        }
    }

    fun requestParam(hashMap: HashMap<String, String>) {
        val it = hashMap.entries.iterator()
        while (it.hasNext()) {
            val pair = it.next() as Map.Entry<*, *>
        }
    }

    private fun stringFromByte(inputStream: InputStream): String {
        val stringBuilder = StringBuilder()
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        try {
            while (true) {
                val str = bufferedReader.readLine() ?: break
                stringBuilder.append(str)
            }
        } catch (localIOException: Exception) {
            localIOException.printStackTrace()
        }

        return inputStream.toString()
    }
}
