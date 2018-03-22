package com.jdkgroup.baseclass

//TODO DEVELOPED BY KAMLESH LAKHANI

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.interacter.disposablemanager.DisposableManager
import com.jdkgroup.utils.logError
import org.parceler.Parcels

import java.io.File
import java.io.FileReader
import java.util.Calendar
import java.util.Date
import java.util.HashMap
import java.util.UUID

public open class BaseFragment : Fragment() {

    private var progressDialog: Dialog? = null
    private var params: HashMap<String, String>? = null
    private var calendar: Calendar? = null

    private var layoutManager: LinearLayoutManager? = null
    private val recyclerViewLinearLayout = 0
    private val recyclerViewGridLayout = 1

    val defaultParameter: HashMap<String, String>
        get() {
            params = HashMap()
            return params!!
        }

    val defaultParamWithIdAndToken: HashMap<String, String>?
        get() {
            params = defaultParameter
            return params
        }

     val isInternet: Boolean
        get() {
            val connectivityManager = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            @SuppressLint("MissingPermission") val networkInfo = connectivityManager.activeNetworkInfo
            if (!(networkInfo != null && networkInfo.isConnectedOrConnecting)) {
                return false
            }
            return true
        }

     fun uuidRandom(): UUID {
        return UUID.randomUUID()
    }

     val currentDate: Date
        get() = Date()

    //For Get the screen dimensions
    private val screenSize: IntArray
        get() {
            val size = Point()
            activity!!.windowManager.defaultDisplay.getSize(size)
            return intArrayOf(size.x, size.y)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
    }

    override fun onDestroyView() {
        DisposableManager.dispose()
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }

     fun isData(llDataPresent: LinearLayout, llDataNo: LinearLayout, status: Int) {
        when (status) {
            0 -> {
                llDataPresent.visibility = View.GONE
                llDataNo.visibility = View.VISIBLE
            }

            1 -> {
                llDataPresent.visibility = View.VISIBLE
                llDataNo.visibility = View.GONE
            }
        }
    }

     fun hideSoftKeyboard() {
        try {
            activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            val imm = activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity!!.window.decorView.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
            imm.hideSoftInputFromWindow(activity!!.window.decorView.applicationWindowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
            imm.hideSoftInputFromWindow(activity!!.window.decorView.windowToken, 0)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

     fun showKeyboard(appCompatEditText: AppCompatEditText) {
        try {
            if (activity != null) {
                val inputManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.showSoftInput(appCompatEditText, InputMethodManager.SHOW_IMPLICIT)
            }
        } catch (e: Exception) {
            logError("Exception on show " + e.toString())
        }

    }

    fun requestEditTextFocus(view: AppCompatEditText) {
        view.requestFocus()
        showKeyboard(view)
    }

    /*TODO PROGRESSBAR*/
    fun showProgressDialog(show: Boolean) {
        //Show Progress bar here
        if (show) {
            showProgressBar()
        } else {
            hideProgressDialog()
        }
    }

    //SHOW PROGRESSBAR
    fun showProgressBar() {
        if (progressDialog == null) {
            progressDialog = Dialog(activity!!)
        }
        val view = LayoutInflater.from(activity).inflate(R.layout.progressbar_dialog, null, false)

        val imageView1 = view.findViewById<AppCompatImageView>(R.id.appIvProgressBar)
        val a1 = AnimationUtils.loadAnimation(activity, R.anim.progress_anim)
        a1.duration = 1500
        imageView1.startAnimation(a1)

        progressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog!!.setContentView(view)
        val window = progressDialog!!.window
        window?.setBackgroundDrawable(ContextCompat.getDrawable(activity!!, android.R.color.transparent))
        progressDialog!!.setCancelable(false)
        progressDialog!!.setCanceledOnTouchOutside(false)
        progressDialog!!.show()
    }

    //HIDE PROGRESSBAR
    fun hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    fun showProgressToolBar(show: Boolean, view: View) {
        // ((BaseActivity)getActivity()).showProgressToolBar(show,view);
    }

    fun onAuthenticationFailure(msg: String) {
        // logoutUser(msg);
    }

    fun checkParams(map: MutableMap<String, String>): Map<String, String> {
        val entryIterator = map.entries.iterator()
        while (entryIterator.hasNext()) {
            val pairs = entryIterator.next()
            if (pairs.value == null) {
                map[pairs.key] = ""
            }
        }
        return map
    }

    //TODO RECYCLERVIEW
     fun setRecyclerView(recyclerView: RecyclerView, spanCount: Int, no: Int): LinearLayoutManager? {
        when (no) {
            0 -> {
                layoutManager = LinearLayoutManager(activity)
                recyclerView.layoutManager = layoutManager
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.setHasFixedSize(true)
            }

            1 -> {
                layoutManager = GridLayoutManager(activity, spanCount)
                recyclerView.layoutManager = layoutManager
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.setHasFixedSize(true)
            }
        }

        return layoutManager
    }

    //TODO GSON
     fun getToJson(alData: List<*>): String {
        return Gson().toJson(alData)
    }

     fun getToJsonClass(src: Any): String {
        return Gson().toJson(src)
    }

     fun <T> getFromJson(str: String, classType: Class<T>): T {
        return Gson().fromJson(str, classType)
    }

    @Throws(Exception::class)
     fun <T> fromJson(file: File, clazz: Class<T>): T {
        return Gson().fromJson(FileReader(file.absoluteFile), clazz)
    }

     fun switchGson(param: Int): Gson? {
        when (param) {
            1 -> return GsonBuilder().create()

            2 //FIRST CHARACTER UPPER CAMEL
            -> return GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().serializeNulls().create()
            else -> {
            }
        }
        return null
    }

    /* TODO LAUNCH FRAGMENT */
     fun <T> getParcelable(bundleName: String): T? {
        return Parcels.unwrap<T>(activity!!.intent.getParcelableExtra<Parcelable>(bundleName))
    }

     fun launch(classType: Class<*>, bundle: Bundle, addFlag: Int) {
        when (addFlag) {
            1 //NO BUNDLE AND NO CLEAR
            -> startActivity(Intent(activity, classType).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))

            2 //NO BUNDLE AND CLEAR ALL HISTORY
            -> startActivity(Intent(activity, classType).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))

            3 //BUNDLE AND NO CLEAR
            -> startActivity(Intent(activity, classType).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT).putExtra("bundle", bundle))

            4 //BUNDLE AND CLEAR ALL HISTORY
            -> startActivity(Intent(activity, classType).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("bundle", bundle))
        }
    }

