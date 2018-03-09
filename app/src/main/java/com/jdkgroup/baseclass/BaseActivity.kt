package com.jdkgroup.baseclass

//TODO DEVELOPED BY KAMLESH LAKHANI

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.text.InputFilter
import android.text.Spanned
import android.view.*
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jdkgroup.constant.AppConstant
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.utils.AppUtils
import com.jdkgroup.utils.logInfo
import org.parceler.Parcels
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.util.*

abstract class BaseActivity : AppCompatActivity() {

    //https://devhub.io
    var progressToolbar: ProgressBar? = null
    private var progressDialog: Dialog? = null
    private var params: HashMap<String, String>? = null
    private var calendar: Calendar? = null

    private var layoutManager: LinearLayoutManager? = null
     var recyclerViewLinearLayout = 0
     var recyclerViewGridLayout = 1

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

    open val activity: Activity
        get() = this

     val isInternet: Boolean
        get() {
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            @SuppressLint("MissingPermission") val networkInfo = connectivityManager.activeNetworkInfo
            if (!(networkInfo != null && networkInfo.isConnectedOrConnecting)) {
                AppUtils.showToast(activity, activity.getString(R.string.no_internet_message))
                return false
            }
            return true
        }

    //For Get the screen dimensions
    private val screenSize: IntArray
        get() {
            val size = Point()
            activity.windowManager.defaultDisplay.getSize(size)
            return intArrayOf(size.x, size.y)
        }

