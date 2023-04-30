package com.example.dmartassignment.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.dmartassignment.R
import com.example.dmartassignment.databinding.ActivityImageBinding


class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setImageView()
    }

    private fun setImageView() {
        //var url = "https://www.jiomart.com/images/product/600x600/491335633/parle-g-gold-biscuits-1-kg-product-images-o491335633-p491335633-0-202303221149.jpg"
        var url = "https://www.jiomart.com/images/product/600x750/441144141_plum/straight-track-pants-with-slip-pockets-model-441144141_plum-0-202302030343.jpg"
        //var url = "https://www.jiomart.com/images/cms/aw_rbslider/slides/1682593093_Jeans_and_Trousers.jpg"
        //var url = "https://www.jiomart.com/images/product/420x420/rv5btawkdn/leadder-kitchenware-new-6-pcs-square-water-bottle-for-school-kitchen-and-for-fridge-multicolor-product-images-orv5btawkdn-p590818202-0-202110091912.jpg"
        //var url = "https://www.jiomart.com/images/product/420x420/493178808/redmi-a1-32-gb-2-gb-ram-light-green-mobile-phone-digital-o493178808-p594779642-2-202210221329.jpeg"
        //var url = "https://www.jiomart.com/images/product/600x750/rvesppqxlr/bruton-men-s-grey-sports-shoes-product-images-rvesppqxlr-0-202207240402.jpg"
        //var url = "https://www.jiomart.com/images/product/600x750/469195018_coral/typographic-print-slim-fit-crew-neck-t-shirt-model-469195018_coral-0-202304120553.jpg"
        //var url = "https://www.jiomart.com/images/cms/aw_rbslider/slides/1682618276_Summer_Deals_on_Dry_Fruits.jpg"

        loadImage(
            imageUrl = url,
            imageView = binding.imgData
        )
    }

    private fun loadImage(imageUrl : String, imageView : ImageView) {
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    bitmap: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    var width = bitmap.width
                    var height = bitmap.height

                    if(width > 600) {
                        binding.imgData.scaleType = ImageView.ScaleType.FIT_XY
                    }
                    else {
                        binding.imgData.scaleType = ImageView.ScaleType.FIT_CENTER
                    }

                    val heightDp = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, height.toFloat(),
                        resources.displayMetrics
                    )

                    val params: ViewGroup.LayoutParams = binding.imgData.layoutParams
                    params.height = heightDp.toInt()
                    binding.imgData.layoutParams = params

                    loadImageData(imageUrl, imageView)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    private fun loadImageData(imageUrl : String, imageView : ImageView) {
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .placeholder(R.drawable.place_holder)
            .error(R.drawable.place_holder)
            .into(imageView)
    }
}