     fun <T> getUnionList(first: MutableList<T>, last: List<T>): List<*> {
        if (isEmptyList(first) && isEmptyList(last)) {
            first.addAll(last)
            return first
        } else if (isEmptyList(first) && !isEmptyList(last)) {
            return first
        }
        return last
    }

     fun isEmptyList(list: List<*>?): Boolean {
        return list != null && !list.isEmpty()
    }

     fun hasLollipop(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

     fun hasM(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    fun decimalPointAfterBeforeAmount(maxDigitsBeforeDecimalPoint: Int, maxDigitsAfterDecimalPoint: Int): InputFilter {

        return InputFilter { source, start, end, dest, dstart, dend ->
            val builder = StringBuilder(dest)
            builder.replace(dstart, dend, source.subSequence(start, end).toString())
            if (!builder.toString().matches(("(([1-9]{1})([0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "})?)?(\\.[0-9]{0," + maxDigitsAfterDecimalPoint + "})?").toRegex())) {
                return@InputFilter if (source.length == 0) dest.subSequence(dstart, dend) else ""
            }
            null
        }
    }

     fun intentOpenBrowser(url: String) {
        if (isInternet) {
            startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
        } else {

        }
    }

     fun IsHasSDCard(): Boolean {
        val status = Environment.getExternalStorageState()
        return status == Environment.MEDIA_MOUNTED
    }

    //For take screenshot with status bar return Bitmap
     fun nbGetScreenShotWithStatusBar(activity: Activity): Bitmap? {
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val width = getScreenSize(activity)[0]
        val height = getScreenSize(activity)[1]
        var bp: Bitmap? = null
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height)
        view.destroyDrawingCache()
        return bp
    }

    //For take screenshot without status bar return Bitmap
     fun nbGetScreenShotWithoutStatusBar(): Bitmap {
        val view = activity!!.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val frame = Rect()
        activity!!.window.decorView.getWindowVisibleDisplayFrame(frame)
        val statusBarHeight = frame.top
        view.destroyDrawingCache()
        return Bitmap.createBitmap(bmp, 0, statusBarHeight, getScreenSize(activity!!)[0], getScreenSize(activity!!)[1] - statusBarHeight)
    }

    //DISABLE SCREEN CAPTURE
     fun disableScreenshotFunctionality() {
        activity!!.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
    }

     fun getDateDifference(startDate: Date, endDate: Date): String {
        try {
            var different = endDate.time - startDate.time
            if (different <= 0) {
                return "0"
            }
            val secondsInMilli: Long = 1000
            val minutesInMilli = secondsInMilli * 60
            val elapsedMinutes = different / minutesInMilli
            different = different % minutesInMilli

            val elapsedSeconds = different / secondsInMilli

            var mSec = ""
            if (elapsedSeconds <= 9)
                mSec = "0"
            return " 0$elapsedMinutes : $mSec$elapsedSeconds"
            // return elapsedMinutes + " min " + elapsedSeconds + " Sec";

        } catch (e: Exception) {
            return "0"
        }

    }

    companion object {

        //SCREEN SIZE
        fun getScreenSize(activity: Activity): IntArray {
            val size = Point()
            val w = activity.windowManager

            w.defaultDisplay.getSize(size)
            return intArrayOf(size.x, size.y)
        }

        fun showSnackBar(coordinatorLayout: CoordinatorLayout, message: String) {
            val snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT)
            snackbar.show()
        }
    }
}