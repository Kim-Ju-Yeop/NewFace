package com.example.newface.view.main.article

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.newface.R
import com.example.newface.databinding.ActivityArticleBinding
import com.example.newface.view.main.PostActivity
import com.example.newface.viewmodel.main.article.ArticleViewModel
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.project.meals.network.Data
import com.project.meals.network.NetRetrofit
import com.project.meals.network.response.Response
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import java.io.*
import java.util.*

class ArticleActivity : AppCompatActivity() {

    lateinit var binding : ActivityArticleBinding
    lateinit var viewModel : ArticleViewModel

    // startActivityForResult Answer
    private val PICK_FROM_ALBUM = 1
    private var tempFile: File? = null

    // View
    internal var imageList = ArrayList<MultipartBody.Part>()
    lateinit var fileNameBody: RequestBody

    // Upload Album
    private var fileExt: String? = null
    private var fileType: String? = null
    private var uploadName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        binding = DataBindingUtil.setContentView(this@ArticleActivity, R.layout.activity_article)
        viewModel = ViewModelProviders.of(this@ArticleActivity).get(ArticleViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner

        observerViewModel()
    }

    fun click(view : View){
        val checkLogin : SharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE)
        viewModel.write(checkLogin)
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@ArticleActivity, androidx.lifecycle.Observer {
                Toast.makeText(this@ArticleActivity, "글 작성을 수행하였습니다.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@ArticleActivity, PostActivity::class.java))
            })
            onFailEvent.observe(this@ArticleActivity, androidx.lifecycle.Observer {
                Toast.makeText(this@ArticleActivity, "서버 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
            })
        }
    }

    // 권한 체크 매서드
    fun tedPermission(view: View) {
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                goToAlbum()
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {}
        }

        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setRationaleMessage(R.string.rationale)
            .setDeniedMessage(R.string.denied)
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .check()
    }

    // 갤러리 접근 호출
    fun goToAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

    // 갤러리 접근 실행
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_FROM_ALBUM) {

            var photoUri: Uri?
            val column_index: Int
            var cursor: Cursor? = null

            try {
                // 갤러리에서 선택된 이미지의 Uri 값을 저장한다.
                photoUri = data!!.data
            } catch (e: NullPointerException) {
                photoUri = null
            }

            try {
                val proj = arrayOf(MediaStore.Images.Media.DATA)

                // assert 예약어는 boolean 예약어와 같다.
                assert(photoUri != null)
                cursor =
                    this.getContentResolver().query(photoUri!!, proj, null, null, null)

                // assert 예약어는 boolean 예약어와 같다.
                assert(cursor != null)
                column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

                cursor.moveToFirst()
                tempFile = File(cursor.getString(column_index))
            } catch (e: NullPointerException) {

            } finally {
                cursor?.close()
            }
            try {
                setImage()
            } catch (e: NullPointerException) {

            }

        }
    }

    // 이미지 설정
    fun setImage() {
        val options = BitmapFactory.Options()
        val originalBm = BitmapFactory.decodeFile(tempFile!!.getAbsolutePath(), options)

//        binding.button.text = originalBm.toString()
        uploadProfile(changeToBytes(), tempFile!!.getName())
    }

    // 이미지파일을 비트로 바꿉니다
    private fun changeToBytes(): ByteArray {

        val size = tempFile!!.length().toInt()
        val bytes = ByteArray(size)
        try {
            val buf = BufferedInputStream(FileInputStream(tempFile))
            buf.read(bytes, 0, bytes.size)
            buf.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return bytes
    }

    // Profile Upload
    fun uploadProfile(imageBytes: ByteArray, originalName: String) {

        val filenameArray = originalName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val extension = filenameArray[filenameArray.size - 1]

        fileType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        fileExt = ".$extension"

        uploadName = Integer.toString(Random().nextInt(999999999))

        val requestFile =
            RequestBody.create(MediaType.parse(Objects.requireNonNull(fileType)), imageBytes)

        val body = MultipartBody.Part.createFormData("files", uploadName + fileExt, requestFile)
        fileNameBody = RequestBody.create(MediaType.parse("text/plain"), uploadName)

        imageList.add(body)

        val neRetrofit = NetRetrofit()

        val res = neRetrofit.article.uploadImage(body)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                Log.e("test", "서버 연동 성공")
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("test", "서버 연동 실패")
            }
        })
    }
}