     fun setContentViewWithoutInject(layoutResId: Int) {
        super.setContentView(layoutResId)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            hideSoftKeyboard()
            onBackPressed()
        }
        return super.onOptionsItemSelected(menuItem)
    }

     fun toolBarSetFont(toolBar: Toolbar) {
        for (i in 0 until toolBar.childCount) {
            val view = toolBar.getChildAt(i)
            if (view is TextView) {
                val titleFont = Typeface.createFromAsset(applicationContext.assets, AppConstant.FONT_AILERON_SEMIBOLD)
                if (view.text == toolBar.title) {
                    view.typeface = titleFont
                    break
                }
            }
        }
    }

     fun hideSoftKeyboard() {
        try {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(window.decorView.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
            imm.hideSoftInputFromWindow(window.decorView.applicationWindowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
            imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

     fun getStringFromId(id: Int): String? {
        var str: String? = null
        try {
            str = this.getString(id)
        } catch (e: Exception) {
        }
        return str
    }


    //SCREEN SIZE
     fun getScreenSize(activity: Activity): IntArray {
        val size = Point()
        val w = activity.windowManager

        w.defaultDisplay.getSize(size)
        return intArrayOf(size.x, size.y)
    }

     fun setFullScreen(activity: Activity) {
        activity.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun showKeyboard(appCompatEditText: AppCompatEditText) {
        try {
            if (this != null) {
                val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.showSoftInput(appCompatEditText, InputMethodManager.SHOW_IMPLICIT)
            }
        } catch (e: Exception) {
            logInfo("Exception on  show " + e.toString())
        }
    }

     fun requestEditTextFocus(view: AppCompatEditText) {
        view.requestFocus()
        showKeyboard(view)
    }

     fun appEdiTextNullSet(id: Int) {
        findViewById<AppCompatEditText>(id).text = null
    }

     fun appEdiTextGetString(id: Int): String {
        return findViewById<AppCompatEditText>(id).text.toString()
    }

     fun appEditTextLowerCaseOnly(id: Int)
    {
        findViewById<AppCompatEditText>(id).filters = arrayOf<InputFilter>(object : InputFilter.AllCaps() {
            override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence {
                return source.toString().toLowerCase()
            }
        })
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

    fun showProgressDialog(show: Boolean) {
        //Show Progress bar here
        if (show) {
            showProgressDialog()
        } else {
            hideProgressDialog()
        }
    }

     fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = Dialog(this)
        } else {
            return
        }
        val view = LayoutInflater.from(this).inflate(R.layout.progressbar_dialog, null, false)

        val appIvProgressBar = view.findViewById<AppCompatImageView>(R.id.appIvProgressBar)
        val animation = AnimationUtils.loadAnimation(this, R.anim.progress_anim)
        animation.duration = 1500
        appIvProgressBar.startAnimation(animation)

        progressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog!!.setContentView(view)
        val window = progressDialog!!.window
        window?.setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent))
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

    fun showProgressToolBar(show: Boolean, view: View?) {
        if (show) {
            progressToolbar!!.visibility = View.VISIBLE
            if (view != null)
                view.visibility = View.GONE

        } else {
            progressToolbar!!.visibility = View.GONE
            if (view != null)
                view.visibility = View.VISIBLE
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

     fun showSnackBar(coordinatorLayout: CoordinatorLayout, message: String) {
        val snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT)
        snackbar.show()
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
     fun getToJson(alData: Any?): Any {
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

            3 -> GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()


            4 -> {
            }

            else -> {
            }
        }
        return null
    }

    //Parcel
     fun launchIsClearParcelable(classType: Class<*>, bundle: Bundle, status: Int) {
        val intent = Intent(this, classType)
        if (status == 0) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        intent.putExtra("bundle", bundle)
        startActivity(intent)
    }

     fun launchParcel(classType: Class<*>, data: Bundle, status: Int) {
        val intent = Intent(activity, classType)
        if (status == 0) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        }
        intent.putExtras(data)
        startActivity(intent)
    }

    /* TODO LAUNCH ACTIVITY */
    /*
     * Bundle bundle = new Bundle();
     * bundle.putParcelable(bundleName, Parcels.wrap(alData));
     * */
     fun <T> getParcelable(bundleName: String): T? {
        return Parcels.unwrap<T>(activity.intent.getParcelableExtra<Parcelable>(bundleName))
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

    /* TODO LAUNCH ACTIVITY/FRAGMENT ANIMATION*/
     fun intentOpenBrowser(url: String) {
        if (isInternet) {
            startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
        } else {

        }
    }

    //TODO FILE SAVE
     fun getImageFileFromSDCard(filename: String): Bitmap? {
        var bitmap: Bitmap? = null
        val imageFile = File(Environment.getExternalStorageDirectory().toString() + File.separator + filename)
        try {
            val fis = FileInputStream(imageFile)
            bitmap = BitmapFactory.decodeStream(fis)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bitmap
    }

     fun getSaveImageSDCard(bitmap: Bitmap, filename: String) {
        val fileMakeDirectory = File(Environment.getExternalStorageDirectory().toString() + File.separator + AppConstant.FOLDER_NAME)
        fileMakeDirectory.mkdirs()

        val file = File(fileMakeDirectory, filename)
        if (file.exists()) file.delete()
        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

     fun appExist() {
        val builder = AlertDialog.Builder(this)
        val alertDialog = builder.create()
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_app_close, null)
        alertDialog.setView(dialogView)

        val appTvYes = dialogView.findViewById(R.id.appTvYes) as AppCompatTextView
        val appTvNo = dialogView.findViewById(R.id.appTvNo) as AppCompatTextView
        appTvYes.setOnClickListener({ finish() })
        appTvNo.setOnClickListener({ alertDialog.dismiss() })

        alertDialog.show()
    }

     fun getFileDelete(fileName: String) {
        val file = File(Environment.getExternalStorageDirectory().toString() + File.separator + AppConstant.FOLDER_NAME + File.separator + fileName)
        val deleted = file.delete()
        if (deleted == true)
            logInfo("Delete successful")
        logInfo("Delete not successful")
    }


     fun IsHasSDCard(): Boolean {
        val status = Environment.getExternalStorageState()
        return status == Environment.MEDIA_MOUNTED
    }

    //For take screenshot with status bar return Bitmap
     fun nbGetScreenShotWithStatusBar(): Bitmap {
        val view = this.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val width = getScreenSize(this)[0]
        val height = getScreenSize(this)[1]
        view.destroyDrawingCache()
        return Bitmap.createBitmap(bmp, 0, 0, width, height)
    }

    //For take screenshot without status bar return Bitmap
     fun nbGetScreenShotWithoutStatusBar(): Bitmap {
        val view = this.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val frame = Rect()
        this.window.decorView.getWindowVisibleDisplayFrame(frame)
        val statusBarHeight = frame.top
        view.destroyDrawingCache()
        return Bitmap.createBitmap(bmp, 0, statusBarHeight, getScreenSize(this)[0], getScreenSize(this)[1] - statusBarHeight)
    }

    //DISABLE SCREEN CAPTURE
     fun disableScreenshotFunctionality() {
        activity.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
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
}